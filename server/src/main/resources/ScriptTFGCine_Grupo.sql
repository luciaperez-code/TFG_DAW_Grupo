DROP DATABASE IF EXISTS cinemaRL;
CREATE DATABASE cinemaRL CHARACTER SET utf8mb4;
USE cinemaRL;

CREATE TABLE users (
  idUser INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  birthDate DATE NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  enabled INT(1) NOT NULL DEFAULT '1',
  registerDate DATE
);

CREATE TABLE cards (
  idCard INT(11) AUTO_INCREMENT PRIMARY KEY,
  number BIGINT,
  holderName VARCHAR(20) NOT NULL,
  expirationDate DATE NOT NULL,
  cvv INT(3) NOT NULL
);

CREATE TABLE users_cards (
  idUser INT NOT NULL,
  idCard INT NOT NULL,
  foreign key(idUser) references users(idUser),
  foreign key(idCard) references cards(idCard),
  PRIMARY KEY(idUser, idCard)
);

CREATE TABLE orders (
  idOrder INT AUTO_INCREMENT PRIMARY KEY,
  createdDate DATE NOT NULL,
  status VARCHAR(20),
  idUser INT NOT NULL,
  idCard INT NOT NULL,
  foreign key(idUser) references users(idUser),
  foreign key(idCard) references cards(idCard)
);

CREATE TABLE screens(
  idScreen INT AUTO_INCREMENT PRIMARY KEY,
  screenType VARCHAR(20),
  normalSeats INT NOT NULL,
  specialSeats INT NOT NULL
);

CREATE TABLE films(
  idFilm INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  year int,
  rated varchar(20),
  released DATE,
  runtime VARCHAR(50),
  genre VARCHAR(50),
  director VARCHAR(50),
  writer VARCHAR(50),
  actors VARCHAR(200),
  plot VARCHAR(600) NOT NULL,
  language VARCHAR(100),
  country VARCHAR(50),
  awards VARCHAR(200),
  score DOUBLE,
  comingsoon boolean,
  image VARCHAR(500)
);

CREATE TABLE projections (
  idProjection INT AUTO_INCREMENT PRIMARY KEY,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL,
  price DEC(9,2),
  occupiedNormalSeats VARCHAR(500),
  occupiedSpecialSeats VARCHAR(500),
  idFilm INT NOT NULL,
  idScreen INT NOT NULL,
  foreign key(idFilm) references films(idFilm),
  foreign key(idScreen) references screens(idScreen)
);

CREATE TABLE product_type(
  idProductType INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
  idProduct INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  image VARCHAR(100),
  description VARCHAR(600) NOT NULL,
  price DEC(9,2),
  stock INT,
  idProductType INT NOT NULL,
  foreign key(idProductType) references product_type(idProductType)
);

CREATE TABLE items_in_order (
  idItemsOrder INT AUTO_INCREMENT PRIMARY KEY,
  quantity INT,
  normalSeats VARCHAR(400),
  specialSeats VARCHAR(400),
  price DEC(9,2),
  idOrder INT NOT NULL,
  idProduct INT,
  idProjection INT,
  foreign key(idOrder) references orders(idOrder),
  foreign key(idProduct) references products(idProduct),
  foreign key(idProjection) references projections(idProjection)
);

CREATE USER cinema IDENTIFIED BY 'cinema';
grant all privileges on cinemaRL.* to cinema; 
FLUSH PRIVILEGES;

-- INSERTAR USUARIOS
-- select * from cinemaRL.users;
INSERT INTO users (idUser, name, surname, birthdate, email, password) values (1, 'Lucia', 'Perez', timestamp '2000-09-09 00:00' ,'lucia@gmail.com', '1234');-- INSERT INTO usuarios VALUES(2, 'Juan', 'Gómez', 'López', '1963-03-17', 'jgomez@gmail.com', 'jgomez','$2a$10$aMS69IPQGlbZYjxvcJuZGuVDvCS/qJh5JDjBajWbTvHns8DfT7tMO', 1,'2023-01-17', 2); 

-- INSERTAR PRODUCTOS
INSERT INTO product_type VALUES (1, 'Comida');
INSERT INTO products VALUES (1, 'Palomitas', null, 'Riquísimas palomitas saladas', 4.5, 200, 1);

--Cambios finales:
--ALTER TABLE cinemarl.cards MODIFY number bigint;