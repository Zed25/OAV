create table "Agenzie"(
	"Nome" character varying(10) primary key
);

create table "Satelliti"(
	"Nome" character varying(10) primary key,
	"DataInizioMissione" timestamp not null,
	"DataFineMissione" timestamp 
);

create table "Partecipazione_Missione"(
	"Agenzia" character varying(10) references "Agenzie"("Nome"),
	"Satellite" character varying(10) references "Satelliti"("Nome"),
	 primary key ("Agenzia", "Satellite")
);

create table "Mappe_Stellari"(
	"Nome" character varying(10) primary key
);

create table "Strumenti"(
	"Nome" character varying(10) primary key,
	"Satellite" character varying(10) references "Satelliti"("Nome"),
	"MappaStellare" character varying(10) references "Mappe_Stellari"("Nome")
);

create table "Bande"(
	"IDBanda" integer primary key,
	"Risoluzione" real not null,
	"LunghezzaSpettrale" real not null
);

create table "Rilevazione_S-B"(
	"Strumento" character varying(10) references "Strumenti"("Nome"),
	"Banda" integer references "Bande"("IDBanda"),
	primary key ("Strumento", "Banda")
);
	
create table "Sorgenti"(
	"IDSorgente" character varying(20) primary key,
	"Luminosità" real not null,
	"Tipo" character varying(10) not null
);

create table "Afferenza_M_S-S"(
	"MappaStellare" character varying(10) references "Mappe_Stellari"("Nome"),
	"Sorgente" character varying(20) references "Sorgenti"("IDSorgente"),
	primary key ("MappaStellare","Sorgente")
);

create table "Confronto_S-S"( 
	"SorgenteConfrontante" character varying(20) references "Sorgenti"("IDSorgente"),
	"SorgenteConfrontata" character varying(20) references "Sorgenti"("IDSorgente"),
	primary key ("SorgenteConfrontante","SorgenteConfrontata")
);

create table "Clumps"(
	"IDClump" integer primary key,
	"LongitudineGalattica" double precision not null,
	"LatitudineGalattica" double precision not null,
	"Temperatura" real not null,
	"RapportoTBM" real not null,
	"Massa" real not null,
	"DensitàSuperficie" real not null,
	"Tipo"  decimal not null,
	"MappaHigal" character varying(10) references "Mappe_Stellari"("Nome")
);

create table "Appartentenza_S-C"(
	"Sorgente" character varying(20) references "Sorgenti"("IDSorgente"),
	"Clump" integer references "Clumps"("IDClump"),
	primary key ("Sorgente","Clump")
);

create table "Ellisse"(
	"Clump" integer references "Clumps"("IDClump"),
	"Banda" integer references "Bande"("IDBanda"),
	"AsseX" double precision not null,
	"AsseY" double precision not null,
	"AngoloRotazione" double precision not null,
	primary key ("Clump","Banda")
);

create table "Flussi"(
	"IDFlusso" integer primary key,
	"Valore" real not null,
	"Errore" real,
	"Banda" integer references "Bande"("IDBanda"),
	"Sorgente" character varying(20) references "Sorgenti"("IDSorgente"),
	"Clump" integer references "Clumps"("IDClump")
);
