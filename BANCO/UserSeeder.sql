-- Populaç?o do Banco de Dados


-- Tabela de Cliente
drop procedure if exists clientSeed;

delimiter //

create procedure clientSeed()
	Begin		
		declare j int default 1;
		while (i <= 10) do
			Insert into `cliente` (nomeCliente,cpf,rg,genero,celular,`status`)
			Values (concat('Consumidor ',j), concat('0'), concat('0'), concat('M'),concat('0'), concat('A');
			Set j = j+1;
		end while;	
	End;
 // 
Call clientSeed();
//
drop procedure if exists clientSeed; 



-- Popula Tabela usuários com 50 usuários --

drop procedure if exists userSeed;
DELIMITER //  
CREATE PROCEDURE userSeed()   
BEGIN
DECLARE i INT DEFAULT 1; 
WHILE (i <= 50) DO
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
-- fim do popula banco