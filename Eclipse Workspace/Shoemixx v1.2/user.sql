-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 12, 2017 at 03:36 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shoemixx-DB`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `privilege` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(300) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `privilege`, `username`, `password`, `firstname`, `lastname`) VALUES
(1, 'admin', 'admin', '1000:f456a8745253685bf974e7e2b8c68c86:31501147038619c59a6b56437948a8cdc06050e31371ed83ecd7dd5a6f733365ba443d3d170a38d6e237b36bfc3366f1ae322cc6f0e2967a5457fe29c0fb10dd', 'admin', 'test'),
(2, 'admin', 'admin2', '1000:efec88df6625918ee2eb8f6d26424a1a:09af6e895f16550974a9579f89f67a3a344040e02be336311b2a9e6ad025163b5668a284c252fca0a237f191687230369549b82523306ed432e45e4d794a88f0', 'admin2', 'admin2'),
(3, 'admin', 'admin3', '1000:7c146b670bb02a74fb1a2370b60b400a:92bd1d9bb15ec7159fc91810ed14e5a4352b42ea4207df01497f5221331670bc46416e03bf4ab2d47abd0d494df7f99045be44608c99b7dff3d18e321622f508', 'admin3', 'admin3'),
(4, 'cashier', 'cashier1', '1000:c5f44fbea8694b23d6646ad801a67ba1:edfcd0534a4d68fde5628810d326c68b84a0e6637e8c6398743d412aa10efdbef023e5c2d0f4d47af71d6d999aa642b530c7bf07e6084a2fbd6cbebee9c5fe46', 'cashier12', 'cashier1'),
(5, 'admin', 'staff1', '1000:05743c6ea565a88d5583337de4e29e86:bcb46f92450be911a1d991bad70fb1b7325d1188548d61e52687bf6204ba18283cea149f71f2fbd4a6ae317a66149cf1d037644b47a8e6c9f73f5a66fa451566', 'Renzo', 'Calma'),
(6, 'cashier', 'test6', '1000:cdeaae582040c0e98e0d64806a357ca0:a2ebf25ba843660547bb4d96fc912b0e268144d83e3f3e5c122896c71181f9a84c84ecd6d7bba9865ef722883a5fd4f9a5307654b3d64555a466d5b2239b7a3a', 'Calma', 'Jose');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
