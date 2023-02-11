package ua.goit.notes.author;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import ua.goit.notes.note.Note;

import java.util.List;

@Data
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(7) default 'USER'")
  private Authority authority;
  @OneToMany (mappedBy = "author", cascade = CascadeType.REMOVE)
  @ToString.Exclude
  private List<Note> notes;
}
