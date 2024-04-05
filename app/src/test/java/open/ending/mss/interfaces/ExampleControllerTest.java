package open.ending.mss.interfaces;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = ExampleController.class)
class ExampleControllerTest {
  @Autowired
  private WebApplicationContext context;
  @MockBean
  private RedissonClient redissonClient;

  private RAtomicLong mockAtomicLong;

  @BeforeEach
  protected void setUp() {
    RestAssuredMockMvc.webAppContextSetup(context);
    RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    mockAtomicLong = mock(RAtomicLong.class);
    when(mockAtomicLong.incrementAndGet()).thenReturn(1L);
  }

  @Test
  void shouldGetFirstWhenCount() {
    when(redissonClient.getAtomicLong(any())).thenReturn(mockAtomicLong);
    RestAssuredMockMvc.given().header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/test/count")
        .then()
        .statusCode(200)
        .body(equalTo("{\"count\":1}"));
  }

}