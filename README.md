[![Build Status](https://dev.azure.com/ferozar/Weather%20Bot/_apis/build/status%2Fz0r3f.weather-app?branchName=master)](https://dev.azure.com/ferozar/Weather%20Bot/_build/latest?definitionId=25&branchName=master)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=z0r3f_weather-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=z0r3f_weather-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=z0r3f_weather-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=z0r3f_weather-app)

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
- [Caffeine, a high performance, near optimal caching library](https://github.com/ben-manes/caffeine)

## Run

```bash
mvn mn:run
```

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
docker buildx build --push --platform linux/amd64 --tag z0r3f/weather-docker:0.7.2 .
```

```bash
docker run -d --name weather-docker -p 8443:8443 -v /Users/fernando/IdeaProjects/weather-app/bot/data:/data z0r3f/weather-docker:latest
```
