-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 23 mars 2021 à 14:46
-- Version du serveur :  10.3.27-MariaDB-0+deb10u1
-- Version de PHP : 7.3.19-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_lycee`
--
CREATE DATABASE IF NOT EXISTS `projet_lycee` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projet_lycee`;

-- --------------------------------------------------------

--
-- Structure de la table `annonces`
--

CREATE TABLE `annonces` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `poste` varchar(50) NOT NULL,
  `description_poste` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `annonces`
--

INSERT INTO `annonces` (`id`, `nom`, `mail`, `poste`, `description_poste`) VALUES
(10, 'Lignani', 'q.lignani@lprs.fr', 'Développeur web', 'Je recherche un développeur compétent');

-- --------------------------------------------------------

--
-- Structure de la table `discussion`
--

CREATE TABLE `discussion` (
  `id` int(11) NOT NULL,
  `id_user1` int(10) UNSIGNED NOT NULL,
  `id_user2` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `discussion`
--

INSERT INTO `discussion` (`id`, `id_user1`, `id_user2`) VALUES
(4, 8, 6),
(6, 10, 6);

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(10) UNSIGNED NOT NULL,
  `titre` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `date` date NOT NULL,
  `Comm` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `id_utilisateur`, `titre`, `description`, `date`, `Comm`) VALUES
(2, 3, 'Dévelopeur Informatique Fullstack H/F', 'À propos d\'AtosAtos est un leader international de la transformation digitale avec plus de 110 000 collaborateurs dans 73 pays et un chiffre d\'affaires annuel de plus de 11 milliards d\'euros. Numéro un européen du Cloud, de la cybersécurité et des supercalculateurs.', '2020-10-21', 'AGEFIPH- Espace Emploi'),
(4, 3, 'stage Developpeur informatique - H/F', 'Qui sommes-nous ? Avec 90 000 collaborateurs présents sur les cinq \r\ncontinents, SUEZ est un leader mondial dans la gestion intelligente et \r\ndurable des ressources. Le Groupe fournit des solutions de gestion de l\'eau \r\net des déchets qui permettent aux villes.', '2020-06-09', 'Jobijoba');

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `id_discussion` int(11) NOT NULL,
  `id_utilisateur` int(10) UNSIGNED NOT NULL,
  `message` text NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `id_discussion`, `id_utilisateur`, `message`, `date`) VALUES
(3, 4, 8, 'Bonjour', '2021-03-13 09:10:24'),
(4, 4, 6, 'Salut', '2021-03-13 09:11:28'),
(6, 5, 6, 'Salut, bien et toi ?', '2021-03-13 11:57:40');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(10) UNSIGNED NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `mdp` varchar(40) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  `date_connexion` date DEFAULT NULL,
  `verif` tinyint(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `mdp`, `role`, `date_connexion`, `verif`) VALUES
(3, 'Nakhil', 'Amine', 'nakhila@orange.fr', '265545635d1704996ba4dbb482377aa542cdf5c0', NULL, NULL, 0),
(4, 'ADMIN', 'ADMIN', 'ADMIN@ADMIN', 'b521caa6e1db82e5a01c924a419870cb72b81635', 'ADMIN', '2021-02-23', 1),
(6, 'Guo', 'Loïc', 'loicguo@gmail.com', '90283840d90de49b8e7984bd99b47fee0d4bd50d', NULL, '2021-03-13', 1),
(8, 'test', 'test', 'fedopal376@gameqo.com', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NULL, '2021-03-13', 1),
(10, 'Goncalves', 'Nathan', 'nathan.ng.goncalves@gmail.com', '306b0f00c8daa94f69ffa295767345905e64f233', NULL, '2021-03-13', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonces`
--
ALTER TABLE `annonces`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_user1` (`id_user1`),
  ADD KEY `fk_id_user2` (`id_user2`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_user_event` (`id_utilisateur`);

--
-- Index pour la table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_user_messages` (`id_utilisateur`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonces`
--
ALTER TABLE `annonces`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD CONSTRAINT `fk_id_user1` FOREIGN KEY (`id_user1`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `fk_id_user2` FOREIGN KEY (`id_user2`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD CONSTRAINT `fk_id_user_event` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `fk_id_user_messages` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
