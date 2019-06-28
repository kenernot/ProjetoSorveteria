SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA projetosorveteria;
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
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idUsuario` INT  NOT NULL,
  `dataPedido` DATE  NOT NULL,
  `descricaoPedido` VARCHAR(50) NULL,
  `valorTotal` DECIMAL(8,2)  NOT NULL,
  `valorDesconto` DECIMAL(8,2) NULL,
  `valorPagar` DECIMAL(8,2)  NOT NULL,
  `qtdTotal` INT  NOT NULL,
  `status` CHAR(1) NOT NULL,
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
	`idItemPedido` INT  NOT NULL AUTO_INCREMENT,
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
  `informarSaldo` DECIMAL(8,2) NULL,
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

-- Populaçao do Banco de Dados

use projetosorveteria;


drop procedure if exists clientSeed; 
drop procedure if exists produtoSeed;
drop procedure if exists userSeed;
drop procedure if exists pedidoSeed;
drop procedure if exists itemPedidoSeed;
drop procedure if exists caixaSeed;
drop procedure if exists itemCaixaSeed;
    
-- Tabela de Cliente

use projetosorveteria;

drop procedure if exists clientSeed;

delimiter //

create procedure clientSeed()
	Begin		
		declare a int default 1;
		while (a <= 9) do
			Insert into `cliente` (nomeCliente,cpf,rg,genero,celular,`status`)
			Values (concat('Consumidor ',a), concat('000.000.000-',a), concat('00.000.000-',a), concat('M'),concat('00000000000000'), concat('A'));
			Set a = a+1;
		end while;	
	End;
 // 
Call clientSeed();
//
drop procedure if exists clientSeed; 
select * from cliente;

-- Tabela Produto

use projetosorveteria;

drop procedure if exists produtoSeed;

delimiter //

create procedure produtoSeed()
	Begin 
		declare b int default 1;
        while (b <= 10) do
			Insert into `Produto` (nomeProduto, valorCompra, valorVenda, observacao, status, tipoProduto)
			Values (concat('Produto ',b), concat(0.00), concat(0.00), concat('Observaçao', b), concat('A'), concat('S'));
            Set b = b+1;
		end while;
    End;
//
Call produtoSeed();
//
drop procedure if exists produtoSeed;
select * from produto;

-- Tabela Usuario

use projetosorveteria;


drop procedure if exists userSeed;
DELIMITER //  
CREATE PROCEDURE userSeed()   
BEGIN
DECLARE i INT DEFAULT 1; 
WHILE (i <= 10) DO
	INSERT INTO `usuario` (nomeFuncionario, nomeUsuario,
			senhaUsuario,`status`,cliente,usuario,produto) 
		VALUES(concat('Administrador ',i), concat('admin',i),
			SHA2(concat('admin',i),'256'),'A','1','1','1');
    SET i = i+1;
END WHILE;
END;
//  
CALL userSeed(); 
//
drop procedure if exists userSeed;

-- Tabela pedido

use projetosorveteria;

drop procedure if exists pedidoSeed;

delimiter //

create procedure pedidoSeed()
	Begin
    Declare c int default 1;
    while (c <= 10) do 
		Insert into `pedido` (idCliente, idUsuario, dataPedido, descricaoPedido, valorTotal, valorDesconto, valorPagar, qtdTotal,status)
		Values (1,1,now(), concat('Pedido ',c), c, c, c,c,'A');
		Set c = c+1;
	end while;
    End;
    //
    call pedidoSeed();
    //
    drop procedure if exists pedidoSeed;
    select * from pedido;
    
    -- Tabela itemPedido

use projetosorveteria;

drop procedure if exists itemPedidoSeed;

delimiter //

create procedure itemPedidoSeed()
	Begin
    Declare d int default 1;
    while (d <= 10) do 
		Insert into `itemPedido` (idPedido,idProduto,qtd, valorUnitario, valorDesconto,valorTotal)
		Values (d,1,50, 0.00,0.00, 0.00);
		Set d = d+1;
	end while;
    End;
    //
    call itemPedidoSeed();
    //
    drop procedure if exists itemPedidoSeed;
    select * from itemPedido;
    
        -- Tabela Caixa

use projetosorveteria;

drop procedure if exists caixaSeed;

delimiter //

create procedure caixaSeed()
	Begin
    Declare e int default 1;
    while (e <= 10) do 
		Insert into `caixa` (idUsuario, dataAbertura, valorAbertura, valorFinal, dataFechamento)
		Values (1,now(), 100.00, 500.00, now());
		Set e = e+1;
	end while;
    End;
    //
    call caixaSeed();
    //
    drop procedure if exists caixaSeed;
    select * from caixa;
    
           -- Tabela Caixa

use projetosorveteria;

drop procedure if exists caixaSeed;

delimiter //

create procedure itemCaixaSeed()
	Begin
    Declare f int default 1;
    while (f <= 10) do 
		Insert into `itemCaixa` (idCaixa, idPedido, descricao, tipoMov, valor)
		Values (f,f,'Sorvete de Chocolate com castanha do Pará','E', 10.00);
		Set f = f+1;
	end while;
    End;
    //
    call itemCaixaSeed();
    //
    drop procedure if exists itemCaixaSeed;
    select * from itemCaixa;
    
    
select * from cliente;
select * from produto;
select * from usuario;
select * from pedido;
select * from itemPedido;
select * from itemCaixa;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
