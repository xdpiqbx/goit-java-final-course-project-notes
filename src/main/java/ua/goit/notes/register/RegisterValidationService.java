package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegisterValidationService {
  public boolean usernameValidation(String username){
    Pattern pattern = Pattern.compile("^[a-zA-Z\\d]*$");
    Matcher matcher = pattern.matcher(username);
    boolean correctSymbolsInName = matcher.matches();
    boolean correctNameLength = username.length() >= 5 && username.length() <= 50;
    return correctNameLength && correctSymbolsInName;
  }
  public boolean passwordValidation(String password){
    return password.length() >= 8 && password.length() <= 100;
  }
}
