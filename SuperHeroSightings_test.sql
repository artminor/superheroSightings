DROP DATABASE IF EXISTS SuperHeroSightings_test;

CREATE DATABASE SuperHeroSightings_test;

USE SuperHeroSightings_test;

CREATE TABLE SuperHeroSightings_test.super
(superId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
description VARCHAR(100) NOT NULL,
isHero BIT NOT NULL,
PRIMARY KEY(superId));

CREATE TABLE SuperHeroSightings_test.power
(powerId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
PRIMARY KEY(powerId));

CREATE TABLE SuperHeroSightings_test.organization
(organizationId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
description VARCHAR(100),
locationID INT NOT NULL,
PRIMARY KEY(organizationId),
INDEX locationId(locationId));

CREATE TABLE SuperHeroSightings_test.location
(locationId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
latitude DECIMAL NOT NULL,
longitude DECIMAL NOT NULL,
street VARCHAR(60) NOT NULL,
city VARCHAR(20) NOT NULL,
state VARCHAR(20) NOT NULL,
zip VARCHAR(5) NOT NULL,
description VARCHAR(100) NOT NULL,
PRIMARY KEY(locationId));

CREATE TABLE SuperHeroSightings_test.sightings
(sightingId INT NOT NULL AUTO_INCREMENT,
sightingDate DATE NOT NULL,
locationId INT NOT NULL,
superId INT NOT NULL,
PRIMARY KEY(sightingId),
INDEX locationId(locationId),
INDEX superId(superId));

CREATE TABLE SuperHeroSightings_test.superPower
(superId INT NOT NULL,
powerId INT NOT NULL,
PRIMARY KEY(superId, powerId),
INDEX superId(superId),
INDEX powerId(powerId));

CREATE TABLE SuperHeroSightings_test.superOrganization
(superId INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(superId, organizationId),
INDEX superId(superId),
INDEX organizationId(organizationId));

CREATE TABLE SuperHeroSightings_test.users
(user_id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(20) NOT NULL,
password VARCHAR(100) NOT NULL,
enabled TINYINT(1) NOT NULL,
PRIMARY KEY (user_id),
KEY username (username));

CREATE TABLE SuperHeroSightings_test.usersOrganization
(user_id INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(user_id, organizationId),
INDEX user_id(user_id),
INDEX organizationId(organizationId));

CREATE TABLE IF NOT EXISTS SuperHeroSightings_test.authorities (
username varchar(20) NOT NULL,
authority varchar(20) NOT NULL,
KEY username (username));

ALTER TABLE SuperHeroSightings_test.authorities 
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (username) REFERENCES users(username);

ALTER TABLE SuperHeroSightings_test.sightings
ADD CONSTRAINT fk_sightingsSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings_test.sightings
ADD CONSTRAINT fk_sightingsLocationId
FOREIGN KEY (locationId) REFERENCES location(locationId);

ALTER TABLE SuperHeroSightings_test.organization
ADD CONSTRAINT fk_locationId
FOREIGN KEY (locationId) REFERENCES location(locationId);

ALTER TABLE SuperHeroSightings_test.superPower
ADD CONSTRAINT fk_superPowerSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings_test.superPower
ADD CONSTRAINT fk_superPowerPowerId
FOREIGN KEY (powerId) REFERENCES power(powerId);

ALTER TABLE SuperHeroSightings_test.superOrganization
ADD CONSTRAINT fk_superOrgSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings_test.superOrganization
ADD CONSTRAINT fk_superOrgOrgId
FOREIGN KEY (organizationId) REFERENCES organization(organizationId);

ALTER TABLE SuperHeroSightings_test.usersOrganization
ADD CONSTRAINT fk_userOrgUserId
FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE SuperHeroSightings_test.usersOrganization
ADD CONSTRAINT fk_userOrgOrgId
FOREIGN KEY (organizationId) REFERENCES organization(organizationId);