
/*
--------------------------------------------- DELETE ---------------------------------------------
*/
/*
Procedure to delete tuple from album 
*/
DROP PROCEDURE IF EXISTS deleteAlbumTuple;
DELIMITER // 
CREATE PROCEDURE deleteAlbumTuple (
	a_id INT
)
BEGIN 
	DELETE FROM album 
    WHERE album_id = a_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given album_id.'
    );
END //
DELIMITER ;
call deleteAlbumTuple(90);

/*
Procedure to delete tuple from customer table
*/
DROP PROCEDURE IF EXISTS deleteCustomerTuple;
DELIMITER // 
CREATE PROCEDURE deleteCustomerTuple (
	c_id INT
)
BEGIN 
	DELETE FROM customer
    WHERE customer_id = c_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given customer_id.'
    );
END //
DELIMITER ;

/*
Procedure to delete tuple from location table
*/
DROP PROCEDURE IF EXISTS deleteLocationTuple;
DELIMITER //
CREATE PROCEDURE deleteLocationTuple (
	l_id INT
)
BEGIN 
	DELETE FROM location 
    WHERE location_id = l_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given location_id.'
    );
END //
DELIMITER ;

/*
Delete tuple in opening_act
*/
DROP PROCEDURE IF EXISTS deleteOpeningActTuple;
DELIMITER //
CREATE PROCEDURE deleteOpeningActTuple (
	a_id INT 
)
BEGIN
	DELETE FROM opening_act
    WHERE act_id = a_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given act_id.'
    );
END //
DELIMITER ;

/*
delete tuple in opening_to_show
*/
DROP PROCEDURE IF EXISTS deleteOpenToShowTuple;
DELIMITER //
CREATE PROCEDURE deleteOpenToShowTuple (
	tour_name VARCHAR(64),
	show_id INT,
	act_id INT
)
BEGIN 
	DELETE FROM opening_to_show
    WHERE tour_name = tour_name AND show_id = show_id AND act_id = act_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;

/*
delete tuple in sabrina_show
*/
DROP PROCEDURE IF EXISTS deleteShowTuple;
DELIMITER //
CREATE PROCEDURE deleteShowTuple (
	tour_name VARCHAR(64), 
	show_id INT
)
BEGIN 
	DELETE FROM sabrina_show
    WHERE tour_name = tour_name AND show_id = show_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;

/*
delete tuple from song
*/
DROP PROCEDURE IF EXISTS deleteSongTuple;
DELIMITER //
CREATE PROCEDURE deleteSongTuple(
	song_id INT
) 
BEGIN 
	DELETE FROM song 
    WHERE song_id = song_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given song_id.'
    );
END //
DELIMITER ;

/*
delete tuple from ticket_sales
*/
DROP PROCEDURE IF EXISTS deleteTicketTuple;
DELIMITER //
CREATE PROCEDURE deleteTicketTuple(
	transaction_id INT 
)
BEGIN 
	DELETE FROM ticket_sales
    WHERE transaction_id = transaction_id;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;

/*
delete tuple from tour
*/
DROP PROCEDURE IF EXISTS deleteTourTuple;
DELIMITER //
CREATE PROCEDURE deleteTourTuple(
	tour_name VARCHAR(64)
)
BEGIN 
	DELETE FROM tour 
    WHERE tour_name= tour_name;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given tour_name.'
    );
END //
DELIMITER ;

/*
delete tuple from venue
*/
DROP PROCEDURE IF EXISTS deleteVenueTuple;
DELIMITER //
CREATE PROCEDURE deleteVenueTuple (
	location_id INT,
	venue_name VARCHAR(64)
)
BEGIN 
	DELETE FROM venue 
    WHERE location_id = location_id AND venue_name = venue_name;
    CALL checkRowCount(
        'Row successfully deleted.',
        'No rows found with the given PKs.'
    );
END //
DELIMITER ;