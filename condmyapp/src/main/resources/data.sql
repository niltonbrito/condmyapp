-- USUÁRIOS PADRÃO
INSERT INTO users (id, username, email, password, role, created_at, updated_at)
VALUES
(1, 'admin', 'admin@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMIN', NOW(), NOW()),
(2, 'sindico1', 'sindico1@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'SINDICO', NOW(), NOW()),
(3, 'morador1', 'morador1@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'MORADOR', NOW(), NOW()),
(4, 'morador2', 'morador2@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'MORADOR', NOW(), NOW()),
(5, 'prestador1', 'prestador1@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'PRESTADOR', NOW(), NOW()),
(6, 'admin2', 'admin2@sistema.com', '$2a$10$W/ZfE6I40ZASwkC1kMir2ORjfcNdo3./N4nH9Eeun1r4dZIka0mUq', 'ADMIN', NOW(), NOW());
