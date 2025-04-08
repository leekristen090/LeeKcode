/*
HW 6 - Lord of The Rings
SQL select statement queries
Questions 4-16

Kristen Lee
*/

USE lotr_lee_k;

/*
-------------------------------- 4 --------------------------------
For each character (found in the lotr_character table), count the 
number of encounters documented within the database. Note: a 
character’s name may appear in two different fields in the encounter 
table. Each tuple in the result should contain the character’s name 
and the count of encounters. Rename the count of encounters to 
num_encounters. Order the result in descending order using 
num_encounters. You need to have a row for each character in the 
lotr_character table
*/
SELECT lc.character_name, SUM(encounters.char_count) AS num_encounters
FROM lotr_character AS lc 
	LEFT JOIN (
		SELECT DISTINCT character1_name AS character_name, COUNT(*) as char_count
		FROM first_encounter
		GROUP BY character1_name
		UNION ALL
		SELECT DISTINCT character2_name AS character_name, COUNT(*) as char_count
		FROM first_encounter
		GROUP BY character2_name
	) AS encounters ON lc.character_name = encounters.character_name
GROUP BY lc.character_name
ORDER BY num_encounters DESC
;

/*
-------------------------------- 5 --------------------------------
Count the number of regions each character has visited (as 
documented in the database). A visit is a region where a character 
has been to or their homeland. Each tuple in the result should 
contain the character’s name and the number of regions the character 
has been documented as visiting as specified in the database. Rename 
the computed count to regions_visited. Order the results in 
descending order using the regions_visited field. 
*/
SELECT lc.character_name, COUNT(DISTINCT region_name) AS regions_visited
FROM lotr_character AS lc
	LEFT JOIN (
		SELECT homeland AS region_name, character_name 
		FROM lotr_character
		UNION ALL
		SELECT region_name, character1_name AS character_name
		FROM first_encounter
		UNION ALL
		SELECT region_name, character2_name AS character_name
		FROM first_encounter
	) AS combined_regions ON lc.character_name = combined_regions.character_name
GROUP BY lc.character_name
ORDER BY regions_visited DESC
;

/*
-------------------------------- 6 --------------------------------
Count the number of regions whose majority species is ‘hobbit’. The 
result should consist of a number, the number of regions. Rename 
the computed value to region_cnt_hobbit_majority.
*/
SELECT COUNT(region_name) AS region_count_hobbit_majority
FROM region
WHERE major_species LIKE 'hobbit'
;

/*
-------------------------------- 7 --------------------------------
What region has been documented as having the most number of first 
encounters? The result should contain the region name and a count of 
the number of characters that visited. Rename the computed value to 
num_visited. 
*/
SELECT region_name, COUNT(DISTINCT character_name) AS num_visited
FROM (
	SELECT region_name, character1_name AS character_name
	FROM first_encounter
	UNION ALL 
	SELECT region_name, character2_name AS character_name
	FROM first_encounter
) AS vis
GROUP BY region_name
ORDER BY num_visited DESC
LIMIT 1
;

/*
-------------------------------- 8 --------------------------------
What region has been visited by all characters? A visit is a region 
where a character has been to or their homeland. Return the region 
name.
10 regions in total.
10 characters listed in our db
*/

SELECT region_name
FROM (
	SELECT COUNT(DISTINCT character_name) AS char_visited, region_name
	FROM (
		SELECT homeland AS region_name, character_name 
		FROM lotr_character
		UNION ALL
		SELECT region_name, character1_name AS character_name
		FROM first_encounter
		UNION ALL
		SELECT region_name, character2_name AS character_name
		FROM first_encounter
	) AS vis
	GROUP BY region_name  
	HAVING char_visited = 10
) AS calc_visits
;

/*
-------------------------------- 9 --------------------------------
 Which species has no characters associated with it? Return the 
 species name and species description.
*/
SELECT s.species_name, s_description, character_name
FROM species AS s LEFT OUTER JOIN lotr_character AS lc ON s.species_name = lc.species_name
WHERE lc.character_name IS NULL
;

/*
-------------------------------- 10 --------------------------------
 In which book title (book name) does ‘Frodo’ encounter ‘Faramir’? 
 The result should contain the book id and its title.
*/
SELECT b.book_id,b. b_title
FROM book AS b JOIN first_encounter AS fe ON b.book_id = fe.book_id
WHERE (character1_name = 'Frodo' AND character2_name = 'Faramir') OR (character1_name = 'Faramir' AND character2_name = 'Frodo')
;

/*
-------------------------------- 11 --------------------------------
For each Middle Earth region (each region in the lotr_region table), 
create an aggregated field that contains a list of character names 
that have the region listed as its homeland. The result set should 
contain the region name and the grouped character names renamed as 
characters. Do not duplicate names within the grouped list of 
character names.
*/
SELECT DISTINCT region_name, GROUP_CONCAT(DISTINCT character_name) AS characters
FROM region AS r LEFT OUTER JOIN lotr_character AS lc ON r.region_name = lc.homeland
GROUP BY region_name
;

/*
-------------------------------- 12 --------------------------------
Which is the largest species (by size)? List the species name in the 
result.
*/
SELECT species_name
FROM species
ORDER BY size DESC
LIMIT 1
;

/*
-------------------------------- 13 --------------------------------
How many characters are listed as “human”? The result should contain 
the computed number of humans renamed to num_humans. 
*/
SELECT COUNT(character_name) AS num_humans
FROM lotr_character
WHERE species_name = 'human'
;

/*
-------------------------------- 14 --------------------------------
 Make a separate table from the first encounter table – where the 
 tuples are the first encounters between one hobbit and one human. 
 Name the table human_hobbit_first_encounter.
*/
CREATE TABLE human_hobbit_first_encounter AS
SELECT fe.*
FROM first_encounter AS fe
	JOIN lotr_character AS lc1 ON fe.character1_name = lc1.character_name
    JOIN lotr_character AS lc2 ON fe.character2_name = lc2.character_name
WHERE (lc1.species_name = 'hobbit' AND lc2.species_name = 'human')
	OR (lc1.species_name = 'human' AND lc2.species_name = 'hobbit')
;

/*
-------------------------------- 15 --------------------------------
For each book, display its id, title along with all the details of 
the species that appeared in the book. Order the result by the 
book_id ascending, and species size descending.
*/
SELECT DISTINCT lc.species_name, s.s_description, s.size, b.book_id
FROM book AS b 
	JOIN lotr_character AS lc ON b.book_id = lc.book_number
	JOIN species AS s ON lc.species_name = s.species_name
ORDER BY b.book_id DESC, size DESC
;

/*
-------------------------------- 16 --------------------------------
For each Middle Earth region, determine the number of characters that 
have their encounter region listed as their homeland. The result set 
should contain the region name and the count of the number of 
characters. Rename the count of the characters to num_origin. Sort 
the result in descending order using num_origin. Make sure you do not 
count a character more than once.
*/
SELECT r.region_name, COUNT(DISTINCT lc.character_name) AS num_origin
FROM region r
	JOIN lotr_character lc ON r.region_name = lc.homeland
	JOIN first_encounter fe ON  fe.region_name = r.region_name
WHERE lc.character_name IN (fe.character1_name, fe.character2_name)
GROUP BY r.region_name
ORDER BY num_origin DESC
;


