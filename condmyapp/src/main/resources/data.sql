-- USUÁRIOS PADRÃO
--DROP DATABASE cond_db;
--CREATE DATABASE cond_db;
--USE cond_db;

-- Pessoas
INSERT IGNORE  INTO person (id, first_name, last_name, cpf, age, gender, phone, birth_date, created_at, updated_at)
VALUES
(1, 'João', 'Silva', '11111111111', 30, 'MASCULINO', '119999999', '1990-01-15',  NOW(), NOW()),
(2, 'Maria', 'Oliveira', '22222222222', 28, 'FEMININO', '219999999', '1992-03-10',  NOW(), NOW()),
(3, 'Carlos', 'Souza', '33333333333', 35, 'MASCULINO', '319999999', '1988-07-20',  NOW(), NOW()),
(4, 'Ana', 'Pereira', '44444444444', 26, 'FEMININO', '419999999', '1994-05-15',  NOW(), NOW()),
(5, 'Pedro', 'Almeida', '55555555555', 40, 'MASCULINO', '519999999', '1984-12-30',  NOW(), NOW()),
(6, 'Julia', 'Costa', '66666666666', 32, 'FEMININO', '619999999', '1991-08-22',  NOW(), NOW()),
(7,  'Ricardo', 'Mendes', '77777777777', 29, 'MASCULINO', '719999999', '1995-02-12', NOW(), NOW()),
(8,  'Fernanda', 'Lima',   '88888888888', 34, 'FEMININO',  '819999999', '1989-11-05', NOW(), NOW()),
(9,  'Lucas',    'Barbosa','99999999999', 31, 'MASCULINO', '919999999', '1992-09-18', NOW(), NOW()),
(10, 'Patrícia', 'Souza',  '10101010101', 27, 'FEMININO',  '119888888', '1996-04-07', NOW(), NOW()),
(11, 'André',    'Ferreira','12121212121', 36,'MASCULINO','219888888', '1987-12-25', NOW(), NOW()),
(12, 'Camila',   'Rocha',  '13131313131', 25, 'FEMININO',  '319888888', '1998-07-14', NOW(), NOW()),
(13, 'Bruno',    'Cardoso','14141414141', 33, 'MASCULINO', '419888888', '1990-06-02', NOW(), NOW()),
(14, 'Larissa',  'Martins','15151515151', 29, 'FEMININO',  '519888888', '1994-01-30', NOW(), NOW()),
(15, 'Diego',    'Ramos',  '16161616161', 38, 'MASCULINO', '619888888', '1985-03-09', NOW(), NOW()),
(16, 'Isabela',  'Teixeira','17171717171',26, 'FEMININO',  '719888888', '1997-10-21', NOW(), NOW());


-- Usuários
INSERT IGNORE  INTO users (id, username, email, password, user_group, status, user_system)
VALUES
(1, 'admin', 'joao@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMINISTRADOR','ATIVO',true),
(2, 'maria_user', 'maria@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'TERCEIRIZADA','BLOQUEADO',false),
(3, 'carlos_admin', 'carlos@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'PROPRIETARIO','ATIVO',false),
(4, 'ana_user', 'ana@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','GERENTE', 'INATIVO',false),
(5, 'pedro_admin', 'pedro@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','ADMINISTRADOR','INATIVO',false),
(6, 'julia_user', 'julia@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq','FORNECEDOR', 'BLOQUEADO',false),
(7,  'ricardo_user',  'ricardo@email.com',  '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'GERENTE', 'ATIVO', false),
(8,  'fernanda_admin','fernanda@email.com','$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMINISTRADOR','ATIVO',     false),
(9,  'lucas_user',    'lucas@email.com',   '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'FORNECEDOR',   'INATIVO',   false),
(10, 'patricia_user', 'patricia@email.com','$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'TERCEIRIZADA', 'ATIVO',     false),
(11, 'andre_admin',   'andre@email.com',   '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'PROPRIETARIO', 'BLOQUEADO', false),
(12, 'camila_user',   'camila@email.com',  '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'GERENTE',      'ATIVO',     false),
(13, 'bruno_admin',   'bruno@email.com',   '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMINISTRADOR','INATIVO',   false),
(14, 'larissa_user',  'larissa@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'TERCEIRIZADA', 'ATIVO',     false),
(15, 'diego_user',    'diego@email.com',   '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'FORNECEDOR',   'BLOQUEADO', false),
(16, 'isabela_admin', 'isabela@email.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'PROPRIETARIO', 'ATIVO',     false);

