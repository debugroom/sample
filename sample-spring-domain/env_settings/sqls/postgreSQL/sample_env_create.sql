
/* Drop Tables */

DROP TABLE IF EXISTS ADDRESS;
DROP TABLE IF EXISTS CREDENTIAL;
DROP TABLE IF EXISTS EMAIL;
DROP TABLE IF EXISTS PHONE;
DROP TABLE IF EXISTS DUSER;




/* Create Tables */

CREATE TABLE ADDRESS
(
	USER_ID varchar(8) NOT NULL,
	ADDRESS_NO int NOT NULL,
	ADDRESS varchar,
	ADDRESS_DETAILS varchar,
	PRIMARY KEY (USER_ID, ADDRESS_NO),
	UNIQUE (USER_ID, ADDRESS_NO)
) WITHOUT OIDS;


CREATE TABLE CREDENTIAL
(
	USER_ID varchar(8) NOT NULL,
	CREDENTIAL_ID varchar(4) NOT NULL,
	CREDENTIAL_TYPE varchar,
	CREDENTIAL_KEY varchar,
	EXPIRED_DATE_AND_TIME timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (USER_ID, CREDENTIAL_ID),
	UNIQUE (USER_ID, CREDENTIAL_ID)
) WITHOUT OIDS;


CREATE TABLE DUSER
(
	USER_ID varchar(8) NOT NULL,
	USER_NAME varchar(50),
	LAST_UPDATED_DATE_AND_TIME timestamp with time zone,
	IS_LOGIN boolean,
	PRIMARY KEY (USER_ID)
) WITHOUT OIDS;


CREATE TABLE EMAIL
(
	USER_ID varchar(8) NOT NULL,
	EMAIL_NO int NOT NULL,
	EMAIL varchar,
	PRIMARY KEY (USER_ID, EMAIL_NO),
	UNIQUE (USER_ID, EMAIL_NO)
) WITHOUT OIDS;


CREATE TABLE PHONE
(
	USER_ID varchar(8) NOT NULL,
	PHONE_NO int NOT NULL,
	PHONE_NUMBER varchar(11),
	PRIMARY KEY (USER_ID, PHONE_NO),
	UNIQUE (USER_ID, PHONE_NO)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE CREDENTIAL
	ADD FOREIGN KEY (USER_ID)
	REFERENCES DUSER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE EMAIL
	ADD FOREIGN KEY (USER_ID)
	REFERENCES DUSER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PHONE
	ADD FOREIGN KEY (USER_ID)
	REFERENCES DUSER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ADDRESS
	ADD FOREIGN KEY (USER_ID)
	REFERENCES DUSER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



