package ua.goit.notes.note;

import jakarta.persistence.*;
import lombok.Data;
import ua.goit.notes.author.Author;

@Data
@Entity
public class Note {
  @Id
  private String id;
  private String title;
  private String content;
  @Enumerated(EnumType.STRING)
  private AccessType accessType;
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name="author_id",
      nullable=false)
  private Author author;
}
