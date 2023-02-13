package ua.goit.notes.note;

import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.AuthorExtended;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  public List<Note> findAllByAuthorId(long id){
    return noteRepository.findAllByAuthorId(id);
  }

  public void createNewNote(AuthorExtended authorExt, String title, String content, String accessType){
    Note note = new Note();
    note.setId(UUID.randomUUID().toString());
    note.setTitle(title);
    note.setContent(content);
    note.setAccessType(AccessType.valueOf(accessType.toUpperCase()));
      Author author = new Author();
      author.setId(authorExt.getId());
    note.setAuthor(author);
    noteRepository.save(note);
  }

  public Note getNoteById(String id){
    return noteRepository.findById(id)
        .orElseGet(()-> {
          Note note = new Note();
          note.setTitle("Not exists");
          note.setContent("Empty");
          note.setAccessType(AccessType.PRIVATE);
          return note;
        });
  }

  public void editNote(String id, AuthorExtended authorExt, String title, String content, String accessType) {
    Note note = new Note();
    note.setId(id);
    note.setTitle(title);
    note.setContent(content);
    note.setAccessType(AccessType.valueOf(accessType.toUpperCase()));
    Author author = new Author();
    author.setId(authorExt.getId());
    note.setAuthor(author);
    noteRepository.save(note);
  }
  public void deleteById(String id){
    noteRepository.deleteById(id);
  }

  public String convertMarkdownToHtml(String content){
    Parser parser = Parser.builder().build();
    Node node = parser.parse(content);
    HtmlRenderer renderer = HtmlRenderer.builder().build();
    return renderer.render(node);
  }
}
