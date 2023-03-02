package ua.goit.notes.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.AuthorRepository;
import ua.goit.notes.author.Authority;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class NoteRepositoryTest {
  @Autowired
  private NoteRepository noteRepository;
  @Autowired
  private AuthorRepository authorRepository;
  @BeforeEach
  void init(){
    String[] names = {"Billy", "Jonny", "Abraham", "Lincoln"};
    for (String name : names) {
      Author author = new Author();
      author.setName(name);
      author.setPassword("password"+name);
      author.setAuthority(Authority.USER);
      authorRepository.save(author);
    }
    String[] strings = {
      "Single Responsibility Principle#a class should do one thing and therefore it should have only a single reason to change",
      "Open-Closed Principle#classes should be open for extension and closed to modification",
      "Liskov Substitution Principle#subclasses should be substitutable for their base classes",
      "Interface Segregation Principle#is about separating the interfaces",
      "Dependency Inversion Principle#classes should depend upon interfaces or abstract classes instead of concrete classes and functions"
    };
    List<Author> authors = authorRepository.findAll();
    authors.forEach(author -> {
      Arrays.stream(strings).forEach(str -> {
        Note note = new Note();
        String[] titleContent = str.split("#");
        note.setTitle(titleContent[0]);
        note.setContent(titleContent[1]);
        note.setAccessType(AccessType.PRIVATE);
        author.setId(author.getId());
        note.setAuthor(author);
        noteRepository.save(note);
      });
    });
  }

  @Test
  void findAllNotesByAuthorId() {
    long id = 1L;
    Author author = authorRepository.findById(id).get();
    List<Note> expectedNotes = author.getNotes();
    List<Note> actualNotes = noteRepository.findAllByAuthorId(author.getId());
    Assertions.assertIterableEquals(expectedNotes, actualNotes);
  }
}