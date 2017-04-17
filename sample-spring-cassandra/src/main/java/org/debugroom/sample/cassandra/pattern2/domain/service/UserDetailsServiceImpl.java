package org.debugroom.sample.cassandra.pattern2.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.debugroom.sample.cassandra.pattern2.common.security.UserDetailsImpl;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.repository.UserRepository;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepository.findUserByLoginId(username);
		return UserDetailsImpl.builder()
								.user(user)
								.authorities(getAuthorities(user))
								.build();
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		if(user.isAdmin()){
			return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		}
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}
