`admin`
`super_secret_password`

## [Miro](https://miro.com/app/board/o9J_l1t0r8Y=/?moveToWidget=3074457362679673943&cot=14)

---

## [ТЗ](https://docs.google.com/document/d/11BxriWk7wtxNUsZJVitx1ZrbcSvyNDaTkEW7AuWPo8I/edit)

---

## Routes
```text
/login
/register
/note/list
/note/create
/note/edit
/note/share/{id}
```

---

prod - СУБД PostgreSQL

default - h2 in-memory

`spring.datasource.username=${DB_USERNAME}`

Змінні оточення повинні бути описані в файлі `readme.md`

---

## Tasks

- Створити міграцію з таблицями `author` (OneToMany), `note` (ManyToOne)
- Entity's `Author` + `enum Authority` and `Note` + `enum AccessType`
- Security (login : password)
- `AuthController`
```java
@Controller
@RequestMapping("/login")
public class AuthController{
  private final AuthService authService;
  
  @GetMapping
  public ModelAndView loginPage(){...}
  @PostMapping
  public RedirectView login(){
    // basic name and password validation!
    //    name - 5 to 50 symbols
    //    pass - 8 to 100 symbols
    // if authorized redirect to -> /note/list
    // else redirect to -> /login
  }
}
```
- `RegisterController`
```java
@Controller
@RequestMapping("/register")
public class RegisterController{
  private final RegisterService registerService;

  @GetMapping
  public ModelAndView registerPage(){...}
  @PostMapping
  public RedirectView register(){
    // basic name and password validation!
    //    name - 5 to 50 symbols
    //    pass - 8 to 100 symbols
    // if register status ok redirect to -> /login
    // else redirect to -> /register with empty form
  }
}
```
- `NoteController`
```java
@Controller
@RequestMapping("/note")
public class NoteController {
  private final NoteService noteService;

  @GetMapping("/list")
  public ModelAndView getListOfNotes(){
    // All authors notes
    // + Текст "Мої нотатки - 3 шт" - це скільки у користувача є нотаток.
  }

  @PostMapping("/create")
  public RedirectView createNote(){
    // basic note validation!
    //    title - 5 to 100 symbols
    //    content - 5 to 10 000 symbols
    // if create status ok redirect to -> /note/list
    // else redirect to -> /create-note-error-page with button -> /note/list
  }

  @GetMapping("/create")
  public ModelAndView pageCreateNote(){
    // Поле "Ім'я нотатки" - <input type="text">
    // Поле "Вміст нотатки" - <textarea rows="20">
    // Вибір типу нотатки - це радіобаттони, за замовчуванням вибрано варіант "Приватне посилання"
  }

  @PostMapping("/edit")
  public RedirectView editNote(){
    // basic note validation!
    //    title - 5 to 100 symbols
    //    content - 5 to 10 000 symbols
    // if edit status ok redirect to -> /note/list
    // else redirect to -> /edit-note-error-page with button -> /note/list
  }

  @GetMapping("/edit")
  public ModelAndView pageEditNote(@RequestParam String id){
    // Всі поля вже заповнені змістом старої нотатки
    // Поле "Ім'я нотатки" - <input type="text">
    // Поле "Вміст нотатки" - <textarea rows="20">
    // Вибір типу нотатки - це радіобаттони, за замовчуванням вибрано варіант "Приватне посилання"
  }

  @GetMapping("/share/{id}")
  public ModelAndView createNote(@PathVariable(id) String id){
    // Якщо користувач відкриває сторінку з неіснуючою або приватною нотаткою, він бачить відповідний екран.
    // get note by id
    // if not private return note
    // else return error page
  }
}
```
- [https://www.bootstrapcdn.com/](https://www.bootstrapcdn.com/)
- Використовуємо СУБД PostgreSQL