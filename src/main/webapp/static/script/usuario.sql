CREATE TABLE `usuario` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL DEFAULT '0',
	`email` VARCHAR(255) NOT NULL DEFAULT '0',
	`login` VARCHAR(150) NOT NULL DEFAULT '0',
	`senha` VARCHAR(100) NOT NULL DEFAULT '0',
	`codTipoUsuario` VARCHAR(3) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	INDEX `id` (`id`),
	INDEX `nome` (`nome`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
