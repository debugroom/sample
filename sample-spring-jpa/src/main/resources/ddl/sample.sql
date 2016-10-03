
/* Drop Tables */

DROP TABLE IF EXISTS ADDRESS;
DROP TABLE IF EXISTS AFFILIATION;
DROP TABLE IF EXISTS EMAIL;
DROP TABLE IF EXISTS GRP;
DROP TABLE IF EXISTS USR;




/* Create Tables */

CREATE TABLE ADDRESS
(
	USER_ID varchar(8) NOT NULL,
	ZIP_CD char(8),
	ADDRESS varchar(255),
	VER int,
	LAST_UPDATED_DATE timestamp,
	PRIMARY KEY (USER_ID)
);


CREATE TABLE AFFILIATION
(
	GROUP_ID char(10) NOT NULL,
	USER_ID varchar(8) NOT NULL,
	VER int,
	LAST_UPDATED_DATE timestamp,
	PRIMARY KEY (GROUP_ID, USER_ID),
);


CREATE TABLE EMAIL
(
	USER_ID varchar(8) NOT NULL,
	EMAIL_ID varchar(255) NOT NULL,
	EMAIL varchar(255),
	VER int,
	LAST_UPDATED_DATE timestamp,
	PRIMARY KEY (USER_ID, EMAIL_ID),
);


CREATE TABLE GRP
(
	GROUP_ID char(10) NOT NULL,
	GROUP_NAME varchar(50),
	VER int,
	LAST_UPDATED_DATE timestamp,
	PRIMARY KEY (GROUP_ID)
);


CREATE TABLE USR
(
	USER_ID varchar(8) NOT NULL,
	USER_NAME varchar(50),
	LOGIN_ID varchar(64),
	VER int,
	LAST_UPDATED_DATE timestamp,
	PRIMARY KEY (USER_ID)
);



/* Create Foreign Keys */

ALTER TABLE AFFILIATION
	ADD FOREIGN KEY (GROUP_ID)
	REFERENCES GRP (GROUP_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ADDRESS
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USR (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE AFFILIATION
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USR (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EMAIL
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USR (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



