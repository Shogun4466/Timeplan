CREATE SCHEMA Timaplan;

CREATE TABLE IF NOT EXISTS Studieprogram (
Studieprogram VARCHAR(45) NOT NULL,
PRIMARY KEY (Studieprogram)
);
CREATE TABLE IF NOT EXISTS EmneValg (
EmneValg VARCHAR(45) NOT NULL,
PRIMARY KEY (EmneValg)
);
CREATE TABLE IF NOT EXISTS Termin (
Termin INT(11) NOT NULL,
Årstid VARCHAR(4) NOT NULL,
PRIMARY KEY (Termin, Årstid)
);

CREATE TABLE IF NOT EXISTS Emnekombinasjon (
EmnekombinasjonNavn VARCHAR(45) NOT NULL,
Studieprogram VARCHAR(45) NOT NULL,
EmneValg VARCHAR(45) NOT NULL,
Termin INT(11) NOT NULL,
Årstid VARCHAR(4) NOT NULL,
PRIMARY KEY (EmnekombinasjonNavn),
CONSTRAINT fk_emnekombinasjon_studieprogram FOREIGN KEY (Studieprogram) REFERENCES Studieprogram (Studieprogram),
CONSTRAINT fk_emnekombinasjon_emnevalg FOREIGN KEY (EmneValg) REFERENCES EmneValg (EmneValg),
CONSTRAINT fk_emnekombinasjon_termin FOREIGN KEY (Termin, Årstid) REFERENCES Termin (Termin, Årstid)
);
CREATE TABLE IF NOT EXISTS Emnekombinasjon1 (
EmnekombinasjonNavn1 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn1)
);
CREATE TABLE IF NOT EXISTS Emnekombkomb (
EmnekombinasjonNavn VARCHAR(45) not null,
EmnekombinasjonNavn1 VARCHAR(45) NOT NULL,
PRIMARY KEY (EmnekombinasjonNavn, EmnekombinasjonNavn1),
CONSTRAINT fk_emnekombinasjon_emnekombinasjon FOREIGN KEY (EmnekombinasjonNavn) REFERENCES Emnekombinasjon (EmnekombinasjonNavn),
CONSTRAINT fk_emnekombinasjon_emnekombinasjon1 FOREIGN KEY (EmnekombinasjonNavn1) REFERENCES Emnekombinasjon1 (EmnekombinasjonNavn1)
);
CREATE TABLE IF NOT EXISTS Emnekombinasjon2 (
EmnekombinasjonNavn2 VARCHAR(45) not null,
EmnekombinasjonNavn1 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn2),
CONSTRAINT fk_emnekombinasjon_emnekombinasjon2 FOREIGN KEY (EmnekombinasjonNavn1) REFERENCES Emnekombinasjon1 (EmnekombinasjonNavn1)
);
CREATE TABLE IF NOT EXISTS Emnekombinasjon3 (
EmnekombinasjonNavn3 VARCHAR(45) not null,
EmnekombinasjonNavn2 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn3),
CONSTRAINT fk_emnekombinasjon_emnekombinasjon3 FOREIGN KEY (EmnekombinasjonNavn2) REFERENCES Emnekombinasjon2 (EmnekombinasjonNavn2)
);
CREATE TABLE IF NOT EXISTS Emnekombinasjon4 (
EmnekombinasjonNavn4 VARCHAR(45) not null,
EmnekombinasjonNavn3 VARCHAR(45) not null,
PRIMARY KEY (EmnekombinasjonNavn4),
CONSTRAINT fk_emnekombinasjon_emnekombinasjon4 FOREIGN KEY (EmnekombinasjonNavn3) REFERENCES Emnekombinasjon3 (EmnekombinasjonNavn3)
);
