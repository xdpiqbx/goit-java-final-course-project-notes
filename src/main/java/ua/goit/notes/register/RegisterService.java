package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.Authority;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final RegisterRepository registerRepository;
  private final PasswordEncoder passwordEncoder;
  public boolean add(RegisterFormData registerFormData){
    if(registerRepository.findByName(registerFormData.getUsername()).isPresent()){
      return false;
    }
    Author author = new Author();
    author.setName(registerFormData.getUsername());
    author.setPassword(passwordEncoder.encode(registerFormData.getPassword()));
    author.setAuthority(Authority.USER);
    registerRepository.save(author);
    return true;
  }
}
