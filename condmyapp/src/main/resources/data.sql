-- USUÁRIOS PADRÃO
--DROP DATABASE cond_db;
--CREATE DATABASE cond_db;
--USE cond_db;

-- Pessoas
INSERT INTO person (id, str_first_name, str_last_name, str_cpf, int_age, str_gender, str_phone, birth_date)
VALUES
(1, 'João', 'Silva', '11111111111', 30, 'M', '119999999', '1990-01-15'),
(2, 'Maria', 'Oliveira', '22222222222', 28, 'F', '219999999', '1992-03-10'),
(3, 'Carlos', 'Souza', '33333333333', 35, 'M', '319999999', '1988-07-20'),
(4, 'Ana', 'Pereira', '44444444444', 26, 'F', '419999999', '1994-05-15'),
(5, 'Pedro', 'Almeida', '55555555555', 40, 'M', '519999999', '1984-12-30'),
(6, 'Julia', 'Costa', '66666666666', 32, 'F', '619999999', '1991-08-22');

-- Usuários
INSERT INTO users (id, username, email, password, user_group, status, created_at, updated_at)
VALUES
(1, 'admin', 'joao@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMINISTRADOR','ATIVO',  NOW(), NOW()),
(2, 'maria_user', 'maria@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'TERCEIRIZADA','BLOQUEADO',  NOW(), NOW()),
(3, 'carlos_admin', 'carlos@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'PROPRIETARIO','ATIVO',  NOW(), NOW()),
(4, 'ana_user', 'ana@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','GERENTE', 'INATIVO',  NOW(), NOW()),
(5, 'pedro_admin', 'pedro@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','ADMINISTRADOR','INATIVO',   NOW(), NOW()),
(6, 'julia_user', 'julia@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','FORNECEDOR', 'BLOQUEADO',   NOW(), NOW());