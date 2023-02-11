package ua.goit.notes.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorAuthProvider implements AuthenticationProvider {
  private final AuthorDetailsService authorDetailsService;
  private final PasswordEncoder passwordEncoder;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserDetails user = authorDetailsService.loadUserByUsername(username);
    return checkPassword(user, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return  UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  private Authentication checkPassword(UserDetails user, String rawPassword){
    if(passwordEncoder.matches(rawPassword, user.getPassword())){
      User innerUser = new User(
          user.getUsername(),
          user.getPassword(),
          user.getAuthorities()
      );
      return new UsernamePasswordAuthenticationToken(innerUser, user.getPassword(), user.getAuthorities());
    }else{
      throw new BadCredentialsException("Bad credentials");
    }
  }
}
