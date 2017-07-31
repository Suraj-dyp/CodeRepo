-- --------------------------------------------------------
-- Host:                         127.0.0.1
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Shows cashflow for a particular trade';

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

-- Dumping structure for table bonzai_trading_system.company
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='This table contains names of the companies which can buy or sell a trade using Bonzai trading system.';

-- Dumping data for table bonzai_trading_system.company: ~6 rows (approximately)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`company_id`, `company_name`) VALUES
	(1, 'Cargill'),
	(2, 'Archer'),
	(3, 'Barry'),
	(4, 'Smithfield'),
	(5, 'Ingredion'),
	(23, 'Roshan');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.end_of_day
CREATE TABLE IF NOT EXISTS `end_of_day` (
  `date` date NOT NULL,
  `trade_id` int(11) NOT NULL,
  `transfer_id` int(11) DEFAULT NULL,
  `commodity` varchar(50) NOT NULL,
  `quantity` double NOT NULL,
  `uom` varchar(3) NOT NULL DEFAULT 'MT',
  `trade_price` double NOT NULL,
  `trade_currency` varchar(3) NOT NULL DEFAULT 'INR',
  `market_price` double NOT NULL,
  `market_currency` varchar(3) NOT NULL DEFAULT 'INR',
  `pnl_amount` double NOT NULL,
  `pnl_currency` varchar(3) NOT NULL DEFAULT 'INR',
  `storage` varchar(50) DEFAULT NULL,
  `counterpart` varchar(50) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  KEY `trade_id` (`trade_id`),
  KEY `transfer_id` (`transfer_id`),
  CONSTRAINT `trade_id` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_id` FOREIGN KEY (`transfer_id`) REFERENCES `trade_transfer` (`transfer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.end_of_day: ~11 rows (approximately)
/*!40000 ALTER TABLE `end_of_day` DISABLE KEYS */;
INSERT INTO `end_of_day` (`date`, `trade_id`, `transfer_id`, `commodity`, `quantity`, `uom`, `trade_price`, `trade_currency`, `market_price`, `market_currency`, `pnl_amount`, `pnl_currency`, `storage`, `counterpart`, `internal_company`) VALUES
	('2017-07-30', 1, NULL, 'rice', 0, 'MT', 23, 'INR', 20, 'INR', 0, 'INR', NULL, 'Cargill', 'Archer'),
	('2017-07-30', 2, NULL, 'wheat', 0, 'MT', 13, 'INR', 15, 'INR', 0, 'INR', NULL, 'Cargill', 'Archer'),
	('2017-07-30', 3, NULL, 'rice', 0, 'MT', 23, 'INR', 20, 'INR', 0, 'INR', NULL, 'Cargill', 'Archer'),
	('2017-07-30', 6, NULL, 'wheat', 0, 'MT', 12, 'INR', 15, 'INR', 0, 'INR', NULL, 'Barry', 'Smithfield'),
	('2017-07-30', 7, NULL, 'rice', 20, 'MT', 22, 'INR', 20, 'INR', -40, 'INR', NULL, 'Barry', 'Smithfield'),
	('2017-07-30', 10, NULL, 'wheat', 1000, 'MT', 13, 'INR', 15, 'INR', 2000, 'INR', NULL, 'Cargill', 'Ingredion'),
	('2017-07-30', 1, 1, 'rice', 100, 'MT', 23, 'INR', 20, 'INR', -300, 'INR', 'pune', 'Cargill', 'Archer'),
	('2017-07-30', 2, 2, 'wheat', 100, 'MT', 13, 'INR', 15, 'INR', 200, 'INR', 'pune', 'Cargill', 'Archer'),
	('2017-07-30', 3, 3, 'rice', 50, 'MT', 23, 'INR', 20, 'INR', -150, 'INR', 'pune', 'Cargill', 'Archer'),
	('2017-07-30', 6, 7, 'wheat', 100, 'MT', 12, 'INR', 15, 'INR', 300, 'INR', 'agra', 'Barry', 'Smithfield'),
	('2017-07-30', 7, 6, 'rice', 20, 'MT', 22, 'INR', 20, 'INR', -40, 'INR', 'agra', 'Barry', 'Smithfield');
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
	('2016-07-30', 'USD', 0.015),
	('2017-07-24', 'USD', 0.015),
	('2017-07-25', 'GBP', 0.015),
	('2017-07-30', 'GBP', 0.011),
	('2017-07-30', 'USD', 0.011);
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

-- Dumping data for table bonzai_trading_system.market_price: ~10 rows (approximately)
/*!40000 ALTER TABLE `market_price` DISABLE KEYS */;
INSERT INTO `market_price` (`date`, `commodity`, `price`, `currency`) VALUES
	('2017-07-25', 'rice', 25, 'INR'),
	('2017-07-25', 'wheat', 10, 'INR'),
	('2017-07-26', 'rice', 20, 'INR'),
	('2017-07-26', 'wheat', 15, 'INR'),
	('2017-07-27', 'rice', 25, 'INR'),
	('2017-07-27', 'wheat', 10, 'INR'),
	('2017-07-28', 'rice', 20, 'INR'),
	('2017-07-28', 'wheat', 15, 'INR'),
	('2017-08-10', 'rice', 20, 'INR'),
	('2017-08-10', 'wheat', 15, 'INR');
/*!40000 ALTER TABLE `market_price` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.remaining_transfer_quantity
CREATE TABLE IF NOT EXISTS `remaining_transfer_quantity` (
  `trade_id` int(11) NOT NULL,
  `remaining_quantity` double NOT NULL,
  KEY `FK_Remaining_Transfer_Quantity_trade_information` (`trade_id`),
  CONSTRAINT `FK_Remaining_Transfer_Quantity_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.remaining_transfer_quantity: ~0 rows (approximately)
/*!40000 ALTER TABLE `remaining_transfer_quantity` DISABLE KEYS */;
/*!40000 ALTER TABLE `remaining_transfer_quantity` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.test
CREATE TABLE IF NOT EXISTS `test` (
  `a` int(11) NOT NULL,
  `b` double NOT NULL,
  `c` date NOT NULL,
  `d` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.test: ~0 rows (approximately)
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`a`, `b`, `c`, `d`) VALUES
	(11, 22, '2017-07-26', 'Suraj');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.trade_information
CREATE TABLE IF NOT EXISTS `trade_information` (
  `trade_id` int(11) NOT NULL AUTO_INCREMENT,
  `counterpart` varchar(50) NOT NULL,
  `internal_company` varchar(50) NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `quantity` double NOT NULL,
  `uom` varchar(3) NOT NULL DEFAULT 'MT',
  `trade_price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  `trade_date` date NOT NULL,
  `trade_type` varchar(50) NOT NULL,
  `completion_status` varchar(25) NOT NULL,
  `frozen_status` varchar(25) NOT NULL,
  `cashflow_generated` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='It captures  all the infromation related to trade.';

-- Dumping data for table bonzai_trading_system.trade_information: ~8 rows (approximately)
/*!40000 ALTER TABLE `trade_information` DISABLE KEYS */;
INSERT INTO `trade_information` (`trade_id`, `counterpart`, `internal_company`, `commodity`, `quantity`, `uom`, `trade_price`, `currency`, `trade_date`, `trade_type`, `completion_status`, `frozen_status`, `cashflow_generated`) VALUES
	(1, 'Cargill', 'Archer', 'rice', 100, 'MT', 23, 'INR', '2017-08-10', 'BUY', 'Complete', 'Not Frozen', 'FALSE'),
	(2, 'Cargill', 'Archer', 'wheat', 100, 'MT', 13, 'INR', '2017-08-10', 'BUY', 'Complete', 'Not Frozen', 'FALSE'),
	(3, 'Cargill', 'Archer', 'rice', 50, 'MT', 23, 'INR', '2017-08-10', 'SELL', 'Complete', 'Not Frozen', 'FALSE'),
	(4, 'Cargill', 'Archer', 'wheat', 50, 'MT', 13, 'INR', '2017-08-11', 'SELL', 'Complete', 'Not Frozen', 'FALSE'),
	(5, 'Barry', 'Smithfield', 'rice', 100, 'MT', 22, 'INR', '2017-08-11', 'BUY', 'Complete', 'Not Frozen', 'FALSE'),
	(6, 'Barry', 'Smithfield', 'wheat', 100, 'MT', 12, 'INR', '2017-08-09', 'BUY', 'Complete', 'Not Frozen', 'FALSE'),
	(7, 'Barry', 'Smithfield', 'rice', 40, 'MT', 22, 'INR', '2017-08-09', 'SELL', 'Complete', 'Not Frozen', 'FALSE'),
	(8, 'Barry', 'Smithfield', 'wheat', 40, 'MT', 12, 'INR', '2017-08-12', 'SELL', 'Complete', 'Not Frozen', 'FALSE'),
	(9, 'Cargill', 'Ingredion', 'rice', 1000, 'MT', 23, 'INR', '2017-08-12', 'BUY', 'Complete', 'Not Frozen', 'FALSE'),
	(10, 'Cargill', 'Ingredion', 'wheat', 1000, 'MT', 13, 'INR', '2017-08-10', 'BUY', 'Complete', 'Not Frozen', 'FALSE');
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
  `storage_id` int(11) NOT NULL,
  PRIMARY KEY (`transfer_id`),
  KEY `FK_trade_transfer_trade_information` (`trade_id`),
  CONSTRAINT `FK_trade_transfer_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer: ~7 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer` DISABLE KEYS */;
INSERT INTO `trade_transfer` (`transfer_id`, `trade_id`, `transfer_type`, `quantity`, `open_quantity`, `transfer_price`, `currency`, `uom`, `transfer_date`, `commodity`, `cashflow_generated`, `storage_id`) VALUES
	(1, 1, 'Build', 100, 50, 23, 'INR', 'MT', '2017-08-05', 'rice', 'FALSE', 1),
	(2, 2, 'Build', 100, 75, 13, 'INR', 'MT', '2017-08-07', 'wheat', 'FALSE', 1),
	(3, 3, 'Draw', 50, 0, 23, 'INR', 'MT', '2017-08-10', 'rice', 'FALSE', 1),
	(4, 4, 'Draw', 25, 0, 13, 'INR', 'MT', '2017-08-10', 'wheat', 'FALSE', 1),
	(5, 5, 'Build', 100, 80, 22, 'INR', 'MT', '2017-08-08', 'rice', 'FALSE', 2),
	(6, 7, 'Draw', 20, 0, 22, 'INR', 'MT', '2017-08-09', 'rice', 'FALSE', 2),
	(7, 6, 'Build', 100, 100, 12, 'INR', 'MT', '2017-08-09', 'wheat', 'FALSE', 2);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.trade_transfer_sk: ~0 rows (approximately)
/*!40000 ALTER TABLE `trade_transfer_sk` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade_transfer_sk` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.valuation
CREATE TABLE IF NOT EXISTS `valuation` (
  `date` date NOT NULL,
  `trade_id` int(11) NOT NULL,
  `pnl_status` enum('profit','loss') DEFAULT NULL,
  `pnl_amount` double DEFAULT NULL,
  PRIMARY KEY (`date`,`trade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.valuation: ~0 rows (approximately)
/*!40000 ALTER TABLE `valuation` DISABLE KEYS */;
INSERT INTO `valuation` (`date`, `trade_id`, `pnl_status`, `pnl_amount`) VALUES
	('2017-07-25', 18, 'profit', 764);
/*!40000 ALTER TABLE `valuation` ENABLE KEYS */;

-- Dumping structure for table bonzai_trading_system.warehouse
CREATE TABLE IF NOT EXISTS `warehouse` (
  `storage_id` int(11) NOT NULL,
  `storage` varchar(50) NOT NULL,
  PRIMARY KEY (`storage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bonzai_trading_system.warehouse: ~2 rows (approximately)
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` (`storage_id`, `storage`) VALUES
	(1, 'pune'),
	(2, 'agra'),
	(3, 'patna');
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
