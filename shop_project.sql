-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 20-Ago-2020 às 20:00
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `shop_project`
--
CREATE DATABASE IF NOT EXISTS `heroku_81dca3fb2356f49`;
USE `heroku_81dca3fb2356f49`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `county` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `address`
--

INSERT INTO `address` (`id`, `county`, `floor`, `neighborhood`, `number`, `postal_code`, `city_id`, `client_id`) VALUES
(1, 'Eevee Street', 'Ap 303', 'Garden', '300', '1345678', 1, 1),
(2, 'Kel Street', 'Ap 800', 'Center', '105', '741852', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Computadores'),
(2, 'Monitores'),
(3, 'Cadeiras Gaming'),
(4, 'Periféricos'),
(5, 'Hardware'),
(6, 'Mobilidade'),
(7, 'Armazenamento');

-- --------------------------------------------------------

--
-- Estrutura da tabela `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `city`
--

INSERT INTO `city` (`id`, `name`, `state_id`) VALUES
(1, 'Austin', 1),
(2, 'Sacramento', 2),
(3, 'San Diego', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `client`
--

INSERT INTO `client` (`id`, `email`, `id_card`, `name`, `type`) VALUES
(1, 'andre@gmail.com', '123456789', 'André', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `order_item`
--

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `order_item`
--

INSERT INTO `order_item` (`discount`, `price`, `quantity`, `product_id`, `order_id`) VALUES
(0, 2000, 1, 1, 1),
(0, 80, 2, 3, 1),
(100, 800, 1, 2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `order_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `payment`
--

INSERT INTO `payment` (`order_id`, `status`) VALUES
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `payment_with_card`
--

DROP TABLE IF EXISTS `payment_with_card`;
CREATE TABLE `payment_with_card` (
  `card_number` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `payment_with_card`
--

INSERT INTO `payment_with_card` (`card_number`, `order_id`) VALUES
(6, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `payment_with_check`
--

DROP TABLE IF EXISTS `payment_with_check`;
CREATE TABLE `payment_with_check` (
  `limit_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `payment_with_check`
--

INSERT INTO `payment_with_check` (`limit_date`, `payment_date`, `order_id`) VALUES
('2017-10-20 00:00:00', NULL, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `phone`
--

DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone` (
  `client_id` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `phone`
--

INSERT INTO `phone` (`client_id`, `phone`) VALUES
(1, '147882369'),
(1, '963852741');

-- --------------------------------------------------------

--
-- Estrutura da tabela `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `product`
--

INSERT INTO `product` (`id`, `name`, `price`) VALUES
(1, 'LeNovo 2020', 2000),
(2, 'AOC 2019', 400),
(3, 'HP 2018', 1400),
(4, 'Mesa Gaming', 500),
(5, 'Headset', 140),
(6, 'RAM 16GB', 75),
(7, 'Alpha Gamer', 180),
(8, 'Xiaomi', 300),
(9, 'Samsung 500GB', 50),
(10, 'Water Cooler Asus', 49),
(11, 'Processador Intel Pentium', 59);

-- --------------------------------------------------------

--
-- Estrutura da tabela `product_category`
--

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `product_category`
--

INSERT INTO `product_category` (`product_id`, `category_id`) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 4),
(6, 5),
(7, 3),
(8, 6),
(9, 7),
(10, 5),
(11, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `shop_order`
--

DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `order_adress_id` int(11) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `shop_order`
--

INSERT INTO `shop_order` (`id`, `time_stamp`, `client_id`, `order_adress_id`) VALUES
(1, '2017-09-30 10:32:00', 1, 1),
(2, '2017-10-10 19:35:00', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `state`
--

INSERT INTO `state` (`id`, `name`) VALUES
(1, 'Texas'),
(2, 'Califórnia');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpo044ng5x4gynb291cv24vtea` (`city_id`),
  ADD KEY `FK7156ty2o5atyuy9f6kuup9dna` (`client_id`);

--
-- Índices para tabela `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6p2u50v8fg2y0js6djc6xanit` (`state_id`);

--
-- Índices para tabela `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`);

--
-- Índices para tabela `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`);

--
-- Índices para tabela `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`order_id`);

--
-- Índices para tabela `payment_with_card`
--
ALTER TABLE `payment_with_card`
  ADD PRIMARY KEY (`order_id`);

--
-- Índices para tabela `payment_with_check`
--
ALTER TABLE `payment_with_check`
  ADD PRIMARY KEY (`order_id`);

--
-- Índices para tabela `phone`
--
ALTER TABLE `phone`
  ADD KEY `FK3o48ec26lujl3kf01hwqplhn2` (`client_id`);

--
-- Índices para tabela `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `product_category`
--
ALTER TABLE `product_category`
  ADD KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  ADD KEY `FK2k3smhbruedlcrvu6clued06x` (`product_id`);

--
-- Índices para tabela `shop_order`
--
ALTER TABLE `shop_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK81r7tfjoftcfe0jgkiykiu7ek` (`client_id`),
  ADD KEY `FKe4p3vcnhsvdo53o9bd9369yuu` (`order_adress_id`);

--
-- Índices para tabela `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `shop_order`
--
ALTER TABLE `shop_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `state`
--
ALTER TABLE `state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK7156ty2o5atyuy9f6kuup9dna` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKpo044ng5x4gynb291cv24vtea` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);

--
-- Limitadores para a tabela `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`);

--
-- Limitadores para a tabela `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK67irffekar4ku5ab7cx4wvrlv` FOREIGN KEY (`order_id`) REFERENCES `shop_order` (`id`);

--
-- Limitadores para a tabela `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FKk9kcndpsa2arsyn97v0ytajpm` FOREIGN KEY (`order_id`) REFERENCES `shop_order` (`id`);

--
-- Limitadores para a tabela `payment_with_card`
--
ALTER TABLE `payment_with_card`
  ADD CONSTRAINT `FK2csiaxrq7ghq3ukgtwiv4i5pf` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`);

--
-- Limitadores para a tabela `payment_with_check`
--
ALTER TABLE `payment_with_check`
  ADD CONSTRAINT `FKpjvq9r4xvduujoyba42x463r3` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`);

--
-- Limitadores para a tabela `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `FK3o48ec26lujl3kf01hwqplhn2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Limitadores para a tabela `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Limitadores para a tabela `shop_order`
--
ALTER TABLE `shop_order`
  ADD CONSTRAINT `FK81r7tfjoftcfe0jgkiykiu7ek` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKe4p3vcnhsvdo53o9bd9369yuu` FOREIGN KEY (`order_adress_id`) REFERENCES `address` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
