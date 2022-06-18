show databases;
create database ecole;
use ecole ;
show tables;


create table etudiant(codeEtudiant varchar(20) , nom varchar(20) ,prenom varchar(20) , idfiliere int ,age int , primary key(codeEtudiant),foreign key(idfiliere) references filiere(idfiliere));
desc  etudiant ;
desc prof ;
desc filiere;
desc classe;
create table prof(codeprof int , nom varchar(20) ,prenom varchar(20) , idfclasse int , primary key(codeprof));

create table filiere(idFiliere int primary key ,nom varchar(10),idprof int ,foreign key (idprof) references prof(codeprof));

create table classe(idclasse int primary key , capacity int , iscc boolean);
ALTER TABLE prof ADD FOREIGN KEY (idfclasse) REFERENCES classe(idclasse);

insert into classe values (4, 33, false);
insert into prof values(4, 'eee', 'ee', 4);
insert into filiere values(5, 'WEBDEV', 4 );

select * from filiere;
select * from etudiant;
select * from classe;
select * from prof;

-- ALTER TABLE prof RENAME COLUMN idfclasse TO idclasse;

