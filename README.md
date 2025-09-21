# 🏢 CondMyApp

# 🏆 Sistema de Gestão de Condomínio – Spring Boot

Aplicação web desenvolvida em **Java 21** com **Spring Boot 3**, orientado a objetos para gerenciamento e cadastro de usuários, moradores e fornecedores de condomínio, com controle de acesso baseado em papéis (roles).  
Inclui autenticação, autorização, CRUD de usuários, proteção contra CSRF e prevenção de cache e segurança reforçada com Spring Security.

---

## 🚀 Tecnologias e Ferramentas

| Camada | Tecnologia/Ferramenta | Descrição |
|-------|------------------------|----------|
| **Backend** | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/> **Java 21** | Linguagem principal da aplicação |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="40" height="40"/> **Spring Boot 3** | Framework para criação da aplicação web |
| | Spring MVC | Padrão MVC para Controllers e rotas |
| | Spring Data JPA | Persistência de dados e repositórios |
| | Spring Security | Autenticação, autorização e CSRF |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg" width="40" height="40"/> MySQL / H2 | Banco de dados (produção e testes) |
| **Frontend** | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/thymeleaf/thymeleaf-original.svg" width="40" height="40"/> **Thymeleaf** | Engine de templates HTML integrada ao Spring |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original-wordmark.svg" width="40" height="40"/> **Bootstrap 4** | Estilização responsiva e componentes prontos |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-plain.svg" width="40" height="40"/> **jQuery / DataTables** | Paginação, busca e ordenação em tabelas |
| **Outros** | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="40" height="40"/> Maven | Gerenciamento de dependências |
| | Lombok | Redução de código boilerplate |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/eclipse/eclipse-original.svg" width="40" height="40"/> Eclipse | IDE de desenvolvimento |
| | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="40" height="40"/> Git | Controle de versão |

---

## ⚙️ Funcionalidades Implementadas

- **Autenticação e Logout Seguro**  
  - Login com senha criptografada (`BCryptPasswordEncoder`).  
  - Logout com invalidação completa de sessão, `SecurityContextHolder.clearContext()` e prevenção de cache.
- **Controle de Acesso por Papel**  
  - Perfis: **ADMIN**, **GERENTE**, **USER** (ou outros definidos em `Role`).  
  - Menus e botões exibidos dinamicamente conforme o papel do usuário.
- **Gestão de Usuários**  
  - CRUD completo (criar, listar, editar, excluir).  
  - Paginação e ordenação de tabelas via DataTables.
- **Proteção contra CSRF**  
  - Token automático em todos os formulários.
- **Templates Dinâmicos**  
  - Layout base com Thymeleaf Fragments (`header`, `footer`, `sidebar`).  
  - Modal de logout seguro integrado ao Bootstrap.

---

## 🗂️ Estrutura do Projeto

```
src/
 └─ main/
    ├─ java/com/bandampla/condmyapp
    │   ├─ controller/        # Controllers MVC
    │   ├─ dto/               # Objetos de transferência de dados
    │   ├─ enums/             # Enumerações (Roles, Status)
    │   ├─ exceptions/        # Exceções customizadas
    │   ├─ utils/             # Utilitários e helpers
    │   ├─ handler/           # Handlers (login success/failure)
    │   ├─ model/             # Entidades JPA (Usuario, Role, etc.)
    │   ├─ repository/        # Interfaces JPA
    │   ├─ service/           # Regras de negócio
    │   └─ config/            # Configurações (SecurityConfig, WebConfig)
    └─ resources/
        ├─ static/            # CSS, JS, imagens
        ├─ templates/         # Páginas Thymeleaf
        │    ├─ auth/         
        │    ├─ error/        
        │    ├─ home/         
        │    ├─ user/         
        │    ├─ fragments/    # Header, footer, mensagens (toasts)
        │    └─ login.html
        ├─ application.properties
        └─ data.sql           # Dados iniciais (usuários, roles)

```

---

## ▶️ Como Executar

1. **Pré-requisitos**  
   - Java 21  
   - Maven 3.9+  
   - MySQL 8 (ou usar H2 para testes)

2. **Clonar o repositório**
   ```bash
   git clone https://github.com/niltonbrito/condmyapp.git
   cd seu-repo
   ```

3. **Configurar banco (opcional para MySQL)**  
   Edite `src/main/resources/application.properties` para ajustar credenciais do banco MySQL ou utilize H2 (memória).
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sua_base
   spring.datasource.username=root
   spring.datasource.password=senha
   ```

4. **Build e execução**
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

5. **Acessar aplicação**  
   - URL: `http://localhost:8080`  
   - Usuários padrão definidos em `data.sql`.

---

## 🔮 Futuras Atualizações

O projeto possui um **plano de evolução contínua**, incluindo:
- Módulo de cadastro de apartamentos.
- Controle de vagas de garagem.
- Controle financeiro e geração de relatórios.
- Mural de avisos para condôminos.
- Outras melhorias planejadas.

Essas funcionalidades serão adicionadas em versões futuras para tornar o sistema ainda mais completo e eficiente.

---

## 🔒 Segurança

- **Logout seguro:**
  - Session invalidation: invalidateHttpSession(true)
  - Limpeza do contexto: clearAuthentication(true)
  - Deleção de cookies: JSESSIONID e XSRF-TOKEN.
  - SecurityContextHolder.clearContext().
- **Proteção contra Cache:**
  - Cabeçalhos Cache-Control, Pragma e Expires definidos para impedir back-button access.
- **CSRF Token:**
  - Incluído automaticamente em todos os formulários (`_csrf`).
  - Gerando e validando todos os formulários via CookieCsrfTokenRepository.

---

## 📜 Licença

Este projeto é de uso interno da Bandampla Soluções em TI.
Distribuição externa somente com autorização.

---

## Prints do projeto

## Tela de Login

<img width="1394" height="706" alt="Captura de tela 2025-08-03 193455" src="https://github.com/user-attachments/assets/9d2ec73c-27a4-47cd-a9e4-b3e75fd26f65" />

---

## Tela principal

<img width="1916" height="905" alt="Captura de tela 2025-08-03 193547" src="https://github.com/user-attachments/assets/4019b872-f558-441f-b10e-2bfc50c0953d" />

---

## Tela lista de usuários

<img width="1915" height="901" alt="Captura de tela 2025-08-03 193606" src="https://github.com/user-attachments/assets/a941226c-505c-4935-a362-19e2614e7846" />

---

## Tela Cadastro de usuários

<img width="1907" height="896" alt="Captura de tela 2025-08-03 193719" src="https://github.com/user-attachments/assets/d4cc764a-73a0-4c98-91ff-355e8afdf158" />

---

## Tela de edição de usuários

<img width="1909" height="900" alt="Captura de tela 2025-08-03 193829" src="https://github.com/user-attachments/assets/14406819-86c3-48ce-8156-c6359081746f" />

---

## Funções Alterar / Deletar / Desbloquear

<img width="1911" height="898" alt="Captura de tela 2025-08-03 193810" src="https://github.com/user-attachments/assets/e3dacb68-2ccc-4b3b-93c4-98e6fe94751e" />

<img width="1907" height="899" alt="Captura de tela 2025-08-03 193756" src="https://github.com/user-attachments/assets/151d304c-7949-4f22-9a7a-d5e41a23884b" />

<img width="1912" height="904" alt="Captura de tela 2025-08-03 193744" src="https://github.com/user-attachments/assets/a7db5e20-1dd3-4fbd-8d47-4b2fad5060c8" />

---

## Tela Alterar Senha

<img width="1908" height="897" alt="Captura de tela 2025-09-15 123259" src="https://github.com/user-attachments/assets/3aee9d50-f5cf-4a3b-8343-0071f4640bbc" />

---

## Modal para Sair da aplicação

<img width="1900" height="873" alt="Captura de tela 2025-09-15 122157" src="https://github.com/user-attachments/assets/0f7739b9-4cbd-4d7f-a496-746ad1349d8a" />

---

## Estatísticas GitHub


<div>
<a href="https://github.com/niltonbrito">
<img height="180em" src="https://github-readme-stats.vercel.app/api/top-langs/?username=niltonbrito&layout=compact&langs_count=7&theme=dracula"/>
<img height="180em" src="https://github-readme-stats.vercel.app/api?username=niltonbrito&show_icons=true&theme=dracula&include_all_commits=true&count_private=true"/>
</div>
  
## Contatos:

<div>
<!--<a href="https://www.youtube.com/seu-canal-youtube-aqui" target="_blank"><img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white" target="_blank"></a>
<a href="https://instagram.com/seu-usuário-instagram-aqui" target="_blank"><img src="https://img.shields.io/badge/-Instagram-%23E4405F?style=for-the-badge&logo=instagram&logoColor=white" target="_blank"></a>
<a href="https://www.twitch.tv/seu-usuário-aqui" target="_blank"><img src="https://img.shields.io/badge/Twitch-9146FF?style=for-the-badge&logo=twitch&logoColor=white" target="_blank"></a>-->
<a href = "mailto:nilton.brito@outlook.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
<a href="https://www.linkedin.com/in/niltonbrito" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>   
<a href="https://whatsa.me/5571992046500/?t=Ol%C3%A1!%20Seja%20bem%20vindo,%20no%20que%20posso%20ajudar?" target="_blank"><img src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white" target="_blank"></a>   
</div>
