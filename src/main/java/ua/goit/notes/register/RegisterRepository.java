package ua.goit.notes.register;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.notes.author.Author;

public interface RegisterRepository extends JpaRepository<Author, Long> {
}
