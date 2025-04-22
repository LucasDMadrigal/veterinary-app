package com.veterinary.veterinaryApp.serviceSecurity;

import com.veterinary.veterinaryApp.Repositories.ClientRepository;
import com.veterinary.veterinaryApp.Repositories.UserRepository;
import com.veterinary.veterinaryApp.models.Admin;
import com.veterinary.veterinaryApp.models.Client;
import com.veterinary.veterinaryApp.models.User;
import com.veterinary.veterinaryApp.models.Veterinarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		String role;



		if (user == null){
			throw new UsernameNotFoundException(username);
		}

		if (user instanceof Admin) {
			role = "ADMIN";
		} else if (user instanceof Veterinarian) {
			role = "VETERINARIAN";
		} else if (user instanceof Client) {
			role = "CLIENT";
		} else {
			throw new UsernameNotFoundException("Unknown user role");
		}
//
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(role)
				.build();
	}
}
