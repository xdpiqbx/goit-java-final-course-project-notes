package ua.goit.notes.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
  private final RegisterService registerService;
  private final RegisterValidationService registerValidationService;
  @GetMapping
  public ModelAndView registerPage() {
    return new ModelAndView("register");
  }
  @PostMapping
  public ModelAndView register(
      @RequestParam String username,
      @RequestParam String password){
    boolean isValidName = registerValidationService.usernameValidation(username.trim());
    boolean isValidPass = registerValidationService.passwordValidation(password.trim());
    if(!isValidName || !isValidPass){
      ModelAndView result = new ModelAndView("register");
      result.addObject("isValidName", isValidName);
      result.addObject("isValidPass", isValidPass);
      return result;
    }
    registerService.add(username.trim(), password.trim());
    return new ModelAndView("login");
  }
}
