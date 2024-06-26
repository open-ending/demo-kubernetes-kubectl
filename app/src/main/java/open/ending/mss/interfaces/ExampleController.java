package open.ending.mss.interfaces;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class ExampleController {
  private final RedissonClient redissonClient;

  @GetMapping("/count")
  Map<String, Long> count() {
    RAtomicLong count = redissonClient.getAtomicLong("test-redis-1");
    return Map.of("count", count.incrementAndGet());
  }
}
