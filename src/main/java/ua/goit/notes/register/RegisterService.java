package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.Authority;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final RegisterRepository registerRepository;
  private final PasswordEncoder passwordEncoder;
  public void add(RegisterForm registerForm){
    Author author = new Author();
    author.setName(registerForm.getUsername());
    author.setPassword(passwordEncoder.encode(registerForm.getPassword()));
    author.setAuthority(Authority.USER);
    registerRepository.save(author);
  }
}
