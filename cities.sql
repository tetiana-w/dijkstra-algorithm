-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2020 at 04:05 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cities`
--
CREATE DATABASE IF NOT EXISTS `cities` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cities`;

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id_city` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id_city`, `name`, `x`, `y`) VALUES
(1, 'Flensburg', NULL, NULL),
(2, 'Oldenburg', NULL, NULL),
(3, 'Bremen', NULL, NULL),
(4, 'Hamburg', NULL, NULL),
(5, 'Rostock', NULL, NULL),
(6, 'Berlin', NULL, NULL),
(7, 'Magdeburg', NULL, NULL),
(8, 'Hannover', NULL, NULL),
(9, 'Osnabrück', NULL, NULL),
(10, 'Münster', NULL, NULL),
(11, 'Dortmund', NULL, NULL),
(12, 'Kassel', NULL, NULL),
(13, 'Leipzig', NULL, NULL),
(14, 'Dresden', NULL, NULL),
(15, 'Erfurt', NULL, NULL),
(16, 'Düsseldorf', NULL, NULL),
(17, 'Köln', NULL, NULL),
(18, 'Frankfurt', NULL, NULL),
(19, 'Nürnberg', NULL, NULL),
(20, 'München', NULL, NULL),
(21, 'Stuttgart', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `distance`
--

CREATE TABLE `distance` (
  `id_distance` int(11) NOT NULL,
  `id_city` int(11) NOT NULL,
  `id_neighbour_city` int(11) NOT NULL,
  `distance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `distance`
--

INSERT INTO `distance` (`id_distance`, `id_city`, `id_neighbour_city`, `distance`) VALUES
(1, 1, 4, 161),
(16, 2, 9, 112),
(15, 3, 2, 54),
(5, 4, 3, 126),
(2, 4, 5, 184),
(3, 4, 6, 289),
(4, 4, 8, 159),
(6, 5, 6, 242),
(9, 6, 7, 156),
(8, 6, 13, 191),
(7, 6, 14, 193),
(10, 7, 8, 149),
(39, 7, 13, 127),
(14, 8, 3, 135),
(13, 8, 9, 140),
(12, 8, 12, 168),
(18, 9, 10, 58),
(17, 9, 12, 179),
(19, 10, 11, 69),
(20, 11, 12, 164),
(22, 11, 16, 70),
(21, 11, 17, 94),
(38, 12, 13, 255),
(35, 14, 13, 123),
(34, 14, 15, 218),
(36, 15, 12, 155),
(37, 15, 13, 146),
(23, 16, 17, 45),
(24, 17, 18, 190),
(25, 18, 12, 180),
(26, 18, 15, 262),
(27, 18, 19, 225),
(28, 18, 21, 210),
(33, 19, 14, 317),
(32, 19, 15, 227),
(31, 20, 19, 170),
(30, 21, 19, 215),
(29, 21, 20, 233);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD UNIQUE KEY `id_city` (`id_city`);

--
-- Indexes for table `distance`
--
ALTER TABLE `distance`
  ADD PRIMARY KEY (`id_city`,`id_neighbour_city`),
  ADD UNIQUE KEY `id_distance` (`id_distance`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id_city` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `distance`
--
ALTER TABLE `distance`
  MODIFY `id_distance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
