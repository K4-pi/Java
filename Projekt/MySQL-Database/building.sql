-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2025 at 09:50 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `building`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartments`
--

CREATE TABLE `apartments` (
  `id` int(11) NOT NULL,
  `nr` int(11) DEFAULT NULL CHECK (`nr` >= 0),
  `electricity` tinyint(1) DEFAULT 0,
  `light` tinyint(1) DEFAULT 0,
  `watertemp` float DEFAULT 16,
  `airtemp` float DEFAULT 16,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `apartments`
--

INSERT INTO `apartments` (`id`, `nr`, `electricity`, `light`, `watertemp`, `airtemp`, `userid`) VALUES
(17, 67, 1, 1, 31.7, 12.9, 37),
(25, 10, 0, 0, 16, 16, NULL),
(26, 25, 1, 1, 17, 16.5, 32),
(27, 100, 0, 0, 16, 16, NULL),
(28, 11, 0, 0, 16, 16, 35),
(29, 12, 1, 0, 16, 16, 36),
(30, 13, 0, 0, 16, 16, 33),
(31, 14, 0, 0, 16, 16, NULL),
(32, 15, 0, 0, 16, 16, NULL),
(33, 99, 0, 0, 16, 16, NULL),
(34, 101, 0, 0, 16, 16, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `doorstatus`
--

CREATE TABLE `doorstatus` (
  `id` int(11) NOT NULL,
  `isclosed` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doorstatus`
--

INSERT INTO `doorstatus` (`id`, `isclosed`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `messagetime` timestamp NOT NULL DEFAULT current_timestamp(),
  `message` varchar(150) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `apartmentnr` int(11) NOT NULL,
  `description` varchar(100) DEFAULT 'No description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `role` varchar(15) NOT NULL,
  `pin` varchar(4) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `role`, `pin`, `username`) VALUES
(1, 'admin', '9999', 'admin'),
(32, 'user', '9021', 'Kowalscy'),
(33, 'user', '1243', 'Wiśniewscy'),
(35, 'user', '1122', 'Lewandowscy'),
(36, 'user', '1234', 'Nowak'),
(37, 'user', '8975', 'Kamińscy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartments`
--
ALTER TABLE `apartments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nr` (`nr`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `doorstatus`
--
ALTER TABLE `doorstatus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`userid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apartments`
--
ALTER TABLE `apartments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `doorstatus`
--
ALTER TABLE `doorstatus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartments`
--
ALTER TABLE `apartments`
  ADD CONSTRAINT `apartments_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userid`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
