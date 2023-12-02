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

INSERT INTO `users` VALUES (1,'Yolanda','Calvo','2001-09-09','yoli@gmail.com','$2a$10$a40K6BRMcnVJmW.StO5Gwed4uz74M8TcJ3cHvpEAqFdvrXXOgT0iy',1,'2023-11-09'),(2, 'Juan', 'Gómez López', '1963-03-17', 'jgomez@gmail.com','$2a$10$aMS69IPQGlbZYjxvcJuZGuVDvCS/qJh5JDjBajWbTvHns8DfT7tMO',1,'2023-11-09');

CREATE TABLE cards (
  idCard INT(11) AUTO_INCREMENT PRIMARY KEY,
  number BIGINT,
  holderName VARCHAR(20) NOT NULL,
  expirationDate DATE NOT NULL,
  cvv INT(3) NOT NULL
);

INSERT INTO `cards` VALUES (1,5540500001000004,'Yolanda Calvo','2025-12-31',456),(2,5540500001009999,'Sergio Ruiz','2025-12-31',4711);


CREATE TABLE users_cards (
  idUser INT NOT NULL,
  idCard INT NOT NULL,
  foreign key(idUser) references users(idUser),
  foreign key(idCard) references cards(idCard),
  PRIMARY KEY(idUser, idCard)
);

INSERT INTO `users_cards` VALUES (1,1);


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

INSERT INTO `screens` VALUES (1,'2D',50,8),(2,'3D',60,10);

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
  images VARCHAR(500)
);

INSERT INTO `films` VALUES (
  NULL,
  'El Rey León',
  1994,
  NULL,
  '1994-01-01',
  '100min',
  'Cartoon',
  'Rob Minkoff, Roger Allers',
  'Walt Disney',
  'Simba, Mufasa, Nala, Scar, Zazú',
  'Ambientada en la sabana africana donde ha nacido el futuro rey. Simba idolatra a su padre, el rey Mufasa, y se toma muy en serio su propio destino real. Pero en el reino no todos celebran la llegada del nuevo cachorro. Scar, el hermano de Mufasa y antiguo heredero al trono, tiene sus propios planes. La batalla por Pride Rock está asolada por la traición, la tragedia y el drama, y termina con el exilio de Simba. Con la ayuda de un curioso par de nuevos amigos, Simba tendrá que aprender a madurar y a recuperar lo que le pertenece por derecho.',
  'Español',
  'Estados Unidos',
  '',
  4.55,
  0,
  'http://image.tmdb.org/t/p/w500//sKCr78MXSLixwmZ8DyJLrpMsd15.jpg'
);
INSERT INTO `films` VALUES (
  NULL,
  'El Padrino',
  1972,
  'R',
  '1972-03-14',
  '175min',
  'Crimen, Drama',
  'Francis Ford Coppola',
  'Mario Puzo, Francis Ford Coppola',
  'Marlon Brando, Al Pacino, James Caan',
  'La historia se centra en la poderosa familia de mafiosos italianos de Nueva York, liderada por Don Vito Corleone. Cuando se desata la violencia entre las familias rivales, Michael Corleone, el hijo más joven, se ve obligado a tomar las riendas del imperio criminal de su padre.',
  'Español',
  'Estados Unidos',
  '3 Premios de la Academia',
  4.8,
  0,
  'http://image.tmdb.org/t/p/w500//3bhkrj58Vtu7enYsRolD1fZdja1.jpg'
);
INSERT INTO `films` VALUES (
  NULL,
  'Titanic',
  1997,
  'PG-13',
  '1997-11-18',
  '195min',
  'Drama, Romance',
  'James Cameron',
  'James Cameron',
  'Leonardo DiCaprio, Kate Winslet, Billy Zane',
  'La historia de amor épica entre Jack y Rose, dos jóvenes de diferentes clases sociales, a bordo del famoso y desafortunado transatlántico Titanic.',
  'Español',
  'Estados Unidos',
  '11 Premios de la Academia',
  4.7,
  0,
  'http://image.tmdb.org/t/p/w500//9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg'
);
INSERT INTO `films` VALUES (
  NULL,
  'El Laberinto del Fauno',
  2006,
  'R',
  '2006-05-27',
  '118min',
  'Drama, Fantasía',
  'Guillermo del Toro',
  'Guillermo del Toro',
  'Ivana Baquero, Sergi López, Maribel Verdú',
  'En la España posguerra, una niña descubre un laberinto mágico que la lleva a un fauno. Con el tiempo, descubre su verdadera identidad y debe enfrentarse a desafíos sobrenaturales para cumplir con su destino.',
  'Español',
  'España, México, Estados Unidos',
  '3 Premios de la Academia',
  4.6,
  0,
  'http://image.tmdb.org/t/p/w500//7PurMm0gUOJZ8Uk0oyuZlt1CKfJ.jpg'
);
INSERT INTO `films` VALUES (
  NULL,
  'Y tu mamá también',
  2001,
  'R',
  '2001-06-08',
  '106min',
  'Aventura, Comedia, Drama',
  'Alfonso Cuarón',
  'Carlos Cuarón, Alfonso Cuarón',
  'Maribel Verdú, Gael García Bernal, Diego Luna',
  'Dos adolescentes hacen un viaje por carretera con una mujer mayor. A medida que exploran la vida y la sexualidad, descubren verdades sobre ellos mismos y su relación con el mundo adulto.',
  'Español',
  'México',
  'Premio del Festival de Cine de Venecia',
  4.4,
  0,
  'http://image.tmdb.org/t/p/w500//izw41PkE8y891yraO6fN1tCNmOG.jpg'
);
INSERT INTO `films` VALUES (
  NULL,
  'El secreto de sus ojos',
  2009,
  'R',
  '2009-08-13',
  '129min',
  'Crimen, Drama, Misterio',
  'Juan José Campanella',
  'Eduardo Sacheri, Juan José Campanella',
  'Ricardo Darín, Soledad Villamil, Pablo Rago',
  'Un oficial de la ley jubilado intenta resolver un caso de asesinato no resuelto mientras reflexiona sobre su vida pasada y su relación con una compañera de trabajo.',
  'Español',
  'Argentina, España',
  'Premio de la Academia a la Mejor Película Extranjera',
  4.5,
  0,
  'http://image.tmdb.org/t/p/w500//dkeAwfZzwL3WvToydE3CXiY80E0.jpg'
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

INSERT INTO `projections` VALUES (5,'2023-11-30','2023-11-30',6.50,'[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]','[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]',1,2);


CREATE TABLE product_type(
  idProductType INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO `product_type` VALUES (1,'Comida'),(2,'Merchandising');

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

INSERT INTO `products` VALUES (1,'Palomitas',NULL,'Palomitas saladas',4.50,200,1),(2,'Toblerone',NULL,'Ñamyyy chocolate',2.50,0,1);


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