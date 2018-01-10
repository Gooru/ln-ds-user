-- You can run this command using the default postgres (aka superuser) user created by postgres:
-- psql -U postgres -f <path to>/create-ds-db.sql

-- DROP DATABASE datascopedb;

CREATE DATABASE datascopedb
  WITH OWNER = dsuser
       ENCODING = 'UTF8'
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       TEMPLATE template0;


grant all privileges on database datascopedb to dsuser;
