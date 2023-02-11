package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.Authority;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegisterService {
  private final RegisterRepository registerRepository;
  private final PasswordEncoder passwordEncoder;
  public void add(String username, String password){
    Author author = new Author();
    author.setName(username);
    author.setPassword(passwordEncoder.encode(password));
    author.setAuthority(Authority.USER);
    registerRepository.save(author);
  }
}
