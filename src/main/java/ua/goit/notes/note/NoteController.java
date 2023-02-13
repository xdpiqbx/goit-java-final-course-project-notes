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
  private final NoteValidationService validationService;
  @GetMapping("/list")
  public ModelAndView getListOfNotes(Authentication authentication){
    AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
    ModelAndView result = new ModelAndView("note-list");
    result.addObject("notes", noteService.findAllByAuthorId(authorExtended.getId()));
    return result;
  }
  @GetMapping("/create")
  public ModelAndView pageCreateNote(){
    return new ModelAndView("create-or-edit-note");
  }
  @PostMapping("/create")
  public RedirectView createNote(
      @RequestParam String title,
      @RequestParam String content,
      @RequestParam String accessType,
      Authentication authentication
  ){
    if(!validationService.isContentValid(content) || !validationService.isTitleValid(title)){
      return new RedirectView("/note/create-edit-note-error-page");
    }
    AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
    noteService.createNewNote(author, title, content, accessType);
    return new RedirectView("/note/list");
  }
  @GetMapping("/edit")
  public ModelAndView pageEditNote(@RequestParam String id){
    Note note = noteService.getNoteById(id);
    ModelAndView result = new ModelAndView("create-or-edit-note");
    result.addObject("note", note);
    result.addObject("public", note.getAccessType().getType().equals("public"));
    result.addObject("private", note.getAccessType().getType().equals("private"));
    return result;
  }
  @PostMapping("/edit")
  public RedirectView editNote(
      @RequestParam String id,
      @RequestParam String title,
      @RequestParam String content,
      @RequestParam String accessType,
      Authentication authentication
  ){
    if(!validationService.isContentValid(content) || !validationService.isTitleValid(title)){
      return new RedirectView("/note/create-edit-note-error-page");
    }
    AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
    noteService.editNote(id, author, title, content, accessType);
    return new RedirectView("/note/list");
  }
  @GetMapping("/share/{id}")
  public ModelAndView createNote(@PathVariable("id") String id){
    Note note = noteService.getNoteById(id);
    if(note.getAccessType().getType().equals("private")){
      return new ModelAndView("note-not-exists");
    }
    note.setContent(noteService.convertMarkdownToHtml(note.getContent()));
    ModelAndView result = new ModelAndView("note");
    result.addObject("note", note);
    return result;
  }
  @PostMapping("/delete")
  public RedirectView deleteNote(@RequestParam String id){
    noteService.deleteById(id);
    return new RedirectView("/note/list");
  }
  @GetMapping("/create-edit-note-error-page")
  public ModelAndView errorPageCreateEdit(){
    return new ModelAndView("create-edit-note-error-page");
  }

  @GetMapping("/addmock")
  public RedirectView createNote(Authentication authentication){
    AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
    noteService.setMockData(author);
    return new RedirectView("/note/list");
  }
}