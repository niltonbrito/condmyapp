package com.bandampla.condmyapp.model;

import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.enums.UserGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Person {

	
	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_group", nullable = false)
	private UserGroup userGroup;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private Status status;

    @Column(name = "tentativas_login", nullable = false)
    private int tentativasLogin = 0;

    @Column(name = "trocar_senha_proximo_login", nullable = false)
    private boolean trocarSenhaProximoLogin = false;
    
    @Column(name = "remember_token")
    private String rememberToken;
    
    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "user_system", nullable = false)
    private boolean userSystem;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getTentativasLogin() {
		return tentativasLogin;
	}

	public void setTentativasLogin(int tentativasLogin) {
		this.tentativasLogin = tentativasLogin;
	}

	public boolean isTrocarSenhaProximoLogin() {
		return trocarSenhaProximoLogin;
	}

	public void setTrocarSenhaProximoLogin(boolean trocarSenhaProximoLogin) {
		this.trocarSenhaProximoLogin = trocarSenhaProximoLogin;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public boolean isUserSystem() {
		return userSystem;
	}

	public void setUserSystem(boolean userSystem) {
		this.userSystem = userSystem;
	}
}
