package org.debugroom.sample.cassandra.pattern2.common.security;

import java.util.Collection;
import java.util.Date;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 117051470779343136L;

	private final User user;
	private final Collection<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		for(Credential credential : user.getCredentials()){
			if(new Date().getTime() < credential.getExpiredDate().getTime()){
				return credential.getCredentialKey();
			}
		}
		return null;
	}

	@Override
	public String getUsername() {
		return user.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !user.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(null == getPassword()){
			return false;
		}
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}
