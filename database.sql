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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table contains information about the additional cost of a trade like brokerage cost,storage cost.';

-- Dumping data for table bonzai_trading_system.additional_cost: ~0 rows (approximately)
/*!40000 ALTER TABLE `additional_cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `additional_cost` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.cashflow
CREATE TABLE IF NOT EXISTS `cashflow` (
  `cashflow_id` int(11) NOT NULL,
  `trade_id` int(11) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  `counterpart` varchar(50) NOT NULL,
  `quantity` double NOT NULL,
  `delivery_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `amount` double NOT NULL,
  `additional_amount` double NOT NULL,
  `cashflow_type` varchar(50) NOT NULL,
  `invoiced` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cashflow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Shows cashflow for a particular trade';

-- Dumping data for table bonzai_trading_system.cashflow: ~1 rows (approximately)
/*!40000 ALTER TABLE `cashflow` DISABLE KEYS */;
INSERT INTO `cashflow` (`cashflow_id`, `trade_id`, `internal_company`, `counterpart`, `quantity`, `delivery_id`, `price`, `amount`, `additional_amount`, `cashflow_type`, `invoiced`) VALUES
	(1, 1, 'Reliance', 'Dubai', 5, 1, 200, 1000, 10, 'payable', 'no');
/*!40000 ALTER TABLE `cashflow` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.end_of_day
CREATE TABLE IF NOT EXISTS `end_of_day` (
  `date` date DEFAULT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `transfer_id` int(11),
  `pnl_amount` double DEFAULT NULL,
  KEY `transfer_id` (`transfer_id`),
  KEY `trade_id` (`trade_id`),
  CONSTRAINT `trade_id` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_id` FOREIGN KEY (`transfer_id`) REFERENCES `trade_transfer` (`transfer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.end_of_day: ~0 rows (approximately)
/*!40000 ALTER TABLE `end_of_day` DISABLE KEYS */;
/*!40000 ALTER TABLE `end_of_day` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.foreign_exchange
CREATE TABLE IF NOT EXISTS `foreign_exchange` (
  `date` date NOT NULL,
  `currency` varchar(3) NOT NULL,
  `inr_factor` double NOT NULL,
  PRIMARY KEY (`date`,`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.foreign_exchange: ~1 rows (approximately)
/*!40000 ALTER TABLE `foreign_exchange` DISABLE KEYS */;
INSERT INTO `foreign_exchange` (`date`, `currency`, `inr_factor`) VALUES
	('2017-07-24', 'GBP', 0.011),
	('2017-07-24', 'USD', 0.015);
/*!40000 ALTER TABLE `foreign_exchange` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `invoice_id` int(11) DEFAULT NULL,
  `internal_company` varchar(50) DEFAULT NULL,
  `counterpart` varchar(50) DEFAULT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `cashflow_id` int(11) DEFAULT NULL,
  `cashflow_type` varchar(50) DEFAULT NULL,
  `cashflow_status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.invoice: ~0 rows (approximately)
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.market_price
CREATE TABLE IF NOT EXISTS `market_price` (
  `date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  PRIMARY KEY (`date`,`commodity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.market_price: ~0 rows (approximately)
/*!40000 ALTER TABLE `market_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `market_price` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.remaining_transfer_quantity
CREATE TABLE IF NOT EXISTS `remaining_transfer_quantity` (
  `trade_id` int(11) NOT NULL,
  `remaining_quantity` double NOT NULL,
  KEY `FK_Remaining_Transfer_Quantity_trade_information` (`trade_id`),
  CONSTRAINT `FK_Remaining_Transfer_Quantity_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.remaining_transfer_quantity: ~1 rows (approximately)
/*!40000 ALTER TABLE `remaining_transfer_quantity` DISABLE KEYS */;
INSERT INTO `remaining_transfer_quantity` (`trade_id`, `remaining_quantity`) VALUES
	(1, 41);
/*!40000 ALTER TABLE `remaining_transfer_quantity` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_information
CREATE TABLE IF NOT EXISTS `trade_information` (
  `trade_id` int(11) NOT NULL AUTO_INCREMENT,
  `counterpart` varchar(50) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `quantity` double NOT NULL,
  `uom` varchar(50) NOT NULL DEFAULT 'MT',
  `trade_price` double NOT NULL,
  `currency` varchar(50) NOT NULL,
  `maturity_date` date NOT NULL,
  `trade_type` varchar(50) NOT NULL,
  `completion_status` varchar(25) NOT NULL,
  `frozen_status` varchar(25) NOT NULL,
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='It captures  all the infromation related to trade.';

-- Dumping data for table bonzai_trading_system.trade_information: ~6 rows (approximately)
/*!40000 ALTER TABLE `trade_information` DISABLE KEYS */;
INSERT INTO `trade_information` (`trade_id`, `counterpart`, `internal_company`, `commodity`, `quantity`, `uom`, `trade_price`, `currency`, `maturity_date`, `trade_type`, `completion_status`, `frozen_status`) VALUES
	(1, 'DubaiOil', 'RelianceOil', 'wheat', 100, 'MT', 10, 'USD', '2017-07-24', 'Buy', 'Complete', 'False'),
	(2, 'Reliance', 'Ion', 'Rice', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen'),
	(3, 'Reliance', 'Ion', 'Rice', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen'),
	(4, 'Reliance', 'Ion', 'Rice', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen'),
	(5, 'Reliance', 'Ion', 'Rice', 100, 'MT', 14, 'INR', '2017-07-24', 'BUY', 'Complete', 'Not frozen'),
	(55, 'a', 'b', 'c', 100, 'Mt', 100, 'ccxc', '2017-07-24', 'xcxc', 'cscs', 'ccdc');
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
  PRIMARY KEY (`transfer_id`),
  KEY `FK_trade_transfer_trade_information` (`trade_id`),
  CONSTRAINT `FK_trade_transfer_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer: ~9 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer` DISABLE KEYS */;
INSERT INTO `trade_transfer` (`transfer_id`, `trade_id`, `transfer_type`, `quantity`, `open_quantity`, `transfer_price`, `currency`, `uom`, `transfer_date`, `commodity`) VALUES
	(1, 1, 'Build', 20, 20, 10, 'INR', 'MT', '2017-07-24', 'wheat'),
	(2, 1, 'Build', 30, 30, 10, 'INR', 'MT', '2017-07-24', 'wheat'),
	(3, 1, 'Build', 2, 2, 10, 'INR', 'MT', '2017-07-24', 'wheat'),
	(4, 1, 'Build', 1, 1, 10, 'INR', 'MT', '2017-07-24', 'wheat'),
	(5, 1, 'Build', 1, 1, 10, 'INR', 'MT', '2017-07-24', 'wheat'),
	(6, 1, 'Build', 1, 1, 10, 'USD', 'MT', '2017-07-24', 'wheat'),
	(7, 1, 'Build', 1, 1, 10, 'USD', 'MT', '2017-07-24', 'wheat'),
	(8, 1, 'Build', 1, 1, 666.6666666666667, 'INR', 'MT', '2017-07-24', 'wheat'),
	(9, 1, 'Build', 2, 2, 666.6666666666667, 'INR', 'MT', '2017-07-24', 'wheat');
/*!40000 ALTER TABLE `trade_transfer` ENABLE KEYS */;

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
	('2017-07-24', 2, 'loss', 2000);
/*!40000 ALTER TABLE `valuation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
