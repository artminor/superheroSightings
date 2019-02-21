DROP DATABASE IF EXISTS SuperHeroSightings;

CREATE DATABASE SuperHeroSightings;

USE SuperHeroSightings;

CREATE TABLE SuperHeroSightings.super
(superId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
description VARCHAR(100) NOT NULL,
isHero BIT NOT NULL,
PRIMARY KEY(superId));

CREATE TABLE SuperHeroSightings.power
(powerId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
PRIMARY KEY(powerId));

CREATE TABLE SuperHeroSightings.organization
(organizationId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
description VARCHAR(100),
locationID INT NOT NULL,
PRIMARY KEY(organizationId),
INDEX locationId(locationId));

CREATE TABLE SuperHeroSightings.location
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

CREATE TABLE SuperHeroSightings.sightings
(sightingId INT NOT NULL AUTO_INCREMENT,
sightingDate DATE NOT NULL,
locationId INT NOT NULL,
superId INT NOT NULL,
PRIMARY KEY(sightingId),
INDEX locationId(locationId),
INDEX superId(superId));

CREATE TABLE SuperHeroSightings.superPower
(superId INT NOT NULL,
powerId INT NOT NULL,
PRIMARY KEY(superId, powerId),
INDEX superId(superId),
INDEX powerId(powerId));

CREATE TABLE SuperHeroSightings.superOrganization
(superId INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(superId, organizationId),
INDEX superId(superId),
INDEX organizationId(organizationId));

CREATE TABLE SuperHeroSightings.users
(user_id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(20) NOT NULL,
password VARCHAR(100) NOT NULL,
enabled TINYINT(1) NOT NULL,
PRIMARY KEY (user_id),
KEY username (username));

CREATE TABLE SuperHeroSightings.usersOrganization
(user_id INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(user_id, organizationId),
INDEX user_id(user_id),
INDEX organizationId(organizationId));

CREATE TABLE IF NOT EXISTS SuperHeroSightings.authorities (
username varchar(20) NOT NULL,
authority varchar(20) NOT NULL,
KEY username (username));

ALTER TABLE SuperHeroSightings.authorities 
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (username) REFERENCES users(username);

ALTER TABLE SuperHeroSightings.sightings
ADD CONSTRAINT fk_sightingsSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings.sightings
ADD CONSTRAINT fk_sightingsLocationId
FOREIGN KEY (locationId) REFERENCES location(locationId);

ALTER TABLE SuperHeroSightings.organization
ADD CONSTRAINT fk_locationId
FOREIGN KEY (locationId) REFERENCES location(locationId);

ALTER TABLE SuperHeroSightings.superPower
ADD CONSTRAINT fk_superPowerSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings.superPower
ADD CONSTRAINT fk_superPowerPowerId
FOREIGN KEY (powerId) REFERENCES power(powerId);

ALTER TABLE SuperHeroSightings.superOrganization
ADD CONSTRAINT fk_superOrgSuperId
FOREIGN KEY (superId) REFERENCES super(superId);

ALTER TABLE SuperHeroSightings.superOrganization
ADD CONSTRAINT fk_superOrgOrgId
FOREIGN KEY (organizationId) REFERENCES organization(organizationId);

ALTER TABLE SuperHeroSightings.usersOrganization
ADD CONSTRAINT fk_userOrgUserId
FOREIGN KEY (user_id) REFERENCES users(user_id);

ALTER TABLE SuperHeroSightings.usersOrganization
ADD CONSTRAINT fk_userOrgOrgId
FOREIGN KEY (organizationId) REFERENCES organization(organizationId);