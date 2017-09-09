-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2017 at 03:01 AM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wipat`
--

-- --------------------------------------------------------

--
-- Table structure for table `access_points`
--

CREATE TABLE IF NOT EXISTS `access_points` (
  `ssid` varchar(500) NOT NULL,
  `mac_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `access_points`
--

INSERT INTO `access_points` (`ssid`, `mac_id`) VALUES
('DIRECT-T7RISHImsWJ', '9e:ad:97:ca:57:2f'),
('Rishi2', '00:17:7c:31:1b:23'),
('Rishi2', '98:de:d0:5b:de:1e');

-- --------------------------------------------------------

--
-- Table structure for table `readings`
--

CREATE TABLE IF NOT EXISTS `readings` (
  `position_id` text NOT NULL,
  `ssid` text NOT NULL,
  `mac_id` text NOT NULL,
  `rssi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `readings`
--

INSERT INTO `readings` (`position_id`, `ssid`, `mac_id`, `rssi`) VALUES
('inside', 'Rishi2', '00:17:7c:31:1b:23', -38),
('inside', 'Tp_Link_4F7FEC', 'c4:e9:84:4f:7f:ec', -94),
('inside', 'Redmi', 'c4:0b:cb:69:61:47', -45),
('inside', 'Rishi2', '98:de:d0:5b:de:1e', -83),
('inside', 'DIRECT-T7RISHImsWJ', '9e:ad:97:ca:57:2f', -26),
('outside', 'PDGaikwad', '54:b8:0a:0e:ab:d4', -82),
('outside', 'vijeesh', '0c:d2:b5:3f:d1:88', -92),
('outside', '', '00:0d:67:3c:de:08', -90),
('outside', 'Rishi2', '00:17:7c:31:1b:23', -42),
('outside', 'DIRECT-T7RISHImsWJ', '9e:ad:97:ca:57:2f', -45),
('outside', 'Redmi', 'c4:0b:cb:69:61:47', -49),
('outside', 'Rishi2', '98:de:d0:5b:de:1e', -81);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access_points`
--
ALTER TABLE `access_points`
  ADD PRIMARY KEY (`ssid`,`mac_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
