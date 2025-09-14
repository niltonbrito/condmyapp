package com.bandampla.condmyapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bandampla.condmyapp.dto.UserRequestDTO;
import com.bandampla.condmyapp.dto.UserResponseDTO;
import com.bandampla.condmyapp.enums.Status;
import com.bandampla.condmyapp.model.User;
import com.bandampla.condmyapp.repository.UserRepository;
import com.bandampla.condmyapp.utils.CalculateAge;
import com.bandampla.condmyapp.utils.MaskUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService customUserDetailsService;
    
    public UserService(UserRepository userRepository, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
    }
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=\\-{};:<>|.?]).{8,}$";

    private boolean senhaForte(String senha) {
        return Pattern.matches(PASSWORD_REGEX, senha);
    }

    // ------------------- LISTAGEM -------------------
    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> response = new ArrayList<>();
        for (User u : users) {
            response.add(convertToDTO(u));
        }
        return response;
    }

    // ------------------- CREATE -------------------
    public UserResponseDTO create(UserRequestDTO dto) {
        if (userRepository.existsByCpf(dto.getCpf())) throw new RuntimeException("CPF já cadastrado.");
        if (userRepository.existsByEmail(dto.getEmail())) throw new RuntimeException("E-mail já cadastrado.");
        if (userRepository.existsByUsername(dto.getUsername())) throw new RuntimeException("Username já cadastrado.");

        if (dto.getPassword() == null || dto.getPassword().isBlank()) throw new RuntimeException("As informe a senha.");
        if (dto.getConfirmPassword() == null || dto.getConfirmPassword().isBlank()) throw new RuntimeException("As informe a confirmação desenha.");
        if (!dto.getPassword().equals(dto.getConfirmPassword())) throw new RuntimeException("As senhas não coincidem.");
        if (!senhaForte(dto.getPassword())) throw new RuntimeException("Senha fraca: pelo menos 8 caracteres, 1 maiúscula, 1 número e 1 caractere especial.");

        User user = convertToEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setTentativasLogin(0);
        userRepository.save(user);

        return convertToDTO(user);
    }

    // ------------------- EDIT -------------------
    public UserResponseDTO edit(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setPassword(""); // não enviar senha
        user.setCpf(MaskUtils.maskCpf(user.getCpf()));
        user.setPhone(MaskUtils.maskPhone(user.getPhone()));

        return convertToDTO(user);
    }

    // ------------------- UPDATE -------------------
    public void update(Long id, UserRequestDTO dto, String confirmPassword, HttpServletRequest request) {
        User userBanco = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (userRepository.existsByCpfAndIdNot(dto.getCpf(), id)) throw new RuntimeException("CPF já em uso.");
        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) throw new RuntimeException("E-mail já em uso.");
        if (userRepository.existsByUsernameAndIdNot(dto.getUsername(), id)) throw new RuntimeException("Username já em uso.");

        userBanco.setFirstName(dto.getFirstName());
        userBanco.setLastName(dto.getLastName());
        userBanco.setUserGroup(dto.getUserGroup());
        userBanco.setDescription(dto.getDescription());
        userBanco.setEmail(dto.getEmail());
        userBanco.setCpf(dto.getCpf());
        userBanco.setStatus(dto.getStatus());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (!dto.getPassword().equals(dto.getConfirmPassword()))
                throw new RuntimeException("As senhas não coincidem.");
            if (!senhaForte(dto.getPassword()))
                throw new RuntimeException("Senha fraca: pelo menos 8 caracteres, 1 maiúscula, 1 número e 1 caractere especial.");

            userBanco.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userBanco.setTrocarSenhaProximoLogin(dto.isTrocarSenhaProximoLogin());
        userRepository.save(userBanco);
    }

    // ------------------- DELETE -------------------
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if ("ADMINISTRADOR".equalsIgnoreCase(user.getUserGroup().name()) && user.isUserSystem()) throw new RuntimeException("Usuário de sistema não pode ser deletado.");

        userRepository.delete(user);
    }

    // ------------------- UNLOCK -------------------
    public void unlockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getStatus() != Status.BLOQUEADO)
            throw new RuntimeException("Usuário não está bloqueado.");

        user.setTentativasLogin(0);
        user.setStatus(Status.ATIVO);
        userRepository.save(user);
    }

    // ------------------- CONVERSÕES DTO -------------------
    public User convertToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName().trim());
        user.setLastName(dto.getLastName().trim());
        user.setEmail(dto.getEmail().toLowerCase().trim());
        user.setPhone(dto.getPhone().replaceAll("\\D", "").trim());
        user.setCpf(dto.getCpf().replaceAll("\\D", "").trim());
        user.setGender(dto.getGender());
        user.setBirthDate(dto.getBirthDate());
        user.setAge((CalculateAge.calcularIdade(dto.getBirthDate())));
        user.setUserGroup(dto.getUserGroup());
        user.setStatus(dto.getStatus());
        user.setUsername(dto.getUsername().toLowerCase().trim());
        user.setDescription(dto.getDescription().trim());
        user.setTrocarSenhaProximoLogin(dto.isTrocarSenhaProximoLogin());
        return user;
    }

    public UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(MaskUtils.maskPhone(user.getPhone()));  // Formata o celular
        dto.setCpf(MaskUtils.maskCpfCripto(user.getCpf()));        // Formata o CPF
        dto.setGender(user.getGender());
        dto.setBirthDate(user.getBirthDate());
        dto.setUserGroup(user.getUserGroup());
        dto.setStatus(user.getStatus());
        dto.setUsername(user.getUsername());
        dto.setDescription(user.getDescription());
        dto.setTrocarSenhaProximoLogin(user.isTrocarSenhaProximoLogin());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

}
