CREATE DATABASE johnny;
use johnny;

CREATE TABLE funcionario (
	cpf VARCHAR(14) NOT NULL,
	salario FLOAT,
	celular VARCHAR(15) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	email_principal VARCHAR(200) NOT NULL,
	email_secundario VARCHAR(200),
	rua VARCHAR(200) NOT NULL,
	numero INT,
	cep INT NOT NULL,
	bairro VARCHAR(200),
	
	CONSTRAINT funcionario_pk PRIMARY KEY (cpf)
);

CREATE TABLE dependente (
    nome VARCHAR(200) NOT NULL,
    cpf_dependente VARCHAR(14) NOT NULL,
    fk_funcionario_cpf VARCHAR(14) NOT NULL,
    data_nascimento DATE,
    
    CONSTRAINT dependente_pk PRIMARY KEY (cpf_dependente),  -- Definida chave primária
    CONSTRAINT fk_funcionario_dependente FOREIGN KEY (fk_funcionario_cpf) REFERENCES funcionario (cpf)
        ON DELETE CASCADE  -- Definida ação de CASCADE para a exclusão de funcionários
);

CREATE TABLE mesa (
    id_mesa INT auto_increment,
    CONSTRAINT mesa_pk PRIMARY KEY (id_mesa)
);

CREATE TABLE garcom (
    fk_funcionario_cpf VARCHAR(14) NOT NULL,
    fk_gerente_cpf VARCHAR(14),  -- Chave estrangeira para indicar o garçom que gerencia este garçom
    pontos INT default 0,
    CONSTRAINT garcom_pk PRIMARY KEY (fk_funcionario_cpf),
    CONSTRAINT fk_garcom_funcionario FOREIGN KEY (fk_funcionario_cpf) REFERENCES funcionario (cpf)
        ON DELETE CASCADE,
    CONSTRAINT fk_garcom_gerente FOREIGN KEY (fk_gerente_cpf) REFERENCES garcom (fk_funcionario_cpf)
        ON DELETE SET NULL  -- Ação opcional, dependendo das regras de negócio
);


CREATE TABLE pedido (
    id_pedido INT auto_increment,
    data_hora DATETIME default NOW(),
    
    CONSTRAINT pedido_pk PRIMARY KEY (id_pedido)
);

CREATE TABLE nutricionista (
    fk_funcionario_cpf VARCHAR(14) NOT NULL,
    CONSTRAINT nutricionista_pk PRIMARY KEY (fk_funcionario_cpf),
    CONSTRAINT fk_nutricionista_funcionario FOREIGN KEY (fk_funcionario_cpf) REFERENCES funcionario (cpf)
        ON DELETE CASCADE
);

CREATE TABLE produtos (
    id_prod INT auto_increment,
    nome VARCHAR(200) NOT NULL,
    valor FLOAT NOT NULL,
    produtos_tipo VARCHAR(100)NOT NULL,
    fk_nutricionista_cpf VARCHAR(14),
    
    CONSTRAINT produtos_pk PRIMARY KEY (id_prod),
    CONSTRAINT fk_produtos_nutricionista FOREIGN KEY (fk_nutricionista_cpf) REFERENCES nutricionista (fk_funcionario_cpf)
);

CREATE TABLE tem (
    fk_produtos_id_prod INT NOT NULL,
    fk_pedido_id_pedido INT NOT NULL,
    qtd_produto INT NOT NULL,
    
    CONSTRAINT tem_pk PRIMARY KEY (fk_produtos_id_prod, fk_pedido_id_pedido),
    CONSTRAINT fk_tem_produtos FOREIGN KEY (fk_produtos_id_prod) REFERENCES produtos (id_prod),
    CONSTRAINT fk_tem_pedido FOREIGN KEY (fk_pedido_id_pedido) REFERENCES pedido (id_pedido)
);

CREATE TABLE faz (
    fk_funcionario_cpf VARCHAR(14) NOT NULL,
    fk_mesa_id_mesa INT NOT NULL,
    fk_pedido_id_pedido INT NOT NULL,
    status VARCHAR(100) NOT NULL,
    
    CONSTRAINT faz_pk PRIMARY KEY (fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido),
    CONSTRAINT fk_faz_garcom FOREIGN KEY (fk_funcionario_cpf) REFERENCES garcom (fk_funcionario_cpf) ON DELETE CASCADE,
    CONSTRAINT fk_faz_mesa FOREIGN KEY (fk_mesa_id_mesa) REFERENCES mesa (id_mesa),
    CONSTRAINT fk_faz_pedido FOREIGN KEY (fk_pedido_id_pedido) REFERENCES pedido (id_pedido)
);

create trigger addPontosFuncionarioDoMes
after insert on faz
for each row
begin
    update garcom
    set pontos = pontos + 1
    where fk_funcionario_cpf = new.fk_funcionario_cpf;
end;

CREATE TRIGGER inserGarcom
BEFORE INSERT ON garcom
FOR EACH ROW
BEGIN
    DECLARE gerente_cpf VARCHAR(14);

    -- Obter o cpf do gerente se houver um
    SELECT fk_funcionario_cpf INTO gerente_cpf
    FROM garcom
    WHERE fk_gerente_cpf IS NULL
    LIMIT 1;
    
    -- Definir o novo registro com o cpf do gerente se houver um gerente
    IF gerente_cpf IS NOT NULL THEN
        SET NEW.fk_gerente_cpf = gerente_cpf;
    END IF;
END;

drop trigger inserGarcom;


-- Inserindo um funcionário
INSERT INTO funcionario (cpf, salario, celular, nome, senha, email_principal, rua, numero, cep, bairro)
VALUES ('12345678901', 5000.00, '11987654321', 'João Silva', 'senha123', 'joao@exemplo.com', 'Rua dos Bobos', 0, 12345, 'Centro');

INSERT INTO funcionario (cpf, salario, celular, nome, senha, email_principal, rua, numero, cep, bairro)
VALUES ('1234567890', 5000.00, '11987654321', 'João Silva', 'senha123', 'joao@exemplo.com', 'Rua dos Bobos', 0, 12345, 'Centro');

INSERT INTO funcionario (cpf, salario, celular, nome, senha, email_principal, rua, numero, cep, bairro)
VALUES ('123456789', 5000.00, '11987654321', 'João Silva', 'senha123', 'joao@exemplo.com', 'Rua dos Bobos', 0, 12345, 'Centro');

-- Inserindo um garçom
INSERT INTO garcom (fk_funcionario_cpf)
VALUES ('109.055.154-12');

-- Inserindo uma mesa
INSERT INTO mesa (id_mesa)
VALUES (1);

-- Inserindo um pedido
INSERT INTO pedido (id_pedido, status, hora)
VALUES (1, 'Aberto', '12:00:00');

-- Relacionando garçom, mesa e pedido
INSERT INTO faz (fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido)
VALUES ('12345678901', 1, 1);

INSERT INTO pedido (id_pedido, status, hora)
VALUES (2, 'Aberto', '12:00:00');

INSERT INTO faz (fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido)
VALUES ('12345678901', 1, 2);

insert into garcom (fk_funcionario_cpf,fk_gerente_cpf)
values ('123456789',null)

INSERT INTO produtos (id_prod, nome, valor, produtos_tipo, fk_nutricionista_cpf)
VALUES
(1, 'Proteína Whey', 199.99, 'Suplemento', NULL),
(2, 'Barra de Cereal', 3.50, 'Snack', NULL),
(3, 'Multivitaminas', 59.90, 'Suplemento', NULL);


update garcom
set fk_gerente_cpf = null 
where fk_funcionario_cpf = '123456789';

update garcom
set fk_gerente_cpf = '12345678901' 
where fk_funcionario_cpf = '123456789';

insert into garcom (fk_funcionario_cpf,fk_gerente_cpf)
values ('12345678901',null)

insert into garcom (fk_funcionario_cpf,fk_gerente_cpf)
values ('1234567890',null)

insert into garcom (fk_funcionario_cpf,fk_gerente_cpf)
values ('123456789',null)

insert into mesa (id_mesa)
values ('1')

INSERT INTO mesa () VALUES ();


DELETE FROM garcom;
