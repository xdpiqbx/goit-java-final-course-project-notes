`Port: 9999`

## Routes
```text
/login
/register (+ redirect to /login || /register)
/note/list (GET)
/note/create (POST)
/note/edit (POST)
/note/share/{id}
```

---

## DB
```text
  User
    name (будь-які символи латиниці та цифри, від 5 до 50 символів включно)
    password (від 8 до 100 символів включно) У БД лише хеши паролів !!!
    
  Note
    id: UUID
    title (від 5 до 100 символів включно)
    content (від 5 до 10 000 символів включно)
    access_type : Enum (private, public)
```
    
`Default user -> admin : super_secret_password`

prod - СУБД PostgreSQL

default - h2 in-memory

`spring.datasource.username=${DB_USERNAME}`

Змінні оточення повинні бути описані в файлі `readme.md`

`application.properties` має бути рядок:

```text
spring.jpa.hibernate.ddl-auto=validate
```