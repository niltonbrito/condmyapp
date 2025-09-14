package com.bandampla.condmyapp.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bandampla.condmyapp.enums.UserGroup;
import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.model.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public UserGroup getUserGroup() {
        return user.getUserGroup();
    }

    public Status getStatus() {
        return user.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getUserGroup().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() != Status.BLOQUEADO;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == Status.ATIVO;
    }
}
