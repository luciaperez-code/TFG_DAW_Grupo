DROP DATABASE IF EXISTS cinemaRL;
CREATE DATABASE cinemaRL CHARACTER SET utf8mb4;
USE cinemaRL;

CREATE TABLE roles (
  id_role INT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users (
  idUser INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  birthDate DATE NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(300) NOT NULL,
  enabled INT(1) NOT NULL DEFAULT '1',
  registerDate DATE,
  id_role INT DEFAULT 2,
  FOREIGN KEY (id_role) REFERENCES roles(id_role)
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
ALTER TABLE orders MODIFY COLUMN idCard INT NULL;

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
  rated varchar(50),
  released DATE,
  runtime VARCHAR(50),
  genre VARCHAR(50),
  director VARCHAR(50),
  writer VARCHAR(50),
  actors VARCHAR(400),
  plot VARCHAR(700) NOT NULL,
  language VARCHAR(100),
  country VARCHAR(50),
  awards VARCHAR(200),
  score DOUBLE,
  comingsoon boolean,
  image VARCHAR(500)
);

CREATE TABLE projections (
  idProjection INT AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  startTime TIME NOT NULL,
  endTime TIME NOT NULL,
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
  idRelatedFilm INT,
  foreign key(idProductType) references product_type(idProductType),
  FOREIGN KEY (idRelatedFilm) REFERENCES films(idFilm)
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


-- INSERTO ROLES
INSERT INTO roles (id_role, name) VALUES (1, 'ADMIN'), (2, 'CLIENT');

-- INSERTAR USUARIOS
INSERT INTO users (name, surname, birthdate, email, password, registerDate) values ('Lucia', 'Perez', timestamp '1997-09-26 00:00' ,'lucia@gmail.com', '$2a$10$Q9Ki2SxlFL6ZnUQXPUKBMe/u4Q1Q8lf6gOMMssglyuL54nU3DltX6', now());
INSERT INTO users (name, surname, birthdate, email, password, registerDate, id_role) values ('Felix', 'De Pablo', timestamp '1990-01-09 00:00' ,'felix@gmail.com', '$2a$10$Q9Ki2SxlFL6ZnUQXPUKBMe/u4Q1Q8lf6gOMMssglyuL54nU3DltX6', timestamp '2024-04-23 00:00', 1);
INSERT INTO users (name, surname, birthdate, email, password, registerDate) values ('María', 'López', timestamp '1977-02-11 00:00' ,'maria@gmail.com', '$2a$10$Q9Ki2SxlFL6ZnUQXPUKBMe/u4Q1Q8lf6gOMMssglyuL54nU3DltX6', now());
INSERT INTO users (name, surname, birthdate, email, password, registerDate) values ('Ana', 'García', timestamp '1967-09-19 00:00' ,'ana@gmail.com', '$2a$10$Q9Ki2SxlFL6ZnUQXPUKBMe/u4Q1Q8lf6gOMMssglyuL54nU3DltX6', timestamp '2024-05-25 00:00');
INSERT INTO users (name, surname, birthdate, email, password, registerDate) values ('Sergio', 'Ruiz', timestamp '1997-12-31 00:00' ,'sergio@gmail.com', '$2a$10$Q9Ki2SxlFL6ZnUQXPUKBMe/u4Q1Q8lf6gOMMssglyuL54nU3DltX6', timestamp '2024-03-23 00:00');

-- INSERTAR TARJETAS
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('4590830100494019', 'Petra Rodriguez', '2025-07-10', '111');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('5540500001202004', 'Lucía Pérez', '2027-11-12', '222');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('5577507001222004', 'Ana Pérez Rodriguez', '2026-08-19', '333');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('4599830185494019', 'Petra Moreno', '2026-05-22', '444');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('1239830100494017', 'Félix de Pablo', '2028-03-29', '555');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('6599850100494019', 'Javier Rodriguez', '2024-02-09', '666');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('7684830100494013', 'Alejandro Sanz', '2025-01-10', '777');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('8099830100454012', 'Alberto Rodriguez', '2026-09-03', '888');
INSERT INTO `cinemarl`.`CARDS` (`number`, `holderName`, `expirationDate`, `cvv`) VALUES ('3729830100194011', 'Petra Saiz', '2027-12-10', '999');

-- INSERT USER_CARDS
INSERT INTO `cinemarl`.`USERS_CARDS` (`idUser`, `idCard`) VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4');
INSERT INTO `cinemarl`.`USERS_CARDS` (`idUser`, `idCard`) VALUES ('2', '5'), ('2', '6');
INSERT INTO `cinemarl`.`USERS_CARDS` (`idUser`, `idCard`) VALUES ('3', '7'), ('3', '8');
INSERT INTO `cinemarl`.`USERS_CARDS` (`idUser`, `idCard`) VALUES ('4', '9');

-- INSERT SCREENS
INSERT INTO `cinemarl`.`SCREENS` (`screenType`, `normalSeats`, `specialSeats`) VALUES ('2D', '50', '8'), ('2D', '60', '10'), ('3D', '50', '8'), ('3D', '60', '10'), ('2D', '50', '8'), ('3D', '60', '10');

-- INSERT FILMS
INSERT INTO `cinemarl`.`films` (`title`, `year`, `rated`, `released`, `runtime`, `genre`, `director`, `writer`, `actors`, `plot`, `language`, `country`, `awards`, `score`, `comingsoon`, `image`)
VALUES  ('El reino del planeta de los simios', '2024', 'No recomendada para menores de 12 años', '2024-04-10', '145 min', 'Ciencia ficción', 'Wes Ball', 'null', 'Kevin Durand, Freya Allan, Peter Macon, Owen Teague, William H. Macy', 'Ambientada varias generaciones en el futuro tras el reinado de César, en la que los simios son la especie dominante que vive en armonía y los humanos se han visto reducidos a vivir en la sombra. Mientras un nuevo y tiránico líder simio construye su imperio, un joven simio emprende un angustioso viaje que le llevará a cuestionarse todo lo que sabe sobre el pasado y a tomar decisiones que definirán el futuro de simios y humanos por igual.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Reino-planeta-de-simios.jpg'),
		('El especialista', '2024', 'No recomendada para menores de 12 años', '2024-04-26', '126 min', 'Acción, comedia', 'David Leitch', 'null', 'Stephanie Hsu, Aaron Taylor-Johnson, Winston Duke, Ryan Gosling, Emily Blunt, Teresa Palmer', 'Es doble de acción y, como todo el mundo en la comunidad de dobles de acción, explota, recibe disparos, se estrella, atraviesa ventanas y cae desde las mayores alturas, todo para nuestro entretenimiento. Y ahora, recién salido de un accidente que casi acaba con su carrera, este héroe de clase trabajadora tiene que localizar a una estrella de cine desaparecida, resolver una conspiración e intentar recuperar al amor de su vida sin dejar de hacer su trabajo diario. ¿Qué podría salir bien?', 'Español', 'Estados Unidos', NULL, '0', '0', 'el_especialista.jpg'),
        ('Garfield', '2024', 'PC', '2024-05-01', '101 min', 'Animación', 'Mark Dindal', 'null', 'Chris Pratt, Ving Rhames, Samuel L. Jackson, Brett Goldstein, Cecily Strong, Hannah Waddingham, Bowen Yang, Harvey Guillén, Nicholas Hoult', 'El mundialmente famoso Garfield, el gato casero que odia los lunes y que adora la lasaña, está a punto de vivir una aventura ¡en el salvaje mundo exterior! Tras una inesperada reunión con su perdido padre –el desaliñado gato callejero Vic–, Garfield y su amigo canino Odie se ven forzados a abandonar sus perfectas y consentidas vidas al unirse a Vic en un muy arriesgado atraco.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Garfield.jpg'),
		('Cazafantasmas: imperio helado', '2024', 'No recomendada para menores de 7 años', '2024-03-22', '115 min', 'Comedia, aventura', 'Gil Kenan', 'null', 'Dan Aykroyd, Paul Rudd, McKenna Grace, Finn Wolfhard, Carrie Coon, Annie Potts, Ernie Hudson, Celeste O’Connor, Patton Oswalt, Logan Kim, Kumail Nanjiani', 'Después de los eventos de Oklahoma, el equipo de Cazafantasmas regresa a donde comenzó todo: ¡Nueva York! La historia de la familia Spengler continúa con un nuevo grupo de Cazafantasmas.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Cazafantasmas.jpg'),
        ('Civil war', '2024', 'No recomendada para menores de 18 años', '2024-04-19', '109 min', 'Acción, drama', 'Alex Garland', 'null', 'Stephen McKinley Henderson, Cailee Spaeny, Kirsten Dunst, Wagner Moura', 'En un futuro cercano, donde América está sumida en una cruenta guerra civil, un equipo de periodistas y fotógrafos de guerra emprenderá un viaje por carretera en dirección a Washington DC. Su misión: llegar antes de que las fuerzas rebeldes asalten la Casa Blanca y arrebaten el control al presidente de Estados Unidos.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Civil-war.jpg'),
        ('Godzila vs Kong', '2024', 'PC', '2024-03-27', '116 min', 'Acción, aventura', 'Adam Wingard', 'null', 'Brian Tyree Henry, Rebecca Hall, Dan Stevens, Fala Chen, Kaylee Hottle, Alex Ferns', 'Una aventura cinematográfica completamente nueva, que enfrentará al todopoderoso Kong y al temible Godzilla contra una colosal amenaza desconocida escondida dentro de nuestro mundo. La nueva y épica película profundizará en las historias de estos titanes, sus orígenes y los misterios de Isla Calavera y más allá, mientras descubre la batalla mítica que ayudó a forjar a estos seres extraordinarios y los unió a la humanidad para siempre.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Godzila-vs-Kong.jpg'),
        ('Amigos imaginarios', '2024', 'PC', '2024-05-17', '104 min', 'Ciencia ficción', 'John Krasinski', 'null', 'Bradley Cooper, Fiona Shaw, Richard Jenkins, John Krasinski, Sam Rockwell, Awkwafina , Steve Carell, Matt Damon, Blake Lively, George Clooney, Keegan-Michael Key, Sebastian Maniscalco, Emily Blunt, Phoebe Waller-Bridge, Maya Rudolph, Ryan Reynolds, Christopher Meloni, Jon Stewart, Amy Schumer, Matthew Rhys, Liza Colon-Zayas, Cailey Fleming, Alan Kim, Louis Gossett Jr.', 'Una niña pasa por una experiencia difícil y entonces empieza a ver a los amigos imaginarios de todo el mundo que se han ido quedando atrás a medida que sus amigos de la vida real han ido creciendo.', 'Español', 'Estados Unidos', NULL, '0', '0', 'Amigos_imaginarios.jpg'),
    	('Dune: Parte dos', '2024', 'No recomendada para menores de 12 años', '2024-03-01', '166 min', 'Acción, aventura', 'Denis Villeneuve', 'null', 'Zendaya , Charlotte Rampling, Javier Bardem, Florence Pugh, Timothée Chalamet, Rebecca Ferguson, Josh Brolin, Stephen McKinley Henderson, Dave Bautista, Léa Seydoux, Austin Butler, Tim Blake Nelson, Christopher Walken, Stellan Skarsgard, Souheila Yacoub', 'Tras los sucesos de la primera parte acontecidos en el planeta Arrakis, el joven Paul Atreides se une a la tribu de los Fremen y comienza un viaje espiritual y marcial para convertirse en mesías, mientras intenta evitar el horrible pero inevitable futuro que ha presenciado: una Guerra Santa en su nombre, que se extiende por todo el universo conocido... Secuela de Dune (2021).', 'Español', 'Estados Unidos', NULL, '0', '0', 'Dune2.jpg'),
        ('La mujer dormida', '2024', 'No recomendada para menores de 16 años', '2024-05-31', '108 min', 'Terror, thriller', 'Laura Alvea', 'null', 'Almudena Amor, Alicia Lobo, Javier Rey, Fran Torres, Amanda Goldsmith, Angelo Olivier', '    Ana (Almudena Amor), auxiliar de enfermería, comienza a sentirse atraída por Agustín (Javier Rey), el marido de una mujer en estado vegetativo a la que ella cuida. Es entonces cuando empieza a ser acosada por extraños fenómenos paranormales que parecen tratar de echarla de la casa y separarla de Agustín.', 'Español', 'España', NULL, '0', '0', 'la-mujer-dormida.jpg'),
        ('Furiosa: de la saga Mad Max', '2024', 'No recomendada para menores de 16 años', '2024-05-24', '145 min', 'Acción', 'George Miller', 'null', 'Anya Taylor-Joy, Chris Hemsworth, Tom Burke, Alyla Browne', 'Al caer el mundo, la joven Furiosa es arrebatada del Lugar Verde de Muchas Madres y cae en manos de una horda de motoristas liderada por el Señor de la Guerra, Dementus. Arrasando el Páramo, se topan con la Ciudadela, presidida por Inmortal Joe. Mientras los dos tiranos luchan por el dominio, Furiosa debe sobrevivir a muchas pruebas mientras reúne los medios para encontrar el camino de vuelta a casa. Precuela de Mad Max: Furia en la carretera (2015)', 'Español', 'Australia', NULL, '0', '0', 'furiosa.jpg'),
        ('Del revés 2 (Inside out 2)', '2024', 'Pendiente', '2024-06-19', '100 min', 'Animación, comedia', 'Kelsey Mann', 'null', 'Tony Hale, Maya Hawke, Ayo Edebiri, Adèle Exarchopoulos, Amy Poehler, Lewis Black, Phyllis Smith, Liza Lapira, Paul Walter Hauser', 'Secuela de la película Del revés (Inside Out), ganadora del Óscar en 2016 a Mejor filme de animación. Alegría, Tristeza, Ira, Asco y Miedo están de vuelta. Pero ahora, en plena adolescencia, están pasando muchas cosas dentro del cuerpo de Riley. La niña que conocimos ahora es una chica que se está haciendo mayor y sus emociones serán un cóctel explosivo. En esta nueva aventura que tiene lugar dentro de la mente de Riley aparecerán nuevas emociones que lo cambiarán todo, como Ansiedad, Envidia, Hastío o Vergüenza. Estas nuevas emociones llegarán al centro de control y lo pondrán todo patas arriba, así que Alegría y su equipo van a tener mucho trabajo.', 'Español', 'Estados Unidos', NULL, '0', '0', 'inside_out_2.jpg'),
		('Deadpool y Lobezno', '2024', 'Pendiente', '2024-07-25', '130 min', 'Ciencia ficción, acción', 'Shawn Levy', 'null', 'Karan Soni, Rob Delaney, Ryan Reynolds, Matthew Macfadyen, Hugh Jackman, Morena Baccarin, Leslie Uggams, Emma Corrin', 'Tercera entrega de la saga "Deadpool", ahora integrada en el Universo Cinematográfico de Marvel (MCU) pero manteniendo su enfoque para adultos, con calificación R. En septiembre de 2022 se confirmó la aparición de Hugh Jackman como Lobezno, por primera vez desde "Logan".', 'Español', 'Estados Unidos', NULL, '0', '0', 'Deadpool-y-lobezno.jpg');

-- INSERT PRODUCT TYPES
INSERT INTO product_type VALUES (1, 'Comida'), (2, 'Merchandising');

-- INSERT PRODUCTS
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Palomitas pequeñas', 'Palomitas_pequeña', 'Riquísimas palomitas saladas tamaño pequeño', '4.50', '187', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Toblerone', 'toblerone', 'Ñamyyy chocolate', '2.50', '200', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Palomitas grandes', 'Palomitas_grande', 'Palomitas saladas tamaño grande', '7.00', '200', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Palomitas grandes con 2 bebidas', 'Palomitasgrandes_2bebidas', 'Palomitas saladas tamaño grande con dos bebidas grandes', '14.00', '0', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Chucherías', 'chuches', 'Paquete Cinema Mix Fini, 250g', '3.00', '0', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Combo palomitas con bebida y perrito', 'Combo_perrito', 'Combo especial: palomitas grandes, bebida grande y perrito caliente recién hecho', '12.00', '0', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Peluche Garfield bebé', 'Peluche_Garfield', 'Adorable peluche suave de Garfield bebé, apto para niños a partir de 3 años', '9.99', '50', '2', '3');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Totebag Garfield', 'Totebag_Garfield', 'Tamaño: 50 x 70cm', '7.99', '80', '2', '3');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Póster Garfield: la película', 'Poster_garfieldPelicula', 'Tamaño: 1m x 70cm', '6.99', '100', '2', '3');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Póster Reino del planeta de los Simios', 'Poster_reinoPlanetaSimios', 'Tamaño: 1m x 70cm', '6.99', '70', '2', '1');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Póster Furiosa', 'Poster-Furiosa', 'Tamaño: 1m x 70cm', '6.99', '80', '2', '10');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`, `idRelatedFilm`) VALUES ('Poster Dune 2', 'Poster-dune2', 'Tamaño: 1m x 70cm', '6.99', '80', '2', '8');
INSERT INTO `cinemarl`.`products` (`name`, `image`, `description`, `price`, `stock`, `idProductType`) VALUES ('Gafas 3D', 'gafas-3d', 'Válido para ver todas nuestras películas 3D', '2.99', '280', '2');

-- INSERT PROJECTIONS 50 NORMAL y 8 SPECIAL 
-- SCREEN 1
INSERT INTO `cinemarl`.`PROJECTIONS` (`date`, `startTime`, `endTime`, `price`, `occupiedNormalSeats`, `occupiedSpecialSeats`, `idFilm`, `idScreen`) 
	VALUES ('2024-06-03', '18:30', '20:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '7', '1'),
			('2024-06-03', '21:00', '22:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '9', '1'),
			('2024-06-04', '18:30', '20:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '7', '1'),
			('2024-06-04', '21:00', '22:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '9', '1'),
			('2024-06-05', '18:30', '20:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '7', '1'),
			('2024-06-05', '21:00', '22:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '9', '1'),
			('2024-06-06', '18:30', '20:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '7', '1'),
			('2024-06-06', '21:00', '22:30', '6.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 0, 1]', '9', '1'),
-- SCREEN 3 (3D)
			('2024-06-03', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 1, 1]', '6', '3'),
			('2024-06-03', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 0, 0, 0, 0, 0, 0, 1]', '6', '3'),
			('2024-06-04', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 1, 0, 0, 0, 0, 0, 1]', '5', '3'),
			('2024-06-04', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 1, 1, 1]', '5', '3'),
			('2024-06-05', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 1, 0, 0, 0, 0, 1]', '6', '3'),
			('2024-06-05', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 1, 0, 0, 0, 0, 1, 1]', '6', '3'),
			('2024-06-06', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 1, 1]', '5', '3'),
			('2024-06-06', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 1, 1, 0, 1, 1]', '5', '3'),
-- SCREEN 5 (2D)
			('2024-06-03', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 1, 1]', '3', '5'),
			('2024-06-03', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 0, 0, 0, 0, 0, 0, 1]', '4', '5'),
			('2024-06-04', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 1, 0, 0, 0, 0, 0, 1]', '3', '5'),
			('2024-06-04', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 1, 1, 1]', '4', '5'),
			('2024-06-05', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 1, 0, 0, 0, 0, 1]', '3', '5'),
			('2024-06-05', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[1, 1, 0, 0, 0, 0, 1, 1]', '4', '5'),
			('2024-06-06', '18:30', '20:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 0, 0, 0, 1, 1]', '3', '5'),
			('2024-06-06', '21:00', '22:30', '8.50', '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]', '[0, 0, 0, 1, 1, 0, 1, 1]', '4', '5');

-- INSERT PROJECTIONS 60 NORMAL Y 10 SPECIAL
-- SCREEN 2
INSERT INTO `cinemarl`.`PROJECTIONS` (`date`, `startTime`, `endTime`, `price`, `occupiedNormalSeats`, `occupiedSpecialSeats`, `idFilm`, `idScreen`) 
	VALUES ('2024-06-03', '18:30:00', '20:30:00', '6.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '2', '2'),
			('2024-06-03', '21:00:00', '22:30:00', '6.50', '[1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '8', '2'),
			('2024-06-04', '18:30:00', '20:30:00', '6.50', '[1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 1, 1, 1]', '2', '2'),
			('2024-06-04', '21:00:00', '22:30:00', '6.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '8', '2'),
			('2024-06-05', '18:30:00', '20:30:00', '6.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 1, 0, 0, 1, 1]', '2', '2'),
			('2024-06-05', '21:00:00', '22:30:00', '6.50', '[1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 1, 0, 0, 0, 1, 1]', '8', '2'),
			('2024-06-06', '18:30:00', '20:30:00', '6.50', '[1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1]', '[1, 1, 0, 0, 1, 0, 0, 0, 1, 1]', '2', '2'),
			('2024-06-06', '21:00:00', '22:30:00', '6.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '8', '2'),
-- SCREEN 4 (3D)
			('2024-06-03', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '1', '4'),
			('2024-06-03', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 1, 1, 1]', '1', '4'),
			('2024-06-04', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '1', '4'),
			('2024-06-04', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '1', '4'),
			('2024-06-05', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 1, 0, 0, 0, 1, 1]', '1', '4'),
			('2024-06-05', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '1', '4'),
			('2024-06-06', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 1, 1, 1]', '1', '4'),
			('2024-06-06', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '1', '4'),
-- SCREEN 6 (3D)
			('2024-06-03', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '10', '6'),
			('2024-06-03', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 1, 1, 1]', '10', '6'),
			('2024-06-04', '21:00:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '10', '6'),
			('2024-06-04', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '10', '6'),
			('2024-06-05', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 1, 0, 0, 0, 1, 1]', '10', '6'),
			('2024-06-05', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 0, 1, 1]', '10', '6'),
			('2024-06-06', '18:30:00', '20:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1]', '[1, 1, 0, 0, 0, 0, 0, 1, 1, 1]', '10', '6'),
			('2024-06-06', '21:00:00', '22:30:00', '8.50', '[1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1]', '[1, 1, 1, 0, 0, 0, 0, 0, 1, 1]', '10', '6');


-- INSERT ORDERS
INSERT INTO `cinemarl`.`ORDERS` (`createdDate`, `status`, `idUser`, `idCard`) VALUES ('2024-05-31', 'Closed', '1', '1');
INSERT INTO `cinemarl`.`ORDERS` (`createdDate`, `status`, `idUser`, `idCard`) VALUES ('2024-05-31', 'Closed', '1', '2');
INSERT INTO `cinemarl`.`ORDERS` (`createdDate`, `status`, `idUser`, `idCard`) VALUES ('2024-05-31', 'Closed', '2', '5');

-- INSERT ITEMS_IN_ORDER
INSERT INTO `cinemarl`.`items_in_order` (`quantity`, `normalSeats`,  `specialSeats`, `price`, `idOrder`, `idProduct`, `idProjection`)
	VALUES ('1', '[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]', '[1,1,0,0,0,0,0,0,0,0]', '6.50', '1', NULL, '1'),
			('2', '[1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]', '[0,0,0,0,0,0,0,0,0,0]', '6.50', '2', NULL, '17'),
			('2', NULL, NULL, '2.50', '2', '2', NULL),
			('1', NULL, NULL, '9.99', '3', '7', NULL),
			('3', NULL, NULL, '2.50', '3', '2', NULL);


















