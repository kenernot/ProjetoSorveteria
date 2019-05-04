SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `projetosorveteria` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `projetosorveteria` ;

-- -----------------------------------------------------
-- Table `projetosorveteria`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`cliente` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nomeCliente` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(14) NULL,
  `rg` VARCHAR(20) NULL,
  `genero` CHAR(1) NULL,
  `celular` VARCHAR(14) NULL,
  `status` CHAR(1) NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE INDEX `idcliente_UNIQUE` (`idcliente` ASC),
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`produto` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(80) NOT NULL,
  `valorCompra` DECIMAL(8,2) NULL,
  `valorVenda` DECIMAL(8,2) NULL,
  `observacao` VARCHAR(50) NULL,
  `status` CHAR(1) NULL,
  `tipoProduto` CHAR(1) NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE INDEX `odProduto_UNIQUE` (`idProduto` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` VARCHAR(45) NOT NULL,
  `nomeUsuario` VARCHAR(15) NOT NULL,
  `senhaUsuario` VARCHAR(65) NOT NULL,
  `status` CHAR(1) NULL,
  `cliente` CHAR(1) NULL,
  `usuario` CHAR(1) NULL,
  `produto` CHAR(1) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`pedido` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`pedido` (
  `idpedido` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idUsuario` INT  NOT NULL,
  `dataPedido` DATE  NOT NULL,
  `descricaoPedido` VARCHAR(50) NULL,
  `valorTotal` DECIMAL(8,2)  NOT NULL,
  `valorDesconto` DECIMAL(8,2) NULL,
  `valorPagar` DECIMAL(8,2)  NOT NULL,
  `qtdTotal` INT  NOT NULL,
  PRIMARY KEY (`idpedido`),
  INDEX `fk_pedido_cliente_idx` (`idCliente` ASC),
  INDEX `fk_pedido_usuario_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_pedido_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `projetosorveteria`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `projetosorveteria`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`itemPedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`itemPedido` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`itemPedido` (
	`iditemPedido` INT  NOT NULL AUTO_INCREMENT,
	`idPedido` INT NOT NULL,
	`idProduto` INT NOT NULL,
	`qtd` DECIMAL(8,2) NOT NULL,
	`valorUnitario` DECIMAL(8,2) NOT NULL,
	`valorDesconto` DECIMAL(8,2) NULL,
	`valorTotal` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`iditemPedido`),
  UNIQUE INDEX `iditemPedido_UNIQUE` (`iditemPedido` ASC),
  INDEX `idPedido_idx` (`idPedido` ASC),
  INDEX `idProduto_idx` (`idProduto` ASC),
  CONSTRAINT `idPedido`
    FOREIGN KEY (`idPedido`)
    REFERENCES `projetosorveteria`.`pedido` (`idpedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idProduto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `projetosorveteria`.`produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`caixa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`caixa` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`caixa` (
  `idCaixa` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `dataAbertura` DATETIME NOT NULL,
  `valorAbertura` DECIMAL(8,2) NOT NULL,
  `valorFinal` DECIMAL(8,2) NULL,
  `dataFechamento` DATETIME NULL,
  PRIMARY KEY (`idCaixa`),
  UNIQUE INDEX `idcaixa_UNIQUE` (`idCaixa` ASC),
  INDEX `idUsuario_idx` (`idUsuario` ASC),
  CONSTRAINT `idUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `projetosorveteria`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetosorveteria`.`itemCaixa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projetosorveteria`.`itemCaixa` ;

CREATE TABLE IF NOT EXISTS `projetosorveteria`.`itemCaixa` (
	`idItemCaixa` INT NOT NULL AUTO_INCREMENT,	
	`idCaixa` INT NOT NULL,
	`idPedido` INT,
	`descricao` VARCHAR(100),
	`tipoMov` CHAR(1)  NOT NULL,
	`valor` DECIMAL(8,2) NOT NULL,
	PRIMARY KEY (`idItemCaixa`),
  	INDEX `idCaixa_idx` (`idCaixa` ASC),	
	CONSTRAINT `idCaixaFK`
    FOREIGN KEY (`idCaixa`)
    REFERENCES `projetosorveteria`.`Caixa` (`idCaixa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   INDEX `idPedido_idx` (`idPedido` ASC),
	CONSTRAINT `idPedidoFK`
    FOREIGN KEY (`idPedido`)
    REFERENCES `projetosorveteria`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
