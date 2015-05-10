-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SI2014Tim12
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SI2014Tim12
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SI2014Tim12` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `SI2014Tim12` ;

-- -----------------------------------------------------
-- Table `SI2014Tim12`.`Materijal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`Materijal` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) NOT NULL,
  `cijena` REAL NOT NULL,
  `mjernaJedinica` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`Materijal` (`id` ASC);

CREATE UNIQUE INDEX `naziv_UNIQUE` ON `SI2014Tim12`.`Materijal` (`naziv` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`Korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`Korisnik` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`Korisnik` (`id` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `SI2014Tim12`.`Korisnik` (`username` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`Pacijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`Pacijent` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `imeIPrezime` VARCHAR(255) NOT NULL,
  `telefon` VARCHAR(255) NOT NULL,
  `opis` VARCHAR(255) NULL,
  `datumRodjenja` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`Pacijent` (`id` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`TipZahvata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`TipZahvata` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) NOT NULL,
  `cijena` REAL NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`TipZahvata` (`id` ASC);

CREATE UNIQUE INDEX `naziv_UNIQUE` ON `SI2014Tim12`.`TipZahvata` (`naziv` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`Termin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`Termin` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `doktor` VARCHAR(255) NOT NULL,
  `vrijeme` DATETIME NOT NULL,
  `otkazano` TINYINT(1) NOT NULL,
  `pacijentId` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Termin_Pacijent`
    FOREIGN KEY (`pacijentId`)
    REFERENCES `SI2014Tim12`.`Pacijent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`Termin` (`id` ASC);

CREATE INDEX `fk_Termin_Pacijent_idx` ON `SI2014Tim12`.`Termin` (`pacijentId` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`MaterijalTipZahvata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`MaterijalTipZahvata` (
  `kolicina` REAL NULL,
  `materijalId` INT(10) NOT NULL,
  `tipZahvataId` INT(10) NOT NULL,
  PRIMARY KEY (`materijalId`, `tipZahvataId`),
  CONSTRAINT `fk_MaterijalTipZahvata_Materijal1`
    FOREIGN KEY (`materijalId`)
    REFERENCES `SI2014Tim12`.`Materijal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterijalTipZahvata_TipZahvata1`
    FOREIGN KEY (`tipZahvataId`)
    REFERENCES `SI2014Tim12`.`TipZahvata` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_MaterijalTipZahvata_Materijal1_idx` ON `SI2014Tim12`.`MaterijalTipZahvata` (`materijalId` ASC);

CREATE INDEX `fk_MaterijalTipZahvata_TipZahvata1_idx` ON `SI2014Tim12`.`MaterijalTipZahvata` (`tipZahvataId` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`Posjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`Posjeta` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `datum` DATETIME NOT NULL,
  `doktor` VARCHAR(255) NOT NULL,
  `dijagnoza` VARCHAR(255) NULL,
  `pacijentId` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Posjeta_Pacijent1`
    FOREIGN KEY (`pacijentId`)
    REFERENCES `SI2014Tim12`.`Pacijent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`Posjeta` (`id` ASC);

CREATE INDEX `fk_Posjeta_Pacijent1_idx` ON `SI2014Tim12`.`Posjeta` (`pacijentId` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`ObavljeniZahvat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`ObavljeniZahvat` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `cijena` REAL NOT NULL,
  `posjetaId` INT(10) NOT NULL,
  `zahvatId` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ObavljeniZahvat_Posjeta1`
    FOREIGN KEY (`posjetaId`)
    REFERENCES `SI2014Tim12`.`Posjeta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ObavljeniZahvat_TipZahvata1`
    FOREIGN KEY (`zahvatId`)
    REFERENCES `SI2014Tim12`.`TipZahvata` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`ObavljeniZahvat` (`id` ASC);

CREATE INDEX `fk_ObavljeniZahvat_Posjeta1_idx` ON `SI2014Tim12`.`ObavljeniZahvat` (`posjetaId` ASC);

CREATE INDEX `fk_ObavljeniZahvat_TipZahvata1_idx` ON `SI2014Tim12`.`ObavljeniZahvat` (`zahvatId` ASC);


-- -----------------------------------------------------
-- Table `SI2014Tim12`.`UtroseniMaterijal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SI2014Tim12`.`UtroseniMaterijal` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `kolicina` REAL NOT NULL,
  `obavljeniZahvatId` INT(10) NOT NULL,
  `materijalId` INT(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_UtroseniMaterijal_ObavljeniZahvat1`
    FOREIGN KEY (`obavljeniZahvatId`)
    REFERENCES `SI2014Tim12`.`ObavljeniZahvat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UtroseniMaterijal_Materijal1`
    FOREIGN KEY (`materijalId`)
    REFERENCES `SI2014Tim12`.`Materijal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `SI2014Tim12`.`UtroseniMaterijal` (`id` ASC);

CREATE INDEX `fk_UtroseniMaterijal_ObavljeniZahvat1_idx` ON `SI2014Tim12`.`UtroseniMaterijal` (`obavljeniZahvatId` ASC);

CREATE INDEX `fk_UtroseniMaterijal_Materijal1_idx` ON `SI2014Tim12`.`UtroseniMaterijal` (`materijalId` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
