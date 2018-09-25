/**
 * 
 */
package com.cloudwalker.jwtauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudwalker.jwtauth.model.User;

/**
 * @author 553243
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
