# demo-kubernetes-kubectl

### 1. 环境预安装
- Docker Engine v24 (Docker Engine v25 使用 `minikube image load`命令暂时有Bug)
- Minikube

### 2. Minikube 启动
```
$ minikube start --nodes 2 -p multinode-demo

# 打上临时标签，node 重启后会失效
$ kubectl label nodes multinode-demo-m02 workload=fulltime
$ kubectl label --list nodes multinode-demo-m02

# 启动 dashboard
$ minikube dashboard -p multinode-demo
```

### 3. 本地打包和内传image
minikube node 内 docker image repository 与本地 host 机器是隔离的，如果想要 host 机器打包image后直接使用，需要将 image 传进 Kubernetes 集群。
```
./gradlew :app:jibDockerBuild --image open-ending.com/mss-app
minikube image load open-ending.com/mss-app -p multinode-demo
```


### 4. 增加负载
```
kubectl run -i --tty load-generator-3 --rm --image=busybox:1.28 --restart=Never -- /bin/sh -c "while sleep 0.01; do wget -q -O- http://mss-app-service.u1:8080/test/count; done"
```

### 5. ingress 端口访问
macOS 由于 minikube ip 不兼容的问题，即使安装和定义了ingress，依然需要`minikube tunnel`, 因此在 tunnel 开启后，解析域名需要使用下面这种写法。
```
$ curl --resolve "mss-app.test:80:127.0.0.1" -i http://mss-app.test/test/count
```