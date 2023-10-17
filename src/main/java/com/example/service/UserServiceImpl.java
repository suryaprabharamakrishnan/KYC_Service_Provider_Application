package com.example.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entities.Role;
import com.example.Entities.User;
import com.example.repository.UserRepository;
import com.example.web.datatransferobject.UserRegistrationDTO;

@Service
public class UserServiceImpl implements UserService {

	// Constructor based Injection (@Autowired is Field based Injection (i.e) not
	// recommended)
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDTO registrationDTO) {
		User user = new User(registrationDTO.getFirstName(), registrationDTO.getLastName(), registrationDTO.getEmail(),
				passwordEncoder.encode(registrationDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public boolean loadUserByEmailname(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return false;
		}
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid UserName or Password");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

}
