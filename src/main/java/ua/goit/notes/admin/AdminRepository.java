package ua.goit.notes.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.notes.author.Author;

public interface AdminRepository extends JpaRepository<Author, Long> {
}
