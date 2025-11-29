Redis example (for demo / local run)

1) Start Redis locally (Windows sample with Docker):

```powershell
# if you have docker
docker run -d -p 6379:6379 --name test-redis redis:7
```

2) The included `RedisClient` uses Jedis and expects host/port. Example scenario in `src/test/resources/features/data.feature` shows how to call it.
