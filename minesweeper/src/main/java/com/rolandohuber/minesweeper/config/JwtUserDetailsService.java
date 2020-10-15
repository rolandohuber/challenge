package com.rolandohuber.minesweeper.config;

import java.util.ArrayList;

import com.rolandohuber.minesweeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.rolandohuber.minesweeper.entity.User user= userRepository.findByUsername(username);

		if (user != null) {
			return new User(user.getUsername(), user.getPassword(),new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}