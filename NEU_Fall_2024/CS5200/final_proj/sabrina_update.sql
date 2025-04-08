
/*
--------------------------------------------- UPDATE ---------------------------------------------
*/

/*
update album tuple
*/
DROP PROCEDURE IF EXISTS updateAlbumTuple;
DELIMITER //
CREATE PROCEDURE updateAlbumTuple(
    IN chosen_album_id INT,
    IN new_album_name VARCHAR(64),
    IN new_release_date DATE
)
BEGIN
    UPDATE album
    SET 
        album_name = COALESCE(new_album_name, album_name),
        release_date = COALESCE(new_release_date, release_date)
    WHERE album_id = chosen_album_id;

    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given album_id.'
    );
END //
DELIMITER ;



/*
update tuple in customer
*/
DROP PROCEDURE IF EXISTS updateCustomerTuple;
DELIMITER //
CREATE PROCEDURE updateCustomerTuple (
	p_customer_id INT,
	p_first_name VARCHAR(256), 
	p_last_name VARCHAR(256), 
	p_phone VARCHAR(15),
	p_email VARCHAR(256)
)
BEGIN 
	UPDATE customer
    SET 
    first_name = COALESCE(p_first_name,first_name),
    last_name = COALESCE(p_last_name,last_name),
    phone = COALESCE(p_phone,phone),
    email = COALESCE(p_email,email)
    WHERE customer_id = p_customer_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given customer_id.'
    );
END //
DELIMITER ;

/*
update location tuple
*/
DROP PROCEDURE IF EXISTS updateLocationTuple;
DELIMITER //
CREATE PROCEDURE updateLocationTuple(
	p_location_id INT,
	p_city VARCHAR(64),
	p_state_region VARCHAR(64),
	p_country VARCHAR(64)
)
BEGIN 
	UPDATE location
    SET 
		location_id = COALESCE(p_city,location_id),
        state_region = COALESCE(p_state_region,state_region),
        country = COALESCE(p_country,country)
    WHERE location_id = p_location_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given location_id.'
    );
END //
DELIMITER ;

/*
update tuple in opening_act
*/
DROP PROCEDURE IF EXISTS updateOpeningActTuple;
DELIMITER //
CREATE PROCEDURE updateOpeningActTuple(
	p_act_id INT,
	p_act_name VARCHAR(64)
)
BEGIN
	UPDATE opening_act
    SET 
		act_name = COALESCE(p_act_name,act_name)
	WHERE act_id = p_act_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given act_id.'
    );
END //
DELIMITER ;

/*
update tuple in opening_to_show
*/
DROP PROCEDURE IF EXISTS updateOpenToShowTuple;
DELIMITER //
CREATE PROCEDURE updateOpenToShowTuple(
	p_tour_name VARCHAR(64),
	p_show_id INT,
	p_act_id INT,
	p_performance_order INT
)
BEGIN 
	UPDATE opening_to_show
    SET 
		act_id = COALESCE(p_act_id,act_id),
        performance_order = COALESCE(p_performance_order,performance_order)
    WHERE tour_name = p_tour_name AND show_id = p_show_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;

/*
update tuple in abrina_show
*/
DROP PROCEDURE IF EXISTS updateShowTuple;
DELIMITER //
CREATE PROCEDURE updateShowTuple (
	p_tour_name VARCHAR(64), 
	p_show_id INT,
	p_venue_name VARCHAR(64),
	p_location_id INT,
	p_scheduled_date DATE, 
	p_show_status ENUM('Upcoming', 'Cancelled', 'Completed')
)
BEGIN 
	UPDATE sabrina_show
    SET 
		venue_name = COALESCE(p_venue_name,venue_name),
        location_id = COALESCE(p_location_id,location_id),
        scheduled_date = COALESCE(p_scheduled_date,scheduled_date),
        show_status = COALESCE(p_show_status,show_status)
    WHERE tour_name = p_tour_name AND show_id = p_show_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;

/*
update song tuple
*/
DROP PROCEDURE IF EXISTS updateSongTuple;
DELIMITER //
CREATE PROCEDURE updateSongTuple(
	p_song_id INT,
	p_song_name VARCHAR(64),  
	p_album_id INT,
	p_tour_name VARCHAR(64),
	p_order_played INT
)
BEGIN 
	UPDATE song
    SET 
		song_name = COALESCE(p_song_name,song_name),
        album_id = COALESCE(p_album_id,album_id),
        tour_name = COALESCE(p_tour_name,tour_name),
        order_played = COALESCE(p_order_played,order_played)
    WHERE song_id = p_song_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given song_id.'
    );
END //
DELIMITER ;

/*
update ticket_sales tuple
*/
DROP PROCEDURE IF EXISTS updateTicketTuple;
DELIMITER //
CREATE PROCEDURE updateTicketTuple(
	p_transaction_id INT, 
	p_purchase_date DATE, 
	p_total_cost DECIMAL(10,2), 
	p_ticket_quantity INT,
	p_customer_id INT,
	p_tour_name VARCHAR(64), 
	p_show_id INT
) 
BEGIN 
	UPDATE ticket_sales
    SET 
		purchase_date = COALESCE(p_purchase_date,purchase_date),
        total_cost = COALESCE(p_total_cost,total_cost),
        ticket_quantity = COALESCE(p_ticket_quantity,ticket_quantity),
        customer_id = COALESCE(p_customer_id,customer_id),
        tour_name = COALESCE(p_tour_name,tour_name),
        show_id = COALESCE(p_show_id,show_id)
    WHERE transaction_id = p_transaction_id;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given transaction_id.'
    );
END //
DELIMITER ;

/*
update tour tuple
*/
DROP PROCEDURE IF EXISTS updateTourTuple;
DELIMITER //
CREATE PROCEDURE updateTourTuple(
	p_tour_name VARCHAR(64),
	p_start_date DATE, 
	p_end_date DATE, 
	p_tour_status ENUM('Upcoming','Ongoing', 'Ended') 
)
BEGIN
	UPDATE tour
    SET 
		start_date = COALESCE(p_start_date,start_date),
        end_date = COALESCE(p_end_date,end_date),
        tour_status = COALESCE(p_tour_status,tour_status)
    WHERE tour_name = p_tour_name;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given tour_name.'
    );
END //
DELIMITER ;

/*
update venue tuple
*/
DROP PROCEDURE IF EXISTS updateVenueTuple;
DELIMITER //
CREATE PROCEDURE updateVenueTuple(
	p_location_id INT,
	p_venue_name VARCHAR(64), 
	p_capacity INT
)
BEGIN 
	UPDATE venue
    SET 
		capacity = COALESCE(p_capacity,capacity)
    WHERE location_id = p_location_id AND venue_name = p_venue_name;
    CALL checkRowCount(
        'Row successfully updated.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;






