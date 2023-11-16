CREATE DATABASE  IF NOT EXISTS `finangportal`;
USE `finangportal`;

SET FOREIGN_KEY_CHECKS = 0; -- temporarily disables foreign key checking before creating tables.

--
-- Create table kreditangebotenStatus
--
DROP TABLE IF EXISTS `kreditangebotenStatus`;

CREATE TABLE `kreditangebotenStatus` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(30) DEFAULT NULL,
  `status_beschreibung` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `STATUS_NAME_UNIQUE` (`status_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Create table kreditangeboten
--
DROP TABLE IF EXISTS `kreditangeboten`;

CREATE TABLE `kreditangeboten` (
  `kreditangebot_id` int NOT NULL AUTO_INCREMENT,
  `kreditangebot_name` varchar(45) DEFAULT NULL,
  `kreditangebot_beschreibung` varchar(250) DEFAULT NULL,
  `kredit_punkte` int DEFAULT NULL,
  `kreditangebot_jahresgebuehr` DECIMAL(10,2) DEFAULT NULL,
  `kreditangebot_erstellungsdatum` DATE DEFAULT NULL,
  `kreditangebot_ablaufdatum` DATE DEFAULT NULL,
  `status_id` int NOT NULL,  -- FK von kreditangebotenStatus(status_id)
  PRIMARY KEY (`kreditangebot_id`),
  KEY `FK_KREDITANGEBOT_STATUS_idx` (`status_id`),
  CONSTRAINT `FK_KREDITANGEBOT_STATUS` FOREIGN KEY (`status_id`)
  REFERENCES `kreditangebotenStatus` (`status_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Create table kreditangebotantrag
--
DROP TABLE IF EXISTS `kreditangebotantrag`;

CREATE TABLE `kreditangebotantrag` (
  `antrag_id` int NOT NULL AUTO_INCREMENT,
  `antragsteller_vname` varchar(50) DEFAULT NULL,
  `antragsteller_name` varchar(50) DEFAULT NULL,
  `antragsteller_telefon` varchar(50) DEFAULT NULL,
  `antragsteller_gehalt` DECIMAL(10,2) DEFAULT NULL,
  `antragsteller_geburtsdatum` DATE DEFAULT NULL,
  `antragsteller_email` varchar(50) DEFAULT NULL,
  `antrag_erstellungsdatum` DATE DEFAULT NULL,
  `kreditangebot_id` int NOT NULL,  -- FK von kreditangeboten(kreditangebot_id)
  `antragstatus_id` int NOT NULL, -- FK von Kreditangebotantrag(antragstatus_id)
  PRIMARY KEY (`antrag_id`),
  KEY `FK_KREDITANGEBOT_idx` (`kreditangebot_id`),
  CONSTRAINT `FK_KREDITANGEBOT_BEANTRAGEN` FOREIGN KEY (`kreditangebot_id`)
  REFERENCES `kreditangeboten` (`kreditangebot_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  KEY `FK_KREDITANGEBOTANTRAG_STATUS_idx` (`antragstatus_id`),
  CONSTRAINT `FK_KREDITANGEBOTANTRAG_STATUS` FOREIGN KEY (`antragstatus_id`)
  REFERENCES `kreditangebotantrag_status` (`antragstatus_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=900534785 DEFAULT CHARSET=latin1;

--
-- Create table kreditangebotantrag_status
--
   CREATE TABLE `kreditangebotantrag_status` (
      `antragstatus_id` int NOT NULL AUTO_INCREMENT,
      `antragstatus_name` varchar(30) DEFAULT NULL,
      `antragstatus_beschreibung` varchar(250) DEFAULT NULL,
      PRIMARY KEY (`antragstatus_id`),
      UNIQUE KEY `ANTRAGSTATUS_NAME_UNIQUE` (`antragstatus_name`)
    ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


--
-- Create table geschlecht
--
DROP TABLE IF EXISTS `geschlecht`;

CREATE TABLE `geschlecht` (
  `geschlecht_id` int NOT NULL AUTO_INCREMENT,
  `geschlecht` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`geschlecht_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Create table userrolle
--
DROP TABLE IF EXISTS `userrolle`;

CREATE TABLE `userrolle` (
  `userrolle_id` int NOT NULL AUTO_INCREMENT,
  `userrolle` varchar(45) DEFAULT NULL,
  `userrolle_beschreibung` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`userrolle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Create table users
--
DROP TABLE IF EXISTS `users`;
--
--CREATE TABLE `users` (
--    `user_id` int NOT NULL AUTO_INCREMENT,
--    `user_vname` varchar(50) DEFAULT NULL,
--    `user_name` varchar(50) DEFAULT NULL,
--    `user_password` varchar(50) DEFAULT NULL,
--    `user_email` varchar(50) DEFAULT NULL,
--    `user_telefon` varchar(50) DEFAULT NULL,
--    `user_erstellungsdatum` DATE DEFAULT NULL,
--    `geschlecht_id` int NOT NULL, -- FK von geschlecht(geschlecht_id)
--    `userrolle_id` int NOT NULL, -- FK von userrolle(userrolle_id)
--    PRIMARY KEY (`user_id`),
--    KEY `FK_GESCHLECHT_idx` (`geschlecht_id`),
--    CONSTRAINT `FK_GESCHLECHT` FOREIGN KEY (`geschlecht_id`)
--    REFERENCES `geschlecht` (`geschlecht_id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
--    CONSTRAINT `FK_USERROLLE` FOREIGN KEY (`userrolle_id`)
--    REFERENCES `userrolle` (`userrolle_id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
--) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;




CREATE TABLE `users` (
    `user_id` int NOT NULL AUTO_INCREMENT,
	`user_vname` varchar(50) DEFAULT NULL,
    `user_name` varchar(50) DEFAULT NULL,
    `user_username` varchar(50) DEFAULT NULL,
    `user_password` char(80) NOT NULL,
    `user_email` varchar (255) DEFAULT NULL,
    `user_telefon` varchar (50) DEFAULT NULL,
    `user_erstellungsdatum` DATE DEFAULT NULL,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



--
-- Create table users_roles
--
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`,`role_id`),

  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`user_id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`role_id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



SET FOREIGN_KEY_CHECKS = 1; -- resets the foreign key verification.


--
-- Change the colmun to varchar(1000)
--
ALTER TABLE kreditangeboten
MODIFY COLUMN kreditangebot_beschreibung VARCHAR(1000);


--
-- Insert Data for table `KreditangebotenStatus`
--

INSERT INTO `kreditangebotenStatus` VALUES
    (10, 'Veroeffentlicht', 'Wenn das Angebot veröffentlicht ist.'),
    (20, 'In Bearbeitung', 'Wenn das Angebot erstellt wird, sich in Bearbeitung befindet und darauf wartet, veröffentlicht zu werden.'),
    (30, 'Abgelaufen', 'Wenn das Angebot abgelaufen ist'),
    (40, 'Im Ruhestand', 'wenn das Angebot zurückgezogen ist');


--
-- Insert Data for table `KreditangebotantragStatus`
--

    INSERT INTO `kreditangebotantrag_status` VALUES
        (10, 'Kontaktiert', 'wenn der Antragsteller kontaktiert wurde.'),
        (20, 'Anrufen', 'wenn der Antragsteller nicht kontaktiert werden konnte.'),
        (30, 'Beratung', 'wenn eine Terminberatung geplant ist'),
        (40, 'Abgesagt', 'wenn der Antragsteller den Antrag zurückgezogen hat');

--
-- Insert Data for table `geschlecht`
--

INSERT INTO `geschlecht` VALUES
    (1, 'Herr'),
    (2, 'Frau');

 --
 -- Insert Data for table `userrolle`
 --

 INSERT INTO `role` VALUES
        (1, 'ROLE_ADMIN', 'Diese Nutzer haben das Recht zum Lesen, Schreiben und Löschen'),
        (2, 'ROLE_CALLCENTER','Diese Nutzer haben das Recht zum Lesen und Schreiben'),
        (3, 'ROLE_USER','Diese Nutzer haben das Recht zum Lesen');

--
-- Insert Data for table `users` password= fun123
--

INSERT INTO `users` VALUES
    (1, 'john', 'Smith', 'john', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'john@gmail.com', '+49 1535245625', '2023-06-10', 1),
    (2, 'mary', 'Mueller', 'mary', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'mary@gmail.com', '+49 1535245625', '2023-06-10', 1),
    (3, 'susan', 'Holz', 'susan', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'susan@gmail.com', '+49 1535245625', '2023-06-10', 1);


--
-- Insert Data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);


--
-- Insert Data for table `Kreditangeboten`
--

INSERT INTO `kreditangeboten` VALUES
    (1, 'Finanzangebot1', 'Finanzangebot1 Beschreibung', 90000, 0, '2023-04-10', NULL, 10),
    (2, 'Finanzangebot2', 'Finanzangebot2 Beschreibung', 200000, 30, '2023-06-10', NULL, 10),
    (3, 'Finanzangebot3', 'Finanzangebot3 Beschreibung', 100000, 0, '2023-06-22', NULL, 10),
    (4, 'Finanzangebot4', 'Finanzangebot4 Beschreibung', 500, 0, '2023-06-23', NULL, 20),
    (5, 'Finanzangebot5', 'Finanzangebot5 Beschreibung', 5000, 150, '2023-01-10', '2023-07-10', 20),
    (6, 'Finanzangebot6', 'Finanzangebot6 Beschreibung', 50000, 1500, '2023-01-15', '2023-07-10', 30);


    --
    -- Insert Data for table `kreditangebotantrag`
    --

    INSERT INTO kreditangebotantrag (antragsteller_vname,
                                     antragsteller_name,
                                     antragsteller_telefon,
                                     antragsteller_gehalt,
                                     antragsteller_geburtsdatum,
                                     antragsteller_email,
                                     antrag_erstellungsdatum,
                                     kreditangebot_id, antragstatus_id)
    VALUES
    ('Max', 'Mustermann', '+49123456789', 3000.00, '1990-01-01', 'max.mustermann@example.com', '2023-10-27', 1, 10),
    ('Anna', 'Schmidt', '+49123456700', 3500.50, '1985-05-15', 'anna.schmidt@example.com', '2023-10-27', 2, 20),
    ('Michael', 'Becker', '+49123456711', 4000.75, '1988-09-30', 'michael.becker@example.com', '2023-10-27', 3, 30),
    ('Sarah', 'Wagner', '+49123456722', 2800.25, '1995-07-20', 'sarah.wagner@example.com', '2023-10-27', 4, 40);



