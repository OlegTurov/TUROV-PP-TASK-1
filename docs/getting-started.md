[Back to README](../README.md) · [Architecture →](architecture.md)

# Начало работы

Руководство по установке, настройке и запуску проекта **PostsWebApp**.

## Системные требования

- **Java Development Kit (JDK):** Версия 21 или выше
- **Apache Maven:** Версия 3.8+ (или встроенный в IDE)
- **PostgreSQL:** Версия 12+

## Установка и запуск

1. **Клонируйте репозиторий:**
   ```bash
   git clone <url-репозитория>
   cd TUROV-PP-TASK-1
   ```

2. **Настройте базу данных PostgreSQL:**
   Создайте базу данных `pp_task_1`:
   ```sql
   CREATE DATABASE pp_task_1;
   ```

3. **Настройте переменные окружения:**
   Укажите учетные данные БД в переменных окружения или в файле `src/main/resources/secrets.yaml`:
   ```yaml
   db:
     username: postgres
     password: your_password
   ```
   Или через переменные окружения:
   ```bash
   export DB_USERNAME=postgres
   export DB_PASSWORD=your_password
   ```

4. **Соберите и запустите приложение:**
   ```bash
   mvn spring-boot:run
   ```

5. **Проверка работоспособности:**
   Откройте в браузере: `http://localhost:8080/login`

## Следующие шаги

- Ознакомьтесь с [Архитектурой проекта](architecture.md)
- Изучите [API и эндпоинты](api.md)

## See Also

- [Архитектура](architecture.md) — структура и слои приложения
- [Конфигурация](configuration.md) — параметры конфигурации и переменные окружения
