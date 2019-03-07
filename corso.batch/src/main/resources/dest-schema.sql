-- DROP SCHEMA IF EXISTS `schemaname` ;  
-- -----------------------------------------------------  
-- Schema confentiweb  
-- -----------------------------------------------------  
-- CREATE SCHEMA IF NOT EXISTS `schemaname` DEFAULT CHARACTER SET utf8 ;  
-- USE `confentiweb` ;  
-- -----------------------------------------------------  
-- Table `confentiweb`.`cew_banca`  
-- -----------------------------------------------------  

CREATE TABLE IF NOT EXISTS `prodotti` (
  `id` varchar(100) DEFAULT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `prezzo` double NOT NULL
);