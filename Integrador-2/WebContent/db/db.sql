
-- -----------------------------------------------------
-- Table `projeto`.`mestre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`mestre` (
  `id_mestre` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `data_nasc` TIMESTAMP NOT NULL,
  `login` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(45) NOT NULL,
  `senhanu` VARCHAR(45) NOT NULL,
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
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NULL DEFAULT 0,
  `estado` VARCHAR(45) NOT NULL,
  `banido` VARCHAR(45) NULL DEFAULT 0,
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
  `data_vinculo` DATETIME NULL,
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
  `data_fim_atividade` TIMESTAMP NOT NULL,
  `status` TINYINT(1) NULL,
  `caminho_imagem` VARCHAR(255) NULL,
  PRIMARY KEY (`usuario_id`, `atividade_id_atividade`),
  CONSTRAINT `fk_usuario_has_atividade_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `projeto`.`usuario` (`id`),
  CONSTRAINT `fk_usuario_has_atividade_atividade1`
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

INSERT INTO mestre (`id_mestre`, `nome`, `data_nasc`, `login`, `senha`, `endereco`, `email`, `ativo`, `cep`, `bairro`, `cidade`, `estado`) VALUES (1, 'Jeremy', '1990-01-21', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'rua puma, n 224', 'jeremy_rauber@live.com',1, '85860-240', 'Vila A','Foz do Iguaçu', 'PR');