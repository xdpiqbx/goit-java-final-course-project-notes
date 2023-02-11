package ua.goit.notes.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.notes.author.Author;
import ua.goit.notes.author.AuthorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorDetailsService implements UserDetailsService {
  @Autowired
  AuthorRepository authorRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Author authorData = authorRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new UserDetails(){
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authority = new ArrayList<>();
        authority.add(authorData.getAuthority().toString());
        return authority.stream()
            .map(it -> (GrantedAuthority) () -> it)
            .toList();
      }
      @Override
      public String getPassword() {
        return authorData.getPassword();
      }
      @Override
      public String getUsername() {
        return authorData.getName();
      }
      @Override
      public boolean isAccountNonExpired() {
        return true;
      }
      @Override
      public boolean isAccountNonLocked() {
        return true;
      }
      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }
      @Override
      public boolean isEnabled() {
        return true;
      }
    };
  }
}
