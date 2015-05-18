CREATE DATABASE blog;
USE blog;

CREATE TABLE Users
(
	user_id 	INT(6),
	PRIMARY KEY(user_id),
	userType   	VARCHAR(15),
	userName 	VARCHAR(30),
	email		VARCHAR(30),
	userPassword	VARCHAR(30),
	realName	VARCHAR(30),
	gender		VARCHAR(6),
	birthday	VARCHAR(10),
	country 	VARCHAR(30)
);

CREATE TABLE Categories
(
	cate_id		INT(3),
	PRIMARY KEY(cate_id),
	cateName	VARCHAR(20)
);

CREATE TABLE Posts
(
	post_id		INT(9),
	PRIMARY KEY(post_id),
	postTitle	VARCHAR(100),
	postBody	VARCHAR(2000),
	userId	INT,
	categoryId	INT,
	FOREIGN KEY (userId) REFERENCES Users(user_id),
	FOREIGN KEY (categoryId) REFERENCES Categories(cate_id),
	published	BOOL,
	publishedDate	VARCHAR(20)
);