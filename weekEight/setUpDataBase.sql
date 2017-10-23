-- Drop the database if it exists, as this is purely a sample database
DROP DATABASE IF EXISTS Hotel;

-- Create a new database for our SGRoster example
CREATE DATABASE Hotel;

-- Switch to the database
-- Note that SQL commands are not case-sensitive 
use Hotel;



-- Create a table of Roomtype
CREATE TABLE RoomType
(TypeID int NOT NULL,
TypeName varchar(50),
PRIMARY KEY(TypeID)
);

create table Amenities(
AmID int not null,
AmType varchar(50),
Price double,
primary key(AmID));

-- Create a table of rooms
CREATE TABLE Room
(RoomNumber int NOT NULL,
Floor int NOT NULL,
MaxOccupancy int NOT NULL,
RoomType int not null,
PRIMARY KEY(RoomNumber),
foreign key(RoomType) references RoomType(TypeID)
);

-- bridge table 
create table RoomAmenities(
RoomAmenitiesID int,
RoomID int not null,
AmID int not null,
primary key(RoomAmenitiesID),
foreign key(RoomID) references Room(RoomNumber),
foreign key(AmID) references Amenities(AmID)
);

-- Create a table of type priving
CREATE TABLE TypePricing(
TypePricingID int not null,
TypeID int NOT NULL,
Price double,
startDate DATE,
endDate DATE,
PRIMARY KEY(TypePricingID),
foreign key(TypeID)references RoomType(TypeID)

);

create table contactinfo(
ContactID int not null,
phone varchar(14),
email varchar(50),
primary key(ContactID));

create table people(
PeopleID int not null,
name varchar(50),
contactID int,
DOB date,
primary key(PeopleID),
foreign key(contactID)references contactinfo(ContactID)
);

create table Reservation
(reservationID int not null,
customerID int not null,
primary key(reservationID),
foreign key(customerID) references people(PeopleID)
);

create table PromoDates(
DateID int not null,
StartDate date,
EndDate date,
primary key(DateID));

create table promotions(
PromoID int not null,
PromoType varchar(50) not null,
Discount int not null,
IsPercent boolean,
DateID int not null,
primary key(PromoID),
foreign key(DateID) references PromoDates(DateID)
);

create table AddOns(
AddOnID int not null,
AddOnType varchar(50),
AddonPrice double,
primary key(AddOnID)
);


create table Bill(
BillID int not null,
ResID int not null,
Tax double not null,
total double not null,
primary key(BillID),
foreign key(ResID) references Reservation(reservationID)
);


create table BillAmenities(
AmID int not null,
BillID int not null,
foreign key(AmID) references amenities(AmID),
foreign key(BillID) references Bill(BillID)
);

create table BillAddOns(
AddonID int not null,
BillID int,
foreign key(AddonID) references addons(AddOnID),
foreign key(BillID) references Bill(BillID)
);

create table BillPromos(
PromoID int not null,
BillID int,
foreign key(PromoID) references promotions(PromoID),
foreign key(BillID) references Bill(BillID)
);

CREATE TABLE RoomRes
(ResID int NOT NULL,
RoomID int not null,
startDate DATE,
endDate DATE,
foreign key(RoomID) references Room(RoomNumber),
foreign key(ResID) references Reservation(reservationID)
);

create table guest(
ResID int not null,
geustID int not null,
foreign key(geustID) references people(PeopleID),
foreign key(ResID) references reservation(reservationID)
);


-- X
-- can use bridge table to accesws prices here
create table BillDetail(
BillID int not null,
RoomTypeCost double, 
AmenitiesCost double, 
AddOnsCost double, 
promotionDiscount double, 
foreign key(BillID) references Bill(BillID)
);