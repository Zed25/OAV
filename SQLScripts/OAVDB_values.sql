
insert into Agencies (Name) values
  ('ESA'), ('NASA');

insert into Satellites (Name, StartMissionDate, EndMissionDate) values
  ('Herschel', '10/07/2009','17/06/2013'),
  ('Spitzer', '18/12/2003', '15/05/2009');


insert into MissionsJoined(Agency, Satellite) values
  ('ESA', 'Herschel'),
  ('NASA', 'Spitzer');


insert into StarMaps(Name) values
  ('Glimpse'), ('HiGal'), ('MIPS-GAL');


insert into Instruments(Name, satallite, StarMap) values
  ('PACS', 'Herschel','HiGal'),
  ('SPIRE','Herschel', 'HiGal'),
  ('IRAC','Spitzer', 'Glimpse'),
  ('MIPS', 'Spitzer','MIPS-GAL');



insert into Bands (Resolution, SpectralLength) values
  (70.0,5.2),
  (160.0, 12.0),
  (250.0,18.0),
  (350.0, 24.0),
  (500.0, 35.0),
  (3.6, 1.7),
  (4.5, 1.8),
  (5.8, 1.9),
  (8.0, 2.0),
  (24.0, 6.0);



insert into S_BSurbay(Instrument, Band) values
  ('PACS', 70.0),
  ('PACS', 160.0),
  ('SPIRE', 250.0),
  ('SPIRE', 350.0),
  ('SPIRE', 500.0),
  ('IRAC', 3.6),
  ('IRAC', 4.5),
  ('IRAC', 5.8),
  ('IRAC', 8.0),
  ('MIPS', 24.0);