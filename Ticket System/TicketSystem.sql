-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.26-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for workout
CREATE DATABASE IF NOT EXISTS `workout` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `workout`;

-- Dumping structure for table workout.login
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `dept` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- Dumping data for table workout.login: ~2 rows (approximately)
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`id`, `username`, `password`, `dept`, `phone`, `mail`) VALUES
	(24, 'Johan', 'pass123', 'sysadmin', '0892529913', 'johan@mailinator.com'),
	(25, 'JamesConor', 'pass12345', 'techsupport', '0838463471', 'james@mailinator.com'),
	(26, 'JohnRiley', 'pass12345', 'manager', '976668161', 'John@mailinator.com');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;

-- Dumping structure for table workout.tickets
CREATE TABLE IF NOT EXISTS `tickets` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `issue` varchar(150) DEFAULT NULL,
  `urgency` varchar(50) DEFAULT NULL,
  `timestamp` varchar(50) DEFAULT NULL,
  `closingtime` varchar(50) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `elapsedtime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `userid` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table workout.tickets: ~5 rows (approximately)
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` (`ID`, `issue`, `urgency`, `timestamp`, `closingtime`, `userid`, `comment`, `elapsedtime`) VALUES
	(23, 'Sample Issue', 'normal', '1514928902670', '1514929131813', 25, 'Sample comment.', '229'),
	(24, 'Internet down', 'urgent', '1514928930497', NULL, 25, 'Sample comment.', NULL),
	(25, 'Gnomes kidnapped Boris', 'longterm', '1514928948052', '1514929113973', 25, 'Sample comment.', '165'),
	(26, 'Can i haz better grades plox?', 'urgent', '1514928970655', NULL, 25, 'Sample comment.', NULL);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
