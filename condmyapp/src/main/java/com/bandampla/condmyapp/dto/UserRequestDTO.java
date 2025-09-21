package com.bandampla.condmyapp.dto;

import java.time.LocalDate;

import com.bandampla.condmyapp.enums.Gender;
import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.enums.UserGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação/edição de usuários.
 * Contém validações de entrada para garantir consistência dos dados.
 */
public class UserRequestDTO {

    // ======== Dados Pessoais ========

    @NotBlank(message = "O nome é obrigatório")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String lastName;

    /**
     * Aceita apenas CPF com 11 dígitos numéricos ou no formato 000.000.000-00.
     * Para validar dígitos do CPF use @CPF (Hibernate Validator) ou um validador customizado.
     */
    @Pattern(
        regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
        message = "CPF inválido. Use apenas números ou o formato 000.000.000-00"
    )
    private String cpf;

    @PastOrPresent(message = "Data de nascimento não pode ser no futuro")
    private LocalDate birthDate;

    private int age;
    /**
     * Gênero é obrigatório se sua regra de negócio exigir.
     */
    @NotNull(message = "O gênero é obrigatório")
    private Gender gender;

    /**
     * Validação simples para telefone brasileiro: (99) 99999-9999 ou 99999-9999.
     * Ajuste o regex conforme sua necessidade.
     */
    @Pattern(
        regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}",
        message = "Telefone inválido. Exemplo: (00) 00000-0000"
    )
    private String phone;

    // ======== Credenciais ========

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 4, max = 20, message = "Username deve ter entre 4 e 20 caracteres")
    private String username;

    @NotNull(message = "O grupo de usuário é obrigatório")
    private UserGroup userGroup;

    @NotNull(message = "O status é obrigatório")
    private Status status;

    /**
     * Senha com mínimo de 8 caracteres.
     * A força da senha (letra maiúscula, número, caractere especial, etc.)
     * é validada no AuthService.
     */
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    /**
     * Campo para confirmação da senha.
     * A igualdade entre password e confirmPassword
     * pode ser validada com um validador customizado de classe.
     */
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String confirmPassword;

    /**
     * Se true, força a troca de senha no próximo login.
     */
    private boolean trocarSenhaProximoLogin;

    /**
     * Campo opcional para descrição ou observações.
     */
    private String description;

    // ======== Getters e Setters ========

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

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
