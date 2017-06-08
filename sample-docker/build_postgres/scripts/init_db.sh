#!/bin/bash

set PASSWORD=postgres

su - postgres -c "pg_ctl start -w;psql -f /var/local/postgresql/scripts/create_role.sql;pg_ctl stop -m fast"
su - postgres -c "pg_ctl start -w;psql -f /var/local/postgresql/scripts/create_db.sql;pg_ctl stop -m fast"
su - postgres -c "pg_ctl start -w;psql -f /var/local/postgresql/scripts/create_table.sql -d sample-app;pg_ctl stop -m fast"
su - postgres -c "pg_ctl start -w;psql -f /var/local/postgresql/scripts/grant_role.sql -d sample-app;pg_ctl stop -m fast"
