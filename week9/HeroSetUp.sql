-- Drop the database if it exists, as this is purely a sample database
DROP DATABASE IF EXISTS Hero;

-- Create a new database for our SGRoster example
CREATE DATABASE Hero;

-- Switch to the database
-- Note that SQL commands are not case-sensitive 
use Hero;



-- Create a table of Roomtype
CREATE TABLE Champion
(ChampionID int auto_increment NOT NULL,
Name varchar(50) not null,
champDesc varchar(100),
isHero boolean not null,
PRIMARY KEY(ChampionID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE Powers
(PowerID int auto_increment NOT NULL,
Name varchar(50) not null,
PowerDesc varchar(100),
PRIMARY KEY(PowerID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table HeroPower(
HeroID int not null,
PowerID int not null,
foreign key(HeroID) references Champion(ChampionID),
foreign key(PowerID) references Powers(PowerID)
);

create table Organization(
OrganizationID int auto_increment not null,
name varchar(50) not null,
OrgDesc varchar(100),
PRIMARY KEY(OrganizationID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Location(
LocationID int auto_increment not null,
name varchar(50) not null,
address varchar(50) not null,
LocationDesc varchar(100),
Longitude double not null,
Latitude double not null,
PRIMARY KEY(LocationID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Contact(
contactID int auto_increment not null,
phone double,
emial varchar(100),
primary key(contactID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Sighting(
sightID int auto_increment not null,
location int not null,
sightDate date not null,
sightDesc varchar(100),
primary key(sightID),
foreign key(location) references Location(LocationID)
);

create table HeroOrg(
heroID int not null,
OrgID int not null,
foreign key(heroID) references Champion(ChampionID),
foreign key(OrgID) references Organization(OrganizationID)
);

create table OrgCont(
OrgID int not null,
ContID int not null,
foreign key(OrgID) references Organization(OrganizationID),
foreign key(ContID) references Contact(contactID) 
);

create table OrgLoc(
OrgID int not null,
LocID int not null,
foreign key(OrgID) references Organization(OrganizationID),
foreign key(LocID) references Location(LocationID)
);

create table HeroSight(
HeroID int not null,
SightID int not null,
foreign key(HeroID) references Champion(championID),
foreign key(SightID) references Sighting(sightID)
);
