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
		return username.toLowerCase().trim();
	}

	public void setUsername(String username) {
		this.username = username.toLowerCase().trim();
	}

	public String getEmail() {
		return email.toLowerCase().trim();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	public String getPassword() {
		return password.trim();
	}

	public void setPassword(String password) {
		this.password = password.trim();
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
		return rememberToken.trim();
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken.trim();
	}

	public String getSessionToken() {
		return sessionToken.trim();
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken.trim();
	}

	public boolean isUserSystem() {
		return userSystem;
	}

	public void setUserSystem(boolean userSystem) {
		this.userSystem = userSystem;
	}
}
