/**
 * 
 */
package com.cloudwalker.jwtauth.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.cloudwalker.jwtauth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 553243
 *
 */
public class UserPrincipal implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  /**
   * @param id
   * @param name
   * @param username
   * @param email
   * @param password
   * @param authorities
   */
  public UserPrincipal(Long id, String name, String username, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    UserPrincipal user = (UserPrincipal) o;
    return Objects.equals(id, user.id);
  }

  public static UserDetails build(User user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());
    return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(),
        user.getPassword(), authorities);
  }

}
