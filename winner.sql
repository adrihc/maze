-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 08-12-2022 a las 00:07:06
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `maze`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `winner`
--

CREATE TABLE `winner` (
  `id` int NOT NULL,
  `username` varchar(40) NOT NULL,
  `score` bigint NOT NULL,
  `mapid` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `winner`
--

INSERT INTO `winner` (`id`, `username`, `score`, `mapid`) VALUES
(2, 'adri', 1709, 1),
(3, '1234', 2297, 1),
(4, 'Test Mapa 2', 21912, 2),
(5, 'Mapa 1', 2319, 1),
(6, 'Test Mapa 1', 1314, 1),
(7, 'Test Mapa 1-2', 1947, 1),
(8, 'Test Mapa 1-3', 2591, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `winner`
--
ALTER TABLE `winner`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `winner`
--
ALTER TABLE `winner`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
