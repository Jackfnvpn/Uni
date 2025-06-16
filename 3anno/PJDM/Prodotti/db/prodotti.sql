CREATE DATABASE IF NOT EXISTS `magazzino`;
USE `magazzino`;

DROP TABLE IF EXISTS `prodotto`;

CREATE TABLE `prodotto` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `nome` VARCHAR(100) NOT NULL,
                            `quantita` INT NOT NULL,
                            `prezzo` DECIMAL(10,2) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `prodotto` (`nome`, `quantita`, `prezzo`) VALUES
                                                          ('Mouse Logitech', 15, 19.99),
                                                          ('Tastiera Meccanica', 8, 59.90),
                                                          ('Monitor 24"', 5, 129.99),
                                                          ('Cavo HDMI', 30, 4.99),
                                                          ('SSD 1TB', 10, 89.50);
