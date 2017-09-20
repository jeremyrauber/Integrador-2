
-- -----------------------------------------------------
-- Table `projeto`.`mestre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`mestre` (
  `id_mestre` INT NOT NULL AUTO_INCREMENT,
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
  `data_cadastro`  DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_mestre`));


-- -----------------------------------------------------
-- Table `projeto`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `data_inicio` TIMESTAMP  NOT NULL DEFAULT now(),
  `data_fim` TIMESTAMP  NOT NULL ,
  `descricao` VARCHAR(45) NULL,
  `palavra_chave` VARCHAR(45) NULL,
  `mestre_id_mestre` INT NOT NULL,
  PRIMARY KEY (`id`, `mestre_id_mestre`),
  INDEX `fk_evento_mestre_idx` (`mestre_id_mestre` ASC),
  CONSTRAINT `fk_evento_mestre`
    FOREIGN KEY (`mestre_id_mestre`)
    REFERENCES `projeto`.`mestre` (`id_mestre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`atividade` (
  `id_atividade` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `nivel` INT NOT NULL,
  PRIMARY KEY (`id_atividade`))
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
  `evento_id` INT NOT NULL,
  `evento_mestre_id_mestre` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `data_vinculo` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `banido_evento` TINYINT(1) NULL,
  PRIMARY KEY (`evento_id`, `evento_mestre_id_mestre`, `usuario_id`),
  CONSTRAINT `fk_evento_has_usuario_evento1`
    FOREIGN KEY (`evento_id` , `evento_mestre_id_mestre`)
    REFERENCES `projeto`.`evento` (`id` , `mestre_id_mestre`),
  CONSTRAINT `fk_evento_has_usuario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `projeto`.`usuario` (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`usuario_has_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`usuario_has_atividade` (
  `usuario_id` INT NOT NULL,
  `atividade_id_atividade` INT NOT NULL,
  `data_fim_atividade` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) NULL,
  `caminho_imagem` VARCHAR(255) NULL,
  PRIMARY KEY (`usuario_id`, `atividade_id_atividade`),
  CONSTRAINT `fk_usuario_has_atividade_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `projeto`.`usuario` (`id`),
  CONSTRAINT `fk_usuario_has_atividade_atividade`
    FOREIGN KEY (`atividade_id_atividade`)
    REFERENCES `projeto`.`atividade` (`id_atividade`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto`.`evento_has_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`evento_has_atividade` (
  `evento_id` INT NOT NULL,
  `evento_mestre_id_mestre` INT NOT NULL,
  `atividade_id_atividade` INT NOT NULL,
  PRIMARY KEY (`evento_id`, `evento_mestre_id_mestre`, `atividade_id_atividade`),
  CONSTRAINT `fk_evento_has_atividade_evento1`
    FOREIGN KEY (`evento_id` , `evento_mestre_id_mestre`)
    REFERENCES `projeto`.`evento` (`id` , `mestre_id_mestre`),
  CONSTRAINT `fk_evento_has_atividade_atividade1`
    FOREIGN KEY (`atividade_id_atividade`)
    REFERENCES `projeto`.`atividade` (`id_atividade`))
ENGINE = InnoDB;

ALTER TABLE mestre ADD UNIQUE (login);
ALTER TABLE evento ADD COLUMN  palavra_chave varchar(255);

INSERT INTO mestre (`id_mestre`, `nome`, `data_nasc`, `login`, `senha`, `endereco`, `email`, `ativo`, `cep`, `bairro`, `cidade`, `estado`) VALUES (1, 'Jeremy', '1990-01-21', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'rua puma, n 224', 'jeremy_rauber@live.com',1, '85860-240', 'Vila A','Foz do Iguaçu', 'PR');
INSERT INTO evento (data_fim, data_inicio, descricao, mestre_id_mestre, nome, palavra_chave, id) values     ('2017-09-14', '2017-09-16', ' Cacar mosquitos valentoes no bairro', 1, 'Aedes na mira', 'adeus egypt', 1);
INSERT INTO usuario (`id`,`nome`,`login`,`senha`,`endereco`,`data_nasc`,`bairro`, `cidade`,`cep`,`email`,`ativo`,`estado`,`banido`,`data_cadastro`) VALUES (1, 'Jobelino das Coves', 'jobe',  'eb62f6b9306db575c2d596b1279627a4', 'av morenitas n 225','2005-10-12','Vilac','Foz do Iguaçu', '85550-040', 'jobedascove@email.com',1,'PR',1,'2017-09-14');
INSERT INTO atividade(id_atividade,descricao,nivel) VALUES (1,"recolher pneus do quintal",3);
INSERT INTO atividade(id_atividade,descricao,nivel) VALUES (2,"Limpar vasos de plantas",1);
INSERT INTO evento_has_atividade(evento_id,evento_mestre_id_mestre,atividade_id_atividade) VALUES (1,1,1);
INSERT INTO usuario_has_atividade(`usuario_id`,`atividade_id_atividade`,`data_fim_atividade`,`status`,`caminho_imagem`) VALUES (1,1,'2014-10-01',0,'C:\\temp\\usuario_1_envio_1.jpeg');
INSERT INTO evento_has_usuario (evento_id,evento_mestre_id_mestre,usuario_id,banido_evento) VALUES (1,1,1,0);
