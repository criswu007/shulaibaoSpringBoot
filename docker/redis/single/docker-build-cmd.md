## 单节点部署

```bash
# pull redis image
docker pull redis:8.0
```

```bash
# run redis
docker run -d \
--name redis \
--restart unless-stopped \
-p 6369:6379 \
-v ./conf:/usr/local/etc/redis \
-v ./data:/data \
-v ./logs:/logs \
-e TZ=Asia/Shanghai \
redis:8.0 \
redis-server /usr/local/etc/redis/redis.conf
```

```bash
# redis 命令
docker exec -it redis redis-cli
127.0.0.1:6379> AUTH your_strong_password_here
127.0.0.1:6379> PING  # 应返回 PONG
127.0.0.1:6379> SET test "Hello"  # 测试写入
127.0.0.1:6379> GET test  # 测试读取

# 查看所有配置参数
docker exec -it my-redis-container redis-cli config get *

# 查看特定配置参数
docker exec -it my-redis-container redis-cli config get port
docker exec -it my-redis-container redis-cli config get maxmemory
```