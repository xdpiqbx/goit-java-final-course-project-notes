package ua.goit.notes.note;

import org.springframework.stereotype.Service;

@Service
public class NoteValidationService {
  public boolean isTitleValid(String title){
    return title.length() >= 5 && title.length() <= 100;
  }
  public boolean isContentValid(String content){
    return content.length() >= 5 && content.length() <= 10000;
  }
}
