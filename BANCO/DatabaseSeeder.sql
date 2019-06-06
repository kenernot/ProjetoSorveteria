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
		Values (1,1,now(), concat('Pedido ',c), 0.00, 0.00,0.00,c,'A');
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