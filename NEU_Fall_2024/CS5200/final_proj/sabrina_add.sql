/*
--------------------------------------------- ADD TUPLE ---------------------------------------------
*/

/*
Procedure to add tuple to album table
*/
DROP PROCEDURE IF EXISTS addAlbumTuple;
DELIMITER //
CREATE PROCEDURE addAlbumTuple(
	p_album_name VARCHAR(64),
    p_release_date DATE
)
BEGIN 
	INSERT INTO album (album_name, release_date) VALUES (p_album_name, p_release_date);
END //
DELIMITER ;
CALL addAlbumTuple('the fakest','2002-08-11');
/*
procedure to add tuple to customer
*/
DROP PROCEDURE IF EXISTS addCustomerTuple;
DELIMITER // 
CREATE PROCEDURE addCustomerTuple(
	first_name VARCHAR(256), 
	last_name VARCHAR(256), 
	phone VARCHAR(15),
	email VARCHAR(256)
)
BEGIN
	INSERT INTO customer (first_name, last_name, phone, email) VALUES (first_name, last_name, phone, email);
END //
DELIMITER ;

/*
procedure to add tuple to location
*/
DROP PROCEDURE IF EXISTS addLocationTuple;
DELIMITER // 
CREATE PROCEDURE addLocationTuple(
	city VARCHAR(64),
	state_region VARCHAR(64),
	country VARCHAR(64)
)
BEGIN 
	INSERT INTO location (city, state_region, country) VALUES (city, state_region, country);
END //
DELIMITER ;

/*
procedure to add tuple to opening_act
*/
DROP PROCEDURE IF EXISTS addOpeningActTuple;
DELIMITER //
CREATE PROCEDURE addOpeningActTuple (
	act_name VARCHAR(64)
)
BEGIN 
	INSERT INTO opening_act (act_name) VALUES (act_name);
END //
DELIMITER ;

/*
procedure to add tuple to opening_to_show
*/
DROP PROCEDURE IF EXISTS addOpenToShowTuple;
DELIMITER //
CREATE PROCEDURE addOpenToShowTuple(
	tour_name VARCHAR(64),
	show_id INT,
	act_id INT,
	performance_order INT
)
BEGIN 
	INSERT INTO opening_to_show VALUES (tour_name, show_id, act_id, performance_order);
END //
DELIMITER ;

/*
add show tuple with date validation 
*/
DROP PROCEDURE IF EXISTS addShowTuple;
DELIMITER //
CREATE PROCEDURE addShowTuple(
	p_tour_name VARCHAR(64), 
	p_show_id INT,
	p_venue_name VARCHAR(64),
	p_location_id INT,
	p_scheduled_date DATE, 
	p_show_status ENUM('Upcoming', 'Cancelled', 'Completed')
)
BEGIN 
	DECLARE tourStart DATE;
    DECLARE tourEnd DATE;
    DECLARE tourExists INT;
    
    SELECT COUNT(*), start_date, start_date
    INTO tourExists, tourStart, tourEnd
    FROM tour
    WHERE tour_name = p_tour_name;
    
    IF tourExists = 0 THEN 
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Tour does not exist.';
    END IF;
    IF p_scheduled_date < tourStart OR (tourEnd IS NOT NULL AND p_scheduled_date > tourEnd) THEN 
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Scheduled date must fall within the tour dates.';
    END IF;
    
	INSERT INTO sabrina_show VALUES(p_tour_name, p_show_id, p_venue_name, p_location_id, p_scheduled_date, p_show_status);
END//
DELIMITER ;
/*
procedure to add tuple to song
*/
DROP PROCEDURE IF EXISTS addSongTuple;
DELIMITER //
CREATE PROCEDURE addSongTuple(
	song_name VARCHAR(64),  
	album_id INT,
	tour_name VARCHAR(64),
	order_played INT
)
BEGIN 
INSERT INTO song (song_name, album_id, tour_name, order_played) VALUES (song_name, album_id, tour_name, order_played);
END //
DELIMITER ;

/*
procedure to add tuple to ticket_sales
*/
DROP PROCEDURE IF EXISTS addTicketSaleTuple;
DELIMITER //
CREATE PROCEDURE addTicketSaleTuple(
	purchase_date DATE, 
	total_cost DECIMAL(10,2), 
	ticket_quantity INT,
	customer_id INT,
	tour_name VARCHAR(64), 
	show_id INT
)
BEGIN 
	INSERT INTO ticket_sales (purchase_date, total_cost, ticket_quantity, customer_id,tour_name,show_id) VALUES (purchase_date, total_cost, ticket_quantity, customer_id,tour_name,show_id);
END //
DELIMITER ;

/*
procedure to add tuple to tour
*/
DROP PROCEDURE IF EXISTS addTourTuple;
DELIMITER //
CREATE PROCEDURE addTourTuple (
	tour_name VARCHAR(64),
	start_date DATE, 
	end_date DATE, 
	tour_status ENUM('Upcoming','Ongoing', 'Ended')
)
BEGIN 
	INSERT INTO tour VALUES (tour_name, start_date, end_date, tour_status);
END //
DELIMITER ;

/*
procedure to add tuple to venue
*/
DROP PROCEDURE IF EXISTS addVenueTuple;
DELIMITER //
CREATE PROCEDURE addVenueTuple(
	location_id INT,
	venue_name VARCHAR(64), 
	capacity INT
)
BEGIN 
	INSERT INTO venue VALUES(location_id, venue_name, capacity);
END //
DELIMITER ;