CREATE DATABASE blog
USE blog

CREATE TABLE users
(
	id 		INT(6) PRIMARY KEY,
	flags   	INT(1),
	userName 	VARCHAR(50),
	email		VARCHAR(50),
	userPassword	VARCHAR(50),
	realName	VARCHAR(50),
	gender		VARCHAR(5),
	birthday	VARCHAR(50),
	country 	VARCHAR(50)
)



DROP DATABASE blog