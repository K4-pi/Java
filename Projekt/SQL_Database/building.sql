-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Maj 18, 2025 at 02:03 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

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
-- Struktura tabeli dla tabeli `apartments`
--

CREATE TABLE `apartments` (
  `id` int(11) NOT NULL,
  `nr` int(11) DEFAULT NULL CHECK (`nr` >= 0),
  `electricity` tinyint(1) DEFAULT 0,
  `light` tinyint(1) DEFAULT 0,
  `watertemp` decimal(4,1) DEFAULT NULL,
  `airtemp` decimal(4,1) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `apartments`
--

INSERT INTO `apartments` (`id`, `nr`, `electricity`, `light`, `watertemp`, `airtemp`, `userid`) VALUES
(1, 0, 0, 0, 4.4, -4.6, 1),
(7, 13, 0, 1, 39.6, 5.5, 30),
(8, 14, 0, 0, 37.1, 6.8, NULL),
(14, 98, 0, 0, 32.0, 12.0, 27),
(17, 67, 1, 0, 32.0, 12.0, 23),
(18, 76, 0, 0, 30.6, 13.0, 25),
(23, 12, 0, 0, 32.0, 12.0, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `doorstatus`
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
-- Struktura tabeli dla tabeli `reports`
--

CREATE TABLE `reports` (
  `id` int(11) NOT NULL,
  `messagetime` timestamp NOT NULL DEFAULT current_timestamp(),
  `message` varchar(150) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `apartmentnr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`id`, `messagetime`, `message`, `username`, `userid`, `apartmentnr`) VALUES
(3, '2025-05-17 17:45:41', 'testowy report 3', 'Karol', 23, 67);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
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
(23, 'user', '1234', 'Karol'),
(25, 'user', '1234', 'koks'),
(26, 'user', '1234', 'koksss'),
(27, 'user', '1234', 'test'),
(28, 'admin', '1234', 'test2'),
(30, 'user', '1234', 'user');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `apartments`
--
ALTER TABLE `apartments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nr` (`nr`),
  ADD KEY `userid` (`userid`);

--
-- Indeksy dla tabeli `doorstatus`
--
ALTER TABLE `doorstatus`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`userid`);

--
-- Indeksy dla tabeli `users`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `doorstatus`
--
ALTER TABLE `doorstatus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

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
