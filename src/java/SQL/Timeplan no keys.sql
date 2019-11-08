drop schema Timeplan;

CREATE SCHEMA Timeplan;
use Timeplan;

CREATE TABLE IF NOT EXISTS Studieprogram (
Studieprogram VARCHAR(45) NOT NULL,
PRIMARY KEY (Studieprogram)
);

CREATE TABLE IF NOT EXISTS `emnekode m/versjon` (
Emnekomb VARCHAR(45),
EmnekombinasjonNavn VARCHAR(45) NOT NULL,
PRIMARY KEY (EmnekombinasjonNavn, Emnekomb)
);
CREATE TABLE IF NOT EXISTS `Terminnr DEFAULT` (
Terminnr INT NOT NULL,
Årstid VARCHAR(4) NOT NULL,
PRIMARY KEY (Terminnr, Årstid)
);

CREATE TABLE IF NOT EXISTS `Emne` (
Emnekode VARCHAR(45) NOT NULL,
/*Studieprogram VARCHAR(45) NOT NULL,
EmneValg VARCHAR(45) NOT NULL,
Terminnr INT NOT NULL,
Årstid VARCHAR(4) NOT NULL,*/
PRIMARY KEY (Emnekode)
);
CREATE TABLE IF NOT EXISTS `Emnekomb nivå 2` (
EmnekombinasjonNavn2 VARCHAR(45) not null,
Emnekombkomb VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn2)
);
CREATE TABLE IF NOT EXISTS `Emnekomb nivå 3` (
EmnekombinasjonNavn3 VARCHAR(45) not null,
EmnekombinasjonNavn2 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn3)
);
CREATE TABLE IF NOT EXISTS `Emnekomb nivå 4` (
EmnekombinasjonNavn4 VARCHAR(45) not null,
EmnekombinasjonNavn3 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn4)
);