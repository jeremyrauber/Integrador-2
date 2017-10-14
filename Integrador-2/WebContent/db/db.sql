
-- -----------------------------------------------------
-- Table `projeto`.`mestre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`mestre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `data_nasc` TIMESTAMP NOT NULL,
  `login` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(255) NULL,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `ativo` TINYINT(1) NULL DEFAULT 0,
  `cep` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `data_cadastro` NOT NULL DATETIME DEFAULT CURRENT_TIMESTAMP,
  `hashValidador` varchar(255),
  `caminho_imagem` varchar(255),
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `projeto`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `data_inicio` TIMESTAMP  NOT NULL DEFAULT now(),
  `data_fim` TIMESTAMP  NOT NULL ,
  `descricao` VARCHAR(255) NULL,
  `palavra_chave` VARCHAR(45) NULL,
  `id_mestre` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_evento_mestre`
    FOREIGN KEY (`id_mestre`)
    REFERENCES `projeto`.`mestre` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `nivel` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `data_nasc` TIMESTAMP NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NULL DEFAULT 0,
  `estado` VARCHAR(45) NOT NULL,
  `banido` INT NULL DEFAULT 0,
  `data_cadastro`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`evento_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`evento_has_usuario` (
  `id_evento` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `data_vinculo` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `banido_evento` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`id_usuario`,`id_evento`),
   CONSTRAINT `fk_evento_evento` FOREIGN KEY (`id_evento`) REFERENCES `projeto`.`evento` (`id`),
   CONSTRAINT `fk_evento_usuario`FOREIGN KEY (`id_usuario`) REFERENCES `projeto`.`usuario` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`usuario_has_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`usuario_has_atividade` (
  `id_usuario` INT NOT NULL,
  `id_atividade` INT NOT NULL,
  `id_evento` INT NOT NULL,
  `data_fim_atividade` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) DEFAULT 0,
  `caminho_imagem` VARCHAR(255) NULL,
  PRIMARY KEY (`id_usuario`,`id_atividade`,`id_evento`),
   CONSTRAINT `fk_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `projeto`.`usuario` (`id`),
   CONSTRAINT `fk_atividade`FOREIGN KEY (`id_atividade`) REFERENCES `projeto`.`atividade` (`id`),
   CONSTRAINT `fk_evento` FOREIGN KEY (`id_evento`) REFERENCES `projeto`.`evento` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`evento_has_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`evento_has_atividade` (
  `id_evento` INT NOT NULL,
  `id_atividade` INT NOT NULL,
  PRIMARY KEY (`id_evento`,`id_atividade`),
  CONSTRAINT `fk_evento_atividade` FOREIGN KEY (`id_evento`) REFERENCES `projeto`.`evento` (`id`),
  CONSTRAINT `fk_atividade_atividade`FOREIGN KEY (`id_atividade`) REFERENCES `projeto`.`atividade` (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `projeto`.`bairro`
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS `projeto`.`bairro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO mestre (`id`, `nome`, `data_nasc`, `login`, `senha`, `endereco`, `email`, `ativo`, `cep`, `bairro`, `cidade`, `estado`) VALUES (1, 'Jeremy', '1990-01-21', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'rua puma, n 224', 'jeremy_rauber@live.com',1, '85860-240', 'Vila A','Foz do Iguaçu', 'PR');
INSERT INTO evento (data_fim, data_inicio, descricao, id_mestre, nome, palavra_chave, id) VALUES ('2017-09-14', '2017-09-16', ' Cacar mosquitos valentoes no bairro', 1, 'Aedes na mira', 'adeus egypt', 1);
INSERT INTO evento (data_fim, data_inicio, descricao, id_mestre, nome, palavra_chave, id) VALUES ('2017-09-21', '2017-10-16', ' Cacar mosquitos valentoes no bairro pt2', 1, 'Aedes na mira pt2', 'adeusaegypt', 2);
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (1, 'Jobelino das Coves', 'jobe',  'eb62f6b9306db575c2d596b1279627a4', 'av morenitas n 225','2005-10-12','Vilac','Foz do Iguacu', '85550-040', 'jobedascove@email.com',1,'PR',0,'2017-09-14');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (2, 'Valderrama', 'val',  'eb62f6b9306db575c2d596b1279627a4', 'av morenitas n 225','2005-10-12','Vilac','Foz do Iguacu', '85550-040', 'valderrama@email.com',1,'PR',0,'2017-09-21');
INSERT INTO atividade(id,descricao,nivel) VALUES (1,"Recolher pneus do quintal",3);
INSERT INTO atividade(id,descricao,nivel) VALUES (2,"Limpar vasos de plantas",1);
INSERT INTO atividade(id,descricao,nivel) VALUES (3,"Limpar uma piscina",2);
INSERT INTO atividade(id,descricao,nivel) VALUES (4,"Remover lixo do quintal",2);
INSERT INTO atividade(id,descricao,nivel) VALUES (5,"Adicionar areia aos vasos de plantas",1);
INSERT INTO evento_has_atividade(id_evento,id_atividade) VALUES (1,1);
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (1,1,1,0,'images/usuario1_atividade1_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (1,2,1,0,'images/usuario1_atividade2_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (1,3,1,0,'images/usuario1_atividade3_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (1,4,1,0,'images/usuario1_atividade4_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (1,5,1,1,'images/usuario1_atividade5_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,1,1,1,'images/usuario2_atividade1_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,2,1,1,'images/usuario2_atividade2_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,3,1,1,'images/usuario2_atividade3_evento1.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,1,2,1,'images/usuario2_atividade1_evento2.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,2,2,1,'images/usuario2_atividade2_evento2.JPG');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`) VALUES (2,3,2,0,'images/usuario2_atividade3_evento2.JPG');
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,1,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,1,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,2,0);


SELECT usuario.nome,id_usuario,SUM(TIMESTAMPDIFF(second,'2017-09-29',data_fim_atividade)) AS tempo_total,count(id_atividade) AS total_atividade FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id WHERE id_evento=1 and status=1 group by id_usuario order by total_atividade DESC, tempo_total ASC;