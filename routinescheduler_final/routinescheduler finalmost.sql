-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 11, 2018 at 05:40 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `routinescheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `course_id` varchar(50) NOT NULL,
  `course_title` varchar(50) NOT NULL,
  `course_credit` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_title`, `course_credit`) VALUES
('IT 1101', 'IT Fundamentals', 3),
('IT 1103', 'Introduction to Programming Environment', 3),
('IT 1105', 'Electrical Circuits', 3),
('IT 1107', 'Differential and Integral Calculus', 3),
('IT 1109', 'Communicative English', 3),
('IT 1104', 'Structured Programming Language Lab', 1.5),
('IT 1106', 'Electrical Circuits Lab', 1.5),
('IT 2101', 'Algorithm Analysis ', 3),
('IT 2103', 'Computer Architecture ', 3),
('IT 2105', 'Electronic Devices and Circuits ', 3),
('IT 2107', 'Discrete Mathematics', 3),
('IT 2109', 'Statistical and Probability Theory ', 3),
('IT 2102', 'Algorithm Analysis Lab ', 1.5),
('IT 2104', 'Computer Architecture Lab ', 1.5),
('IT 2106', 'Electronic Devices and Circuits Lab ', 1.5),
('IT 3101', 'Database Management System ', 3),
('IT 3103', 'Computer Network and Internet Technology ', 3),
('IT 3105', 'Signal and System ', 3),
('IT 3107', 'Operating System ', 3),
('IT 3109', 'Simulation and Modelling', 3),
('IT 3102', 'Database Management System Lab ', 1.5),
('IT 3104', 'Computer Network and Internet Technology Lab ', 1.5),
('IT 3108', 'Operating System Lab ', 1.5),
('IT 4101', 'Artificial Intelligences & Neural Networks ', 3),
('IT 4103', 'Management Information System ', 3),
('IT 4105', 'Human Computer Interfacing ', 3),
('IT 4107', 'Parallel and Distributed System ', 3),
('IT 4109', 'Multimedia Systems & Application ', 3),
('IT 4102', 'Artificial Intelligences & Neural Networks Lab ', 1.5);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `room_no` varchar(11) NOT NULL,
  `floor` int(5) NOT NULL,
  `room_type` varchar(35) NOT NULL,
  `capacity` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_no`, `floor`, `room_type`, `capacity`) VALUES
('Lab-1', 3, 'Computer Lab', 60),
('Lab-2', 3, 'Computer Lab', 55),
('Lab-3', 3, 'Computer Lab', 80),
('232', 2, 'Computer Lab', 55);

-- --------------------------------------------------------

--
-- Table structure for table `routine`
--

DROP TABLE IF EXISTS `routine`;
CREATE TABLE IF NOT EXISTS `routine` (
  `courseid` varchar(50) NOT NULL,
  `teacherid` varchar(50) NOT NULL,
  `roomid` varchar(50) NOT NULL,
  `classtime` varchar(70) NOT NULL,
  `classorder` int(255) NOT NULL,
  `uuid` varchar(70) NOT NULL,
  `saveddate` timestamp NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `routine`
--

INSERT INTO `routine` (`courseid`, `teacherid`, `roomid`, `classtime`, `classorder`, `uuid`, `saveddate`) VALUES
('IT 1101', 'K M Akkas Ali', 'Lab-1', 'THU 08:30 - 09:50', 1, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1101', 'K M Akkas Ali', 'Lab-2', 'MON 08:30 - 09:50', 2, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1103', 'Jesmin Akhter', 'Lab-1', 'WED 10:00 - 11:20', 3, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1103', 'Jesmin Akhter', '232', 'MON 10:00 - 11:20', 4, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1105', 'M Shamim Kaiser', '232', 'SUN 10:00 - 11:20', 5, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1105', 'M Shamim Kaiser', 'Lab-1', 'TUE 10:00 - 11:20', 6, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1107', 'M Mesbahuddin Sarker', 'Lab-1', 'THU 10:00 - 11:20', 7, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1107', 'M Mesbahuddin Sarker', 'Lab-2', 'WED 08:30 - 09:50', 8, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1109', 'Risala Tahsin Khan', 'Lab-2', 'TUE 11:30 - 12:50', 9, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1109', 'Risala Tahsin Khan', 'Lab-3', 'SUN 11:30 - 12:50', 10, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1104', 'Jesmin Akhter', '232', 'THU 11:30 - 12:50', 11, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1106', 'M Shamim Kaiser', '232', 'THU 02:00 - 04:50', 12, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2101', 'Jesmin Akhter', '232', 'WED 11:30 - 12:50', 13, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2101', 'Jesmin Akhter', 'Lab-2', 'SUN 10:00 - 11:20', 14, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2103', 'Mohammad Abu Yusuf', 'Lab-3', 'WED 10:00 - 11:20', 15, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2103', 'Mohammad Abu Yusuf', 'Lab-3', 'TUE 10:00 - 11:20', 16, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2105', 'M Shamim Kaiser', '232', 'SUN 11:30 - 12:50', 17, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2105', 'M Shamim Kaiser', '232', 'TUE 11:30 - 12:50', 18, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2107', 'Md. Wahiduzzaman', 'Lab-1', 'MON 10:00 - 11:20', 19, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2107', 'Md. Wahiduzzaman', 'Lab-3', 'MON 02:00 - 04:50', 20, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2109', 'Md. Fazlul Karim Patwary', 'Lab-3', 'THU 08:30 - 09:50', 21, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2109', 'Md. Fazlul Karim Patwary', '232', 'MON 08:30 - 09:50', 22, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2102', 'Mohammad Abu Yusuf', 'Lab-2', 'THU 02:00 - 04:50', 23, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2104', 'Jesmin Akhter', 'Lab-3', 'TUE 02:00 - 04:50', 24, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 2106', 'M Shamim Kaiser', 'Lab-1', 'SUN 02:00 - 04:50', 25, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3101', 'Fahima Tabassum', '232', 'WED 10:00 - 11:20', 26, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3101', 'Fahima Tabassum', 'Lab-1', 'THU 11:30 - 12:50', 27, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3103', 'Risala Tahsin Khan', 'Lab-2', 'SUN 08:30 - 09:50', 28, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3103', 'Risala Tahsin Khan', '232', 'WED 11:30 - 12:50', 29, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3105', 'K M Akkas Ali', 'Lab-1', 'TUE 08:30 - 09:50', 30, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3105', 'K M Akkas Ali', 'Lab-2', 'WED 08:30 - 09:50', 31, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3107', 'Md. Wahiduzzaman', '232', 'THU 10:00 - 11:20', 32, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3107', 'Md. Wahiduzzaman', 'Lab-1', 'SUN 11:30 - 12:50', 33, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3109', 'Mohammad Abu Yusuf', 'Lab-3', 'TUE 11:30 - 12:50', 34, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3109', 'Mohammad Abu Yusuf', 'Lab-3', 'MON 10:00 - 11:20', 35, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3102', 'Md. Fazlul Karim Patwary', 'Lab-2', 'MON 02:00 - 04:50', 36, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3104', 'Risala Tahsin Khan', 'Lab-1', 'THU 02:00 - 04:50', 37, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 3108', 'Md. Wahiduzzaman', 'Lab-1', 'TUE 02:00 - 04:50', 38, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4101', 'Fahima Tabassum', '232', 'TUE 10:00 - 11:20', 39, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4101', 'Fahima Tabassum', 'Lab-2', 'THU 08:30 - 09:50', 40, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4103', 'Md. Wahiduzzaman', 'Lab-2', 'WED 10:00 - 11:20', 41, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4103', 'Md. Wahiduzzaman', 'Lab-3', 'MON 11:30 - 12:50', 42, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4105', 'M Mesbahuddin Sarker', 'Lab-3', 'MON 08:30 - 09:50', 43, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4105', 'M Mesbahuddin Sarker', 'Lab-2', 'SUN 11:30 - 12:50', 44, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4107', 'Mohammad Abu Yusuf', 'Lab-3', 'WED 08:30 - 09:50', 45, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4107', 'Mohammad Abu Yusuf', 'Lab-2', 'THU 10:00 - 11:20', 46, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4109', 'Md. Fazlul Karim Patwary', '232', 'THU 11:30 - 12:50', 47, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4109', 'Md. Fazlul Karim Patwary', '232', 'TUE 08:30 - 09:50', 48, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 4102', 'Fahima Tabassum', 'Lab-3', 'MON 02:00 - 04:50', 49, '59317a8a-8b3e-40d0-b8eb-a11ec07d8995', '2018-01-11 15:58:55'),
('IT 1101', 'K M Akkas Ali', '232', 'WED 08:30 - 09:50', 1, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1101', 'K M Akkas Ali', 'Lab-3', 'SUN 08:30 - 09:50', 2, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1103', 'Jesmin Akhter', 'Lab-2', 'TUE 11:30 - 12:50', 3, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1103', 'Jesmin Akhter', 'Lab-3', 'THU 11:30 - 12:50', 4, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1105', 'M Shamim Kaiser', 'Lab-3', 'TUE 08:30 - 09:50', 5, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1105', 'M Shamim Kaiser', 'Lab-2', 'MON 10:00 - 11:20', 6, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1107', 'M Mesbahuddin Sarker', 'Lab-1', 'WED 10:00 - 11:20', 7, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1107', 'M Mesbahuddin Sarker', '232', 'THU 08:30 - 09:50', 8, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1109', 'Risala Tahsin Khan', '232', 'MON 08:30 - 09:50', 9, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1109', 'Risala Tahsin Khan', 'Lab-3', 'THU 10:00 - 11:20', 10, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1104', 'Jesmin Akhter', 'Lab-3', 'TUE 02:00 - 04:50', 11, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 1106', 'M Shamim Kaiser', 'Lab-3', 'SUN 02:00 - 04:50', 12, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2101', 'Mohammad Abu Yusuf', 'Lab-1', 'WED 08:30 - 09:50', 13, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2101', 'Mohammad Abu Yusuf', 'Lab-1', 'MON 10:00 - 11:20', 14, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2103', 'Jesmin Akhter', 'Lab-3', 'MON 11:30 - 12:50', 15, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2103', 'Jesmin Akhter', 'Lab-1', 'TUE 10:00 - 11:20', 16, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2105', 'M Shamim Kaiser', 'Lab-2', 'THU 08:30 - 09:50', 17, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2105', 'M Shamim Kaiser', 'Lab-3', 'TUE 11:30 - 12:50', 18, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2107', 'M Mesbahuddin Sarker', 'Lab-2', 'SUN 10:00 - 11:20', 19, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2107', 'M Mesbahuddin Sarker', 'Lab-2', 'THU 11:30 - 12:50', 20, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2109', 'Md. Fazlul Karim Patwary', '232', 'SUN 08:30 - 09:50', 21, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2109', 'Md. Fazlul Karim Patwary', 'Lab-1', 'MON 08:30 - 09:50', 22, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2102', 'Mohammad Abu Yusuf', 'Lab-3', 'WED 02:00 - 04:50', 23, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2104', 'Jesmin Akhter', 'Lab-1', 'THU 02:00 - 04:50', 24, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 2106', 'M Shamim Kaiser', 'Lab-2', 'MON 02:00 - 04:50', 25, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3101', 'Fahima Tabassum', 'Lab-3', 'SUN 11:30 - 12:50', 26, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3101', 'Fahima Tabassum', 'Lab-2', 'WED 08:30 - 09:50', 27, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3103', 'Risala Tahsin Khan', 'Lab-1', 'TUE 11:30 - 12:50', 28, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3103', 'Risala Tahsin Khan', '232', 'MON 10:00 - 11:20', 29, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3105', 'K M Akkas Ali', 'Lab-1', 'SUN 08:30 - 09:50', 30, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3105', 'K M Akkas Ali', 'Lab-1', 'MON 08:30 - 09:50', 31, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3107', 'Md. Wahiduzzaman', 'Lab-1', 'SUN 10:00 - 11:20', 32, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3107', 'Md. Wahiduzzaman', 'Lab-2', 'THU 10:00 - 11:20', 33, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3109', 'Mohammad Abu Yusuf', '232', 'MON 11:30 - 12:50', 34, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3109', 'Mohammad Abu Yusuf', 'Lab-2', 'WED 10:00 - 11:20', 35, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3102', 'Md. Fazlul Karim Patwary', 'Lab-1', 'WED 02:00 - 04:50', 36, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3104', 'Risala Tahsin Khan', 'Lab-1', 'TUE 02:00 - 04:50', 37, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 3108', 'Md. Wahiduzzaman', '232', 'MON 02:00 - 04:50', 38, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4101', 'Fahima Tabassum', '232', 'TUE 10:00 - 11:20', 39, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4101', 'Fahima Tabassum', 'Lab-3', 'SUN 10:00 - 11:20', 40, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4103', 'M Shamim Kaiser', 'Lab-2', 'THU 10:00 - 11:20', 41, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4103', 'M Shamim Kaiser', 'Lab-3', 'WED 11:30 - 12:50', 42, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4105', 'Md. Wahiduzzaman', 'Lab-2', 'SUN 11:30 - 12:50', 43, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4105', 'Md. Wahiduzzaman', 'Lab-2', 'MON 10:00 - 11:20', 44, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4107', 'M Mesbahuddin Sarker', '232', 'TUE 11:30 - 12:50', 45, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4107', 'M Mesbahuddin Sarker', 'Lab-2', 'MON 11:30 - 12:50', 46, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4109', 'Mohammad Abu Yusuf', '232', 'TUE 08:30 - 09:50', 47, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4109', 'Mohammad Abu Yusuf', 'Lab-3', 'SUN 08:30 - 09:50', 48, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50'),
('IT 4102', 'Fahima Tabassum', '232', 'SUN 02:00 - 04:50', 49, 'b7c4ad72-7c9d-44b6-a2c0-a5128ce3b26a', '2018-01-11 16:29:50');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` varchar(20) NOT NULL,
  `teacher_name` varchar(50) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `department_name` varchar(35) NOT NULL,
  `email_id` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `designation`, `department_name`, `email_id`) VALUES
('KMA', 'K M Akkas Ali', 'Associate PRofessor', 'IIT', 'akkas@juniv.edu'),
('FT', 'Fahima Tabassum', 'Associate Professor', 'IIT', 'fahima@juniv.edu'),
('RTK', 'Risala Tahsin Khan', 'Associate Professor', 'IIT', 'risala@juniv.edu'),
('MAY', 'Mohammad Abu Yusuf', 'Associate Professor', 'IIT', 'yousuf@juniv.edu'),
('JA', 'Jesmin Akhter', 'Associate Professor', 'IIT', 'jesmin@juniv.edu'),
('MMS', 'M Mesbahuddin Sarker', 'Associate Professor', 'IIT', 'sarker@juniv.edu'),
('WZ', 'Md. Wahiduzzaman', 'Associate Professor', 'IIT', 'ohiduzzaman@yahoo.com'),
('FKP', 'Md. Fazlul Karim Patwary', 'Associate Professor', 'IIT', 'patwary@juniv.edu'),
('MSK', 'M Shamim Kaiser', 'Associate Professor', 'IIT', 'mkaiser@juniv.edu'),
('MSI', 'Md. Shahidul Islam', 'Assistant Professor', 'IIT', 'msislam@juniv.edu');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
