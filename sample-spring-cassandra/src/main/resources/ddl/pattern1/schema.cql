USE sample;

DROP TABLE IF EXISTS credential;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS group;
DROP TABLE IF EXISTS address;
DROP TYPE IF EXISTS addressofuser;
DROP TYPE IF EXISTS userofgroup;
DROP TYPE IF EXISTS groupofuser;

CREATE TYPE sample.addressofuser (
    address text,
    last_updated_date timestamp,
    ver int,
    zip_cd text
);

CREATE TYPE sample.groupofuser (
    group_id bigint,
    group_name text,
    last_updated_date timestamp,
    ver int
);

CREATE TYPE sample.userofgroup (
    is_admin boolean,
    is_enabled boolean,
    is_locked boolean,
    last_updated_date timestamp,
    user_id bigint,
    user_name text,
    ver int
);

CREATE TABLE sample.credential (
    login_id text,
    credential_type text,
    credential_key text,
    expired_date timestamp,
    last_updated_date timestamp,
    user_id bigint,
    ver int,
    PRIMARY KEY ((login_id, credential_type))
); 

CREATE TABLE sample.users (
    user_id bigint PRIMARY KEY,
    address frozen<addressofuser>,
    groups list<frozen<groupofuser>>,
    is_admin boolean,
    is_enabled boolean,
    is_locked boolean,
    last_updated_date timestamp,
    user_name text,
    ver int
);

CREATE TABLE sample.group (
    group_id bigint PRIMARY KEY,
    group_name text,
    last_updated_date timestamp,
    users list<frozen<userofgroup>>,
    ver int
); 

CREATE TABLE sample.email (
    email text PRIMARY KEY,
    last_updated_date timestamp,
    user_id bigint,
    ver int
);

CREATE TABLE sample.address (
    zip_cd text,
    user_id bigint,
    address text,
    last_updated_date timestamp,
    ver int,
    PRIMARY KEY (zip_cd, user_id)
); 