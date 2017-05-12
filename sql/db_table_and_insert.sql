DROP TABLE IF EXISTS  ewidencja_ludnosci;
CREATE TABLE ewidencja_ludnosci (
	ewd_pesel   VARCHAR(11) NOT NULL PRIMARY KEY COMMENT 'Pesel'  ,
	ewd_name    VARCHAR(30) NOT NULL COMMENT 'Person name',
	ewd_surname VARCHAR(30) NOT NULL COMMENT 'Person surname',
	ewd_city    VARCHAR(50) NOT NULL COMMENT 'City live person'
);

INSERT INTO ewidencja_ludnosci VALUES ('90102413339', 'Damian', 'Uziębło', 'Katowice');
INSERT INTO ewidencja_ludnosci VALUES ('33101814195', 'Paweł', 'Lelewicz', 'Częstochowa');
INSERT INTO ewidencja_ludnosci VALUES ('79051106843', 'Anna', 'Mikołajczyk', 'Katowice');
INSERT INTO ewidencja_ludnosci VALUES ('19031118408', 'Agata', 'Kołodziej', 'Tychy');
INSERT INTO ewidencja_ludnosci VALUES ('95020708128', 'Celina', 'Cebula', 'Częstochowa');
INSERT INTO ewidencja_ludnosci VALUES ('55042211987', 'Karolina', 'Pankowska', 'Katowice');