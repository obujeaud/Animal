-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.5.61 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de la base pour bestioles_test
CREATE DATABASE IF NOT EXISTS `bestioles_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bestioles_test`;

-- Export de la structure de la table bestioles_test. animal
CREATE TABLE IF NOT EXISTS `animal` (
  `id_animal` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `coat_color` varchar(50) DEFAULT NULL,
  `id_species` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_animal`),
  KEY `FK_animal_species` (`id_species`),
  CONSTRAINT `FK_animal_species` FOREIGN KEY (`id_species`) REFERENCES `species` (`id_species`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Export de données de la table bestioles_test.animal : ~9 rows (environ)
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` (`id_animal`, `name`, `sex`, `coat_color`, `id_species`) VALUES
	(1, 'Jupiter', 'M', 'Green', 2),
	(2, 'Bugs', 'M', 'Grey', 3),
	(3, 'Lola', 'F', 'Brown', 2),
	(4, 'Garfield', 'F', 'White Brown spotted', 2),
	(5, 'Rantanplan', 'M', 'Brown', 1),
	(6, 'Sarkozy', 'M', 'Yellow', 1),
	(7, 'Débilou', 'M', 'Orange', 7),
	(8, 'Roberta', 'F', 'Pink', 5),
	(9, 'Suzette', 'F', 'Rainbow', 8);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;

-- Export de la structure de la table bestioles_test. animal__person
CREATE TABLE IF NOT EXISTS `animal__person` (
  `id_person` int(11) DEFAULT NULL,
  `id_animal` int(11) DEFAULT NULL,
  KEY `FK_animal__person_animal` (`id_animal`),
  KEY `FK_animal__person_person` (`id_person`),
  CONSTRAINT `FK_animal__person_animal` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`) ON DELETE CASCADE,
  CONSTRAINT `FK_animal__person_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table bestioles_test.animal__person : ~10 rows (environ)
/*!40000 ALTER TABLE `animal__person` DISABLE KEYS */;
INSERT INTO `animal__person` (`id_person`, `id_animal`) VALUES
	(1, 4),
	(2, 4),
	(3, 2),
	(3, 3),
	(4, 1),
	(3, 5),
	(3, 7),
	(6, 8),
	(6, 7),
	(6, 6);
/*!40000 ALTER TABLE `animal__person` ENABLE KEYS */;

-- Export de la structure de la procédure bestioles_test. myA
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `myA`(
	IN `ch` VARCHAR(50)

,
	OUT `nb` INT

)
BEGIN
select count(*) into nb from animal a, species s where a.id_species = s.id_species and s.common_name like ch;
END//
DELIMITER ;

-- Export de la structure de la procédure bestioles_test. myAnimal
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `myAnimal`()
BEGIN
select * from animal;
END//
DELIMITER ;

-- Export de la structure de la procédure bestioles_test. myProc
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `myProc`()
begin
select * from species;
end//
DELIMITER ;

-- Export de la structure de la table bestioles_test. person
CREATE TABLE IF NOT EXISTS `person` (
  `id_person` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Export de données de la table bestioles_test.person : ~6 rows (environ)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id_person`, `nom`, `prenom`, `age`) VALUES
	(1, 'Jupiter', 'Dieu', 2),
	(2, 'Bujeaud', 'Manue', 26),
	(3, 'Menes', 'Pierre', 78),
	(4, 'Klackson', 'Michel', 60),
	(5, 'Depardeu', 'Gérard', 70),
	(6, 'Frêche', 'George', 78);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Export de la structure de la table bestioles_test. species
CREATE TABLE IF NOT EXISTS `species` (
  `id_species` int(11) NOT NULL AUTO_INCREMENT,
  `common_name` varchar(50) DEFAULT NULL,
  `latin_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_species`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Export de données de la table bestioles_test.species : ~9 rows (environ)
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` (`id_species`, `common_name`, `latin_name`) VALUES
	(1, 'dog', 'canem'),
	(2, 'Poisson', 'Pisces'),
	(3, 'rabbit', 'Oryctolagus cuniculus'),
	(4, 'Loup', 'Lupus'),
	(5, 'Ours', 'Ursus'),
	(6, 'EnPlus', 'Renardus'),
	(7, 'Grenouille', 'Grenus'),
	(8, 'Renard', 'Foxus'),
	(9, 'Baleine', 'Balenus balenus');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
