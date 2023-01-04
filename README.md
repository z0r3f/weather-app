# Weather Bot
Simple telegram bot for weather information. 

### Use:
- [Weather API](https://openweathermap.org/api)
- [Telegram API](https://core.telegram.org/bots)

### Made with:
- [Kotlin](https://kotlinlang.org/)
- [Junit](https://junit.org/)
- [Archimedes Framework](https://github.com/archimedes-projects/archimedes-jvm)
- [Docker](https://www.docker.com/blog/multi-arch-build-and-images-the-simple-way/)


## Docker db // OLD

```bash
docker run -d --name local_postgres -v my_dbdata:/var/lib/postgresql/data -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=verysecret -e POSTGRES_DB=postgres -d postgres:latest
```

## Docker

```bash
mvn package
```

```bash
docker buildx build --push --platform linux/amd64 --tag z0r3f/weather-docker:latest .
docker buildx build --push --platform linux/amd64 --tag z0r3f/weather-docker:0.0.1 .
```
