-- phpMyAdmin SQL Dump
-- version 4.1.14
--
-- Client :  127.0.0.1
-- Généré le :  Lun 18 juin 1940 à 17:12
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `lycee_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  `id_prof_principal` int(11) DEFAULT NULL,
  `undeletable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_prof_prin` (`id_prof_principal`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`id`, `libelle`, `id_prof_principal`, `undeletable`) VALUES
(1, 'Non Attribue', NULL, 1),
(2, 'BTS SIO SLAM', NULL, 0),
(9, 'test', NULL, 0),
(10, '', NULL, 0),
(11, '', NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `id_classe` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_classe` (`id_classe`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id`, `nom`, `prenom`, `id_classe`) VALUES
(1, 'LIGNANI', 'Quentin', 2),
(2, 'GUO', 'Loïc', 2),
(3, 'NAKHIL', 'Amine', 2),
(4, 'Martini', 'Americano', 1);

-- --------------------------------------------------------

--
-- Structure de la table `heure`
--

CREATE TABLE IF NOT EXISTS `heure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `heure`
--

INSERT INTO `heure` (`id`, `libelle`) VALUES
(1, '8h'),
(2, '9h'),
(3, '10h'),
(4, '11h'),
(5, '12h'),
(6, '14h'),
(7, '15h'),
(8, '16h'),
(9, '17h');

-- --------------------------------------------------------

--
-- Structure de la table `jour`
--

CREATE TABLE IF NOT EXISTS `jour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `jour`
--

INSERT INTO `jour` (`id`, `libelle`) VALUES
(1, 'Lundi'),
(2, 'Mardi'),
(3, 'Mercredi'),
(4, 'Jeudi'),
(5, 'Vendredi');

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE IF NOT EXISTS `planning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_jour` int(11) NOT NULL,
  `id_heure` int(11) NOT NULL,
  `id_classe` int(11) NOT NULL,
  `id_professeur` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prof` (`id_professeur`),
  KEY `fk_classe2` (`id_classe`),
  KEY `fk_jour` (`id_jour`),
  KEY `fk_heure` (`id_heure`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `planning`
--

INSERT INTO `planning` (`id`, `id_jour`, `id_heure`, `id_classe`, `id_professeur`) VALUES
(1, 1, 1, 2, 2),
(2, 1, 8, 2, 2),
(3, 3, 5, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `identifiant` varchar(30) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `matiere` varchar(30) DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'professeur',
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `identifiant`, `mdp`, `matiere`, `role`) VALUES
(1, 'Admin', 'Admin', 'Admin@Admin', 'Admin', 'admin', NULL, 'Admin'),
(2, 'Guo', 'Olivier', 'Oli@oli', 'test', 'test', 'Mathématique', 'professeur');

-- --------------------------------------------------------

--
-- Structure de la table `vie_scolaire`
--

CREATE TABLE IF NOT EXISTS `vie_scolaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_eleve` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `justification` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_eleve` (`id_eleve`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `vie_scolaire`
--

INSERT INTO `vie_scolaire` (`id`, `id_eleve`, `type`, `date`, `justification`) VALUES
(1, 4, 'Absence', '2021-04-14', 'Maladie');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `fk_prof_prin` FOREIGN KEY (`id_prof_principal`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `fk_classe` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id`);

--
-- Contraintes pour la table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `fk_classe2` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id`),
  ADD CONSTRAINT `fk_heure` FOREIGN KEY (`id_heure`) REFERENCES `heure` (`id`),
  ADD CONSTRAINT `fk_jour` FOREIGN KEY (`id_jour`) REFERENCES `jour` (`id`),
  ADD CONSTRAINT `fk_prof` FOREIGN KEY (`id_professeur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `vie_scolaire`
--
ALTER TABLE `vie_scolaire`
  ADD CONSTRAINT `fk_eleve` FOREIGN KEY (`id_eleve`) REFERENCES `eleve` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
