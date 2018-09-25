/**
 * 
 */
package com.cloudwalker.jwtauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudwalker.jwtauth.model.Role;
import com.cloudwalker.jwtauth.model.RoleName;

/**
 * @author 553243
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
