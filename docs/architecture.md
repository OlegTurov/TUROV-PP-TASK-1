[← Начало работы](getting-started.md) · [Back to README](../README.md) · [API Reference →](api.md)

# Архитектура проекта

Обзор архитектуры, структуры каталогов и ключевых паттернов **PostsWebApp**.

## Стек технологий

- **Язык:** Java 21
- **Фреймворк:** Spring Boot 3.5.6 (Spring MVC, Spring JDBC, Spring WebSocket)
- **База данных:** PostgreSQL с миграциями Liquibase
- **Шаблонизатор:** Thymeleaf
- **Безопасность:** Spring Security Crypto (хеширование паролей BCrypt/PasswordEncoder)
- **Утилиты:** Lombok

## Структура каталогов

```
src/main/java/com/example/itis/
├── Application.java              # Точка входа Spring Boot
├── config/                       # Конфигурационные классы (Web, WebSocket, Locale)
├── controller/                   # Контроллеры (Auth, Posts, Locale)
├── dto/                          # Объекты передачи данных (DTO)
├── entity/                       # Сущности предметной области (User, Post, Session)
├── exception/                    # Кастомные исключения
├── exceptionhandler/             # Глобальный обработчик исключений
├── helper/                       # Вспомогательные утилиты (PasswordHelper)
├── interceptor/                  # Перехватчики запросов (AuthInterceptor)
├── repository/                   # Репозитории (Spring JDBC)
└── service/                      # Бизнес-логика (Auth, Posts, WebSocket)
```

## Ключевые паттерны

1. **Многослойная архитектура (Layered Architecture):**
   - **Controller Layer:** Обработка HTTP-запросов и рендеринг Thymeleaf-шаблонов.
   - **Service Layer:** Реализация бизнес-логики и правил валидации.
   - **Repository Layer:** Прямое взаимодействие с БД с помощью Spring JDBC (`JdbcTemplate`).

2. **Аутентификация и сессии:**
   - Кастомный `AuthInterceptor` проверяет сессии пользователей через куку `SESSION_ID`.
   - Пароли хешируются с использованием `PasswordHelper`.

3. **Миграции базы данных:**
   - Управление схемой БД осуществляется через Liquibase (`db.changelog-master.yaml`).

## See Also

- [Начало работы](getting-started.md) — установка и запуск
- [API Reference](api.md) — описание эндпоинтов и маршрутов
