drop database if exists GestRetards2;
create database GestRetards2;
use GestRetards2;

create table User(
    IdU int not null,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    email varchar (50) not null unique,
    telephone varchar (10) not null unique,
    adresse varchar (50) not null,
    mdp varchar (100) not null,
    constraint pk_User primary key (IdU)
);

create table Professeur (
    IdPf int (6) not null,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    diplome varchar (50) not null,
    email varchar (50) not null unique,
    telephone varchar (10) not null unique,
    adresse varchar (50) not null,
    mdp varchar (100) not null,
    constraint pk_Professeur primary key (IdPf)
);

create table Administrateur (
    IdAd int (6) not null,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    email varchar (50) not null unique,
    telephone varchar (10) not null unique,
    adresse varchar (50) not null,
    mdp varchar (100) not null,
    URLSignature varchar (100) not null,
    constraint pk_Administrateur primary key (IdAd)
);

create table Transport (
    IdTp varchar (30) not null,
    nom varchar (30) not null,
    type varchar (15) not null,
    transporteur varchar (25) not null,
    pictogramme varchar (75),
    etat set ("Fluide","Perturbée") default "Fluide",
    constraint pk_Transport primary key (IdTp)
);

create table Classe (
    IdCl int(6) not null auto_increment,
    nom varchar (25) not null,
    nbEtudiants int (2) default 0,
    email varchar (50) not null unique,
    diplomePrepare varchar (30) not null,
    promotion varchar (9),
    constraint pk_Classe primary key (IdCl)
);

create table Matiere(
    IdM int (6) not null auto_increment,
    intitule varchar (25) not null,
    constraint pk_Matiere primary key (IdM)
);

create table Station(
    IdSt varchar (30) not null,
    nom varchar (30) not null,
    ville varchar (30) not null,
    constraint pk_Station primary key (IdSt)
);

create table Etudiant (
    IdE int (6) not null,
    nom varchar (25) not null,
    prenom varchar (25) not null,
    email varchar (50) not null unique,
    telephone varchar (10) not null unique,
    mdp varchar (100) not null,
    adresse varchar (50) not null,
    IdCl int (6) not null,
    constraint pk_Etudiant primary key (IdE),
    constraint fk_Classe foreign key (IdCl) references Classe(IdCl)
);

create table HistoUser(
    IdHU int (6) not null auto_increment,
    IdU int (6) not null,
    mdp varchar(50),
    constraint pk_histouser primary key(IdHU, IdU),
    constraint fk_user foreign key(IdU) references User(IdU) on delete cascade
);

create table Perturbation(
    IdPt varchar (90) not null,
    raisonCourte varchar (250),
    raisonLongue varchar (250),
    dateDebMessage datetime,
    dateFinMessage datetime,
    constraint pk_Perturbation primary key (IdPt)
);

create table Cours(
    IdCl int (6) not null,
    IdPf int (6) not null,
    dateTS datetime not null,
    matiere varchar (25) not null,
    dateC date not null,
    heureDeb time not null,
    heureFin time not null,
    duree time,
    salle varchar (20),
    constraint check_heure check (heureDeb < heureFin),
    constraint pk_Cours primary key (IdCl, IdPf,dateTS),
    constraint fk_Classe2 foreign key (IdCl) references Classe(IdCl) on delete cascade on update cascade,
    constraint fk_Professeur2 foreign key (IdPf) references Professeur(IdPf) on delete cascade on update cascade
);

create table Trajet(
    IdSt varchar (30) not null,
    IdE int (6) not null,
    constraint pk_Trajet primary key(IdSt,IdE),
    constraint fk_Station foreign key (IdSt) references Station(IdSt) on delete cascade on update cascade,
    constraint fk_Etudiant4 foreign key (IdE) references Etudiant(IdE) on delete cascade on update cascade
);

create table Enseigner(
    IdM int (6) not null,
    IdPf int (6) not null,
    constraint pk_Enseigner primary key (IdM,IdPf),
    constraint fk_Professeur3 foreign key (IdPf) references Professeur(IdPf) on delete cascade on update cascade,
    constraint fk_Matiere foreign key (IdM) references Matiere(IdM) on delete cascade on update cascade
);

create table Appartenir(
    IdSt varchar(30) not null,
    IdTp varchar (30) not null,
    constraint pk_Appartenir primary key (IdSt,IdTp),
    constraint fk_Station2 foreign key (IdSt) references Station(IdSt) on delete cascade on update cascade,
    constraint fk_Transport foreign key (IdTp) references Transport(IdTp) on delete cascade on update cascade
);

create table Billet(
    dateB date not null,
    heureB time not null,
    dureeRetard time not null,
    URLSignature varchar (50) not null,
    dateheure datetime not null,
    raison varchar (50) default " ",
    IdAd int (6) not null,
    IdE int (6) not null,
    constraint pk_Billet primary key (IdE,IdAd,dateheure),
    constraint fk_Etudiant3 foreign key (IdE) references Etudiant(IdE) on delete cascade on update cascade,
    constraint fk_Administrateur2 foreign key (IdAd) references Administrateur(IdAd) on delete cascade on update cascade
);

create table Concerner(
    IdSt varchar (30) not null,
    IdPt varchar (90) not null,
    constraint pk_Concerner primary key (IdSt,IdPt),
    constraint fk_Perturbation foreign key (IdPt) references Perturbation(IdPt) on delete cascade on update cascade,
    constraint fk_Station3 foreign key (IdSt) references Station(IdSt) on delete cascade on update cascade
);

--  /ADMIN
--      /INSERT

drop trigger if exists insert_admin;
delimiter //
create trigger insert_admin
before insert on Administrateur
for each row
begin
declare e int;
declare p int;
declare u int;

-- auto_increment
if new.IdAd is null OR new.IdAd in (select IdU from User) OR new.IdAd = 0
    then
        set new.IdAd = ifnull((select idu from User where idu >= all(select idu from user)),0) + 1;
end if;

-- heritage

set new.mdp = sha1(new.mdp);
insert into User values (new.IdAd,new.nom,new.prenom,new.email,new.telephone,new.adresse,new.mdp);

end //
delimiter ;

--      /UPDATE
--          /BEFORE
drop trigger if exists beforeupd_admin;
delimiter //
create trigger beforeupd_admin
before update on Administrateur
for each row
begin

-- chiffrage mdp + controle
if sha1(new.mdp) = old.mdp
    then
        signal sqlstate '45000'
        set message_text = 'Le mot de passe doit etre different';
    else if sha1(new.mdp) in (select mdp from HistoUser where IdU = new.IdAd) = 1
        && new.mdp != (select mdp from User where IdU = new.IdAd)
        then
            signal sqlstate '45000'
            set message_text = 'Le mot de passe a déjà été utilisé dans le passé';
        else if sha1(new.mdp) != sha1(old.mdp) 
                && new.mdp != (select mdp from User where IdU = new.IdAd)
                then
                    set new.mdp = sha1(new.mdp);
        end if;
    end if;
end if;
end //
delimiter ;

--          /AFTER

drop trigger if exists afterupd_admin;
delimiter //
create trigger afterupd_admin
after update on Administrateur
for each row
begin
declare mdpU varchar (100);
declare nomU varchar (25);
declare adresseU varchar(50);
declare emailU varchar (50);
declare telephoneU varchar (10);
declare prenomU varchar (25);

-- historisation

if  new.mdp in (select mdp from HistoUser where IdU = new.IdAd) = 0
    && new.mdp != (select mdp from User where IdU = new.IdAd)
    then
        insert into HistoUser values (null,new.IdAd,old.mdp);
end if;

-- heritage mdp
select mdp into mdpU from User where IdU = new.IdAd;
if mdpU != new.mdp
    then
        update User set mdp = new.mdp where IdU = new.IdAd;
end if;
-- heritage (hors-mdp)
select nom,prenom,adresse,telephone,email into nomU,prenomU,adresseU,telephoneU,emailU from User where IdU = new.IdAd;
        if new.nom != nomU OR new.prenom != prenomU OR new.email != emailU OR new.telephone != telephoneU OR new.adresse != adresseU
            then
                update User
                    set nom = new.nom,
                    prenom = new.prenom,
                    email = new.email,
                    telephone = new.telephone,
                    adresse = new.adresse
                    where IdU = new.IdAd;
end if;
end //
delimiter ;

--      /DELETE

drop trigger if exists del_admin;
delimiter //
create trigger del_admin
after delete on Administrateur
for each row
begin
if old.idad in (select idu from user)
    then
        delete from User
        where IdU = old.IdAd;
end if;
end //
delimiter ;

--  /ETUDIANT
--      /INSERT
--          /BEFORE

drop trigger if exists beforeins_etudiant;
delimiter //
create trigger beforeins_etudiant
before insert on Etudiant
for each row
begin

-- auto_increment
if new.IdE is null OR new.IdE in (select IdU from User) OR new.IdE = 0
    then
        set new.IdE = ifnull((select idu from User where idu >= all(select idu from user)),0) + 1;
end if;

-- heritage
set new.mdp = sha1(new.mdp);
insert into user values (new.IdE,new.nom,new.prenom,new.email,new.telephone,new.adresse,new.mdp);

end //
delimiter ;
--          /AFTER

drop trigger if exists afterins_etudiant;
delimiter //
create trigger afterins_etudiant
after insert on Etudiant
for each row
begin
declare somme int;

-- maj nbEtudiant dans classe
select count(*) into somme from Etudiant where IdCl = new.IdCl;
update Classe
    set nbEtudiants = somme
    where IdCl = new.IdCl;
end //
delimiter ;

--      /UPDATE
--          /BEFORE
drop trigger if exists beforeupd_etudiant;
delimiter //
create trigger beforeupd_etudiant
before update on Etudiant
for each row
begin

-- chiffrage mdp + controle
if sha1(new.mdp) = old.mdp
    then
        signal sqlstate '45000'
        set message_text = 'Le mot de passe doit etre different';
    else if sha1(new.mdp) in (select mdp from HistoUser where IdU = new.IdE) = 1
        && new.mdp != (select mdp from User where IdU = new.IdE)
        then
            signal sqlstate '45000'
            set message_text = 'Le mot de passe a déjà été utilisé dans le passé';
        else if sha1(new.mdp) != sha1(old.mdp) 
                && new.mdp != (select mdp from User where IdU = new.IdE)
                then
                    set new.mdp = sha1(new.mdp);
        end if;
    end if;
end if;
end //
delimiter ;

--              /AFTER

drop trigger if exists afterupd_etudiant;
delimiter //
create trigger afterupd_etudiant
after update on Etudiant
for each row
begin
declare somme int;
declare mdpU varchar (100);
declare nomU varchar (25);
declare adresseU varchar (50);
declare emailU varchar (50);
declare telephoneU varchar (10);
declare prenomU varchar (25);

-- historisation

if  new.mdp in (select mdp from HistoUser where IdU = new.IdE) = 0
    && new.mdp != (select mdp from User where IdU = new.IdE)
    then
        insert into HistoUser values (null,new.IdE,old.mdp);
end if;

-- heritage mdp
select mdp into mdpU from User where IdU = new.IdE;
if mdpU != new.mdp
    then
        update User set mdp = new.mdp where IdU = new.IdE;
end if;

-- heritage (hors-mdp)
select nom,prenom,adresse,telephone,email into nomU,prenomU,adresseU,telephoneU,emailU from User where IdU = new.IdE;
        if new.nom != nomU OR new.prenom != prenomU OR new.email != emailU OR new.telephone != telephoneU OR new.adresse != adresseU
            then
                update User
                    set nom = new.nom,
                    prenom = new.prenom,
                    email = new.email,
                    telephone = new.telephone,
                    adresse = new.adresse
                    where IdU = new.IdE;
end if;

-- maj nbEtudiant dans classe
if old.IdCl != new.IdCl
    then
        select count(*) into somme from Etudiant where IdCl = new.IdCl;
        update Classe
            set nbEtudiants = somme
            where IdCl = new.IdCl;
        select count(*) into somme from Etudiant where IdCl = old.IdCl;
        update Classe
            set nbEtudiants = somme
            where IdCl = old.IdCl;
end if;
end //
delimiter ;

--    /DELETE

drop trigger if exists del_etudiant;
delimiter //
create trigger del_etudiant
after delete on Etudiant
for each row
begin
declare somme int;

-- heritage
if old.ide in (select idu from user)
    then
        delete from User
        where IdU = old.ide;
end if;

-- maj nbEtudiant dans classe
select count(*) into somme from Etudiant where IdCl = old.IdCl;
update Classe
    set nbEtudiants = somme
    where IdCl = old.IdCl;
end //
delimiter ;

--  /PROFESSEUR
--      /INSERT

drop trigger if exists ins_prof;
delimiter //
create trigger ins_prof
before insert on Professeur
for each row
begin
-- auto_increment
if new.IdPf is null OR new.IdPf in (select IdU from User) OR new.IdPf = 0
    then
        set new.IdPf = ifnull((select idu from User where idu >= all(select idu from user)),0) + 1;
end if;

-- heritage
set new.mdp = sha1(new.mdp);
insert into user values (new.IdPf,new.nom,new.prenom,new.email,new.telephone,new.adresse,new.mdp);

end //
delimiter ;

--    /UPDATE

drop trigger if exists beforeupd_prof;
delimiter //
create trigger beforeupd_prof
before update on Professeur
for each row
begin

-- chiffrage mdp + controle
if sha1(new.mdp) = old.mdp
    then
        signal sqlstate '45000'
        set message_text = 'Le mot de passe doit etre different';
    else if sha1(new.mdp) in (select mdp from HistoUser where IdU = new.IdPf) = 1
        && new.mdp != (select mdp from User where IdU = new.IdPf)
        then
            signal sqlstate '45000'
            set message_text = 'Le mot de passe a déjà été utilisé dans le passé';
        else if sha1(new.mdp) != sha1(old.mdp) 
                && new.mdp != (select mdp from User where IdU = new.IdPf)
                then
                    set new.mdp = sha1(new.mdp);
        end if;
    end if;
end if;
end //
delimiter ;

--              /AFTER

drop trigger if exists afterupd_prof;
delimiter //
create trigger afterupd_prof
after update on Professeur
for each row
begin
declare mdpU varchar (100);
declare nomU varchar (25);
declare adresseU varchar (50);
declare emailU varchar (50);
declare telephoneU varchar (10);
declare prenomU varchar (25);

-- historisation

if  new.mdp in (select mdp from HistoUser where IdU = new.IdPf) = 0
    && new.mdp != (select mdp from User where IdU = new.IdPf)
    then
        insert into HistoUser values (null,new.IdPf,old.mdp);
end if;

-- heritage mdp
select mdp into mdpU from User where IdU = new.IdPf;
if mdpU != new.mdp
    then
        update User set mdp = new.mdp where IdU = new.IdPf;
end if;
-- heritage (hors-mdp)
select nom,prenom,adresse,telephone,email into nomU,prenomU,adresseU,telephoneU,emailU from User where IdU = new.IdPf;
        if new.nom != nomU OR new.prenom != prenomU OR new.email != emailU OR new.telephone != telephoneU OR new.adresse != adresseU
            then
                update User
                    set nom = new.nom,
                    prenom = new.prenom,
                    email = new.email,
                    telephone = new.telephone,
                    adresse = new.adresse
                    where IdU = new.IdPf;
end if;
end //
delimiter ;

--    /DELETE

drop trigger if exists del_prof;
delimiter //
create trigger del_prof
after delete on Professeur
for each row
begin
if old.idpf in (select idu from user)
    then
        delete from User
        where IdU = old.IdPf;
end if;
end //
delimiter ;

--  /USER
--      /UPDATE
--          /BEFORE

drop trigger if exists beforeupd_user;
delimiter //
create trigger beforeupd_user
before update on User
for each row
begin

-- chiffrage mdp + controle
if sha1(new.mdp) = old.mdp
    then
        signal sqlstate '45000'
        set message_text = 'Le mot de passe doit etre different';
    else if sha1(new.mdp) in (select mdp from HistoUser h where h.IdU = new.IdU) = 1
            && (new.mdp != (select mdp from Administrateur where IdAd = new.IdU)
                OR new.mdp != (select mdp from Professeur where IdPf = new.IdU)
                OR new.mdp != (select mdp from Etudiant where IdE = new.IdU))
        then
            signal sqlstate '45000'
            set message_text = 'Le mot de passe a déjà été utilisé dans le passé';
        else if sha1(new.mdp) != sha1(old.mdp)
                && (new.mdp != (select mdp from Administrateur where IdAd = new.IdU)
                OR new.mdp != (select mdp from Professeur where IdPf = new.IdU)
                OR new.mdp != (select mdp from Etudiant where IdE = new.IdU))
                then
                    set new.mdp = sha1(new.mdp);
        end if;
    end if;
end if;
end //
delimiter ;

--          /AFTER

drop trigger if exists afterupd_user;
delimiter //
create trigger afterupd_user
after update on User
for each row
begin
declare mdp2 varchar (100);
declare nom2 varchar (25);
declare adresse2 varchar(50);
declare email2 varchar (50);
declare telephone2 varchar (10);
declare prenom2 varchar (25);

-- historisation mdp
if  new.mdp in (select mdp from HistoUser h where h.IdU = new.IdU) = 0
    && (new.mdp != (select mdp from Administrateur where IdAd = new.IdU)
                OR new.mdp != (select mdp from Professeur where IdPf = new.IdU)
                OR new.mdp != (select mdp from Etudiant where IdE = new.IdU))
    then
        insert into HistoUser values (null,new.IdU,old.mdp);
end if;

-- heritages
if new.IdU IN (select IdPf from Professeur) = 1
    then
--      mdp
        select mdp into mdp2 from Professeur where IdPf = new.IdU;
        if mdp2 != new.mdp
            then
                update Professeur set mdp = new.mdp where IdPf = new.IdU;
        end if;
--      autres
        select nom,prenom,adresse,telephone,email into nom2,prenom2,adresse2,telephone2,email2 from Professeur where IdPf = new.IdU;
        if new.nom != nom2 OR new.prenom != prenom2 OR new.email != email2 OR new.telephone != telephone2 OR new.adresse != adresse2
            then
                update Professeur
                    set nom = new.nom,
                    prenom = new.prenom,
                    email = new.email,
                    telephone = new.telephone,
                    adresse = new.adresse
                    where IdPf = new.IdU;
        end if;
else if new.IdU IN (select IdE from Etudiant) = 1
        then
--      mdp
            select mdp into mdp2 from Etudiant where IdE = new.IdU;
            if mdp2 != new.mdp
                then
                    update Etudiant set mdp = new.mdp where IdE = new.IdU;
            end if;
--      autres
            select nom,prenom,adresse,telephone,email into nom2,prenom2,adresse2,telephone2,email2 from Etudiant where IdE = new.IdU;
            if new.nom != nom2 OR new.prenom != prenom2 OR new.email != email2 OR new.telephone != telephone2 OR new.adresse != adresse2
                then
                    update Etudiant
                        set nom = new.nom,
                        prenom = new.prenom,
                        email = new.email,
                        telephone = new.telephone,
                        adresse = new.adresse
                        where IdE = new.IdU;
            end if;
        else if new.IdU IN (select IdAd from Administrateur) = 1
                then
--      mdp
                    select mdp into mdp2 from Administrateur where IdAd = new.IdU;
                    if mdp2 != new.mdp
                        then
                            update Administrateur set mdp = new.mdp where IdAd = new.IdU;
                    end if;
--      autres
                    select nom,prenom,adresse,telephone,email into nom2,prenom2,adresse2,telephone2,email2 from Administrateur where IdAd = new.IdU;
                    if new.nom != nom2 OR new.prenom != prenom2 OR new.email != email2 OR new.telephone != telephone2 OR new.adresse != adresse2
                        then
                            update Administrateur
                                set nom = new.nom,
                                prenom = new.prenom,
                                email = new.email,
                                telephone = new.telephone,
                                adresse = new.adresse
                                where IdAd = new.IdU;
            end if;     
        end if;
    end if;
end if;
end //
delimiter ;

--       /DELETE

drop trigger if exists del_user;
delimiter //
create trigger del_user
after delete on user
for each row
begin
    if old.IdU in (select idPf from Professeur) OR old.IdU in (select ide from Etudiant) OR old.IdU in (select idad from administrateur)
        then
            delete from Professeur where idpf = old.IdU;
            delete from Administrateur where idad = old.IdU;
            delete from Etudiant where ide = old.IdU;
    end if;
end //
delimiter ;

--  /CONCERNER
--      /INSERT

drop trigger if exists InsFluidite;
delimiter //
create trigger InsFluidite
after insert on Concerner
for each row
begin
-- Fluidite Transport (ATTENTION: CHARGER LA VUE Vue_Perturbation_Ligne pour que le trigger fonctionne) --
update Transport
    set etat = "Perturbée"
        where IdTp in (select IdTp from Vue_Perturbation_Ligne);
end //
delimiter ;

--      /DELETE

drop trigger if exists DelFluidite;
delimiter //
create trigger DelFluidite
    after delete on Concerner
    for each row
begin
    update Transport
        set etat = "Fluide"
            where etat = "Perturbée";
end //
delimiter ;

-- /BILLET
--      /INSERT

drop trigger if exists InsBilletTempsJustif;
delimiter //
create trigger InsBilletTempsJustif
    before insert on Billet
    for each row
begin
declare dureeR time;

-- Billet autotime --
select dureeRetard into dureeR from Vue_RetardQuiDuree where IdE = new.IdE;
if new.IdE in (select IdE from Vue_EtudiantRetardJustifie)
    then
        set new.raison = "Transports ";
        set new.dureeRetard = dureeR;
        set new.dateheure = now();
        set new.dateB = curdate();
        set new.heureB = curtime();
-- auto sign
        set new.URLSignature = (select URLSignature from Administrateur where IdAd = new.IdAd);
end if;
end //
delimiter ;

-- /COURS
--      /INSERT

drop trigger if exists InsCours;
delimiter //
create trigger InsCours
before insert on Cours
for each row
begin

-- Cours autotime 
set new.dateTS = now();

-- Duree auto
set new.duree = new.heureFin - new.heureDeb;

end //
delimiter ;

--      /UPDATE

drop trigger if exists UpdCours;
delimiter //
create trigger UpdCours
before insert on Cours
for each row
begin

-- Duree auto

set new.duree = new.heureFin - new.heureDeb;

end //
delimiter ;


insert into Administrateur values (null, 'Admin', 'Gerant', 'a@gmail.com', '000000000', 'Paris', 'ok', 'signature');
insert into Classe values (null, "SIO1", null, "sio1@gmail.com", "BTS SIO", "2021/2022");