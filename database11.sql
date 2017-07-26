-- --------------------------------------------------------
-- Host:                         10.101.160.203
-- Server version:               5.7.19-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bonzai_trading_system
CREATE DATABASE IF NOT EXISTS `bonzai_trading_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bonzai_trading_system`;

-- Dumping structure for table bonzai_trading_system.additional_cost
CREATE TABLE IF NOT EXISTS `additional_cost` (
  `cost_id` int(11) NOT NULL AUTO_INCREMENT,
  `cost_type` varchar(50) NOT NULL,
  `trade_id` int(11) NOT NULL,
  `cost_type_price` double NOT NULL,
  `currency` varchar(50) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`cost_id`),
  KEY `FK_additional_cost_trade_information` (`trade_id`),
  CONSTRAINT `FK_additional_cost_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='This table contains information about the additional cost of a trade like brokerage cost,storage cost.';

-- Dumping data for table bonzai_trading_system.additional_cost: ~8 rows (approximately)
/*!40000 ALTER TABLE `additional_cost` DISABLE KEYS */;
INSERT INTO `additional_cost` (`cost_id`, `cost_type`, `trade_id`, `cost_type_price`, `currency`, `date`) VALUES
	(1, 'logistics\r\n', 1, 200, 'USD', '2017-07-25'),
	(2, 'insurance', 1, 150, 'USD', '2017-07-25'),
	(3, 'extra', 1, 50, 'GBP', '2017-07-25'),
	(4, 'logistics', 2, 250, 'USD', '2017-07-25'),
	(5, 'logistic', 15, 2, 'USD', '2017-07-25'),
	(6, 'insurance', 15, 3, 'GBP', '2017-07-25'),
	(7, 'broker', 15, 7, 'USD', '2017-07-25'),
	(8, 'log', 13, 10, 'USD', '2017-07-26');
/*!40000 ALTER TABLE `additional_cost` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.cashflow
CREATE TABLE IF NOT EXISTS `cashflow` (
  `cashflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) NOT NULL,
  `delivery_id` int(11),
  `internal_company` varchar(50) NOT NULL,
  `counterpart` varchar(50) NOT NULL,
  `quantity` double NOT NULL,
  `uom` enum('MT') NOT NULL DEFAULT 'MT',
  `price` double NOT NULL,
  `currency` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `additional_amount` double DEFAULT NULL,
  `cashflow_type` enum('PAYABLE','RECEIVABLE') NOT NULL,
  `cashflow_date` date NOT NULL,
  `isInvoiced` enum('false','true') NOT NULL DEFAULT 'false',
  PRIMARY KEY (`cashflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=754 DEFAULT CHARSET=utf8 COMMENT='Shows cashflow for a particular trade';

-- Dumping data for table bonzai_trading_system.cashflow: ~63 rows (approximately)
/*!40000 ALTER TABLE `cashflow` DISABLE KEYS */;
INSERT INTO `cashflow` (`cashflow_id`, `trade_id`, `delivery_id`, `internal_company`, `counterpart`, `quantity`, `uom`, `price`, `currency`, `amount`, `additional_amount`, `cashflow_type`, `cashflow_date`, `isInvoiced`) VALUES
	(691, 1, 0, 'RelianceOil', 'DubaiOil', 100, 'MT', 10, 'USD', 1000, 0, 'PAYABLE', '2017-07-26', 'false'),
	(692, 2, 0, 'Ion', 'Reliance', 100, 'MT', 14, 'INR', 1400, 0, 'PAYABLE', '2017-07-26', 'false'),
	(693, 3, 0, 'Ion', 'Reliance', 100, 'MT', 14, 'INR', 1400, 0, 'PAYABLE', '2017-07-26', 'false'),
	(694, 4, 0, 'Ion', 'Reliance', 50, 'MT', 14, 'INR', 700, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(695, 5, 0, 'Ion', 'Reliance', 50, 'MT', 14, 'INR', 700, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(696, 6, 0, 'RelianceOil', 'DubaiOil', 100, 'MT', 10, 'INR', 1000, 0, 'PAYABLE', '2017-07-26', 'false'),
	(697, 7, 0, 'HP', 'RelianceOil', 50, 'MT', 15, 'USD', 750, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(698, 8, 0, 'RelianceOil', 'DubaiOil', 50, 'MT', 11, 'USD', 550, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(699, 9, 0, 'Ion', 'Reliance', 50, 'MT', 13, 'INR', 650, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(700, 10, 0, 'RelianceOil', 'DubaiOil', 60, 'MT', 9, 'INR', 540, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(701, 11, 0, 'Ion', 'Reliance ', 100, 'MT', 14, 'INR', 1400, 0, 'PAYABLE', '2017-07-26', 'false'),
	(702, 12, 0, 'Ion', 'Reliance', 55, 'MT', 15, 'INR', 825, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(703, 13, 0, 'Ion', 'Relaince', 100, 'MT', 14, 'INR', 1400, 0, 'PAYABLE', '2017-07-26', 'false'),
	(704, 14, 0, 'Ion', 'Reliance', 50, 'MT', 14, 'INR', 700, 0, 'RECEIVABLE', '2017-07-26', 'false'),
	(705, 15, 0, 'Sundrop', 'Gemini', 300, 'MT', 25, 'USD', 7500, 0, 'PAYABLE', '2017-07-26', 'false'),
	(706, 16, 0, 'SundropRefined', 'GeminiRefined', 400, 'MT', 40, 'USD', 16000, 0, 'PAYABLE', '2017-07-26', 'false'),
	(707, 17, 0, 'TajBasmati', 'Dawat', 60, 'MT', 30, 'USD', 1800, 0, 'PAYABLE', '2017-07-26', 'false'),
	(708, 18, 0, 'PeanutOil', 'Gemini', 160, 'MT', 35, 'USD', 5600, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(709, 29, 0, 'FarmerAssociate', 'Sunfarma', 10, 'MT', 10, 'USD', 100, 0, 'PAYABLE', '2017-07-26', 'false'),
	(710, 15, 26, 'Sundrop', 'Gemini', 15, 'MT', 25, 'USD', 375, 0, 'PAYABLE', '2017-07-26', 'false'),
	(711, 16, 27, 'SundropRefined', 'GeminiRefined', 46, 'MT', 40, 'USD', 1840, 0, 'PAYABLE', '2017-07-26', 'false'),
	(712, 17, 28, 'TajBasmati', 'Dawat', 12, 'MT', 30, 'USD', 360, 0, 'PAYABLE', '2017-07-26', 'false'),
	(713, 18, 30, 'PeanutOil', 'Gemini', 20, 'MT', 35, 'USD', 700, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(714, 18, 31, 'PeanutOil', 'Gemini', 10, 'MT', 35, 'USD', 350, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(715, 15, 32, 'Sundrop', 'Gemini', 3, 'MT', 25, 'USD', 75, 0, 'PAYABLE', '2017-07-26', 'false'),
	(716, 15, 33, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(717, 16, 35, 'SundropRefined', 'GeminiRefined', 2, 'MT', 40, 'USD', 80, 0, 'PAYABLE', '2017-07-26', 'false'),
	(718, 15, 36, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(719, 15, 37, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(720, 15, 38, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(721, 15, 39, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(722, 18, 40, 'PeanutOil', 'Gemini', 2, 'MT', 35, 'USD', 70, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(723, 16, 41, 'SundropRefined', 'GeminiRefined', 2, 'MT', 40, 'USD', 80, 0, 'PAYABLE', '2017-07-26', 'false'),
	(724, 15, 42, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(725, 18, 43, 'PeanutOil', 'Gemini', 1, 'MT', 35, 'USD', 35, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(726, 15, 44, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(727, 15, 45, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(728, 18, 46, 'PeanutOil', 'Gemini', 3, 'MT', 35, 'USD', 105, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(729, 15, 47, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(730, 15, 48, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(731, 18, 49, 'PeanutOil', 'Gemini', 2, 'MT', 35, 'USD', 70, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(732, 15, 50, 'Sundrop', 'Gemini', 4, 'MT', 25, 'USD', 100, 0, 'PAYABLE', '2017-07-26', 'false'),
	(733, 15, 51, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(734, 15, 52, 'Sundrop', 'Gemini', 10, 'MT', 25, 'USD', 250, 0, 'PAYABLE', '2017-07-26', 'false'),
	(735, 18, 54, 'PeanutOil', 'Gemini', 17, 'MT', 35, 'USD', 595, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(736, 15, 68, 'Sundrop', 'Gemini', 5, 'MT', 25, 'USD', 125, 0, 'PAYABLE', '2017-07-26', 'false'),
	(737, 16, 69, 'SundropRefined', 'GeminiRefined', 9, 'MT', 40, 'USD', 360, 0, 'PAYABLE', '2017-07-26', 'false'),
	(738, 15, 70, 'Sundrop', 'Gemini', 3, 'MT', 25, 'USD', 75, 0, 'PAYABLE', '2017-07-26', 'false'),
	(739, 18, 71, 'PeanutOil', 'Gemini', 16, 'MT', 35, 'USD', 560, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(740, 18, 72, 'PeanutOil', 'Gemini', 1, 'MT', 35, 'USD', 35, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(741, 15, 73, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(742, 15, 74, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(743, 15, 75, 'Sundrop', 'Gemini', 1, 'MT', 25, 'USD', 25, 0, 'PAYABLE', '2017-07-26', 'false'),
	(744, 16, 76, 'SundropRefined', 'GeminiRefined', 8, 'MT', 40, 'USD', 320, 0, 'PAYABLE', '2017-07-26', 'false'),
	(745, 15, 77, 'Sundrop', 'Gemini', 9, 'MT', 25, 'USD', 225, 0, 'PAYABLE', '2017-07-26', 'false'),
	(746, 15, 78, 'Sundrop', 'Gemini', 6, 'MT', 25, 'USD', 150, 0, 'PAYABLE', '2017-07-26', 'false'),
	(747, 16, 79, 'SundropRefined', 'GeminiRefined', 2, 'MT', 40, 'USD', 80, 0, 'PAYABLE', '2017-07-26', 'false'),
	(748, 18, 80, 'PeanutOil', 'Gemini', 6, 'MT', 35, 'USD', 210, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(749, 15, 81, 'Sundrop', 'Gemini', 2, 'MT', 25, 'USD', 50, 0, 'PAYABLE', '2017-07-26', 'false'),
	(750, 15, 82, 'Sundrop', 'Gemini', 4, 'MT', 25, 'USD', 100, 0, 'PAYABLE', '2017-07-26', 'false'),
	(751, 15, 83, 'Sundrop', 'Gemini', 5, 'MT', 25, 'USD', 125, 0, 'PAYABLE', '2017-07-26', 'false'),
	(752, 18, 84, 'PeanutOil', 'Gemini', 2, 'MT', 35, 'USD', 70, 0, 'RECEIVABLE', '2017-07-26', 'true'),
	(753, 15, 85, 'Sundrop', 'Gemini', 3, 'MT', 25, 'USD', 75, 0, 'PAYABLE', '2017-07-26', 'false');
/*!40000 ALTER TABLE `cashflow` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.cashflow_invoice_status
CREATE TABLE IF NOT EXISTS `cashflow_invoice_status` (
  `cashflow_id` int(11) NOT NULL,
  `invoice_id` varchar(122) NOT NULL,
  PRIMARY KEY (`cashflow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.cashflow_invoice_status: ~14 rows (approximately)
/*!40000 ALTER TABLE `cashflow_invoice_status` DISABLE KEYS */;
INSERT INTO `cashflow_invoice_status` (`cashflow_id`, `invoice_id`) VALUES
	(708, 'PeanutOilGemini18'),
	(713, 'PeanutOilGemini18'),
	(714, 'PeanutOilGemini18'),
	(722, 'PeanutOilGemini18'),
	(725, 'PeanutOilGemini18'),
	(728, 'PeanutOilGemini18'),
	(731, 'PeanutOilGemini18'),
	(735, 'PeanutOilGemini18'),
	(739, 'PeanutOilGemini18'),
	(740, 'PeanutOilGemini18'),
	(748, 'PeanutOilGemini18'),
	(752, 'PeanutOilGemini18');
/*!40000 ALTER TABLE `cashflow_invoice_status` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.company
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='This table contains names of the companies which can buy or sell a trade using Bonzai trading system.';

-- Dumping data for table bonzai_trading_system.company: ~10 rows (approximately)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`company_id`, `company_name`) VALUES
	(1, 'Ion'),
	(2, 'Reliance Oil'),
	(3, 'Reliance'),
	(4, 'Dubai Oil'),
	(5, 'Parle G'),
	(6, 'Dabar'),
	(7, 'Gemini'),
	(8, 'Everest'),
	(9, 'Advanta Limited'),
	(10, 'National Agro Industry');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.end_of_day
CREATE TABLE IF NOT EXISTS `end_of_day` (
  `date` date DEFAULT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `transfer_id` int(11) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `trade_price` double DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `pnl_amount` double DEFAULT NULL,
  KEY `trade_id` (`trade_id`),
  KEY `transfer_id` (`transfer_id`),
  CONSTRAINT `trade_id` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_id` FOREIGN KEY (`transfer_id`) REFERENCES `trade_transfer_sk` (`transfer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.end_of_day: ~18 rows (approximately)
/*!40000 ALTER TABLE `end_of_day` DISABLE KEYS */;
INSERT INTO `end_of_day` (`date`, `trade_id`, `transfer_id`, `quantity`, `trade_price`, `market_price`, `pnl_amount`) VALUES
	('2017-07-26', 1, NULL, 100, 10, 11, 100),
	('2017-07-26', 2, NULL, 100, 14, 11, -300),
	('2017-07-26', 3, NULL, 100, 14, 9, -500),
	('2017-07-26', 4, NULL, 50, 14, 9, -250),
	('2017-07-26', 5, NULL, 50, 14, 9, -250),
	('2017-07-26', 6, NULL, 100, 10, 11, 100),
	('2017-07-26', 7, NULL, 50, 15, 11, -200),
	('2017-07-26', 8, NULL, 50, 11, 11, 0),
	('2017-07-26', 9, NULL, 50, 13, 11, -100),
	('2017-07-26', 10, NULL, 60, 9, 11, 120),
	('2017-07-26', 11, NULL, 100, 14, 11, -300),
	('2017-07-26', 12, NULL, 55, 15, 11, -220),
	('2017-07-26', 13, NULL, 100, 14, 9, -500),
	('2017-07-26', 14, NULL, 50, 14, 9, -250),
	('2017-07-26', 15, NULL, 300, 25, 11, -4200),
	('2017-07-26', 16, NULL, 400, 40, 11, -11600),
	('2017-07-26', 17, NULL, 60, 30, 9, -1260),
	('2017-07-26', 18, NULL, 160, 35, 11, -3840);
/*!40000 ALTER TABLE `end_of_day` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.foreign_exchange
CREATE TABLE IF NOT EXISTS `foreign_exchange` (
  `date` date NOT NULL,
  `currency` varchar(3) NOT NULL,
  `inr_factor` double NOT NULL,
  PRIMARY KEY (`date`,`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.foreign_exchange: ~12 rows (approximately)
/*!40000 ALTER TABLE `foreign_exchange` DISABLE KEYS */;
INSERT INTO `foreign_exchange` (`date`, `currency`, `inr_factor`) VALUES
	('2016-07-27', 'GBP', 0.01),
	('2016-07-27', 'USD', 0.015),
	('2016-07-28', 'GBP', 0.01),
	('2016-07-28', 'USD', 0.015),
	('2016-07-29', 'GBP', 0.01),
	('2016-07-29', 'USD', 0.015),
	('2017-07-24', 'GBP', 0.011),
	('2017-07-24', 'USD', 0.015),
	('2017-07-25', 'GBP', 0.015),
	('2017-07-25', 'USD', 0.011),
	('2017-07-26', 'GBP', 1),
	('2017-07-26', 'USD', 1);
/*!40000 ALTER TABLE `foreign_exchange` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.invoice_status
CREATE TABLE IF NOT EXISTS `invoice_status` (
  `invoice_id` varchar(122) NOT NULL,
  `invoice_status` varchar(50) NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.invoice_status: ~3 rows (approximately)
/*!40000 ALTER TABLE `invoice_status` DISABLE KEYS */;
INSERT INTO `invoice_status` (`invoice_id`, `invoice_status`) VALUES
	('PeanutOilGemini18', 'pending');
/*!40000 ALTER TABLE `invoice_status` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.market_price
CREATE TABLE IF NOT EXISTS `market_price` (
  `date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  PRIMARY KEY (`date`,`commodity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.market_price: ~14 rows (approximately)
/*!40000 ALTER TABLE `market_price` DISABLE KEYS */;
INSERT INTO `market_price` (`date`, `commodity`, `price`, `currency`) VALUES
	('2016-07-27', 'rice', 105, 'USD'),
	('2016-07-27', 'wheat', 150, 'USD'),
	('2016-07-28', 'rice', 105, 'USD'),
	('2016-07-28', 'wheat', 150, 'USD'),
	('2016-07-29', 'rice', 9, 'USD'),
	('2016-07-29', 'wheat', 11, 'USD'),
	('2017-07-24', 'bajra', 25, 'INR'),
	('2017-07-24', 'rice', 20, 'INR'),
	('2017-07-24', 'wheat', 15, 'INR'),
	('2017-07-25', 'bajra', 26, 'INR'),
	('2017-07-25', 'rice', 16, 'INR'),
	('2017-07-25', 'wheat', 19, 'INR'),
	('2017-07-26', 'rice', 9, 'USD'),
	('2017-07-26', 'wheat', 11, 'USD');
/*!40000 ALTER TABLE `market_price` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.remaining_transfer_quantity
CREATE TABLE IF NOT EXISTS `remaining_transfer_quantity` (
  `trade_id` int(11) NOT NULL,
  `remaining_quantity` double NOT NULL,
  KEY `FK_Remaining_Transfer_Quantity_trade_information` (`trade_id`),
  CONSTRAINT `FK_Remaining_Transfer_Quantity_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.remaining_transfer_quantity: ~9 rows (approximately)
/*!40000 ALTER TABLE `remaining_transfer_quantity` DISABLE KEYS */;
INSERT INTO `remaining_transfer_quantity` (`trade_id`, `remaining_quantity`) VALUES
	(1, 11),
	(7, 98),
	(15, 197),
	(16, 304),
	(17, 48),
	(18, 80),
	(3, 78),
	(13, 80),
	(14, 33);
/*!40000 ALTER TABLE `remaining_transfer_quantity` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.storage
CREATE TABLE IF NOT EXISTS `storage` (
  `storage_id` int(11) NOT NULL DEFAULT '1',
  `storage_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.storage: ~3 rows (approximately)
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` (`storage_id`, `storage_name`) VALUES
	(1, 'BombayPort'),
	(2, 'Chennai'),
	(3, 'Jamnagar');
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_information
CREATE TABLE IF NOT EXISTS `trade_information` (
  `trade_id` int(11) NOT NULL AUTO_INCREMENT,
  `counterpart` varchar(50) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `quantity` double NOT NULL DEFAULT '0',
  `uom` varchar(50) NOT NULL DEFAULT 'MT',
  `trade_price` double NOT NULL DEFAULT '0',
  `currency` varchar(50) DEFAULT NULL,
  `maturity_date` date DEFAULT NULL,
  `trade_type` enum('BUY','SELL') DEFAULT NULL,
  `completion_status` enum('Y','N') DEFAULT NULL,
  `frozen_status` enum('Y','N') DEFAULT NULL,
  `cashflow_generated` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='It captures  all the infromation related to trade.';

-- Dumping data for table bonzai_trading_system.trade_information: ~20 rows (approximately)
/*!40000 ALTER TABLE `trade_information` DISABLE KEYS */;
INSERT INTO `trade_information` (`trade_id`, `counterpart`, `internal_company`, `commodity`, `quantity`, `uom`, `trade_price`, `currency`, `maturity_date`, `trade_type`, `completion_status`, `frozen_status`, `cashflow_generated`) VALUES
	(1, 'DubaiOil', 'RelianceOil', 'wheat', 100, 'MT', 10, 'USD', '2017-07-30', 'BUY', 'Y', 'Y', 'TRUE'),
	(2, 'Reliance', 'Ion', 'wheat', 100, 'MT', 14, 'INR', '2017-07-29', 'BUY', 'Y', 'Y', 'TRUE'),
	(3, 'Reliance', 'Ion', 'rice', 100, 'MT', 14, 'INR', '2017-07-28', 'BUY', 'Y', 'Y', 'TRUE'),
	(4, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-28', 'SELL', 'Y', 'Y', 'TRUE'),
	(5, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-29', 'SELL', 'Y', 'Y', 'TRUE'),
	(6, 'DubaiOil', 'RelianceOil', 'wheat', 100, 'MT', 10, 'INR', '2017-07-30', 'BUY', 'Y', 'Y', 'TRUE'),
	(7, 'RelianceOil', 'HP', 'wheat', 50, 'MT', 15, 'USD', '2017-07-30', 'SELL', 'Y', 'Y', 'TRUE'),
	(8, 'DubaiOil', 'RelianceOil', 'wheat', 50, 'MT', 11, 'USD', '2017-07-29', 'SELL', 'Y', 'Y', 'TRUE'),
	(9, 'Reliance', 'Ion', 'wheat', 50, 'MT', 13, 'INR', '2017-07-29', 'SELL', 'Y', 'Y', 'TRUE'),
	(10, 'DubaiOil', 'RelianceOil', 'wheat', 60, 'MT', 9, 'INR', '2017-07-31', 'SELL', 'Y', 'Y', 'TRUE'),
	(11, 'Reliance ', 'Ion', 'wheat', 100, 'MT', 14, 'INR', '2017-08-01', 'BUY', 'Y', 'Y', 'TRUE'),
	(12, 'Reliance', 'Ion', 'wheat', 55, 'MT', 15, 'INR', '2017-07-28', 'SELL', 'Y', 'Y', 'TRUE'),
	(13, 'Relaince', 'Ion', 'rice', 100, 'MT', 30, 'INR', '2017-07-29', 'BUY', 'Y', 'Y', 'TRUE'),
	(14, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-29', 'SELL', 'Y', 'Y', 'TRUE'),
	(15, 'Gemini', 'Sundrop', 'wheat', 300, 'MT', 25, 'USD', '2017-07-30', 'BUY', 'Y', 'Y', 'TRUE'),
	(16, 'GeminiRefined', 'SundropRefined', 'wheat', 400, 'MT', 40, 'USD', '2017-07-28', 'BUY', 'Y', 'Y', 'TRUE'),
	(17, 'Dawat', 'TajBasmati', 'rice', 60, 'MT', 30, 'USD', '2017-07-28', 'BUY', 'Y', 'Y', 'TRUE'),
	(18, 'Gemini', 'PeanutOil', 'wheat', 160, 'MT', 35, 'USD', '2017-07-30', 'SELL', 'Y', 'Y', 'TRUE'),
	(19, 'Dabar', 'Reliance', 'rice', 0, 'MT', 0, NULL, NULL, NULL, 'N', 'N', 'FALSE');
/*!40000 ALTER TABLE `trade_information` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_transfer
CREATE TABLE IF NOT EXISTS `trade_transfer` (
  `transfer_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) NOT NULL,
  `transfer_type` enum('Build','Draw') NOT NULL,
  `quantity` double NOT NULL,
  `open_quantity` double NOT NULL,
  `transfer_price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  `uom` varchar(3) NOT NULL DEFAULT 'MT',
  `transfer_date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `cashflow_generated` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  `storage_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`transfer_id`),
  KEY `FK_trade_transfer_trade_information` (`trade_id`),
  CONSTRAINT `FK_trade_transfer_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer: ~53 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer` DISABLE KEYS */;
INSERT INTO `trade_transfer` (`transfer_id`, `trade_id`, `transfer_type`, `quantity`, `open_quantity`, `transfer_price`, `currency`, `uom`, `transfer_date`, `commodity`, `cashflow_generated`, `storage_id`) VALUES
	(26, 15, 'Build', 15, 0, 2272.727272727273, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(27, 16, 'Build', 46, 25, 3636.3636363636365, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(28, 17, 'Build', 12, 12, 2727.2727272727275, 'INR', 'MT', '2017-07-25', 'rice', 'TRUE', 1),
	(30, 18, 'Draw', 20, 0, 3301.0432190760057, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(31, 18, 'Draw', 10, 0, 3636.3636363636365, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(32, 15, 'Build', 3, 3, 3290.727272727273, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(33, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-25', 'wheat', 'TRUE', 1),
	(35, 16, 'Build', 2, 2, 3636.3636363636365, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(36, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(37, 15, 'Build', 1, 1, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(38, 15, 'Build', 1, 1, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(39, 15, 'Build', 1, 1, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(40, 18, 'Draw', 2, 0, 3555.983086680761, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(41, 16, 'Build', 2, 2, 3636.3636363636365, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(42, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(43, 18, 'Draw', 1, 0, 3544.193939393939, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(44, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(45, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(46, 18, 'Draw', 3, 0, 3521.151515151516, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(47, 15, 'Build', 2, 2, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(48, 15, 'Build', 2, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(49, 18, 'Draw', 2, 0, 3495.2875695732855, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(50, 15, 'Build', 4, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(51, 15, 'Build', 1, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(52, 15, 'Build', 10, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(54, 18, 'Draw', 17, 0, 3673.8110047846894, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(68, 15, 'Build', 5, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(69, 16, 'Build', 9, 0, 3636.3636363636365, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(70, 15, 'Build', 3, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(71, 18, 'Draw', 16, 0, 3473.7112299465243, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(72, 18, 'Draw', 1, 0, 3290.727272727273, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(73, 15, 'Build', 2, 0, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(74, 15, 'Build', 1, 1, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(75, 15, 'Build', 1, 0, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(76, 16, 'Build', 8, 8, 2666.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(77, 15, 'Build', 9, 4, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(78, 15, 'Build', 6, 6, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(79, 16, 'Build', 2, 2, 2666.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(80, 18, 'Draw', 6, 0, 2495.2380952380954, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(81, 15, 'Build', 2, 2, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(82, 15, 'Build', 4, 4, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(83, 15, 'Build', 5, 5, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(84, 18, 'Draw', 2, 0, 2495.238095238096, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 2),
	(85, 15, 'Build', 3, 3, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'TRUE', 1),
	(86, 15, 'Build', 8, 8, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'FALSE', 2),
	(87, 16, 'Build', 2, 2, 2666.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'FALSE', 1),
	(88, 1, 'Build', 12, 12, 27332.666666666668, 'INR', 'MT', '2017-07-26', 'wheat', 'FALSE', 1),
	(89, 15, 'Build', 2, 2, 2466.666666666667, 'INR', 'MT', '2017-07-26', 'wheat', 'FALSE', 1),
	(90, 3, 'Build', 10, 0, 14, 'INR', 'MT', '2017-07-26', 'rice', 'FALSE', 3),
	(91, 13, 'Build', 20, 13, 40, 'INR', 'MT', '2017-07-26', 'rice', 'FALSE', 3),
	(92, 14, 'Draw', 15, 0, 31.333333333333332, 'INR', 'MT', '2017-07-26', 'rice', 'FALSE', 3),
	(93, 3, 'Build', 12, 12, 14, 'INR', 'MT', '2017-07-26', 'rice', 'FALSE', 3),
	(94, 14, 'Draw', 2, 0, 28.444444444444443, 'INR', 'MT', '2017-07-26', 'rice', 'FALSE', 3);
/*!40000 ALTER TABLE `trade_transfer` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_transfer_sk
CREATE TABLE IF NOT EXISTS `trade_transfer_sk` (
  `transfer_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) NOT NULL,
  `transfer_type` varchar(50) NOT NULL,
  `quantity` double NOT NULL,
  `open_quantity` double NOT NULL,
  `transfer_price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  `uom` varchar(3) NOT NULL DEFAULT 'MT',
  `transfer_date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  PRIMARY KEY (`transfer_id`),
  KEY `FK_trade_transfer_sk_trade_information` (`trade_id`),
  CONSTRAINT `FK_trade_transfer_sk_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer_sk: ~7 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer_sk` DISABLE KEYS */;
INSERT INTO `trade_transfer_sk` (`transfer_id`, `trade_id`, `transfer_type`, `quantity`, `open_quantity`, `transfer_price`, `currency`, `uom`, `transfer_date`, `commodity`) VALUES
	(1, 1, 'Build', 100, 50, 10, 'INR', 'MT', '2017-07-25', 'wheat'),
	(2, 2, 'Build', 100, 100, 14, 'INR', 'MT', '2017-07-25', 'wheat'),
	(3, 3, 'Build', 100, 0, 14, 'INR', 'MT', '2017-07-25', 'rice'),
	(4, 4, 'Draw', 50, 0, 14, 'INR', 'MT', '2017-07-25', 'rice'),
	(5, 5, 'Draw', 50, 0, 14, 'INR', 'MT', '2017-07-25', 'rice'),
	(6, 7, 'Draw', 25, 0, 15, 'INR', 'MT', '2017-07-25', 'wheat'),
	(7, 7, 'Draw', 25, 0, 15, 'INR', 'MT', '2017-07-25', 'wheat');
/*!40000 ALTER TABLE `trade_transfer_sk` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.valuation
CREATE TABLE IF NOT EXISTS `valuation` (
  `date` date NOT NULL,
  `trade_id` int(11) NOT NULL,
  `pnl_status` enum('profit','loss') DEFAULT NULL,
  `pnl_amount` double DEFAULT NULL,
  PRIMARY KEY (`date`,`trade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.valuation: ~18 rows (approximately)
/*!40000 ALTER TABLE `valuation` DISABLE KEYS */;
INSERT INTO `valuation` (`date`, `trade_id`, `pnl_status`, `pnl_amount`) VALUES
	('2017-07-26', 1, 'loss', 300),
	('2017-07-26', 2, 'loss', 550),
	('2017-07-26', 3, 'loss', 500),
	('2017-07-26', 4, 'profit', 250),
	('2017-07-26', 5, 'profit', 250),
	('2017-07-26', 6, 'profit', 100),
	('2017-07-26', 7, 'profit', 200),
	('2017-07-26', 8, 'loss', 0),
	('2017-07-26', 9, 'profit', 100),
	('2017-07-26', 10, 'loss', 120),
	('2017-07-26', 11, 'loss', 300),
	('2017-07-26', 12, 'profit', 220),
	('2017-07-26', 13, 'loss', 2110),
	('2017-07-26', 14, 'profit', 250),
	('2017-07-26', 15, 'loss', 4212),
	('2017-07-26', 16, 'loss', 11600),
	('2017-07-26', 17, 'loss', 1260),
	('2017-07-26', 18, 'profit', 3840);
/*!40000 ALTER TABLE `valuation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
