package ua.goit.notes.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterFormData {
  @NotBlank(message = "Name is mandatory")
  @Size(min = 5, max = 50)
  @Pattern(regexp = "^[a-zA-Z\\d]*$")
  private String username;
  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 100)
  private String password;
}
