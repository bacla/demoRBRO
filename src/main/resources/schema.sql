CREATE TABLE  account (
                               id   INTEGER      NOT NULL AUTO_INCREMENT,
                               iban VARCHAR(128) NOT NULL,
                               currency INTEGER NOT NULL,
                               balance DECIMAL NOT NULL,
                               last_update_date DATE NOT NULL,
                               PRIMARY KEY (id)
);
