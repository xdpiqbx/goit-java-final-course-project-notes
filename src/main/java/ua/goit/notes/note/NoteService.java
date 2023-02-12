package ua.goit.notes.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  List<Note> findAllByAuthorId(long id){
    return noteRepository.findAllByAuthorId(id);
  }
}
