create table Users (
	User_ID character varying(20) primary key,
	Password character(32) not null,
	Name character varying(20) not null,
	Surname character varying(20) not null,
	Email text not null,
	Type character varying(20) not null
);

INSERT INTO Users(User_ID, Password, Name, Surname, Email, Type)
		VALUES
			('Zeddicus', 'Zeddicus', 'Simone', 'Mancini', 'simone.mancini@gmail.com', 'Admin'),
			('mario.rossi', 'mario.rossi', 'Mario', 'Rossi', 'mario.rossi@gmail.com', 'User');
