
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
  `data_cadastro` DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP,
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
  `banido` INT NOT NULL DEFAULT 0,
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

INSERT INTO mestre (`id`, `nome`, `data_nasc`, `login`, `senha`, `endereco`, `email`, `ativo`, `cep`, `bairro`, `cidade`, `estado`) VALUES (1, 'Jeremy', '1990-01-21', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'rua puma, n 224', 'jeremy_rauber@live.com',1, '85860-240', 'Vila A','Foz do Igua√ßu', 'PR');
INSERT INTO evento (data_fim, data_inicio, descricao, id_mestre, nome, palavra_chave, id) VALUES ('2017-11-16', '2017-10-21', ' Cacar mosquitos valentoes no bairro', 1, 'Aedes na mira', 'adeus egypt', 1);
INSERT INTO evento (data_fim, data_inicio, descricao, id_mestre, nome, palavra_chave, id) VALUES ('2017-12-16', '2017-10-22', ' Cacar mosquitos valentoes no bairro pt2', 1, 'Aedes na mira pt2', 'adeusaegypt', 2);
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (1, 'Jose', 'jose',  'eb62f6b9306db575c2d596b1279627a4', 'rua treze n 225','2005-10-12','Vila C','Foz do Iguacu', '85551-040', 'joses@email.com',1,'PR',0,'2017-09-14');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (2, 'Valdir', 'val',  'eb62f6b9306db575c2d596b1279627a4', 'av Brodoski n 105','2004-01-12','Vila A','Foz do Iguacu', '85550-040', 'valdirrama@email.com',1,'PR',0,'2017-09-21');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (3, 'Maria', 'mar',  'eb62f6b9306db575c2d596b1279627a4', 'av Silvio Americo Sasdelini n 1225','2005-10-12','Tres Bandeiras','Foz do Iguacu', '85551-140', 'mariazinha@email.com',1,'PR',0,'2017-09-22');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (4, 'Joaquim', 'quim',  'eb62f6b9306db575c2d596b1279627a4', 'Av Brasil n 1205','2005-01-12','Centro','Foz do Iguacu', '85550-540', 'joaquino@email.com',1,'PR',0,'2017-04-11');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (5, 'Marcela', 'armcela',  'eb62f6b9306db575c2d596b1279627a4', 'Rua Santos Dummond 0125','2003-09-13','Centro','Foz do Iguacu', '85551-240', 'marcela@email.com',1,'PR',0,'2017-10-04');
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (6, 'Caroline', 'caroleta',  'eb62f6b9306db575c2d596b1279627a4', 'Rua das Carolas 201','2006-06-15','Jardim das Flores','Foz do Iguacu', '85750-045', 'caarol@email.com',1,'PR',0,'2017-10-03');
INSERT INTO atividade(id,descricao,nivel) VALUES (1,"Recolher pneus do quintal",3);
INSERT INTO atividade(id,descricao,nivel) VALUES (2,"Limpar vasos de plantas",1);
INSERT INTO atividade(id,descricao,nivel) VALUES (3,"Esvaziar o resetarorio de agua da geladeira",3);
INSERT INTO atividade(id,descricao,nivel) VALUES (4,"Remover lixo do quintal",2);
INSERT INTO atividade(id,descricao,nivel) VALUES (5,"Limpar uma caixa da agua de 500 litros",1);
INSERT INTO atividade(id,descricao,nivel) VALUES (6,"Remover todos os pontos ·gua parada do quintal",2);
INSERT INTO evento_has_atividade(id_evento,id_atividade) VALUES (1,1);
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (1,1,1,0,'images/usuario1_atividade1_evento1.JPG','2017-10-21 10:00:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (1,4,1,0,'images/usuario1_atividade4_evento1.JPG','2017-10-21 10:40:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (1,5,1,0,'images/usuario1_atividade5_evento1.JPG','2017-10-21 10:50:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (2,1,1,0,'images/usuario2_atividade1_evento1.JPG','2017-10-21 11:00:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (2,1,2,0,'images/usuario2_atividade1_evento2.JPG','2017-10-22 10:40:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (2,2,2,0,'images/usuario2_atividade2_evento2.JPG','2017-10-22 10:40:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (2,3,1,0,'images/usuario2_atividade3_evento1.JPG','2017-10-21 13:40:00');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (2,3,2,0,'images/usuario2_atividade3_evento2.JPG','2017-10-22 10:40:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (3,1,1,0,'images/usuario3_atividade1_evento1.JPG','2017-10-21 16:10:05');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (4,1,1,0,'images/usuario4_atividade1_evento1.JPG','2017-10-21 10:40:15');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (5,1,1,0,'images/usuario5_atividade1_evento1.JPG','2017-10-21 19:00:55');
INSERT INTO usuario_has_atividade(`id_usuario`,`id_atividade`,`id_evento`,`status`,`caminho_imagem`,`data_fim_atividade`) VALUES (6,1,1,0,'images/usuario6_atividade1_evento1.JPG','2017-10-21 21:00:37');
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,1,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,2,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,3,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,4,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,5,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (1,6,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,1,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,2,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,3,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,4,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,5,0);
INSERT INTO evento_has_usuario (id_evento,id_usuario,banido_evento) VALUES (2,6,0);


SELECT usuario.nome,id_usuario,SUM(TIMESTAMPDIFF(second,'2017-09-29',data_fim_atividade)) AS tempo_total,count(id_atividade) AS total_atividade FROM usuario INNER JOIN usuario_has_atividade ON id_usuario=id WHERE id_evento=1 and status=1 group by id_usuario order by total_atividade DESC, tempo_total ASC;