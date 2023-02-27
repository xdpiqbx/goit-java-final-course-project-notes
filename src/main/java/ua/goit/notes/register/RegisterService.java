package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.AuthorRepository;
import ua.goit.notes.author.Authority;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final AuthorRepository authorRepository;
  private final PasswordEncoder passwordEncoder;
  public boolean add(RegisterFormData registerFormData){
    if(authorRepository.findByName(registerFormData.getUsername()).isPresent()){
      return false;
    }
    Author author = new Author();
    author.setName(registerFormData.getUsername());
    author.setPassword(passwordEncoder.encode(registerFormData.getPassword()));
    author.setAuthority(Authority.USER);
    authorRepository.save(author);
    return true;
  }
}
