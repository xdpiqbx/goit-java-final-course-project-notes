package ua.goit.notes.register;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.notes.author.Author;
import ua.goit.notes.note.Note;

import java.util.ArrayList;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Author, Long> {
  Optional<Author> findByName(String name);
}
