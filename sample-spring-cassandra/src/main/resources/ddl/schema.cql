USE sample;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
           user_id bigint,
           user_name text,
           first_name text,
           last_name text,
           PRIMARY KEY (user_id)
       ); 
