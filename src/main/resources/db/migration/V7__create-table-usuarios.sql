CREATE TABLE usuarios(

    id SERIAL,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    primary key(id)

);