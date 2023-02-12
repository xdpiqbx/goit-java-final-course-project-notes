package ua.goit.notes.note;

import lombok.RequiredArgsConstructor;
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
    // basic note validation!
    //    title - 5 to 100 symbols
    //    content - 5 to 10 000 symbols
    // if create status ok redirect to -> /note/list
    // else redirect to -> /create-note-error-page with button -> /note/list
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
}
