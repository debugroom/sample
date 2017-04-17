USE sample;

DROP TABLE IF EXISTS affiliation;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS group;
DROP TYPE IF EXISTS address;
DROP TYPE IF EXISTS credential;
DROP TYPE IF EXISTS email;

CREATE TYPE sample.address (
    address text,
    zip_cd text
);

CREATE TYPE sample.credential (
    credential_key text,
    credential_type text,
    expired_date timestamp,
    last_updated_date timestamp,
    ver int
);

CREATE TYPE sample.email (
    email text,
    last_updated_date timestamp,
    ver int
);

CREATE TABLE sample.affiliation (
    group_id bigint,
    user_id bigint,
    ver int,
    last_updated_date timestamp,
    PRIMARY KEY (group_id, user_id)
);

CREATE TABLE sample.group (
    group_id bigint,
    group_name text,
    ver int,
    last_updated_date timestamp,
    PRIMARY KEY (group_id)
);

CREATE TABLE sample.users (
    user_id bigint,
    user_name text,
    login_id text,
    is_admin boolean,
    is_enabled boolean,
    is_locked boolean,
    address frozen<address>,
    emails list<frozen<email>>,
    credentials list<frozen<credential>>,
    ver int,
    last_updated_date timestamp,
    PRIMARY KEY (user_id)
); 

