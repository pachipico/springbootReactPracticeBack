package com.jwtpractice.user.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
	private Long userId;
	private String email;
	private String name;
	private String createdDate;
	private String modifiedDate;
	private String roles;
	private String password;
	private String address;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		Arrays.asList(roles.split(",")).forEach(r -> {
			list.add(() -> r);
		});
		return list;
	}

	@Override
	public String getUsername() {

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public User(String email, String name, String roles, String password, String address) {

		this.email = email;
		this.name = name;
		this.roles = roles;
		this.password = password;
		this.address = address;
	}

}
