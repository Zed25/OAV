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
	BandID integer primary key,
	Resolution real not null,
	SpectralLength real not null
);

create table S-BSurbay(
	Instrument character varying (10) references Instruments (Name),
	Band integer references Bands (BandID),
	primary key (Instrument, Band)
);
	
create table Sources (
	SourceID character varying (20) primary key,
	Luminosity real not null,
	Type character varying (10) not null
);

create table Collection (
	StarMap character varying (10) references StarMaps(Name),
	Source character varying (20) references Sources(SourceID),
	primary key (StarMap, Source)
);

create table S_S_Comparison(
	ComparingSource character varying (20) references Sources(SourceID),
	ComparedSource character varying (20) references Sources(SourceID),
	primary key (ComparingSource, ComparedSource)
);

create table Clumps(
	ClumpID integer primary key,
	GalacticLongitude double precision not null,
	GalacticLatitude double precision not null,
	Temperature real not null,
	LMRatio real not null,
	Mass real not null,
	SurfeceDensity real not null,
	Type decimal not null,
	HigalMaps character varying (10) references StarMaps (Name)
);

create table S-C_Membership(
	Source character varying (20) references Sources (SourceID),
	Clump integer references Clumps (ClumpID),
	primary key (Source,Clump)
);

create table Ellipses (
	Clump integer references Clumps(ClumpID),
	Band integer references Bands(BandID),
	Xaxis double precision not null,
	Yaxis double precision not null,
	PositionAngle double precision not null,
	primary key (Clump , Band)
);

create table Fluxes (
	FluxID integer primary key,
	Value real not null,
	Error real,
	Band integer references Bands (BandID),
	Source character varying (20) references Sources (SourceID),
	Clump integer references Clumps (ClumpID)
);
