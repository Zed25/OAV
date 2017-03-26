create table "Utenti"(
	"User-id" character varying(20) primary key,
	"Password" character(32) not null,
	"Nome" character varying(20) not null,
	"Cognome" character varying(20) not null,
	"email" text not null,
	"tipo" character varying(20) not null
);