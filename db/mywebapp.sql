create database mywebapp;
use mywebapp;
CREATE TABLE `users`(
    `index` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255),
    `password` VARCHAR(255),
    PRIMARY KEY (`index`)
);
