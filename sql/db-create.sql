SET NAMES utf8;

DROP DATABASE IF EXISTS travel_agency;
CREATE DATABASE travel_agency CHARACTER SET utf8 COLLATE utf8_bin;

USE travel_agency;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`   INT                                   NOT NULL,
    `name` ENUM ('admin', 'manager', 'customer') NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'manager');
INSERT INTO roles VALUES(2, 'customer');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name`  VARCHAR(20) NOT NULL,
    `login`      VARCHAR(15) NOT NULL,
    `pass`       VARCHAR(15) NOT NULL,
    `status`     TINYINT(1)  NOT NULL,
    `role_id`    INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC),
    INDEX `fk_new_table_1_idx` (`role_id` ASC),
    CONSTRAINT `fk_new_table_1`
        FOREIGN KEY (`role_id`)
            REFERENCES `travel_agency`.`roles` (`id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
);

INSERT INTO users VALUES(DEFAULT, 'Peter', 'Petrov', 'admin', 'admin', 0, 0),
                        (DEFAULT, 'Georg', 'Georgeson', 'manager', 'manager', 0, 1),
                        (DEFAULT, 'Georg2', 'Anderson', 'jora', '432', 0, 2),
                        (DEFAULT, 'Georg3', 'Peterson', 'jor3', '432', 0, 2),
                        (DEFAULT, 'Georg4', 'Jopa', 'jor4', '432', 0, 2);

DROP TABLE IF EXISTS `tours`;
CREATE TABLE `tours`
(
    `id`              INT                                            NOT NULL AUTO_INCREMENT,
    `country`         VARCHAR(15)                                    NOT NULL,
    `city`            VARCHAR(20)                                    NOT NULL,
    `hotel_name`      VARCHAR(25)                                    NOT NULL,
    `hotel_type`      INT                                            NOT NULL,
    `duration`        INT                                            NOT NULL,
    `people_quantity` INT                                            NOT NULL,
    `price`           INT                                            NOT NULL,
    `last_minute`     TINYINT(1)                                     NOT NULL,
    `type`            VARCHAR(10)                                    NOT NULL,
    `status`          ENUM ('free','registered', 'paid', 'canceled') NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO tours VALUES (DEFAULT, 'EGYPT', 'Sharm El Sheikh', 'Fortune Sharm', 3, 9, 2, 12950, 1, 'rest', 'free'),
                         (DEFAULT, 'EGYPT', 'Sharm El Sheikh', 'Fortune Sharm', 4, 7, 2, 11050, 1, 'rest', 'free'),
                         (DEFAULT, 'Turkey', 'Belek', 'Medusa Hotel', 5, 10, 2, 10100, 1, 'rest', 'free'),
                         (DEFAULT, 'Turkey', 'Kemer', 'Medusa Hotel', 4, 8, 2, 9850, 0, 'rest', 'free'),
                         (DEFAULT, 'Кипр', 'Пафос', 'Агапинор Hotel', 3, 6, 2, 10170, 0, 'rest', 'free'),
                         (DEFAULT, 'Кипр', 'Пафос', 'Агапинор Hotel', 3, 6, 2, 10170, 0, 'rest', 'free'),
                         (DEFAULT, 'Польша', 'Краков', 'Krakov', 3, 2, 1, 4756, 0, 'excursion', 'free'),
                         (DEFAULT, 'Italy', 'Milan', 'Hotel', 4, 2, 1, 8000, 1, 'shopping', 'free');

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `user_id` INT        NOT NULL,
    `tour_id` INT        NOT NULL,
    `is_paid` TINYINT(1) NOT NULL,
    PRIMARY KEY (`user_id`, `tour_id`),
    INDEX `fk_tours_1_idx` (`tour_id` ASC),
    CONSTRAINT `fk_users_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `travel_agency`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tours_1`
        FOREIGN KEY (`tour_id`)
            REFERENCES `travel_agency`.`tours` (`id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT
);