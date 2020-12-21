-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.53 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for logdb
CREATE DATABASE IF NOT EXISTS `logdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `logdb`;

-- Dumping structure for table logdb.all_log_data
CREATE TABLE IF NOT EXISTS `all_log_data` (
  `request_date` datetime DEFAULT NULL,
  `ip_address` varchar(50) DEFAULT NULL,
  `request` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table logdb.daily_log_data
CREATE TABLE IF NOT EXISTS `daily_log_data` (
  `ip_address` varchar(50) DEFAULT NULL,
  `num_of_request` int(11) DEFAULT NULL,
  `comments` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.
-- Dumping structure for table logdb.hourly_log_data
CREATE TABLE IF NOT EXISTS `hourly_log_data` (
  `ip_address` varchar(50) DEFAULT NULL,
  `num_of_request` int(11) DEFAULT NULL,
  `comments` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
