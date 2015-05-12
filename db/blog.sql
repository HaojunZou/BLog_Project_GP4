CREATE DATABASE blog
USE blog

CREATE TABLE users
(
	id 		INT(6) PRIMARY KEY,
	userType   	INT(1),
	userName 	VARCHAR(30),
	email		VARCHAR(30),
	userPassword	VARCHAR(30),
	realName	VARCHAR(30),
	gender		VARCHAR(6),
	birthday	VARCHAR(10),
	country 	VARCHAR(30),
	userStatus	VARCHAR(10) DEFAULT "Not Logged"
)

ALTER TABLE users ADD userStatus VARCHAR(10) DEFAULT "Not Logged";

DROP DATABASE blog