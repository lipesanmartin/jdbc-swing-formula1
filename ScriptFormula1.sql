CREATE DATABASE IF NOT EXISTS Formula1;

USE Formula1;

CREATE TABLE Equipe (
  ID INT PRIMARY KEY,
  Nome VARCHAR(255) NOT NULL,
  País VARCHAR(255) NOT NULL,
  Chefe VARCHAR(255) NOT NULL
);

CREATE TABLE Piloto (
  ID INT PRIMARY KEY,
  Nome VARCHAR(255) NOT NULL,
  Nacionalidade VARCHAR(255) NOT NULL,
  NumeroCarro INT,
  EquipeID INT,
  FOREIGN KEY (EquipeID) REFERENCES Equipe(ID)
);

CREATE TABLE Corrida (
  ID INT PRIMARY KEY,
  Nome VARCHAR(255) NOT NULL,
  Circuito VARCHAR(255) NOT NULL,
  VencedorID INT,
  FOREIGN KEY (VencedorID) REFERENCES Piloto(ID)
);

INSERT INTO `equipe` VALUES 
(1,'Alfa Romeo','Suíça','Alessandro Bravi'),
(2,'AlphaTauri','Italia','Franz Tost'),
(3,'Alpine','França','Otmar Szafnauer'),
(4,'Aston Martin','Reino Unido','Mike Krack'),
(5,'Ferrari','Italia','Frederic Vasseur'),
(6,'Haas','Estados Unidos','Günther Steiner'),
(7,'McLaren','Reino Unido','Zak Brown'),
(8,'Mercedes','Alemanha','Toto Wolf'),
(9,'Red Bull Racing','Áustria','Christian Horner'),
(10,'Williams','Reino Unido','James Vowles');

INSERT INTO `piloto` VALUES 
(1,'Max Verstappen','Holanda',1,9),
(2,'Sergio Perez','México',12,9),
(3,'Lewis Hamilton','Reino Unido',44,7),
(4,'George Russell','Reino Unido',66,8),
(5,'Charles Leclerc','Mônaco',16,5),
(6,'Carlos Sainz','Espanha',5,5),
(7,'Fernando Alonso','Espanha',14,4),
(8,'Lance Stroll','Canadá',18,4),
(9,'Lando Norris','Reino Unido',4,7),
(10,'Oscar Piastri','Austrália',81,7),
(11,'Guanyu Zhou','China',11,2),
(12,'Valtteri Bottas','Finlândia',77,1),
(13,'Nyck de Vries','Holanda',21,2),
(14,'Yuki Tsunoda','Japão',22,2),
(15,'Pierre Gasly','França',10,3),
(16,'Esteban Ocon','França',31,3),
(17,'Kevin Magnussen','Dinamarca',20,6),
(18,'Nico Hülkenberg','Alemanha',27,6),
(19,'Logan Sargeant','Estados Unidos',2,10),
(20,'Alexander Albon','Tailândia',23,10);

INSERT INTO `corrida` VALUES
(1,'GP do Bahrein','Circuito Internacional do Bahrein',5),
(2,'GP da Arábia Saudita','Jeddah Corniche Circuit',1),
(3,'GP da Austrália','Circuito de Albert Park',5),
(4,'GP da Emilia-Romagna','Autódromo Enzo e Dino Ferrari',1),
(5,'GP de Miami','Miami International Autodrome',1),
(6,'GP da Espanha','Circuito da Catalunha',1),
(7,'GP de Mônaco','Circuito de Mônaco',2),
(8,'GP do Azerbaijão','Circuito Urbano de Baku',1),
(9,'GP do Canadá','Circuito Gilles Villeneuve',1),
(10,'GP da Grã-Bretanha','Circuito de Silverstone',6),
(11,'GP da Áustria','Red Bull Ring',5),
(12,'GP da França','Circuito Paul Ricard',1),
(13,'GP da Hungria','Hungaroring',1),
(14,'GP da Bélgica','Spa-Francorchamps',1),
(15,'GP da Holanda','Circuito de Park Zandvoort',1),
(16,'GP da Itália','Circuito de Monza',1),
(17,'GP de Singapura','Circuito Urbano de Marina Bay',2),
(18,'GP do Japão','Circuito de Suzuka',1),
(19,'GP dos Estados Unidos','Cicuito das Américas',1),
(20,'GP do México','Autódromo Hermanos Rodríguez',1),
(21,'GP do Brasil','Autódromo José Carlos Pace',4),
(22,'GP de Abu Dhabi','Circuito de Yas Marina',1);


