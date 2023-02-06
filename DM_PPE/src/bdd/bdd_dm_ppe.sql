drop database if exists DM_PPE_JAVA;
create database DM_PPE_JAVA;
use DM_PPE_JAVA;

create table Professeur (
    IdPf int (6) not null auto_increment,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    diplome varchar (50) not null,
    email varchar (25) not null unique,
    mdp varchar (25) not null,
    adresse varchar (50) not null,
    constraint pk_Professeur primary key (IdPf)
);

create table Matiere(
    IdM int (6) not null auto_increment,
    intitule varchar (25) not null,
    constraint pk_Matiere primary key (IdM)
);

create table Enseigner(
    IdM int (6) not null,
    IdPf int (6) not null,
    constraint pk_Enseigner primary key (IdM,IdPf),
    constraint fk_Professeur3 foreign key (IdPf) references Professeur(IdPf) on delete cascade on update cascade,
    constraint fk_Matiere foreign key (IdM) references Matiere(IdM) on delete cascade on update cascade
);

create table Classe (
    IdCl int(6) not null auto_increment,
    nom varchar (25) not null,
    diplomePrepare varchar (30) not null,
    promotion varchar (9),
    constraint pk_Classe primary key (IdCl)
);

create table Etudiant (
    IdE int (6) not null auto_increment,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    email varchar (25) not null unique,
    adresse varchar (50) not null,
    IdCl int (6) not null,
    constraint pk_Etudiant primary key (IdE,IdCl),
    constraint fk_Classe foreign key (IdCl) references Classe(IdCl)
);

insert into Professeur values 
(null, "NomProf1", "PrenomProf1", "MASTER INFORMATIQUE", "prof1@gmail.com", "prof1", "PARIS");
