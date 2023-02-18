package ua.goit.notes.authorization;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorDetails extends UserDetails {
  public long getId();
}
