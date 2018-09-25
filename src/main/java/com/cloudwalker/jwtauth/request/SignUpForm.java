/**
 * 
 */
package com.cloudwalker.jwtauth.request;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 553243
 *
 */
public class SignUpForm {
  @NotBlank
  @Size(min = 3, max = 50)
  private String name;

  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  @NotBlank
  @Size(max = 60)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the role
   */
  public Set<String> getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(Set<String> role) {
    this.role = role;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }


}
