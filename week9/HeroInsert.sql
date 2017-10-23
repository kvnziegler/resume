use Hero;

insert into Champion (Name, champDesc, isHero) values('Delivery-Boy Man', 'Always delivers justice on time', TRUE);

insert into Powers (Name, PowerDesc) values('all of them', 'never really in danger..makes for bad story but unbeatable hero');

insert into HeroPower (HeroID, PowerID) values(1,1);

insert into Organization (name, OrgDesc) values('Planet express crew','Our crew is expendable your package is not!');

insert into Location(name, address, LocationDesc, Longitude, Latitude) values('Miranda','N/A', 'farthest out rim plannet deep in reever territory', 65496813216854, 65498465164);

insert into Contact(phone, emial) values(5555555, 'hermes.conrad@planetexpress.com');

insert into Sighting(location, sightDate, sightDesc) values(1,'2017-10-10','seen running away from reevers while crying');

insert into HeroOrg(heroID, OrgID) values(1,1);

insert into OrgCont (OrgID, ContID) values(1,1);

insert into OrgLoc(OrgID, LocID) values(1,1);

insert into HeroSight(HeroID, SightID) values(1,1);