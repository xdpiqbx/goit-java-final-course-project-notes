package ua.goit.notes.author;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorRepositoryTest {
  @Autowired
  private AuthorRepository authorRepository;
  @BeforeEach
  void init(){
    // admin already in database
    String[] names = {"Billy", "Jonny", "Abraham", "Lincoln"};
    for (String name : names) {
      Author author = new Author();
      author.setName(name);
      author.setPassword("password"+name);
      author.setAuthority(Authority.USER);
      authorRepository.save(author);
    }
  }
  @Test
  void findAuthorByName() {
    Author actualAuthor = authorRepository.findByName("Billy").get();
    Assertions.assertNotNull(actualAuthor);
  }
  @Test
  void findAllAuthors() {
    int size = authorRepository.findAll().size();
    Assertions.assertEquals(size, 5);
  }
  @Test
  void findAuthorById() {
    Author jonny = authorRepository.findByName("Jonny").get();
    Author author = authorRepository.findById(jonny.getId()).get();
    Assertions.assertEquals(jonny, author);
  }
  @Test
  void deleteById() {
    Author jonny = authorRepository.findByName("Jonny").get();
    authorRepository.deleteById(jonny.getId());
    boolean isPresent = authorRepository.findByName("Jonny").isPresent();
    Assertions.assertFalse(isPresent);
  }
  @Test
  void editAuthor() {
    String newName = "Elizabeth";
    Author jonny = authorRepository.findByName("Jonny").get();
    jonny.setName(newName);
    authorRepository.save(jonny);
    Author author = authorRepository.findById(jonny.getId()).get();
    Assertions.assertEquals(author.getName(), newName);
  }
}