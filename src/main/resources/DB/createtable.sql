CREATE DATABASE  IF NOT EXISTS `BICHOMONGO` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `BICHOMONGO`;

DROP TABLE IF EXISTS `Especie`;

CREATE TABLE `Especie` (
  `idEspecie` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `altura` int(11) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `energiaInicial` int(11) DEFAULT NULL,
  `urlFoto` varchar(100) DEFAULT NULL,
  `cantidadBichos` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEspecie`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

LOCK TABLES `Especie` WRITE;

UNLOCK TABLES;
														
														
														
														