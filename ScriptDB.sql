-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Manufacturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Manufacturer` (
  `manufacturer` VARCHAR(30) NOT NULL,
  `country` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`manufacturer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Product` (
  `idProduct` INT NOT NULL,
  `productName` VARCHAR(45) NOT NULL,
  `sizeScreen` FLOAT NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `usbPort` VARCHAR(45) NOT NULL,
  `os` VARCHAR(45) NOT NULL,
  `manufacturer_manufacturer` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idProduct`, `manufacturer_manufacturer`),
  INDEX `fk_Product_Manufacturer1_idx` (`manufacturer_manufacturer` ASC),
  CONSTRAINT `fk_Product_Manufacturer1`
    FOREIGN KEY (`manufacturer_manufacturer`)
    REFERENCES `mydb`.`Manufacturer` (`manufacturer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Client` (
  `idClient` INT NOT NULL,
  `firstName` VARCHAR(20) NOT NULL,
  `secondName` VARCHAR(20) NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Sale` (
  `idSale` INT NOT NULL,
  `price` FLOAT NOT NULL,
  `salesTerms` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `dateOfSale` DATE NOT NULL,
  `User_idUser` INT NOT NULL,
  `Client_idClient` INT NOT NULL,
  `Product_idProduct` INT NOT NULL,
  PRIMARY KEY (`idSale`, `Client_idClient`, `Product_idProduct`),
  INDEX `fk_Acquisition_Client1_idx` (`Client_idClient` ASC),
  INDEX `fk_Acquisition_Product1_idx` (`Product_idProduct` ASC),
  INDEX `fk_Acquisition_User1_idx` (`User_idUser` ASC),
  CONSTRAINT `fk_Acquisition_Client1`
    FOREIGN KEY (`Client_idClient`)
    REFERENCES `mydb`.`Client` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Acquisition_Product1`
    FOREIGN KEY (`Product_idProduct`)
    REFERENCES `mydb`.`Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Acquisition_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `mydb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`User` (`idUser`, `login`, `password`, `role`) VALUES (1, 'Admin', 'admin', 1);
INSERT INTO `mydb`.`User` (`idUser`, `login`, `password`, `role`) VALUES (20, 'User', 'User', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Manufacturer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('OnePlus', 'Китай');
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('Lenovo', 'Китай');
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('Samsung', 'Республика Корея');
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('Acer', 'Тайвань');
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('Huawei', 'Китай');
INSERT INTO `mydb`.`Manufacturer` (`manufacturer`, `country`) VALUES ('Asus', 'Тайвань');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (1, 'OnePlus6', 6.0, 'Черный', 'Type-C', 'Android 8.0', 'OnePlus');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (2, 'Lenovo ideapad Y700', 15.6, 'Черный + Красный', '2*USB 3.0 + USB 2.0', 'Windows 10', 'Lenovo');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (3, 'Samsung Galaxy S8', 6.2, 'Красный', 'Type-C', 'Android 8.0', 'Samsung');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (4, 'Acer Aspire S24', 24.0, 'Серебристый', '3*USB 3.0', 'Windows 10', 'Acer');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (5, 'Huawei P20 Lite', 5.5, 'Изумруд', 'Type-C', 'Android 8.0', 'Huawei');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (6, 'Samsung Galaxy J8', 5.8, 'Белый', 'Type-C', 'Android 7.0', 'Samsung');
INSERT INTO `mydb`.`Product` (`idProduct`, `productName`, `sizeScreen`, `color`, `usbPort`, `os`, `manufacturer_manufacturer`) VALUES (7, 'ASUS X507', 15.6, 'Серый', '2*USB 3.0 + USB 2.0', 'Windows 10', 'Asus');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Client`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (12, 'Иван', 'Иванов', '+375(29)645-78-09', 'Ivan.Karan@bk.ru', 'ул.Богдановича 143/1 кв.105');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (15, 'Алексей', 'Йолов', '+375(29)375-98-23', 'Iolov.34@gamil.com', 'ул.Притыцкого 77 кв.17');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (24, 'Петр', 'Никифоров', '+375(33)175-37-85', 'nikiforov.@yandex.ru', 'ул.Чигладзе 1 кв.1');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (9, 'Александр', 'Зайцев', '+375(44)934-04-77', 'zaicev17@mail.ru', 'ул.Беды 8 кв.45');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (13, 'Иван', 'Прокоп', '+375(25)356-32-74', 'shatal@gamil.com', 'ул.Жукова 88 кв.29');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (18, 'Андрей', 'Смирнов', '+375(33)448-27-93', 'C.Andrey@mail.ru', 'ул.Беды 12 кв.12');
INSERT INTO `mydb`.`Client` (`idClient`, `firstName`, `secondName`, `phoneNumber`, `email`, `address`) VALUES (10, 'Максим', 'Левин', '+375(29)616-92-86', 'LevMax@gmail.com', 'ул.Гамарника 40 кв.32');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Sale`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (456, 550.0, 'Полная оплата', 'Телефон', '2018-11-12', 20, 10, 1);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (745, 1200.0, 'Полная оплата', 'Телефон', '2018-10-31', 20, 24, 3);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (376, 1800.0, 'Рассрочка', 'Моноблок', '2018-8-31', 20, 15, 4);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (245, 1800.0, 'Рассрочка', 'Ноутбук', '2018-8-15', 20, 13, 2);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (757, 870.0, 'Полная оплата', 'Ноутбук', '2018-9-23', 20, 18, 7);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (292, 820.0, 'Рассрочка', 'Телефон', '2018-9-15', 20, 12, 6);
INSERT INTO `mydb`.`Sale` (`idSale`, `price`, `salesTerms`, `category`, `dateOfSale`, `User_idUser`, `Client_idClient`, `Product_idProduct`) VALUES (264, 750.0, 'Полная оплата', 'Телефон', '2018-10-15', 20, 9, 5);

COMMIT;

