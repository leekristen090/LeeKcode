/*
HW 6 - Lord of The Rings
Create table commands

Kristen Lee
*/

DROP DATABASE IF EXISTS lotr_lee_k;
CREATE DATABASE lotr_lee_k;
USE lotr_lee_k;

/*
-------------------------------- CREATE --------------------------------
create the tables in db lotr_Lee_K
*/
CREATE TABLE species (
species_name VARCHAR(64) PRIMARY KEY,
s_description VARCHAR(256) NOT NULL,
size INT
);

CREATE TABLE region (
region_name VARCHAR(64) PRIMARY KEY, 
major_species VARCHAR(64) NOT NULL,
r_description VARCHAR(256) NOT NULL, 
middle_earth_location VARCHAR(256), 
leader VARCHAR(64),
FOREIGN KEY (major_species) REFERENCES species(species_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS book;
CREATE TABLE book (
book_id INT,
b_title VARCHAR(64), 
PRIMARY KEY (book_id, b_title)
);

DROP TABLE IF EXISTS lotr_character;
CREATE TABLE lotr_character (
character_name VARCHAR(64) PRIMARY KEY,
homeland VARCHAR(64) NOT NULL, 
-- 0 -> F, 1 -> T 
royalty BOOL DEFAULT 0, -- 0 -> not roylaty, 1 -> roylaty
fellowship BOOL DEFAULT 0, -- 0 -> not fellowship, 1 -> fellowship
survive BOOL DEFAULT 1, -- 0 -> dies, 1 -> survives
alias VARCHAR(256) NOT NULL UNIQUE,
book_number INT NOT NULL,
species_name VARCHAR(64) NOT NULL,
FOREIGN KEY (homeland) REFERENCES region(region_name) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (book_number) REFERENCES book(book_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (species_name) REFERENCES species(species_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS first_encounter;
CREATE TABLE first_encounter (
character1_name VARCHAR(64) NOT NULL,
character2_name VARCHAR(64) NOT NULL,
book_id INT NOT NULL, 
region_name VARCHAR(64) NOT NULL,
FOREIGN KEY (character1_name) REFERENCES lotr_character(character_name) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (character2_name) REFERENCES lotr_character(character_name) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (region_name) REFERENCES region(region_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);


/*
-------------------------------- INSERT --------------------------------
insert data into the tables in db lotr_Lee_K
??? or figure out how to import the csv files
*/

-- inserting into species
INSERT INTO species VALUES
('balrog','Ancient evil creatures', 5),
('dwarf', 'Dwellers of the mountains of middle earth mines for precious metals',2),
('elf', 'Fairest and wisest species', 4),
('ent','Ancient beings inhabiting trees that protect the forest',6),
('hobbit','Also known as halflings; mortal ancient creatures that enjoy the simple life',1),
('human', 'Created during the first age of middle earth; three ages after elves and other middle earth species. Mortal creatures',3),
('Maiar','Holy ones with mystical powers', 0),
('orc', 'Enslaved elves that were tortured and generated a new evil species',3)
;

-- inserting into book
INSERT INTO book VALUES 
(1,'the fellowship of the ring'),
(2,'the two towers'),
(3,'the return of the king')
;

-- inserting into region
INSERT INTO region VALUES 
('bree','human','village','north on the crossroads',null),
('gondor','human','mountainous terrain','south','Kings of Gondor'),
('isengard','orc','broad plain containing the tower of Orthanc','south','Saruman'),
('lonely mountain', 'dwarf','cave','northeast','Durin folk'),
('mirkwood','elf','forested elven village','east','Thranduil'),
('mordor', 'orc','volcanic plain','southeast','Sauron'),
('rivendell','elf','well hidden elf village in the foot hills of the Misty Mountains',null,'Elrond'),
('rohan','human','green and fertile area','south','King of Rohan'),
('shire','hobbit','grassy rolling hills','northwest','King of Arthedain'),
('undying lands','Maiar','beyond middle earth','outside middle earth',null)
;

-- inserting into character
INSERT INTO lotr_character VALUES
('Aragorn','gondor',1,1,1,'strider',1,'human'),
('Elrond','rivendell',1,0,1,'lord of rivendell',1,'elf'),
('Eowyn','rohan',1,0,1,'white lady of rohan',2,'human'),
('Faramir','gondor',1,0,1,'captain of the white tower',3,'human'),
('Frodo','shire',0,1,1,'bearer of the one ring',1,'hobbit'),
('Gandalf','undying lands',0,1,1,'greybeard',1,'Maiar'),
('Gimli','lonely mountain',0,1,1,'lockbearer',1,'dwarf'),
('Legolas','mirkwood',1,1,1,'prince of the woodlands',1,'elf'),
('Saruman','isengard',0,0,0,'saruman the white',1,'Maiar'),
('Sauron','mordor',0,0,0,'dark lord',1,'Maiar')
;

-- inserting into first_encounter
INSERT INTO first_encounter VALUES 
('Aragorn','Eowyn',2,'rohan'),
('Faramir','Frodo',3,'mordor'),
('Frodo','Aragorn',1,'bree'),
('Frodo','Elrond',1,'rivendell'),
('Gandalf','Frodo',1,'shire'),
('Gimli','Elrond',1,'rivendell'),
('Gimli','Legolas',1,'rivendell'),
('Saruman','Aragorn',2,'isengard'),
('Sauron','Frodo',1,'bree')
;


