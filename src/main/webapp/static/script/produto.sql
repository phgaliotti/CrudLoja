CREATE TABLE `produto` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	`descricaoProduto` VARCHAR(255) NOT NULL,
	`preco` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `nome` (`nome`),
	INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=65
;
