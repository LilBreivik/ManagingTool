-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 10. Okt 2018 um 09:35
-- Server-Version: 10.1.23-MariaDB-9+deb9u1
-- PHP-Version: 7.0.30-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `filesStorage`
--

-- --------------------------------------------------------
 
--
-- Constraints der exportierten Tabellen
--

 
--
-- Constraints der Tabelle `accounts`
--
ALTER TABLE `accounts` DROP FOREIGN KEY `USER_ID_FK`

ALTER TABLE `accounts` ADD CONSTRAINT `USER_ID_FK` FOREIGN KEY (`ACCOUNT_USER_ID`) REFERENCES `users`(`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `sessions`
--
ALTER TABLE `sessions` DROP  FOREIGN KEY  `ACCOUNT_SESSSION_ID_FK` 

ALTER TABLE `sessions` ADD CONSTRAINT `ACCOUNT_SESSSION_ID_FK` FOREIGN KEY (`SESSION_ACCOUNT_ID`) REFERENCES `accounts`(`ACCOUNT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- Constraints der Tabelle `reset_urls`
--
ALTER TABLE `reset_urls` DROP  FOREIGN KEY  `RESET_URL_ACCOUNT_ID_FK` 

ALTER TABLE `reset_urls` ADD CONSTRAINT `RESET_URL_ACCOUNT_ID_FK` FOREIGN KEY (`RESET_URL_ACCOUNT_ID`) REFERENCES `accounts`(`ACCOUNT_ID`) ON DELETE CASCADE ON UPDATE CASCADE;



