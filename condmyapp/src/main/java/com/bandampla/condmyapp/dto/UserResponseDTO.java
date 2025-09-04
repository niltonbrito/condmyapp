package com.bandampla.condmyapp.dto;

import java.time.LocalDateTime;

import com.bandampla.condmyapp.enums.Gender;
import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.enums.UserGroup;

public class UserResponseDTO {

	    private Long id;
	    private String firstName;
	    private String lastName;
	    private String cpf;
	    private Gender gender;
	    private String phone;
	    private String email;
	    private String username;
	    private UserGroup userGroup;
	    private Status status;
	    private boolean trocarSenhaProximoLogin;
	    private String description;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	    
}
