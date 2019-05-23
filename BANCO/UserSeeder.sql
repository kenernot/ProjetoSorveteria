-- popula o banco com 50 usuários diferentes
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