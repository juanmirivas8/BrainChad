create schema if not exists BrainChad;
set schema BrainChad;
CREATE TABLE IF NOT EXISTS Usuario(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nickname VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL ,
    sexo BOOLEAN NOT NULL ,
    nombre VARCHAR(50) NOT NULL ,
    moneda DOUBLE NOT NULL DEFAULT 0.0,
    puntuacion DOUBLE NOT NULL DEFAULT 0.0,
    fecha_nacimiento DATE NOT NULL ,
    fecha_registro TIMESTAMP NOT NULL DEFAULT (NOW())
);
CREATE TABLE IF NOT EXISTS Pregunta(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID INT DEFAULT 0,
    titulo VARCHAR(350) NOT NULL ,
    respCorrecta VARCHAR(100) NOT NULL ,
    respInc1 VARCHAR(100) NOT NULL ,
    respInc2 VARCHAR(100) NOT NULL ,
    respInc3 VARCHAR(100) NOT NULL ,
    imagen BLOB,
    categoria VARCHAR(20),
    fecha_Creacion TIMESTAMP NOT NULL DEFAULT (NOW()),
    FOREIGN KEY (userID) REFERENCES Usuario(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Test(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID INT ,
    nombre VARCHAR(30),
    fecha_Creacion TIMESTAMP NOT NULL DEFAULT (NOW()),
    FOREIGN KEY (userID) REFERENCES Usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PreguntasTest(
    id INT PRIMARY KEY AUTO_INCREMENT,
    preguntaID INT NOT NULL,
    testID INT NOT NULL ,
    fecha_Creacion TIMESTAMP DEFAULT(NOW()),
    FOREIGN KEY (preguntaID) REFERENCES Pregunta(id) ON DELETE CASCADE ,
    FOREIGN KEY (testID) REFERENCES Test(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Amistad(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID_A INT NOT NULL ,
    userID_B INT NOT NULL ,
    status BOOLEAN,
    fecha_Aceptacion TIMESTAMP,
    fecha_envio TIMESTAMP DEFAULT(NOW()),
    FOREIGN KEY (userID_A) REFERENCES Usuario(id) ON DELETE CASCADE ,
    FOREIGN KEY (userID_B) REFERENCES Usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS TestRespondido(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    testID INT ,
    fecha_Creacion TIMESTAMP DEFAULT(NOW()),
    FOREIGN KEY (userID) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (testID) REFERENCES Test(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS PreguntaRespondida(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL ,
    testID INT NOT NULL ,
    preguntaID INT NOT NULL ,
    respuesta VARCHAR(100) ,
    fecha_Creacion TIMESTAMP NOT NULL DEFAULT(NOW()),
    FOREIGN KEY (userID) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (testID) REFERENCES TestRespondido(id) ON DELETE CASCADE,
    FOREIGN KEY (preguntaID) REFERENCES Pregunta(id) ON DELETE CASCADE
)
