Banking REST API on Spring Boot
Описание проекта
Это REST API для банковского приложения, реализующее следующие функции:
Управление пользователями и их данными (email, телефоны)
Поиск пользователей с фильтрацией
Денежные переводы между пользователями
Автоматическое начисление процентов на баланс
JWT аутентификация
Валидация данных и обработка ошибок

Технологии
Java 11
Spring Boot 2.7.x
PostgreSQL - основное хранилище данных
Liquibase - управление миграциями БД
Redis - кэширование
Elasticsearch - полнотекстовый поиск
JWT - аутентификация
MapStruct - маппинг DTO
Swagger/OpenAPI - документация API
Docker - контейнеризация

Запуск проекта
Требования
Docker и Docker Compose

Java 11 JDK

Maven

Инструкции
Клонируйте репозиторий:

bash
git clone https://github.com/yourusername/banking-api.git
cd banking-api
Соберите проект:

bash
mvn clean package
Запустите сервисы через Docker Compose:

bash
docker-compose up -d
Приложение будет доступно по адресу: http://localhost:8080

Документация API (Swagger UI): http://localhost:8080/swagger-ui.html


TODO
Высокий приоритет
Добавить интеграционные тесты для TransferService
Реализовать полноценное логгирование важных операций
Добавить мониторинг (Prometheus + Grafana)
Настроить health-check для Docker

Средний приоритет
Реализовать email/push уведомления о переводах
Добавить историю транзакций
Реализовать двухфакторную аутентификацию
Добавить rate limiting для API

Низкий приоритет
Реализовать WebSocket для уведомлений
Добавить GraphQL endpoint
Реализовать локализацию ошибок
Настроить CI/CD pipeline

Конфигурация
Основные настройки можно изменить в файлах:

application.yml - общие настройки
application-dev.yml - для разработки
application-prod.yml - для production

Контакты
По вопросам сотрудничества или поддержки проекта обращайтесь:
Email:mrpolyakovalexey@gmail.com


