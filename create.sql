CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals(id SERIAL PRIMARY KEY, name VARCHAR, agerange VARCHAR, health VARCHAR, rangerid INTEGER, type VARCHAR);
CREATE TABLE locations(id SERIAL PRIMARY KEY, name VARCHAR,latitude NUMERIC(4,2),longitude NUMERIC(5,2));
CREATE TABLE rangers(id SERIAL PRIMARY KEY, name VARCHAR,contact VARCHAR, badgeid INTEGER);
CREATE TABLE sightings(id SERIAL PRIMARY KEY,rangerid INTEGER,animalid INTEGER,locationid INTEGER,timesighted TIMESTAMP);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

