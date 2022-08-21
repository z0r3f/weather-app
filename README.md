# Weather Bot
Simple telegram bot for weather information. 

### Use:
- [Weather API](https://openweathermap.org/api)
- [Telegram API](https://core.telegram.org/bots)
- [PostgreSQL](https://www.postgresql.org/)

### Made with:
- [Kotlin](https://kotlinlang.org/)
- [Junit](https://junit.org/)
- [Archimedes Framework](https://github.com/archimedes-projects/archimedes-jvm)


## Docker

```bash
docker run -d --name local_postgres -v my_dbdata:/var/lib/postgresql/data -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=verysecret -e POSTGRES_DB=postgres -d postgres:latest
```
