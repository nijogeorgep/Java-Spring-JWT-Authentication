/**
 * 
 */
package com.cloudwalker.jwtauth.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 553243
 *
 */
public class LoginForm {
  @NotBlank
  @Size(min = 3, max = 60)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

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
