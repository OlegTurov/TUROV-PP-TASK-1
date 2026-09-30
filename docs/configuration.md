[← API Reference](api.md) · [Back to README](../README.md)

# Конфигурация

Параметры настройки и конфигурации приложения **PostsWebApp**.

## Файл конфигурации (`application.yaml`)

Основные настройки находятся в `src/main/resources/application.yaml`:

```yaml
spring:
  config:
    import: optional:classpath:secrets.yaml

  messages:
    basename: messages
    encoding: UTF-8

  datasource:
    url: jdbc:postgresql://localhost:5432/pp_task_1
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  thymeleaf:
    cache: false

  liquibase:
    enabled: true
    change-log: classpath:db/changelog/db.changelog-master.yaml

server:
  port: 8080
```

## Переменные окружения и секреты

- `DB_USERNAME` — имя пользователя PostgreSQL (по умолчанию задается в `secrets.yaml` или через окружение).
- `DB_PASSWORD` — пароль пользователя PostgreSQL.
- `server.port` — порт веб-сервера (по умолчанию `8080`).

## See Also

- [Начало работы](getting-started.md) — запуск приложения
- [API Reference](api.md) — эндпоинты системы
