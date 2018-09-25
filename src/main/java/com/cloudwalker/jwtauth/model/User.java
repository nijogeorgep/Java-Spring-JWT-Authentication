/**
 * 
 */
package com.cloudwalker.jwtauth.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

/**
 * @author 553243
 *
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 3, max = 50)
  private String name;

  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  @NaturalId
  @Email
  @Size(max = 50)
  @NotBlank
  private String email;

  @NotBlank
  @Size(min = 3, max = 100)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  /**
   * 
   */
  public User() {}

  /**
   * @param name
   * @param username
   * @param email
   * @param password
   */
  public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

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

  /**
   * @return the roles
   */
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * @param roles the roles to set
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }


}
