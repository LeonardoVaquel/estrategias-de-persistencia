CREATE TABLE bicho (
  nombreBicho VARCHAR(255) NOT NULL ,
  especie  VARCHAR(255) NOT NULL,
  energia int ,
  
  PRIMARY KEY (nombreBicho)
)
ENGINE = InnoDB;

CREATE TABLE especie(
nombreEspecie VARCHAR (255) NOT NULL),
altura int,
peso int,
energiaInicial int,
urlFoto VARCHAR (255),
cantidadBichos int,
PRIMARY KEY (nombreEspecie)
)
ENGINE =InnoDB;
														
														
														
														