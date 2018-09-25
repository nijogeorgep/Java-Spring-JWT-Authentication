/**
 * 
 */
package com.cloudwalker.jwtauth.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cloudwalker.jwtauth.model.User;
import com.cloudwalker.jwtauth.repository.UserRepository;

/**
 * @author 553243
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.
   * String)
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
            "User Not Found with -> username or email : " + username));

    return UserPrincipal.build(user);
  }

}
