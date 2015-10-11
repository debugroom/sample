DELETE FROM ADDRESS;
DELETE FROM PHONE;
DELETE FROM EMAIL;
DELETE FROM CREDENTIAL;
DELETE FROM DUSER;

INSERT INTO DUSER VALUES('00000000', 'org.debugroom', '2015-01-01 00:00:00.0',false);
INSERT INTO DUSER VALUES('00000001', '(・∀・)', '2015-01-01 00:00:00.0',false);
INSERT INTO DUSER VALUES('00000002', '(ΦωΦ)', '2015-01-01 00:00:00.0',false);

INSERT INTO CREDENTIAL VALUES('00000000', '0000', 'password', 'org.debugroom', '2015-01-01 00:00:00.0');
INSERT INTO CREDENTIAL VALUES('00000000', '0001', 'accessToken', '123456789qwertyuiopasdfghjklzxcvbnm', '2015-01-01 00:00:00.0');
INSERT INTO CREDENTIAL VALUES('00000001', '0000', 'password', '(・ω・｀)', '2015-01-01 00:00:00.0');

INSERT INTO EMAIL VALUES('00000000', 1, 'debugroom@debugroom.org');
INSERT INTO EMAIL VALUES('00000000', 2, 'debugroom2@debugroom.org');
INSERT INTO EMAIL VALUES('00000001', 1, 'debugroom3@debugroom.org');

INSERT INTO PHONE VALUES('00000000', 1, '07012345678');
INSERT INTO PHONE VALUES('00000000', 2, '08012345678');
INSERT INTO PHONE VALUES('00000001', 1, '09012345678');

INSERT INTO ADDRESS VALUES('00000000', 1, '1-1-1-Akihabara, Chiyoda, Tokyo', 'Building 1F');
INSERT INTO ADDRESS VALUES('00000000', 2, '1-1-1-Akihabara, Chiyoda, Tokyo', 'Building 2F');
INSERT INTO ADDRESS VALUES('00000001', 1, '3-3-3-Toyosu, Koto, Tokyo', 'Building 36F');

/* SUPER USER only can do as follow commnad. switch postgreSQL super user. */
/*
COPY DUSER FROM '/<Your Absolute path>/spring_sample/env_settings/sqls/postgreSQL/data/duser.csv' USING DELIMITERS ',';
COPY CREDENTIAL FROM '/<Your Absolute path>/spring_sample/env_settings/sqls/postgreSQL/data/credential.csv' USING DELIMITERS ',';
 */