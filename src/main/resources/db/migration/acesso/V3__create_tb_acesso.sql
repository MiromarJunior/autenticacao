BEGIN;

CREATE TABLE IF NOT EXISTS sch_autenticacao.tb_acesso (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(256) NOT NULL,
    CONSTRAINT acesso_unique_nome UNIQUE (nome)
);

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (1, 'LISTAR_USUARIO_ADM', 'Listar Todos Usuários');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (2, 'EDITAR_USUARIO_ADM', 'Editar Todos Usuários');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (3, 'INCLUIR_USUARIO', 'Incluir Usuário');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (4, 'EXCLUIR_USUARIO_ADM', 'Excluir Todos Usuários');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (5, 'LISTAR_PERFIL', 'Listar Perfil');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (6, 'EDITAR_PERFIL', 'Editar Perfil');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (7, 'INCLUIR_PERFIL', 'Incluir Perfil');

INSERT INTO sch_autenticacao.tb_acesso (id, nome, descricao)
VALUES (8, 'EXCLUIR_PERFIL', 'Excluir Perfil');

COMMIT;