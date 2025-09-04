package com.bandampla.condmyapp.dto;

import java.util.Date;

import com.bandampla.condmyapp.enums.Gender;
import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.enums.UserGroup;

public class UserRequestDTO {
	
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birthDate;
    private Gender gender;
    private String phone;
    private String email;
    private String username;
    private String password;
    private UserGroup userGroup;
    private Status status;
    private boolean trocarSenhaProximoLogin;
    private String description;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public boolean isTrocarSenhaProximoLogin() {
		return trocarSenhaProximoLogin;
	}
	public void setTrocarSenhaProximoLogin(boolean trocarSenhaProximoLogin) {
		this.trocarSenhaProximoLogin = trocarSenhaProximoLogin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}