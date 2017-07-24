create table Agencies (
  Name character varying (10) primary key
);

create table Satellites (
  Name character varying (10) primary key,
  StartMissionDate timestamp not null,
  EndMissionDate timestamp
);

create table MissionsJoined(
  Agency character varying (10) references Agencies (Name) NOT NULL,
  Satellite character varying (10) references Satellites (Name)  NOT NULL,
  primary key (Agency, Satellite)
);

create table  StarMaps(
  Name character varying (10) primary key
);

create table Instruments (
  Name character varying (10) primary key,
  Satallite character varying (10) references Satellites (Name) NOT NULL,
  StarMap character varying (10) references StarMaps (Name) NOT NULL
);

create table Bands(
  Resolution real PRIMARY KEY ,
  SpectralLength real not null
);

create table S_BSurbay(
  Instrument character varying (10) references Instruments (Name) NOT NULL,
  Band real references Bands (Resolution) NOT NULL,
  primary key (Instrument, Band)
);

create table Sources (
  SourceID character varying (20) primary key,
  GalacticLongitude double precision DEFAULT 0.0,
  GalacticLatitude double precision DEFAULT 0.0,
  ComparedSource character varying (20) references Sources(SourceID)
);

create table Collection (
  StarMap character varying (10) references StarMaps(Name)  NOT NULL,
  Source character varying (20) references Sources(SourceID) NOT NULL,
  primary key (StarMap, Source)
);

create table Clumps(
  ClumpID integer primary key,
  GalacticLongitude double precision DEFAULT 0.0,
  GalacticLatitude double precision DEFAULT 0.0,
  Temperature real DEFAULT 0.0,
  LMRatio real DEFAULT 0.0,
  SurfaceDensity real DEFAULT 0.0,
  Type decimal DEFAULT 0,
  HigalMaps character varying (10) references StarMaps (Name)
);

create table S_C_Membership(
  Source character varying (20) references Sources (SourceID) NOT NULL,
  Clump integer references Clumps (ClumpID)  NOT NULL,
  primary key (Source , Clump)
);

create table Ellipses (
  Clump integer NOT NULL,
  Band REAL references Bands(Resolution)  NOT NULL,
  Maxaxis double precision NOT NULL,
  Minaxis double precision NOT NULL,
  PositionAngle double precision NOT NULL,
  primary key (Clump , Band)
);

create table Fluxes (
  FluxID SERIAL PRIMARY KEY,
  Value real NOT NULL,
  Error real,
  Band real references Bands (Resolution)  NOT NULL,
  Source character varying (20) references Sources (SourceID),
  Clump integer references Clumps (ClumpID)
);

CREATE VIEW V1 AS
  SELECT f1.source AS V1source, (f1.value-f2.value) AS difvalue4558
  FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source
  WHERE ( ((f1.band=4.5) OR (f2.band=5.8)) AND ((f1.value-f2.value)>0.7) );

CREATE VIEW V2 AS
  SELECT f1.source AS V2source, (f1.value-f2.value) AS difvalue3645
  FROM fluxes AS f1 INNER JOIN fluxes AS f2 ON f1.source=f2.source
  WHERE ( ((f1.band=3.6) OR (f2.band=4.5)) AND ((f1.value-f2.value)>0.7) );


