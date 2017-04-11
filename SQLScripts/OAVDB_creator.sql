create table Agencies (
	Name character varying (10) primary key
);

create table Satellites (
	Name character varying (10) primary key,
	StartMissionDate timestamp not null,
	EndMissionDate timestamp
);

create table MissionsJoined(
	Agency character varying (10) references Agencies (Name),
	Satellite character varying (10) references Satellites (Name),
	primary key (Agency, Satellite)
);

create table  StarMaps(
	Name character varying (10) primary key
);

create table Instruments (
	Name character varying (10) primary key,
	Satallite character varying (10) references Satellites (Name),
	StarMap character varying (10) references StarMaps (Name)
);

create table Bands(
	Resolution real PRIMARY KEY ,
	SpectralLength real not null
);

create table S_BSurbay(
	Instrument character varying (10) references Instruments (Name),
	Band real references Bands (Resolution),
	primary key (Instrument, Band)
);

create table Sources (
	SourceID character varying (20) primary key,
	GalacticLongitude double precision DEFAULT 0.0,
	GalacticLatitude double precision DEFAULT 0.0,
	Type character varying (10) DEFAULT '',
	ComparedSource character varying (20) references Sources(SourceID)
);

create table Collection (
	StarMap character varying (10) references StarMaps(Name),
	Source character varying (20) references Sources(SourceID),
	primary key (StarMap, Source)
);


create table Clumps(
	ClumpID integer primary key,
	GalacticLongitude double precision DEFAULT 0.0,
	GalacticLatitude double precision DEFAULT 0.0,
	Temperature real DEFAULT 0.0,
	LMRatio real DEFAULT 0.0,
	Mass real DEFAULT 0.0,
	SurfeceDensity real DEFAULT 0.0,
	Type decimal DEFAULT 0,
	HigalMaps character varying (10) references StarMaps (Name)
);

create table S_C_Membership(
	Source character varying (20) references Sources (SourceID),
	Clump integer references Clumps (ClumpID),
	primary key (Source,Clump)
);

create table Ellipses (
	Clump integer not null,
	Band integer references Bands(Resolution),
	Maxaxis double precision not null,
	Minaxis double precision not null,
	PositionAngle double precision not null,
	primary key (Clump , Band)
);

create table Fluxes (
	FluxID SERIAL PRIMARY KEY,
	Value real not null,
	Error real,
	Band integer references Bands (Resolution),
	Source character varying (20) references Sources (SourceID),
	Clump integer references Clumps (ClumpID)
);

