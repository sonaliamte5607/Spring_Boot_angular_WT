package com.assignment.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.springjwt.models.Manager;
import com.assignment.springjwt.repository.ManagerRepository;

@Service
public class ManagerDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ManagerRepository managerRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager manager = managerRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return ManagerDetailsImpl.build(manager);
	}

}
