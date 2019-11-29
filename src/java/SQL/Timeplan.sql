drop schema Timeplan;

CREATE SCHEMA Timeplan;
use Timeplan;

CREATE TABLE IF NOT EXISTS Studieprogram (
Studieprogram VARCHAR(45) NOT NULL,
Emnekomb varchar(45) NOT NULL,
PRIMARY KEY (Studieprogram, Emnekomb)
);

CREATE TABLE IF NOT EXISTS `emnekode m/versjon` (
Emnekomb VARCHAR(45),
EmnekombinasjonNavn VARCHAR(45) NOT NULL,
PRIMARY KEY (EmnekombinasjonNavn, Emnekomb)
);
CREATE TABLE IF NOT EXISTS `Terminnr DEFAULT` (
Terminnr VARCHAR(2) NOT NULL,
Studieprogram VARCHAR(45) NOT NULL,
PRIMARY KEY (Terminnr, Studieprogram)
);

CREATE TABLE IF NOT EXISTS `TERMINNR_RELATIV_START2` (
Terminnr VARCHAR(2) NOT NULL,
Studieprogram VARCHAR(45) NOT NULL,
PRIMARY KEY (Terminnr, Studieprogram)
);

CREATE TABLE IF NOT EXISTS `Emnekombinasjon` (
Studieprogram VARCHAR(45) NOT NULL,
Emnekombkomb varchar(45) NOT NULL,
/*EmnevalgKode VARCHAR(45) NOT NULL,
Terminnr INT NOT NULL,
Årstid VARCHAR(4) NOT NULL,*/
PRIMARY KEY (Studieprogram, emnekombkomb)
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

CREATE TABLE IF NOT EXISTS `EMNEVALGSTATKODE` (
EmnevalgKode VARCHAR(20) NOT NULL,
Emnekombkomb VARCHAR(45) NOT NULL,
PRIMARY KEY (EmnevalgKode, Emnekombkomb)
);
CREATE TABLE IF NOT EXISTS `Semester` (
Årstid VARCHAR(6) NOT NULL,
Emnekombkomb VARCHAR(45) NOT NULL,
PRIMARY KEY (Årstid, Emnekombkomb)
);