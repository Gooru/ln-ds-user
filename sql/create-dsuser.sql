-- You can run this command using the default postgres (aka superuser) role created by postgres:
-- psql -U postgres -f <path to>/create-dsuser.sql

-- DROP ROLE dsuser;
CREATE ROLE dsuser WITH INHERIT LOGIN REPLICATION PASSWORD 'dspass';


