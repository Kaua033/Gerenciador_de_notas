CREATE TYPE role_usuario AS ENUM ('PROFESSOR', 'ALUNO', 'RESPONSAVEL');
CREATE TYPE situacao_final AS ENUM ('APROVADO', 'RECUPERACAO', 'REPROVADO', 'CURSANDO');

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    role role_usuario NOT NULL DEFAULT 'ALUNO',
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ano_letivo INT NOT NULL
);

CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    usuario_id INT UNIQUE REFERENCES usuario(id) ON DELETE SET NULL,
    turma_id INT NOT NULL REFERENCES turma(id) ON DELETE RESTRICT,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    matricula VARCHAR(30) UNIQUE NOT NULL,
    data_nascimento DATE,
    telefone VARCHAR(20),
    email VARCHAR(150),
    foto_url VARCHAR(255),
    nome_pai VARCHAR(150),
    telefone_pai VARCHAR(20),
    nome_mae VARCHAR(150),
    telefone_mae VARCHAR(20),
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    usuario_id INT UNIQUE REFERENCES usuario(id) ON DELETE SET NULL,
    nome VARCHAR(150) NOT NULL,
    registro VARCHAR(30) UNIQUE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT
);

CREATE TABLE unidade (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    ordem SMALLINT NOT NULL,
    ano_letivo INT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    UNIQUE (ano_letivo, ordem)
);

CREATE TABLE turma_disciplina_professor (
    id SERIAL PRIMARY KEY,
    turma_id INT NOT NULL REFERENCES turma(id) ON DELETE CASCADE,
    disciplina_id INT NOT NULL REFERENCES disciplina(id) ON DELETE CASCADE,
    professor_id INT NOT NULL REFERENCES professor(id) ON DELETE RESTRICT,
    ano_letivo INT NOT NULL,
    UNIQUE (turma_id, disciplina_id, ano_letivo)
);

CREATE TABLE nota (
    id SERIAL PRIMARY KEY,
    aluno_id INT NOT NULL REFERENCES aluno(id) ON DELETE CASCADE,
    disciplina_id INT NOT NULL REFERENCES disciplina(id) ON DELETE CASCADE,
    unidade_id INT NOT NULL REFERENCES unidade(id) ON DELETE CASCADE,
    valor NUMERIC(4,2) NOT NULL CHECK (valor >= 0 AND valor <= 10),
    observacao TEXT,
    lancado_por INT REFERENCES professor(id) ON DELETE SET NULL,
    lancado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (aluno_id, disciplina_id, unidade_id)
);

CREATE INDEX idx_nota_aluno ON nota(aluno_id);
CREATE INDEX idx_nota_disciplina ON nota(disciplina_id);
CREATE INDEX idx_nota_unidade ON nota(unidade_id);