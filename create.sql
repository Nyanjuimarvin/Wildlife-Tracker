CREATE DATABASE wildlife_tracker;
CREATE TABLE animals(id SERIAL PRIMARY KEY, name VARCHAR, age VARCHAR, health VARCHAR, rangerid INTEGER, type VARCHAR);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

