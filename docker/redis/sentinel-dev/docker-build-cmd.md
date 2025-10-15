## 一主两从三哨兵 高可用部署

```bash
# up-构建、创建、启动
docker compose up -d
```
```bash
# down-停止服务并删除资源(容器、挂载卷、网络、镜像), 默认只删除容器和网络资源
docker compose down
```