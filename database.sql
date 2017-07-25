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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='This table contains information about the additional cost of a trade like brokerage cost,storage cost.';

-- Dumping data for table bonzai_trading_system.additional_cost: ~4 rows (approximately)
/*!40000 ALTER TABLE `additional_cost` DISABLE KEYS */;
INSERT INTO `additional_cost` (`cost_id`, `cost_type`, `trade_id`, `cost_type_price`, `currency`, `date`) VALUES
	(1, 'logistics\r\n', 1, 200, 'USD', '2017-07-25'),
	(2, 'insurance', 1, 150, 'USD', '2017-07-25'),
	(3, 'extra', 1, 50, 'GBP', '2017-07-25'),
	(4, 'logistics', 2, 250, 'USD', '2017-07-25');
/*!40000 ALTER TABLE `additional_cost` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.cashflow
CREATE TABLE IF NOT EXISTS `cashflow` (
  `cashflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) NOT NULL,
  `delivery_id` int(11) DEFAULT NULL,
  `internal_company` varchar(50) NOT NULL,
  `counterpart` varchar(50) NOT NULL,
  `quantity` double NOT NULL,
  `uom` varchar(50) NOT NULL DEFAULT 'MT',
  `price` double NOT NULL,
  `currency` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `additional_amount` double DEFAULT NULL,
  `cashflow_type` varchar(50) NOT NULL,
  `cashflow_date` date NOT NULL,
  PRIMARY KEY (`cashflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Shows cashflow for a particular trade';

-- Dumping data for table bonzai_trading_system.cashflow: ~0 rows (approximately)
/*!40000 ALTER TABLE `cashflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashflow` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.cashflow_invoice_status
CREATE TABLE IF NOT EXISTS `cashflow_invoice_status` (
  `cashflow_id` int(11) NOT NULL,
  `invoice_id` varchar(122) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.cashflow_invoice_status: ~4 rows (approximately)
/*!40000 ALTER TABLE `cashflow_invoice_status` DISABLE KEYS */;
INSERT INTO `cashflow_invoice_status` (`cashflow_id`, `invoice_id`) VALUES
	(1, 'RelianceDubai11'),
	(2, 'RelianceDubai'),
	(3, NULL),
	(4, NULL);
/*!40000 ALTER TABLE `cashflow_invoice_status` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.end_of_day
CREATE TABLE IF NOT EXISTS `end_of_day` (
  `date` date DEFAULT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `transfer_id` int(11) DEFAULT NULL,
  `pnl_amount` double DEFAULT NULL,
  KEY `trade_id` (`trade_id`),
  KEY `transfer_id` (`transfer_id`),
  CONSTRAINT `trade_id` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_id` FOREIGN KEY (`transfer_id`) REFERENCES `trade_transfer_sk` (`transfer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.end_of_day: ~25 rows (approximately)
/*!40000 ALTER TABLE `end_of_day` DISABLE KEYS */;
INSERT INTO `end_of_day` (`date`, `trade_id`, `transfer_id`, `pnl_amount`) VALUES
	('2017-07-25', 1, NULL, 900),
	('2017-07-25', 2, NULL, 500),
	('2017-07-25', 3, NULL, 200),
	('2017-07-25', 4, NULL, 100),
	('2017-07-25', 5, NULL, 100),
	('2017-07-25', 6, NULL, 900),
	('2017-07-25', 7, NULL, 200),
	('2017-07-25', 8, NULL, 400),
	('2017-07-25', 9, NULL, 300),
	('2017-07-25', 10, NULL, 600),
	('2017-07-25', 11, NULL, 500),
	('2017-07-25', 12, NULL, 220),
	('2017-07-25', 13, NULL, 200),
	('2017-07-25', 14, NULL, 100),
	('2017-07-25', 15, NULL, -1800),
	('2017-07-25', 16, NULL, -8400),
	('2017-07-25', 17, NULL, -840),
	('2017-07-25', 18, NULL, -2560),
	('2017-07-25', 1, 1, 900),
	('2017-07-25', 2, 2, 500),
	('2017-07-25', 3, 3, 200),
	('2017-07-25', 4, 4, 100),
	('2017-07-25', 5, 5, 100),
	('2017-07-25', 7, 6, 100),
	('2017-07-25', 7, 7, 100);
/*!40000 ALTER TABLE `end_of_day` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.foreign_exchange
CREATE TABLE IF NOT EXISTS `foreign_exchange` (
  `date` date NOT NULL,
  `currency` varchar(3) NOT NULL,
  `inr_factor` double NOT NULL,
  PRIMARY KEY (`date`,`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.foreign_exchange: ~6 rows (approximately)
/*!40000 ALTER TABLE `foreign_exchange` DISABLE KEYS */;
INSERT INTO `foreign_exchange` (`date`, `currency`, `inr_factor`) VALUES
	('2016-07-29', 'GBP', 0.01),
	('2016-07-29', 'USD', 0.015),
	('2017-07-24', 'GBP', 0.011),
	('2017-07-24', 'USD', 0.015),
	('2017-07-25', 'GBP', 0.015),
	('2017-07-25', 'USD', 0.011);
/*!40000 ALTER TABLE `foreign_exchange` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.invoice_status
CREATE TABLE IF NOT EXISTS `invoice_status` (
  `invoice_id` varchar(122) NOT NULL,
  `invoice_status` varchar(50) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.invoice_status: ~2 rows (approximately)
/*!40000 ALTER TABLE `invoice_status` DISABLE KEYS */;
INSERT INTO `invoice_status` (`invoice_id`, `invoice_status`) VALUES
	('RelianceDubai11', 'pending'),
	('RelianceDubai', 'pending');
/*!40000 ALTER TABLE `invoice_status` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.market_price
CREATE TABLE IF NOT EXISTS `market_price` (
  `date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  PRIMARY KEY (`date`,`commodity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.market_price: ~8 rows (approximately)
/*!40000 ALTER TABLE `market_price` DISABLE KEYS */;
INSERT INTO `market_price` (`date`, `commodity`, `price`, `currency`) VALUES
	('2016-07-29', 'rice', 9, 'USD'),
	('2016-07-29', 'wheat', 11, 'USD'),
	('2017-07-24', 'bajra', 25, 'INR'),
	('2017-07-24', 'rice', 20, 'INR'),
	('2017-07-24', 'wheat', 15, 'INR'),
	('2017-07-25', 'bajra', 26, 'INR'),
	('2017-07-25', 'rice', 16, 'INR'),
	('2017-07-25', 'wheat', 19, 'INR');
/*!40000 ALTER TABLE `market_price` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.remaining_transfer_quantity
CREATE TABLE IF NOT EXISTS `remaining_transfer_quantity` (
  `trade_id` int(11) NOT NULL,
  `remaining_quantity` double NOT NULL,
  KEY `FK_Remaining_Transfer_Quantity_trade_information` (`trade_id`),
  CONSTRAINT `FK_Remaining_Transfer_Quantity_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.remaining_transfer_quantity: ~6 rows (approximately)
/*!40000 ALTER TABLE `remaining_transfer_quantity` DISABLE KEYS */;
INSERT INTO `remaining_transfer_quantity` (`trade_id`, `remaining_quantity`) VALUES
	(1, 23),
	(7, 98),
	(15, 285),
	(16, 354),
	(17, 48),
	(18, 130);
/*!40000 ALTER TABLE `remaining_transfer_quantity` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_information
CREATE TABLE IF NOT EXISTS `trade_information` (
  `trade_id` int(11) NOT NULL AUTO_INCREMENT,
  `counterpart` varchar(50) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  `commodity` varchar(25) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `uom` varchar(50) NOT NULL DEFAULT 'MT',
  `trade_price` double DEFAULT NULL,
  `currency` varchar(50) DEFAULT NULL,
  `maturity_date` date DEFAULT NULL,
  `trade_type` varchar(50) DEFAULT NULL,
  `completion_status` varchar(25) DEFAULT NULL,
  `frozen_status` varchar(25) DEFAULT NULL,
  `cashflow_generated` enum('TRUE','FALSE') DEFAULT 'FALSE',
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='It captures  all the infromation related to trade.';

-- Dumping data for table bonzai_trading_system.trade_information: ~18 rows (approximately)
/*!40000 ALTER TABLE `trade_information` DISABLE KEYS */;
INSERT INTO `trade_information` (`trade_id`, `counterpart`, `internal_company`, `commodity`, `quantity`, `uom`, `trade_price`, `currency`, `maturity_date`, `trade_type`, `completion_status`, `frozen_status`, `cashflow_generated`) VALUES
	(1, 'DubaiOil', 'RelianceOil', 'wheat', 100, 'MT', 10, 'USD', '2017-07-24', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(2, 'Reliance', 'Ion', 'wheat', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(3, 'Reliance', 'Ion', 'rice', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(4, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-24', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(5, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-24', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(6, 'DubaiOil', 'RelianceOil', 'wheat', 100, 'MT', 10, 'INR', '2017-07-25', 'BUY', 'Comploete', 'Not frozen', 'FALSE'),
	(7, 'RelianceOil', 'HP', 'wheat', 50, 'MT', 15, 'USD', '2017-07-25', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(8, 'DubaiOil', 'RelianceOil', 'wheat', 50, 'MT', 11, 'USD', '2017-07-24', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(9, 'Reliance', 'Ion', 'wheat', 50, 'MT', 13, 'INR', '2017-07-24', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(10, 'DubaiOil', 'RelianceOil', 'wheat', 60, 'MT', 9, 'INR', '2017-07-25', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(11, 'Reliance ', 'Ion', 'wheat', 100, 'MT', 14, 'INR', '2017-07-25', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(12, 'Reliance', 'Ion', 'wheat', 55, 'MT', 15, 'INR', '2017-07-25', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(13, 'Relaince', 'Ion', 'rice', 100, 'MT', 14, 'INR', '2017-07-25', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(14, 'Reliance', 'Ion', 'rice', 50, 'MT', 14, 'INR', '2017-07-25', 'SELL', 'Complete', 'Not frozen', 'FALSE'),
	(15, 'Gemini', 'Sundrop', 'wheat', 300, 'MT', 25, 'USD', '2017-07-25', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(16, 'GeminiRefined', 'SundropRefined', 'wheat', 400, 'MT', 40, 'USD', '2017-07-25', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(17, 'Dawat', 'TajBasmati', 'rice', 60, 'MT', 30, 'USD', '2017-07-25', 'BUY', 'Complete', 'Not frozen', 'FALSE'),
	(18, 'Gemini', 'PeanutOil', 'wheat', 160, 'MT', 35, 'USD', '2017-07-25', 'SELL', 'Complete', 'Not frozen', 'FALSE');
/*!40000 ALTER TABLE `trade_information` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_transfer
CREATE TABLE IF NOT EXISTS `trade_transfer` (
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
  `cashflow_generated` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  PRIMARY KEY (`transfer_id`),
  KEY `FK_trade_transfer_trade_information` (`trade_id`),
  CONSTRAINT `FK_trade_transfer_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer: ~5 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer` DISABLE KEYS */;
INSERT INTO `trade_transfer` (`transfer_id`, `trade_id`, `transfer_type`, `quantity`, `open_quantity`, `transfer_price`, `currency`, `uom`, `transfer_date`, `commodity`, `cashflow_generated`) VALUES
	(26, 15, 'Build', 15, 0, 2272.727272727273, 'INR', 'MT', '2017-07-25', 'wheat', 'FALSE'),
	(27, 16, 'Build', 46, 31, 3636.3636363636365, 'INR', 'MT', '2017-07-25', 'wheat', 'FALSE'),
	(28, 17, 'Build', 12, 12, 2727.2727272727275, 'INR', 'MT', '2017-07-25', 'rice', 'FALSE'),
	(30, 18, 'Draw', 20, 0, 3301.0432190760057, 'INR', 'MT', '2017-07-25', 'wheat', 'FALSE'),
	(31, 18, 'Draw', 10, 0, 3636.3636363636365, 'INR', 'MT', '2017-07-25', 'wheat', 'FALSE');
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

-- Dumping data for table bonzai_trading_system.valuation: ~1 rows (approximately)
/*!40000 ALTER TABLE `valuation` DISABLE KEYS */;
INSERT INTO `valuation` (`date`, `trade_id`, `pnl_status`, `pnl_amount`) VALUES
	('2017-07-25', 18, 'profit', 764);
/*!40000 ALTER TABLE `valuation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
