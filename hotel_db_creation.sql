DROP SCHEMA IF EXISTS hotel;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`room_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room_type` (
  `room_type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(127) NOT NULL,
  `capacity` INT NOT NULL,
  `price` INT NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`room_type_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `room_number` VARCHAR(8) NOT NULL,
  `room_type_id` INT NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE INDEX `room_number_UNIQUE` (`room_number` ASC),
  INDEX `fk_room_room_type1_idx` (`room_type_id` ASC),
  CONSTRAINT `fk_room_room_type1`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `hotel`.`room_type` (`room_type_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`users` (
  `users_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(64) NOT NULL,
  `last_name` VARCHAR(64) NOT NULL,
  `patronymic` VARCHAR(64) NULL,
  `role` ENUM('administrator', 'client') NOT NULL,
  PRIMARY KEY (`users_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`reservation` (
  `reservation_id` INT NOT NULL AUTO_INCREMENT,
  `reservation_date` DATETIME NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `client_comment` VARCHAR(255) NULL,
  `administrator_comment` VARCHAR(255) NULL,
  `status` ENUM('new', 'approved', 'denied', 'in_progress', 'payed') NOT NULL,
  `users_id` INT NOT NULL,
  `room_type_id` INT NOT NULL,
  `room_id` INT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_reservation_users1_idx` (`users_id` ASC),
  INDEX `fk_reservation_room_type1_idx` (`room_type_id` ASC),
  INDEX `fk_reservation_room1_idx` (`room_id` ASC),
  CONSTRAINT `fk_reservation_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `hotel`.`users` (`users_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_room_type1`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `hotel`.`room_type` (`room_type_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `hotel`.`room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `total` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `payment_method` ENUM('cash', 'card') NOT NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_reservation_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_payment_reservation`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `hotel`.`reservation` (`reservation_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Стандарт з 2 ліжками", 2, 40000, "1 кімната, 2 одномісних ліжка.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Стандарт з 3 ліжками", 3, 60000, "1 кімната, 3 одномісних ліжка.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Стандарт з 4 ліжками", 4, 80000, "2 кімнати, 4 одномісних ліжка.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Стандарт з великим ліжком", 2, 45000, "1 кімната,  1 двомісне ліжко.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Стандарт з великим ліжком та диваном", 3, 65000, "2 кімнати. 1 двомісне ліжко, 1 диван.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Напівлюкс з великим ліжком", 2, 70000, "1 кімната, 1 двомісне ліжко.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Напівлюкс з великим ліжком та диваном", 3, 85000, "2 кімнати, 1 двомісне ліжко, 1 диван.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Напівлюкс з двома великими ліжками", 4, 100000, "2 кімнати, 2 двомісних ліжка.");
INSERT INTO `hotel`.room_type (name, capacity, price, description) VALUES ("Люкс з великим ліжком", 2, 100000, "2 кімнати, 1 двомісне ліжко");

INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("1", 1);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("2", 1);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("3", 2);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("4", 2);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("5", 3);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("6", 3);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("7", 4);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("8", 4);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("9", 5);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("10", 6);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("11", 6);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("12", 7);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("13", 8);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("14", 9);
INSERT INTO `hotel`.room (room_number, room_type_id) VALUES ("15", 9);

INSERT INTO `hotel`.users (username, password, email, phone_number, first_name, last_name, patronymic, role) VALUES ("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "admin@hotel.ua", "380987654321", "Мирослава", "Кот", "Дмитрівна", "administrator");