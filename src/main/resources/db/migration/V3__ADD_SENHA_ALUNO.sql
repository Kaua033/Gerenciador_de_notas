-- Autenticação por matrícula + senha: adiciona o hash da senha do aluno
ALTER TABLE aluno ADD COLUMN senha_hash VARCHAR(255) NOT NULL;
