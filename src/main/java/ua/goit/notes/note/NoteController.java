package ua.goit.notes.note;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.notes.author.AuthorExtended;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
  private final NoteService noteService;
  @GetMapping("/list")
  public ModelAndView getListOfNotes(Authentication authentication){
    AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
    ModelAndView result = new ModelAndView("note-list");
    result.addObject("notes", noteService.findAllByAuthorId(authorExtended.getId()));
    return result;
  }
  @GetMapping("/create")
  public ModelAndView pageCreateNote(){
    return new ModelAndView("create-note");
  }
  @PostMapping("/create")
  public RedirectView createNote(
      @RequestParam String title,
      @RequestParam String content,
      @RequestParam String accessType,
      Authentication authentication
  ){
    AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
    noteService.createNewNote(author,title,content,accessType);
    return new RedirectView("/note/list");
  }
  @GetMapping("/edit")
  public ModelAndView pageEditNote(@RequestParam String id){
    // Всі поля вже заповнені змістом старої нотатки
    // Поле "Ім'я нотатки" - <input type="text">
    // Поле "Вміст нотатки" - <textarea rows="20">
    // Вибір типу нотатки - це радіобаттони, за замовчуванням вибрано варіант "Приватне посилання"
    return null;
  }
  @PostMapping("/edit")
  public RedirectView editNote(){
    // basic note validation!
    //    title - 5 to 100 symbols
    //    content - 5 to 10 000 symbols
    // if edit status ok redirect to -> /note/list
    // else redirect to -> /edit-note-error-page with button -> /note/list
    return null;
  }
  @GetMapping("/share/{id}")
  public ModelAndView createNote(@PathVariable("id") String id){
    // Якщо користувач відкриває сторінку з неіснуючою або приватною нотаткою, він бачить відповідний екран.
    // get note by id
    // if not private return note
    // else return error page
    return null;
  }
}
