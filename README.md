<div class="header" markdown="1" align="center">
</div>
<h1 align="center">Aspect-oriented programming</h1>
  <p align="center">
  The homework project for <a href="https://t1.ru/internship/item/otkrytaya-shkola-dlya-java-razrabotchikov/">open school JAVA by T1</a>
  <br/><br/>
    <a href="https://github.com/Zazergel/aop/issues">Report Bug</a> *
    <a href="https://github.com/Zazergel/aop/issues">Request Feature</a>
  </p>
  <div class="header" markdown="1" align="center">
    
  ![Contributors](https://img.shields.io/github/contributors/Zazergel/aop?color=dark-green) 
  ![Forks](https://img.shields.io/github/forks/Zazergel/aop?style=social) 
  ![Issues](https://img.shields.io/github/issues/Zazergel/aop) 
</div>

# Build with

<p align="left">
    <img src="https://skillicons.dev/icons?i=java,maven,spring,postgres,hibernate,docker" />
</p>

## Описание проекта
Этот проект представляет собой простое Spring Boot приложение, которое демонстрирует использование Spring AOP для логирования операций в системе управления пользователями и их заказами. Приложение включает в себя сервисы для управления пользователями (UserService) и заказами (OrderService), а также аспект логирования (LoggingAspect), который автоматически логирует вызовы этих сервисов.

### Сущности
В приложении определены две основные сущности:
- `User`: представляет пользователя с полями `id`, `name` и `email`.
- `Order`: представляет заказ с полями `id`, `description` и `status`.

Между этими сущностями настроена связь один ко многим, где один пользователь может иметь множество заказов.

### Репозитории
Для каждой сущности созданы репозитории `UserRepo` и `OrderRepo`, которые расширяют `JpaRepository`. Это позволяет выполнять базовые операции CRUD над данными пользователей и заказов.

### Сервисы
Сервисы `UserService` и `OrderService` реализуют методы для создания, чтения, обновления и удаления пользователей и их заказов.

### Аспект логирования
`LoggingAspect` содержит логику логирования операций в сервисах. С помощью аннотаций `Spring AOP` определены точки среза для выполнения логирования, такие как выполнение любых методов в сервисах.

### Настройка логирования
Логирование настроено с использованием библиотеки `Log4j2`, которая позволяет выводить логи в консоль или файл. Конфигурационный файл `logback-spring.xml` содержит настройки аппендеров и уровней логирования.

### Тестирование
Тестовые классы для сервисов `UserServiceTest` и `OrderServiceTest` созданы для проверки корректности работы логирования. При вызове методов сервисов из тестов добавляются соответствующие записи в логах. Для тестов используется база данных `H2`. 

### Дополнительно
- Файл логов приложения сохраняется в виде одельного `volume` в докер контейнере и доступен в папке `app/logs`.

## Установка и запуск
Убедитесь, что у вас установлен Docker актуальной версии.
1. Клонируйте репозиторий: `git clone https://github.com/Zazergel/aop.git`
2. Перейдите в корневой каталог проекта.
3. Запустите приложение с помощью `docker-compose.yml` файла командой: `docker compose up`. 

## Authors

 **[Zazergel](https://github.com/Zazergel/)**
