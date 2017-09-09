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
-- Database: `attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `atten`
--

CREATE TABLE IF NOT EXISTS `atten` (
  `student_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `lecture_no` int(11) NOT NULL,
  `topic` varchar(1000) NOT NULL,
  `attendance` tinyint(1) NOT NULL,
  `Class_id` varchar(11) NOT NULL,
  `subj_id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atten`
--

INSERT INTO `atten` (`student_id`, `date`, `lecture_no`, `topic`, `attendance`, `Class_id`, `subj_id`) VALUES
('100', '2017-06-04', 3, 'F', 1, 'BE-A', 'G'),
('123', '2017-05-29', 12, 'economy', 1, 'BE-B', 'eco'),
('31', '2017-05-29', 4, 'ds', 1, 'BE-B', 'comp'),
('3134085', '2017-05-29', 4, 'aaaaa', 1, 'BE-B', 'Maths'),
('34', '2017-05-28', 2, 'that', 1, 'BE-A', 'this'),
('5', '2017-06-04', 3, 'F', 1, 'BE-A', 'G'),
('500', '2017-05-30', 3, 'ds', 1, 'BE-C', 'it'),
('500', '2017-06-04', 7, 'ds', 1, 'BE-C', 'sd'),
('e136', '2017-05-29', 5, 'ds', 1, 'BE-C', 'it'),
('e43', '2017-05-28', 5, 'nice', 1, 'BE-C', 'new'),
('TYIT1', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT1', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT10', '2017-04-06', 1, 'Servlet', 0, '1', '1'),
('TYIT10', '2017-04-07', 1, 'JSP', 0, '1', '1'),
('TYIT11', '2017-04-06', 1, 'Servlet', 0, '1', '1'),
('TYIT11', '2017-04-07', 1, 'JSP', 0, '1', '1'),
('TYIT12', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT12', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT13', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT13', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT14', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT14', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT15', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT15', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT16', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT16', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT17', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT17', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT18', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT18', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT19', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT19', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT2', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT2', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT3', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT3', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT4', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT4', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT5', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT5', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT6', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT6', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT7', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT7', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT8', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT8', '2017-04-07', 1, 'JSP', 1, '1', '1'),
('TYIT9', '2017-04-06', 1, 'Servlet', 1, '1', '1'),
('TYIT9', '2017-04-07', 1, 'JSP', 1, '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `conducted`
--

CREATE TABLE IF NOT EXISTS `conducted` (
  `lecture_no` int(11) NOT NULL,
  `date` date NOT NULL,
  `class_id` varchar(11) NOT NULL,
  `subject_id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conducted`
--

INSERT INTO `conducted` (`lecture_no`, `date`, `class_id`, `subject_id`) VALUES
(0, '0000-00-00', '0', '0'),
(1, '0000-00-00', '1', '1'),
(1, '2017-04-06', '1', '1'),
(1, '2017-05-26', '1', '1'),
(1, '2017-05-27', '1', '1'),
(2, '2017-05-27', '0', '0'),
(2, '2017-05-28', 'BE-A', 'this'),
(3, '2017-05-29', 'BE-C', 'it'),
(3, '2017-05-30', 'BE-C', 'it'),
(3, '2017-06-04', 'BE-A', 'G'),
(3, '2017-06-04', 'BE-C', 'sd'),
(4, '2017-05-29', 'BE-B', 'comp'),
(5, '2017-05-29', 'BE-C', 'it'),
(7, '2017-06-04', 'BE-C', 'sd'),
(12, '2017-05-29', 'BE-B', 'eco');

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE IF NOT EXISTS `details` (
  `lno` int(11) NOT NULL,
  `classid` varchar(50) NOT NULL,
  `sub` varchar(50) NOT NULL,
  `topic` varchar(50) NOT NULL,
  `start` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`lno`, `classid`, `sub`, `topic`, `start`) VALUES
(3, 'BE-A', 'G', 'F', 1),
(7, 'BE-C', 'sd', 'ds', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `rollno` varchar(50) NOT NULL,
  `name` varchar(500) NOT NULL,
  `classid` varchar(50) NOT NULL,
  `mac` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`rollno`, `name`, `classid`, `mac`) VALUES
('500', 'Togo', 'BE-C', '000AF5FE41E'),
('100', 'radhi', 'BE-A', '00166D10832'),
('e43', 'bro', 'BE-C', '4480EBB7586'),
('3134085', 'Saurabh', 'BE-B', '806C1BA6731'),
('5', 'Shrey', 'BE-A', 'ACC1EE65B84');

-- --------------------------------------------------------

--
-- Table structure for table `stud_login`
--

CREATE TABLE IF NOT EXISTS `stud_login` (
  `stud_id` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stud_login`
--

INSERT INTO `stud_login` (`stud_id`, `pass`, `type`) VALUES
('500', 'aaa', 1),
('TYIT1', 'RASHMI123', 1),
('TYIT10', 'SNEHAL123', 1),
('TYIT11', 'SHIVANI123', 1),
('TYIT12', 'MAYURI123', 1),
('TYIT13', 'ADITYA123', 1),
('TYIT14', 'IFFAT123', 1),
('TYIT15', 'TAPAN123', 1),
('TYIT16', 'SHWETA123', 1),
('TYIT17', 'ABHISHEK123', 1),
('TYIT18', 'YASH123', 1),
('TYIT19', 'SAYALI123', 1),
('TYIT2', 'SUPRIYA123', 1),
('TYIT3', 'MEGHA123', 1),
('TYIT4', 'CHINMAY123', 1),
('TYIT5', 'SOMESH123', 1),
('TYIT6', 'PREETAM123', 1),
('TYIT7', 'ALISHA123', 1),
('TYIT8', 'SNEHAL123', 1),
('TYIT9', 'JAGANNATH123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` int(11) NOT NULL,
  `Teacher_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `Teacher_name`) VALUES
(1, ' Upadhaye'),
(2, ' Suparna Naik'),
(3, 'Anami'),
(4, 'aaa');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_login`
--

CREATE TABLE IF NOT EXISTS `teacher_login` (
  `id` int(11) NOT NULL,
  `User_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_login`
--

INSERT INTO `teacher_login` (`id`, `User_name`, `password`) VALUES
(1, 'Upadhaye', 'Upadhaye123'),
(2, 'Suparna.Naik', 'Suparna.Naik123'),
(3, 'Anami', 'Anami123'),
(4, 'aaa', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `atten`
--
ALTER TABLE `atten`
  ADD PRIMARY KEY (`student_id`,`date`,`lecture_no`),
  ADD KEY `Class_id` (`Class_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `conducted`
--
ALTER TABLE `conducted`
  ADD PRIMARY KEY (`lecture_no`,`date`,`class_id`);

--
-- Indexes for table `details`
--
ALTER TABLE `details`
  ADD PRIMARY KEY (`lno`,`classid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`mac`);

--
-- Indexes for table `stud_login`
--
ALTER TABLE `stud_login`
  ADD PRIMARY KEY (`stud_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- Indexes for table `teacher_login`
--
ALTER TABLE `teacher_login`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `teacher_login`
--
ALTER TABLE `teacher_login`
  ADD CONSTRAINT `teacher_login_ibfk_1` FOREIGN KEY (`id`) REFERENCES `teacher` (`teacher_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
