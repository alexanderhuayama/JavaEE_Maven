-- Crea la base de datos
DROP DATABASE IF EXISTS db_store_phone;
CREATE DATABASE db_store_phone;
USE db_store_phone;

-- Crea la tabla tb_cell_phones
DROP TABLE IF EXISTS tb_cell_phones;
CREATE TABLE tb_cell_phones(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    register_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Procedimiento almacenado para obtener la lista de celulares
DROP PROCEDURE IF EXISTS sp_get_cellphones;
DELIMITER $$
CREATE PROCEDURE sp_get_cellphones()
BEGIN
	SELECT id, brand, model, price, register_date, update_date FROM tb_cell_phones;
END $$;
DELIMITER ;

CALL sp_get_cellphones();

-- Procedimiento almacenado para registrar un celular
DROP PROCEDURE IF EXISTS sp_register_cellphone;
DELIMITER $$
CREATE PROCEDURE sp_register_cellphone(
	IN v_brand VARCHAR(100),
    IN v_model VARCHAR(100),
    in v_price DECIMAL(10,2)
)
BEGIN
	INSERT INTO tb_cell_phones (brand, model, price) VALUES (v_brand, v_model, v_price);
END $$
DELIMITER ;

CALL sp_register_cellphone('SAMSUNG','GALAXY J5',1500);

-- Procedimiento almacenado para obtener un celular por id
DROP PROCEDURE IF EXISTS sp_get_cellphone_by_id;
DELIMITER $$
CREATE PROCEDURE sp_get_cellphone_by_id(
	IN v_id INT
)
BEGIN
	SELECT id, brand, model, price, register_date, update_date FROM tb_cell_phones
    WHERE id = v_id;
END $$
DELIMITER ;

CALL sp_get_cellphone_by_id(1);

-- Procedimiento almacenado para actualizar un celular
DROP PROCEDURE IF EXISTS sp_update_cellphone;
DELIMITER $$
CREATE PROCEDURE sp_update_cellphone(
	IN v_id INT,
    IN v_brand VARCHAR(100),
    IN v_model VARCHAR(100),
    in v_price DECIMAL(10,2)
)
BEGIN
	UPDATE tb_cell_phones
    SET brand = v_brand,
		model = v_model,
        price = v_price,
        update_date = CURRENT_TIMESTAMP
	WHERE id = v_id;
END $$
DELIMITER ;

CALL sp_update_cellphone(1, 'MOTOROLA', 'MOTO Z3', 2000);

-- Procedimiento almacenado para eliminar un celular
DROP PROCEDURE IF EXISTS sp_delete_cellphone;
DELIMITER $$
CREATE PROCEDURE sp_delete_cellphone(
	IN v_id INT
)
BEGIN
	DELETE FROM tb_cell_phones WHERE id = v_id;
END $$
DELIMITER ;

CALL sp_delete_cellphone(1);