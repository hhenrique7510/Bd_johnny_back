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
    fk_funcionario_cpf VARCHAR(14),
    fk_mesa_id_mesa INT NOT NULL,
    fk_pedido_id_pedido INT NOT NULL,
    status VARCHAR(100) NOT NULL,

    CONSTRAINT faz_pk PRIMARY KEY (fk_mesa_id_mesa, fk_pedido_id_pedido),
    CONSTRAINT fk_faz_garcom FOREIGN KEY (fk_funcionario_cpf) REFERENCES garcom (fk_funcionario_cpf)ON DELETE set null,
    CONSTRAINT fk_faz_mesa FOREIGN KEY (fk_mesa_id_mesa) REFERENCES mesa (id_mesa),
    CONSTRAINT fk_faz_pedido FOREIGN KEY (fk_pedido_id_pedido) REFERENCES pedido (id_pedido)
);

DELIMITER $$

CREATE TRIGGER addPontosFuncionarioDoMes
AFTER INSERT ON faz
FOR EACH ROW
BEGIN
    DECLARE valor FLOAT;
    DECLARE quantidade INT;
    DECLARE total_pontos FLOAT DEFAULT 0;
    DECLARE done INT DEFAULT 0;
    DECLARE cur CURSOR FOR
        SELECT t.qtd_produto, p.valor
        FROM tem t
        JOIN produtos p ON t.fk_produtos_id_prod = p.id_prod
        WHERE t.fk_pedido_id_pedido = NEW.fk_pedido_id_pedido;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO quantidade, valor;
        IF done THEN
            LEAVE read_loop;
        END IF;
        SET total_pontos = total_pontos + (quantidade * valor);
    END LOOP;

    CLOSE cur;

    UPDATE garcom
    SET pontos = pontos + total_pontos
    WHERE fk_funcionario_cpf = NEW.fk_funcionario_cpf;
END$$

DELIMITER ;




CREATE TABLE garcom_historico (
    id_garcom_historico INT AUTO_INCREMENT PRIMARY KEY,
    fk_funcionario_cpf VARCHAR(14),
    fk_gerente_cpf VARCHAR(14),
    pontos INT,
    nome VARCHAR(200) NOT NULL,
    id_pedido INT, -- Nova coluna para armazenar o ID do pedido
    data_exclusao DATETIME DEFAULT NOW()
);

CREATE TABLE produtos_historico (
    id_prod_historico INT AUTO_INCREMENT PRIMARY KEY,
    id_prod INT,
    nome VARCHAR(200),
    valor FLOAT,
    produtos_tipo VARCHAR(100),
    fk_nutricionista_cpf VARCHAR(14),
    data_hora DATETIME DEFAULT NOW(),
    id_pedido INT -- Nova coluna para armazenar o ID do pedido
);





INSERT INTO mesa (id_mesa)
VALUES (1);
INSERT INTO mesa (id_mesa)
VALUES (2);
INSERT INTO mesa (id_mesa)
VALUES (3);

DELIMITER $$

CREATE TRIGGER pedido_fechado
AFTER UPDATE ON faz
FOR EACH ROW
BEGIN
    IF NEW.status = 'fechado' AND OLD.status != 'fechado' THEN -- Verifica se o status mudou de algo diferente de "fechado" para "fechado"
        -- Insere os dados do garçom no histórico
        INSERT INTO garcom_historico (fk_funcionario_cpf, fk_gerente_cpf, pontos, nome, id_pedido)
        SELECT g.fk_funcionario_cpf, g.fk_gerente_cpf, g.pontos, f.nome, NEW.fk_pedido_id_pedido
        FROM garcom g
        JOIN funcionario f ON g.fk_funcionario_cpf = f.cpf
        WHERE g.fk_funcionario_cpf = NEW.fk_funcionario_cpf;

        -- Insere os dados dos produtos no histórico
        INSERT INTO produtos_historico (id_prod, nome, valor, produtos_tipo, fk_nutricionista_cpf, id_pedido)
        SELECT t.fk_produtos_id_prod, p.nome, p.valor, p.produtos_tipo, p.fk_nutricionista_cpf, NEW.fk_pedido_id_pedido
        FROM tem t
        JOIN produtos p ON t.fk_produtos_id_prod = p.id_prod
        WHERE t.fk_pedido_id_pedido = NEW.fk_pedido_id_pedido;
    END IF;
END$$

DELIMITER ;