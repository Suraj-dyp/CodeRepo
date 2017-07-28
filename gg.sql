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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.cashflow_invoice_status
CREATE TABLE IF NOT EXISTS `cashflow_invoice_status` (
  `cashflow_id` int(11) NOT NULL,
  `invoice_id` varchar(122) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.company
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='This table contains names of the companies which can buy or sell a trade using Bonzai trading system.';

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.end_of_day
CREATE TABLE IF NOT EXISTS `end_of_day` (
  `date` date NOT NULL,
  `trade_id` int(11) NOT NULL,
  `transfer_id` int(11) DEFAULT NULL,
  `quantity` double NOT NULL,
  `trade_price` double NOT NULL,
  `market_price` double NOT NULL,
  `pnl_amount` double NOT NULL,
  UNIQUE KEY `date_trade_id_transfer_id` (`date`,`trade_id`,`transfer_id`),
  KEY `trade_id` (`trade_id`),
  KEY `transfer_id` (`transfer_id`),
  CONSTRAINT `trade_id` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_id` FOREIGN KEY (`transfer_id`) REFERENCES `trade_transfer` (`transfer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.foreign_exchange
CREATE TABLE IF NOT EXISTS `foreign_exchange` (
  `date` date NOT NULL,
  `currency` varchar(3) NOT NULL,
  `inr_factor` double NOT NULL,
  PRIMARY KEY (`date`,`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.invoice_status
CREATE TABLE IF NOT EXISTS `invoice_status` (
  `invoice_id` varchar(122) NOT NULL,
  `invoice_status` varchar(50) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.market_price
CREATE TABLE IF NOT EXISTS `market_price` (
  `date` date NOT NULL,
  `commodity` varchar(25) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT 'INR',
  PRIMARY KEY (`date`,`commodity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.remaining_transfer_quantity
CREATE TABLE IF NOT EXISTS `remaining_transfer_quantity` (
  `trade_id` int(11) NOT NULL,
  `remaining_quantity` double NOT NULL,
  KEY `FK_Remaining_Transfer_Quantity_trade_information` (`trade_id`),
  CONSTRAINT `FK_Remaining_Transfer_Quantity_trade_information` FOREIGN KEY (`trade_id`) REFERENCES `trade_information` (`trade_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.test
CREATE TABLE IF NOT EXISTS `test` (
  `a` int(11) NOT NULL,
  `b` double NOT NULL,
  `c` date NOT NULL,
  `d` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
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
  `maturity_date` date NOT NULL,
  `trade_type` varchar(50) NOT NULL,
  `completion_status` varchar(25) NOT NULL,
  `frozen_status` varchar(25) NOT NULL,
  `cashflow_generated` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='It captures  all the infromation related to trade.';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.valuation
CREATE TABLE IF NOT EXISTS `valuation` (
  `date` date NOT NULL,
  `trade_id` int(11) NOT NULL,
  `pnl_status` enum('profit','loss') DEFAULT NULL,
  `pnl_amount` double DEFAULT NULL,
  PRIMARY KEY (`date`,`trade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table bonzai_trading_system.warehouse
CREATE TABLE IF NOT EXISTS `warehouse` (
  `storgage_id` int(11) NOT NULL,
  `storage` varchar(50) NOT NULL,
  PRIMARY KEY (`storgage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
