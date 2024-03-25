-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 06, 2024 at 01:44 AM
-- Server version: 8.0.35
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `muscu19`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int NOT NULL,
  `nom` text,
  `Prenom` text,
  `email` varchar(255) DEFAULT NULL,
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `taille` int DEFAULT NULL,
  `poids` int DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `code_postal` int DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `Experience` varchar(20) DEFAULT NULL,
  `sexe` varchar(20) DEFAULT NULL,
  `corpulence` varchar(20) DEFAULT NULL,
  `nom_Photo_Face` text,
  `nom_Photo_Derriere` text,
  `nom_Photo_Droite` text,
  `nom_Photo_Gauche` text,
  `age` int DEFAULT NULL,
  `niveau_activite` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom`, `Prenom`, `email`, `nom_utilisateur`, `taille`, `poids`, `adresse`, `code_postal`, `description`, `Experience`, `sexe`, `corpulence`, `nom_Photo_Face`, `nom_Photo_Derriere`, `nom_Photo_Droite`, `nom_Photo_Gauche`, `age`, `niveau_activite`) VALUES
(1, 'fqsdfdsfqsdf', 'sdfsdfqsdfqsd', 'jhsrika06@gmail.com', 'dsqfdsfqsdfq', 157, 48, 'jhsrika06@gmail.com', 456456, 'fqsfdsfqsdf', 'professionnel', 'M', 'Endomorphe', NULL, NULL, NULL, NULL, 21, 'Activité Modérée'),
(2, 'Anani', 'Sofaîna ', 'jhsrika06@gmail.com', 'MitrailleuseDz', 159, 46, '51 Rue Albert Camus', 6300, 'Problèmes aux genoux.\nProblèmes aux cervicales.\nProblèmes au coude.\ndéséquilibre, bras et jambes', 'Débutant', 'F', 'Mesomorphe', NULL, NULL, NULL, NULL, 21, 'Activité Modérée'),
(3, 'Anani', 'SofaÏna', 'jhsrika06@gmail.com', 'MitrailleuseDZ', 159, 46, '51 Rue Albert Camus ', 5100, ';phobie hopital', 'Débutant', 'F', 'Ectomorphe', NULL, NULL, NULL, NULL, 21, 'Activité Modérée'),
(4, 'Boue', 'Thomas', 'jhsrika06@gmail.com', 'zazadu93', 160, 57, '51 rue albert Camus', 5100, 'problèmes au dos \nproblèmes au tibiats\nproblèmes au côtes \nproblèmes aux genoux ', 'Débutant', 'M', 'Mesomorphe', 'profil_gauche.jpg', 'profil_droit.jpg', 'face.jpg', 'profil_gauche.jpg', 21, 'Activitè restreinte');

-- --------------------------------------------------------

--
-- Table structure for table `client_selection`
--

CREATE TABLE `client_selection` (
  `id` int NOT NULL,
  `id_client` int DEFAULT NULL,
  `nom` text,
  `Prenom` text,
  `email` varchar(255) DEFAULT NULL,
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `taille` int DEFAULT NULL,
  `poids` int DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `code_postal` int DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `Experience` varchar(20) DEFAULT NULL,
  `sexe` varchar(20) DEFAULT NULL,
  `corpulence` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `niveau_activite` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `client_selection`
--

INSERT INTO `client_selection` (`id`, `id_client`, `nom`, `Prenom`, `email`, `nom_utilisateur`, `taille`, `poids`, `adresse`, `code_postal`, `description`, `Experience`, `sexe`, `corpulence`, `age`, `niveau_activite`) VALUES
(1, 4, 'Boue', 'Thomas', 'jhsrika06@gmail.com', 'zazadu93', 160, 57, '51 rue albert Camus', 5100, 'problèmes au dos \nproblèmes au tibiats\nproblèmes au côtes \nproblèmes aux genoux ', 'Débutant', 'M', 'Mesomorphe', 21, 'Activitè restreinte');

-- --------------------------------------------------------

--
-- Table structure for table `coach`
--

CREATE TABLE `coach` (
  `id` int NOT NULL,
  `nom_utilisateur` varchar(50) DEFAULT NULL,
  `mot_de_passe` varchar(2000) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mdp_application` text,
  `mdp_gmail` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `coach`
--

INSERT INTO `coach` (`id`, `nom_utilisateur`, `mot_de_passe`, `email`, `mdp_application`, `mdp_gmail`) VALUES
(1, 'TestCultugymcoach', '$2a$04$cniCboo6CRy6cKEmViC89eHSUv2/BBXcSY9wU89xgYERzwZbn.jii', 'testtesttesttestappcultugym@gmail.com', NULL, NULL),
(3, 'cultugym', '$2a$04$3khi6MrADhL/RR.SPPN9duwr8uRBbA/rW0fciyCJxLX2ABa9WvB4u', 'testtesttestcultugym@gmail.com', NULL, NULL),
(6, 'jhonlelascar', '$2a$04$/h8udUTyFCIlP9clISH/YO2Ros5pnWebgWdHhop8AFMLJSpBt1w8C', 'jhsrika06@gmail.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `coach_actuel`
--

CREATE TABLE `coach_actuel` (
  `id` int NOT NULL,
  `nom_utilisateur` varchar(1000) DEFAULT NULL,
  `mot_de_passe` text,
  `email` varchar(100) DEFAULT NULL,
  `id_coach` int DEFAULT NULL,
  `mdp_application` text,
  `mdp_gmail` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `coach_actuel`
--

INSERT INTO `coach_actuel` (`id`, `nom_utilisateur`, `mot_de_passe`, `email`, `id_coach`, `mdp_application`, `mdp_gmail`) VALUES
(1, 'cultugym', '$2a$04$3khi6MrADhL/RR.SPPN9duwr8uRBbA/rW0fciyCJxLX2ABa9WvB4u', 'testtesttestcultugym@gmail.com', 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dimanche`
--

CREATE TABLE `dimanche` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `informations_bonus`
--

CREATE TABLE `informations_bonus` (
  `id` int NOT NULL,
  `type_de_programme` varchar(30) DEFAULT NULL,
  `imc` float DEFAULT NULL,
  `BMR` float DEFAULT NULL,
  `NEAT` float DEFAULT NULL,
  `id_client` int DEFAULT NULL,
  `nom_utilisateur` varchar(100) DEFAULT NULL,
  `niveau_activite` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `informations_bonus`
--

INSERT INTO `informations_bonus` (`id`, `type_de_programme`, `imc`, `BMR`, `NEAT`, `id_client`, `nom_utilisateur`, `niveau_activite`) VALUES
(1, 'Full body', 19.4734, 1361.25, 2586.38, 1, 'dsqfdsfqsdfq', 'Activité Modérée'),
(2, 'Full body', 18.1955, 1187.75, 2256.73, 2, 'MitrailleuseDz', 'Activité Modérée'),
(3, 'Full body', 18.1955, 1187.75, 2256.73, 2, 'MitrailleuseDZ', 'Activité Modérée'),
(4, 'Split', 22.2656, 1470, 2352, 4, 'zazadu93', 'Activitè restreinte');

-- --------------------------------------------------------

--
-- Table structure for table `jeudi`
--

CREATE TABLE `jeudi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `liste_exercice`
--

CREATE TABLE `liste_exercice` (
  `id` int NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `liste_exercice`
--

INSERT INTO `liste_exercice` (`id`, `nom`) VALUES
(1, 'Développé Couché à la Barre'),
(2, 'Développé Couché Incliné à la Barre'),
(3, 'Développé Couché aux Haltères'),
(4, 'Développé Couché Incliné aux Haltères'),
(5, 'Dips avec Poids pour la Poitrine'),
(6, 'Développé Couché Décliné'),
(7, 'Soulevé de Terre à la Barre'),
(8, 'Rowing à la Barre Penché en Avant'),
(9, 'Tractions avec Poids'),
(10, 'Tractions en Supination'),
(11, 'Rowing T-Bar Debout'),
(12, 'Tirage Vertical en Supination'),
(13, 'Tirage Vertical en Pronation'),
(14, 'Rowing Assis à la Poulie'),
(15, 'Crunch à la Poulie'),
(16, 'Élévation des Genoux avec Haltères Suspendus'),
(17, 'Landmine'),
(18, 'Élévation des Jambes en Chaise Capitaine'),
(19, 'Extension à la Roue Abdominale'),
(20, 'Planche'),
(21, 'Sit-ups Déclinés avec Poids'),
(22, 'Pédalage en Position Couchée'),
(23, 'Exercice de la Planche Latérale'),
(24, 'Développé Militaire à la Barre Debout'),
(25, 'Développé Militaire aux Haltères Assis'),
(26, 'Développé Arnold pour les Épaules'),
(27, 'Oiseau Inversé Penché en Avant'),
(28, 'Élévation Latérale'),
(29, 'Élévation Frontale aux Haltères'),
(30, 'Pompes en Position Inversée (Handstand Push-ups)'),
(31, 'Squat à la Barre'),
(32, 'Élévation des Mollets Debout'),
(33, 'Presse à Mollets'),
(34, 'Soulevé de Terre Roumain'),
(35, 'Presse à Cuisses'),
(36, 'Squat Hack'),
(37, 'Fentes aux Haltères'),
(38, 'Curl Marteau Assis'),
(39, 'Curl à la Barre Debout'),
(40, 'Rows Inversés'),
(41, 'Curl Zottman'),
(42, 'Tractions avec Poids en Supination'),
(43, 'Curl en Déclinaison sur Banc'),
(44, 'Curl à la Barre EZ en Préacher'),
(45, 'Curl à la Poulie Debout'),
(46, 'Développé Couché en Prise Serrée'),
(47, 'Écrasement de Crâne (Skullcrusher)'),
(48, 'Dips avec Poids'),
(49, 'Extension du Triceps à la Poulie'),
(50, 'Extension du Triceps avec Haltère en Arrière de la Tête');

-- --------------------------------------------------------

--
-- Table structure for table `lundi`
--

CREATE TABLE `lundi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mardi`
--

CREATE TABLE `mardi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mercredi`
--

CREATE TABLE `mercredi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `programme`
--

CREATE TABLE `programme` (
  `id_programme` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` float DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL,
  `connexion` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `programme`
--

INSERT INTO `programme` (`id_programme`, `nom_exercice`, `repetitions`, `series`, `tempsDeRepos`, `SeriesEchauffement`, `degreDEchec`, `percentRepMax`, `id_client`, `connexion`) VALUES
(7, 'Développé Militaire aux Haltères Assis', 3, 4, 1.5, 4, 3, 3, 2, NULL),
(8, 'Développé Militaire aux Haltères Assis', 3, 4, 1.5, 4, 3, 3, 2, NULL),
(9, 'Curl Marteau Assis', 3, 4, 1.5, 4, 3, 3, 2, NULL),
(10, 'Dips avec Poids', 3, 4, 1.5, 4, 3, 3, 2, NULL),
(11, 'Développé Couché à la Barre', 4, 3, 1, 4, 4, 3, 3, NULL),
(12, 'Pédalage en Position Couchée', 4, 3, 1, 4, 4, 3, 3, NULL),
(13, 'Élévation Latérale', 4, 3, 1, 4, 4, 3, 3, NULL),
(21, 'Curl à la Poulie Debout', 4, 6, 1.5, 4, 4, 3, 1, NULL),
(22, 'Tirage Vertical en Supination', 4, 6, 1.5, 4, 4, 3, 1, NULL),
(23, 'Extension du Triceps à la Poulie', 4, 6, 1.5, 4, 4, 3, 1, NULL),
(24, 'Pédalage en Position Couchée', 4, 6, 1.5, 4, 4, 3, 1, NULL),
(25, 'Rows Inversés', 4, 6, 1.5, 5, 4, 3, 1, NULL),
(26, 'Pédalage en Position Couchée', 4, 6, 1.5, 5, 4, 3, 1, NULL),
(27, 'Oiseau Inversé Penché en Avant', 4, 6, 1.5, 5, 4, 3, 1, NULL),
(28, 'Développé Couché Décliné', 4, 6, 1.5, 5, 4, 3, 1, NULL),
(29, 'Développé Couché Incliné aux Haltères', 3, 3, 2, 3, 5, 3, 4, NULL),
(30, 'Élévation Frontale aux Haltères', 3, 3, 2, 3, 5, 3, 4, NULL),
(31, 'Extension du Triceps à la Poulie', 3, 3, 2, 3, 5, 3, 4, NULL),
(32, 'Pédalage en Position Couchée', 3, 3, 2, 3, 5, 3, 4, NULL),
(33, 'Tractions en Supination', 3, 3, 2, 3, 5, 3, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `samedi`
--

CREATE TABLE `samedi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vendredi`
--

CREATE TABLE `vendredi` (
  `id` int NOT NULL,
  `nom_exercice` varchar(50) DEFAULT NULL,
  `repetitions` int DEFAULT NULL,
  `series` int DEFAULT NULL,
  `tempsDeRepos` int DEFAULT NULL,
  `SeriesEchauffement` int DEFAULT NULL,
  `degreDEchec` int DEFAULT NULL,
  `percentRepMax` int DEFAULT NULL,
  `id_client` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_selection`
--
ALTER TABLE `client_selection`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_client` (`id_client`);

--
-- Indexes for table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coach_actuel`
--
ALTER TABLE `coach_actuel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dimanche`
--
ALTER TABLE `dimanche`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `informations_bonus`
--
ALTER TABLE `informations_bonus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jeudi`
--
ALTER TABLE `jeudi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `liste_exercice`
--
ALTER TABLE `liste_exercice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lundi`
--
ALTER TABLE `lundi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mardi`
--
ALTER TABLE `mardi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mercredi`
--
ALTER TABLE `mercredi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `programme`
--
ALTER TABLE `programme`
  ADD PRIMARY KEY (`id_programme`);

--
-- Indexes for table `samedi`
--
ALTER TABLE `samedi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendredi`
--
ALTER TABLE `vendredi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client_selection`
--
ALTER TABLE `client_selection`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `coach`
--
ALTER TABLE `coach`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `coach_actuel`
--
ALTER TABLE `coach_actuel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `dimanche`
--
ALTER TABLE `dimanche`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `informations_bonus`
--
ALTER TABLE `informations_bonus`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `jeudi`
--
ALTER TABLE `jeudi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `liste_exercice`
--
ALTER TABLE `liste_exercice`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `lundi`
--
ALTER TABLE `lundi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mardi`
--
ALTER TABLE `mardi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mercredi`
--
ALTER TABLE `mercredi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `programme`
--
ALTER TABLE `programme`
  MODIFY `id_programme` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `samedi`
--
ALTER TABLE `samedi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendredi`
--
ALTER TABLE `vendredi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
