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
  registerDate DATE NOT NULL
);

CREATE TABLE cards (
  idCard INT(11) AUTO_INCREMENT PRIMARY KEY,
  number INT(12),
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
  name VARCHAR(50) NOT NULL,
  genre VARCHAR(50),
  duration TIME NOT NULL,
  image VARCHAR(100),
  synopsis VARCHAR(600) NOT NULL
);

CREATE TABLE projections (
  idProjection INT AUTO_INCREMENT PRIMARY KEY,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL,
  price DEC(9,2),
  occupiedNormalSeats VARCHAR(200),
  occupiedSpecialSeats VARCHAR(200),
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
-- INSERT INTO usuarios VALUES(1, 'Raúl', 'Calvo Barón', '1993-04-07', 'mperez@gmail.com', 'mperez','$2a$10$aMS69IPQGlbZYjxvcJuZGuVDvCS/qJh5JDjBajWbTvHns8DfT7tMO', 1, '2023-01-07', 2); 
-- INSERT INTO usuarios VALUES(2, 'Juan', 'Gómez', 'López', '1963-03-17', 'jgomez@gmail.com', 'jgomez','$2a$10$aMS69IPQGlbZYjxvcJuZGuVDvCS/qJh5JDjBajWbTvHns8DfT7tMO', 1,'2023-01-17', 2); 

-- INSERTAR TARJETAS
-- INSERT INTO tarjetas VALUES(1, 1544615, 'María Pérez Pérez', '2023-04-07', 485);
-- INSERT INTO tarjetas VALUES(2, 5225595, 'Daniel Ruiz Ortega', '2025-02-09', 367);

-- INSERTAR PRODUCTOS
-- INSERT INTO productos VALUES(1, 'Persona 5: Royal', 'persona-5-royal.jpg', 'Tras verse obligado a cambiarse a otro instituto en Tokio, el protagonista tiene un extraño sueño. "Eres, sin lugar a dudas, prisionero del destino. La perdición te aguarda en el futuro". Con el objetivo de su "rehabilitación" en mente, deberá ponerse la máscara de un Ladrón Fantasma para salvar el mundo de sus distorsionados deseos.', 59.99, 50, 1);
-- INSERT INTO productos VALUES(2, 'The Legend of Zelda: Breath of the Wild', 'zelda.jpg', 'La historia de la familia real de Hyrule es también la historia del cataclismo. Y la historia del cataclismo siempre ha sido la de Ganon. Descubre el enigmático pasado de esta asolada tierra, un mundo entero repleto de aventuras que espera ser explorado, y viaja como Link por bastos campos, espesos bosques y cumbres nevadas bajo el cielo abierto de Hyrule para revelar cómo la oscuridad se impuso sobre la luz.', 59.99, 100, 2);
-- INSERT INTO productos VALUES(3, 'The Last of Us', 'last-of-us-1.jpg', 'Cinco años después de su peligroso viaje a través de unos Estados Unidos devastados, Ellie y Joel se han asentado en Jackson, Wyoming. Vivir en una próspera comunidad de supervivientes les ha otorgado paz y estabilidad a pesar de la amenaza constante que suponen los peligrosos enemigos.', 19.99, 75, 1);

-- USUARIOS CON TARJETAS
-- INSERT INTO usuarios_con_tarjetas VALUES(1, 1);
-- INSERT INTO usuarios_con_tarjetas VALUES(1, 2);


-- INSERTAR PEDIDOS
-- INSERT INTO pedidos VALUES(1, '2023-01-07', 'Terminado', 1, 1, 3);

-- PRODUCTOS EN PEDIDO
-- INSERT INTO productos_en_pedido VALUES(1, 1, 59.99, 1, 1);
-- INSERT INTO productos_en_pedido VALUES(2, 1, 69.99, 1, 4);
