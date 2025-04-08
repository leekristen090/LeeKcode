USE team_sabrina;

/*
This file holds general functions, procedures, etc.
For CRUD see sabrina_add.sql, sabrina_delete.sql, and sabrina_update.sql
*/

/*
Procedure to get future shows
*/
DROP PROCEDURE IF EXISTS get_future_show;
DELIMITER //
CREATE PROCEDURE get_future_show()
BEGIN
	SELECT *
	FROM sabrina_show
    WHERE scheduled_date > NOW()
    ORDER BY scheduled_date
    ;
END //
DELIMITER ;

CALL get_future_show();

/*
Procedure to get furture shows with available seats
*/
DROP PROCEDURE IF EXISTS get_future_show_with_available_seats;
DELIMITER //
CREATE PROCEDURE get_future_show_with_available_seats()
BEGIN 
SELECT ss.tour_name,ss.show_id,ss.venue_name,city, country,scheduled_date, IFNULL(SUM(ticket_quantity),0) AS sold, v.capacity, (capacity - IFNULL(SUM(ticket_quantity), 0)) AS available 
FROM sabrina_show AS ss LEFT JOIN ticket_sales AS ts ON ss.show_id = ts.show_id AND ss.tour_name = ts.tour_name
	JOIN venue AS v ON ss.venue_name = v.venue_name AND ss.location_id = v.location_id
    JOIN location AS l ON v.location_id = l.location_id
WHERE scheduled_date > NOW()
GROUP BY ss.show_id, v.capacity,ss.venue_name,ss.tour_name
;
END //
DELIMITER ;

CALL get_future_show_with_available_seats();

/*
Procedure to retrieve show with venue and location names and sold seats
*/
DROP PROCEDURE IF EXISTS getAllShowsVenueLocationSeats;
DELIMITER //
CREATE PROCEDURE getAllShowsVenueLocationSeats()
BEGIN 
SELECT ss.tour_name,ss.show_id,ss.venue_name,city, country,scheduled_date, IFNULL(SUM(ticket_quantity),0) AS sold, v.capacity, (capacity - IFNULL(SUM(ticket_quantity), 0)) AS available 
FROM sabrina_show AS ss LEFT JOIN ticket_sales AS ts ON ss.show_id = ts.show_id AND ss.tour_name = ts.tour_name
	JOIN venue AS v ON ss.venue_name = v.venue_name AND ss.location_id = v.location_id
    JOIN location AS l ON v.location_id = l.location_id
GROUP BY ss.show_id, v.capacity,ss.venue_name,ss.tour_name
ORDER BY ss.show_id
;
END //
DELIMITER ;
CALL getAllShowsVenueLocationSeats();

DROP PROCEDURE IF EXISTS checkRowCount;
DELIMITER //
CREATE PROCEDURE checkRowCount(message_success VARCHAR(255), message_failure VARCHAR(255))
BEGIN
    IF ROW_COUNT() = 0 THEN
        SELECT message_failure AS Message;
    ELSE
        SELECT message_success AS Message;
    END IF;
END //
DELIMITER ;


/*
I want events that update the status of shows and tours to go from upcoming to ongoing to complete based on the dates
maybe a thing to buy ticket and have a trigger to update available seats
*/

/*
event to update show status as the dates approach and pass
*/
DELIMITER //
CREATE EVENT update_sabrina_show_status
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
	-- past shows as 'Completed'
    UPDATE sabrina_show
    SET show_status = 'Completed'
    WHERE scheduled_date < CURDATE() AND show_status != 'Cancelled';

    -- Mark shows scheduled for today or later as 'Upcoming' if not 'Cancelled'
    UPDATE sabrina_show
    SET show_status = 'Upcoming'
    WHERE scheduled_date >= CURDATE() AND show_status != 'Cancelled';
END //
DELIMITER ;





