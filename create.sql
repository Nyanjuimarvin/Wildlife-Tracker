CREATE DATABASE wildlife_tracker;
CREATE TABLE animals(id SERIAL PRIMARY KEY, name VARCHAR, age VARCHAR, health VARCHAR, type VARCHAR);
CREATE TABLE sightings(id SERIAL PRIMARY KEY,rangerid INTEGER,animalid INTEGER,locationid INTEGER,timesighted TIMESTAMP);
CREATE TABLE rangers(id SERIAL PRIMARY KEY, name VARCHAR,contact VARCHAR, badgeid INTEGER);
CREATE TABLE locations(id SERIAL PRIMARY KEY, name VARCHAR,latitude NUMERIC(3,2),longitude NUMERIC(3,2));
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

