CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE projetos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL
);

CREATE TABLE projeto_usuarios (
    projeto_id BIGINT,
    usuario_id BIGINT,
    PRIMARY KEY (projeto_id, usuario_id),
    FOREIGN KEY (projeto_id) REFERENCES projetos(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE tarefas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    status VARCHAR(50) NOT NULL,
    projeto_id BIGINT NOT NULL,
    responsavel_id BIGINT,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id),
    FOREIGN KEY (responsavel_id) REFERENCES usuarios(id)
);
