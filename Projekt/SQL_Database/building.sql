-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Maj 12, 2025 at 03:53 PM
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
  `watertemp` double DEFAULT 32,
  `airtemp` double DEFAULT 12,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `apartments`
--

INSERT INTO `apartments` (`id`, `nr`, `electricity`, `light`, `watertemp`, `airtemp`, `userid`) VALUES
(1, 0, 0, 0, 4.4, -4.6, 1),
(2, 12, 0, 0, 40.500000000000064, 8.000000000000014, 2),
(7, 13, 1, 1, 36.40000000000006, 7.400000000000016, 3),
(8, 14, 0, 0, 36.40000000000006, 7.400000000000016, 4);

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
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `role` varchar(15) NOT NULL,
  `pin` varchar(4) NOT NULL,
  `username` varchar(25) NOT NULL DEFAULT 'UNIQUE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `role`, `pin`, `username`) VALUES
(1, 'admin', '9999', 'admin'),
(2, 'user', '1234', 'Katarzyna'),
(3, 'user', '1111', 'Bob'),
(4, 'user', '2222', 'Robert'),
(5, 'user', '4444', 'Grzegorz');

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
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apartments`
--
ALTER TABLE `apartments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `doorstatus`
--
ALTER TABLE `doorstatus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartments`
--
ALTER TABLE `apartments`
  ADD CONSTRAINT `apartments_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
