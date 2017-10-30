use Hero;

insert into Champion (Name, champDesc, isHero) values('Delivery-Boy Man', 'Always delivers justice on time', TRUE);
insert into Champion (Name, champDesc, isHero) values('Reaper', 'mysterious villain, nothing else is known', FALSE);

insert into Powers (Name, PowerDesc) values('all of them', 'never really in danger..makes for bad story but unbeatable hero');
insert into Powers (Name, PowerDesc) values('dual shot guns', 'minimal range but dangerous at close range');

insert into HeroPower (HeroID, PowerID) values(1,1);
insert into HeroPower (HeroID, PowerID) values(2,2);

insert into Organization (name, OrgDesc) values('Planet express crew','Our crew is expendable your package is not!');
insert into Organization (name, OrgDesc) values('Claw','...');

insert into Location(name, address, LocationDesc, Longitude, Latitude) values('Miranda','N/A', 'farthest out rim plannet deep in reever territory', 65496813216854, 65498465164);
insert into Location(name, address, LocationDesc, Longitude, Latitude) values('Secret Swamp','123 croc ln.', 'smelly', 15486, 486974);

insert into Contact(phone, emial) values(5555555, 'hermes.conrad@planetexpress.com');
insert into Contact(phone, emial) values(5555555, 'bad@evil.com');

insert into Sighting(location, sightDate, sightDesc) values(1,'2017-10-10','seen running away from reevers while crying');
insert into Sighting(location, sightDate, sightDesc) values(2,'2017-11-10','claw on location');

insert into HeroOrg(heroID, OrgID) values(1,1);
insert into HeroOrg(heroID, OrgID) values(2,2);

insert into OrgCont (OrgID, ContID) values(1,1);
insert into OrgCont (OrgID, ContID) values(2,2);

insert into OrgLoc(OrgID, LocID) values(1,1);
insert into OrgLoc(OrgID, LocID) values(2,2);

insert into HeroSight(HeroID, SightID) values(1,1);
insert into HeroSight(HeroID, SightID) values(2,2);