package ua.goit.notes.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
  private final RegisterService registerService;
  private final RegisterDataValidator registerDataValidator;
  @GetMapping
  public ModelAndView registerPage() {
    return new ModelAndView("register");
  }
  @PostMapping
  public ModelAndView register(
      @Valid @ModelAttribute("registerForm") RegisterForm registerForm,
      BindingResult bindingResult
  ){
    if (bindingResult.hasErrors()){
      ModelAndView result = new ModelAndView("register");
      List<String> validate = registerDataValidator.validate(registerForm);
      result.addObject("isValidName", !validate.contains("username"));
      result.addObject("isValidPass", !validate.contains("password"));
      return result;
    }
    registerService.add(registerForm);
    return new ModelAndView("login");
  }
}
