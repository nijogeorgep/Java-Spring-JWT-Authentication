/**
 * 
 */
package com.cloudwalker.jwtauth.response;

/**
 * @author 553243
 *
 */
public class JwtResponse {
  private String token;
  private String type = "Bearer";

  /**
   * @param accessToken
   */
  public JwtResponse(String accessToken) {
    this.token = accessToken;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token the token to set
   */
  public void setToken(String accessToken) {
    this.token = accessToken;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String accessType) {
    this.type = accessType;
  }


}
