USE Send_Go;

-- DROP tutto in ordine corretto
DROP TABLE IF EXISTS messaggio;
DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS spedizione;
DROP TABLE IF EXISTS ordine;
DROP TABLE IF EXISTS voci_carrello;
DROP TABLE IF EXISTS carrello;
DROP TABLE IF EXISTS corriere;
DROP TABLE IF EXISTS operatore;
DROP TABLE IF EXISTS customer;

-- customer
CREATE TABLE customer (
                          N_cliente INT PRIMARY KEY AUTO_INCREMENT,
                          Nome VARCHAR(50),
                          Cognome VARCHAR(50),
                          Numero_Telefono VARCHAR(20),
                          Email VARCHAR(100),
                          Password VARCHAR(100)
);

-- operatore
CREATE TABLE operatore (
                           ID INT PRIMARY KEY AUTO_INCREMENT,
                           Nome VARCHAR(50),
                           Cognome VARCHAR(50),
                           Email VARCHAR(100)
);

-- ordine
CREATE TABLE ordine (
                        N_ordine INT PRIMARY KEY AUTO_INCREMENT,
                        Prezzo DECIMAL(10,2),
                        N_cliente INT,
                        FOREIGN KEY (N_cliente) REFERENCES customer(N_Cliente)
);

-- corriere
CREATE TABLE corriere (
                          ID INT PRIMARY KEY AUTO_INCREMENT,
                          Nome VARCHAR(50),
                          Tariffa DECIMAL(10,2)
);

CREATE TABLE voci_carrello (
                               ID INT PRIMARY KEY AUTO_INCREMENT,
                               N_cliente INT,
                               Nome_Partenza VARCHAR(50),
                               Cognome_Partenza VARCHAR(50),
                               Nome_Destinazione VARCHAR(50),
                               Cognome_Destinazione VARCHAR(50),
                               data_ritiro DATE,
                               Cap_Mittenza VARCHAR(10),
                               Località_Mittenza VARCHAR(50),
                               Provincia_Mittenza VARCHAR(50),
                               Indirizzo_Mittenza VARCHAR(100),
                               Cap_Destinazione VARCHAR(10),
                               Località_Destinazione VARCHAR(50),
                               Provincia_Destinazione VARCHAR(50),
                               Indirizzo_Destinazione VARCHAR(100),
                               Peso DECIMAL(10,2),
                               Altezza DECIMAL(10,2),
                               Lunghezza DECIMAL(10,2),
                               Larghezza DECIMAL(10,2),
                               N_corriere INT,
                               FOREIGN KEY (N_cliente) REFERENCES customer(N_cliente),
                               FOREIGN KEY (N_corriere) REFERENCES corriere(ID)
);

CREATE TABLE spedizione (
                            ID INT PRIMARY KEY AUTO_INCREMENT,
                            Nome_Partenza VARCHAR(50),
                            Cognome_Partenza VARCHAR(50),
                            Nome_Destinazione VARCHAR(50),
                            Cognome_Destinazione VARCHAR(50),
                            data_ritiro DATE,
                            Cap_Mittenza VARCHAR(10),
                            Località_Mittenza VARCHAR(50),
                            Provincia_Mittenza VARCHAR(50),
                            Indirizzo_Mittenza VARCHAR(100),
                            Cap_Destinazione VARCHAR(10),
                            Località_Destinazione VARCHAR(50),
                            Provincia_Destinazione VARCHAR(50),
                            Indirizzo_Destinazione VARCHAR(100),
                            Peso DECIMAL(10,2),
                            Altezza DECIMAL(10,2),
                            Lunghezza DECIMAL(10,2),
                            Larghezza DECIMAL(10,2),
                            N_ordine INT,
                            FOREIGN KEY (N_ordine) REFERENCES ordine(N_ordine)
);

-- ticket
CREATE TABLE ticket (
                        ID INT PRIMARY KEY AUTO_INCREMENT,
                        N_spedizione INT,
                        N_ordine INT,
                        FOREIGN KEY (N_spedizione) REFERENCES spedizione(ID),
                        FOREIGN KEY (N_ordine) REFERENCES ordine(N_ordine)
);

-- messaggio
CREATE TABLE messaggio (
                           ID INT PRIMARY KEY AUTO_INCREMENT,
                           N_ticket INT,
                           testo TEXT,
                           mittente_tipo BOOLEAN,
                           FOREIGN KEY (N_ticket) REFERENCES ticket(ID)
);
