USE bus_schema_LeeK;
/*
CS 5200 - Homework 7 
Kristen Lee
*/

/*
------------------------------------------------------------ 1. ------------------------------------------------------------
Write a function num_buses_with_type(bus_type_p) that accepts a bus type and returns the number of buses with that bus type.
*/
-- solution 
DELIMITER //
CREATE FUNCTION num_buses_with_type(
	bus_type_p enum('sleeper', 'seater', 'Double Decker', 'Shuttle')
)
RETURNS INT 
DETERMINISTIC READS SQL DATA
BEGIN  
	DECLARE bus_type_var INT;
    
    SELECT COUNT(type) INTO bus_type_var
    FROM bus_type 
    WHERE type = bus_type_p
    ;
    RETURN (bus_type_var);
END //
DELIMITER ;

-- test
SELECT DISTINCT type, num_buses_with_type(type) AS count_type
FROM bus_type
;

SELECT num_buses_with_type('sleeper')
;

/*
------------------------------------------------------------ 2. ------------------------------------------------------------
Write a procedure customers_to_destination(dest_city_p, dest_state_p) that accepts a destination city name and a destination 
state name and returns a result set of all customer’s journeys to that city. The result contains the customer’s user name, 
first name, last name, the scheduled date and time of the bus trip, the route instance id, and the fare for the trip. If the 
provided parameters specify a city not in the database, then provide the following error message: “No buses to that destination 
city dest_city_p dest_state_p”. Also, use SIGNAL to notify the calling code of the error.
*/
-- solution 
DROP PROCEDURE IF EXISTS customers_to_destination;
DELIMITER //
CREATE PROCEDURE customers_to_destination(
	dest_city_p VARCHAR(64),
    dest_state_p CHAR(2)
)
BEGIN 
	DECLARE bus_exists INT;
    DECLARE error_message VARCHAR(500);
    
    SELECT COUNT(*) INTO bus_exists
    FROM route AS r JOIN route_instance AS ri ON r.route_id = ri.route_id
    WHERE r.destination_city_name = dest_city_p AND r.destination_state = dest_state_p
    ;
    IF bus_exists = 0 THEN 
		SET error_message = CONCAT('No buses to that destination city ', dest_city_p, ', ', dest_state_p);
        SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = error_message;          
	ELSE 
		SELECT c.username,first_name, last_name, scheduled_date_time, r_instance,fare_price, destination_city_name, destination_state
		FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
		JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
		JOIN route AS r ON ri.route_id = r.route_id
		WHERE dest_city_p = destination_city_name AND dest_state_p = destination_state
		;
    END IF;
END //
DELIMITER ; 

-- test
CALL customers_to_destination("Chicago", "IL");
CALL customers_to_destination("Indianapolis", "IN"); -- throws error

/*
------------------------------------------------------------ 3. ------------------------------------------------------------
Write a procedure named city_has_trips(city_id_p) that accepts a city id and returns a result set of all bus routes leaving from 
that city. The result should contain the city name, the state name, the scheduled departure time and the fare. If a city_id is 
provided that is not found in the city table, generate an error from the procedure stating that the passed city is not valid 
and use SIGNAL to throw error ‘45000’.
*/
-- solution 
DROP PROCEDURE IF EXISTS city_has_trips;
DELIMITER //
CREATE PROCEDURE city_has_trips(
	city_id_p INT
)
BEGIN
	DECLARE cityName VARCHAR(255);
    DECLARE state_name VARCHAR(255);

    SELECT city_name, state INTO cityName, state_name 
    FROM city 
    WHERE city_id = city_id_p;
    
    IF cityName IS NULL THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid city id.';
	ELSE 
		SELECT city_id, c.city_name AS cityName, state AS state_name, scheduled_date_time, fare_price
		FROM state_info AS si JOIN city AS c ON si.abbreviation = c.state
		JOIN route AS r ON c.state = r.origin_state
		JOIN route_instance AS ri ON r.route_id = ri.route_id
        WHERE city_id = city_id_p
        ;
    END IF;
END //
DELIMITER ;

-- test
CALL city_has_trips(1);
CALL city_has_trips(7); -- throws error

/*
------------------------------------------------------------ 4. ------------------------------------------------------------
Write a procedure named destination_in_less_than(city_p, hours_p) that accepts two parameters, a city id and a number of hours 
and returns a list of destination city, destination state, estimated duration time, estimated mileage and fare that can be reached 
from city id within hours_p. A bus for that route does not necessarily need to be scheduled.
*/
SELECT destination_city_name, destination_state, estimated_time, estimated_miles, fare_price
FROM city AS c JOIN route AS r ON c.city_name = r.destination_city_name
	JOIN route_instance AS ri ON r.route_id = ri.route_id
;

-- solution 
DROP PROCEDURE IF EXISTS destination_in_less_than;
DELIMITER //
CREATE PROCEDURE destination_in_less_than(
	city_p INT, 
    hours_p INT 
)
BEGIN 
	DECLARE originC VARCHAR(255);
    DECLARE originS VARCHAR(255);
    SELECT DISTINCT c.city_name, r.origin_state INTO originC, originS
    FROM city AS c JOIN route AS r ON c.city_name = r.origin_city_name
    WHERE city_id = city_p
    ;
    
	SELECT destination_city_name, destination_state, estimated_time, estimated_miles, fare_price
	FROM route AS r LEFT JOIN route_instance AS ri ON r.route_id = ri.route_id
    -- use left join for routes without bus assigned yet
	WHERE origin_city_name = originC
		AND origin_state = originS
		AND hours_p >= estimated_time
    ;
END//
DELIMITER ;

-- test
CALL destination_in_less_than(1,100);
CALL destination_in_less_than(2,100);

/*
------------------------------------------------------------ 5. ------------------------------------------------------------
Write a function named get_driver_journey_num(driver_p) that accepts a driver id as an argument and returns the number of 
journeys the bus driver has or will take.
*/
-- solution 
DELIMITER //
CREATE FUNCTION get_driver_journey_num (
	driver_p INT
)
RETURNS INT 
DETERMINISTIC READS SQL DATA
BEGIN 
	DECLARE driver_var INT;
	SELECT COUNT(r_instance) INTO driver_var
	FROM route_instance
    WHERE driver_p = driver_id
	GROUP BY driver_id
    ;
    RETURN driver_var;
END //
DELIMITER ;

-- test
SELECT DISTINCT driver_id, get_driver_journey_num(driver_id) AS num_journeys
FROM route_instance
;

SELECT get_driver_journey_num(1)
;

/*
------------------------------------------------------------ 6. ------------------------------------------------------------
Write a procedure named get_customer_journeys(username_p) that accepts a username as an argument and returns the record for 
each set of tickets purchased for a journey. Each record in the result should contain the customer’s user name, first name, 
last name, purchase method, number of seats booked, the fare price, scheduled date time, the route instance number, the origin 
city, the origin state, the destination city, destination state, the bus type, the bus model, the bus license plate and the 
bus id. Order the results by scheduled date time, followed by origin city. 
*/
SELECT c.username, first_name, last_name, payment_method, seats_booked, fare_price, scheduled_date_time,
	origin_city_name, origin_state, destination_city_name, destination_state, type, model, license_plate, bt.bus_id
FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route AS r ON ri.route_id = r.route_id
    JOIN bus_type AS bt ON ri.bus_id = bt.bus_id 
ORDER BY scheduled_date_time,origin_city_name
;

-- solution 
DROP PROCEDURE IF EXISTS get_customer_journeys;
DELIMITER //
CREATE PROCEDURE get_customer_journeys(
	username_p VARCHAR(64)
)
BEGIN 
	SELECT c.username, first_name, last_name, payment_method, seats_booked, fare_price, scheduled_date_time,
	origin_city_name, origin_state, destination_city_name, destination_state, type, model, license_plate, bt.bus_id
	FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
		JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
		JOIN route AS r ON ri.route_id = r.route_id
		JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
        WHERE c.username = username_p
	ORDER BY scheduled_date_time, origin_city_name
;
END //
DELIMITER ;

-- test
CALL get_customer_journeys('lemo');
CALL get_customer_journeys('kathleen');

/*
------------------------------------------------------------ 7. ------------------------------------------------------------
Write a function named more_route_instances(email1_p, email2_p). It accepts 2 emails for customers and returns 1 if email1 has 
taken more bus trips than email2, 0 if they have the same number of trips, and -1 if email2 has more trips than user1. Make 
sure you do not count the same journey more than once, since a user may purchase multiple tickets for the same journey at 
different times.
*/
-- solution 
DROP FUNCTION IF EXISTS more_route_instances;
DELIMITER // 
CREATE FUNCTION more_route_instances(
	email1_p VARCHAR(64),
    email2_p VARCHAR(64)
)
RETURNS INT
DETERMINISTIC READS SQL DATA
BEGIN 

	DECLARE calc_email1 INT;
    DECLARE calc_email2 INT;
    
    SELECT COUNT(DISTINCT route_instance) INTO calc_email1
    FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    WHERE c.email = email1_p
    ;
    
    SELECT COUNT(DISTINCT route_instance) INTO calc_email2
    FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    WHERE c.email = email2_p
    ;
    
    IF calc_email1 > calc_email2 THEN 
		RETURN 1;
	ELSEIF calc_email1 < calc_email2 THEN 
		RETURN -1;
	ELSE 
		RETURN 0;
    END IF;
    
END //
DELIMITER ;

-- test
SELECT more_route_instances('lemonade@gmail.com','kath@gmail.com');
SELECT more_route_instances('kath@gmail.com','lemonade@gmail.com');
SELECT more_route_instances('lemonade@gmail.com','jayz@yahoo.com');

/*
------------------------------------------------------------ 8. ------------------------------------------------------------
Create a procedure named buy_tickets( origin_city_p, origin_state_p, destination_city_p, destination_state_p, username_p, 
num_tickets_p, payment_method_p ) that sells num_tickets_p to username_p for the next bus leaving the origin_city headed to 
the destination_city. Make sure the user exists, if they do not, SIGNAL error ‘45000’ with an appropriate error message. You 
must identify the next occurring route instance from the origin city to the destination city, there may be multiple route 
instances scheduled between the two cities. Ensure, you book the user on the next available bus route from origin city to 
destination city. If there is no future scheduled bus route between the two cities, generate an error using SIGNAL with error 
number 45000. Also, make sure the number of specified tickets are available to be sold for the route instance. If not, generate 
an error using SIGNAL with error number 45000. 

To test, have cust3 book five tickets using a credit card on the next bus from 
Boston MA to  Miami FL. Please provide SELECT statements that verify the tuple has been inserted into the appropriate tables. 
Choose another pair of cities and book at least 1 other bus trip. Also, provide other test cases that ensure your error handling 
is working properly. 
*/
-- solution 
DROP PROCEDURE IF EXISTS buy_tickets;
DELIMITER // 
CREATE PROCEDURE buy_tickets (
	origin_city_p VARCHAR(64), 
    origin_state_p CHAR(2), 
    destination_city_p VARCHAR(64), 
    destination_state_p CHAR(2), 
    username_p VARCHAR(64), 
	num_tickets_p INT, 
    payment_method_p enum('Credit Card','Debit Card','Online Wallet')
)
BEGIN 
	DECLARE available_seats INT;
    DECLARE route_inst_id_var INT; 
    DECLARE total_capacity INT;
    
    -- checking user validity
    IF NOT EXISTS (SELECT 1 FROM customer WHERE username = username_p) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User does not exist.';
    END IF;
    
    SELECT r_instance, capacity, (capacity - IFNULL(SUM(seats_booked), 0)) INTO route_inst_id_var, total_capacity, available_seats
	FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
		LEFT JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
        JOIN route AS r ON ri.route_id = r.route_id
	WHERE origin_city_name = origin_city_p
		AND origin_state = origin_state_p
        AND destination_city_name = destination_city_p
        AND destination_state = destination_state_p
        AND scheduled_date_time > NOW()
	GROUP BY r_instance
    ORDER BY ri.scheduled_date_time ASC 
    LIMIT 1
	;
    
    -- check future
    IF route_inst_id_var IS NULL THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No future scheduled journeys for this route.';
    END IF;
    
    -- enough seats?
    IF available_seats < num_tickets_p THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Not enough tickets available.';
    END IF;
    
    INSERT INTO bus_journey_booking ( username, route_instance, seats_booked, payment_method)
    VAlUES(username_p, route_inst_id_var, num_tickets_p,payment_method_p);
    
END //
DELIMITER ;

-- test
CALL buy_tickets('Boston','MA','Miami', 'FL','cust3', 5, 'Credit Card');
SELECT *
FROM bus_journey_booking
WHERE username = 'cust3' AND seats_booked = 5
;

CALL buy_tickets('Boston','MA','Miami', 'FL','cust4', 5, 'Credit Card'); -- invalid customer
CALL buy_tickets('Houston','TX','Miami', 'FL','cust3', 1, 'Debit Card'); -- no future scheduled journeys
CALL buy_tickets('Houston','TX','Chicago', 'IL','cust3', 100, 'Debit Card'); -- not enough availble tickets

CALL buy_tickets('New York','NY','Miami', 'FL','cust3', 1, 'Debit Card'); -- this is a valid buy
SELECT bjb.*, origin_city_name,origin_state
FROM bus_journey_booking AS bjb JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
	JOIN route AS r ON ri.route_id = r.route_id
WHERE username = 'cust3' AND origin_city_name = 'New York'
;

/*
------------------------------------------------------------ 9. ------------------------------------------------------------
Write a procedure named get_future_trips_with_available_seats_() that accepts no arguments and returns bus trips that are in 
the future and have seats available for sale. The result records should contain the origin city, origin state, destination city,  
destination state, scheduled departure date, route insance, bus type, bus model, cost of a ticket, bus capacity, number of sold 
seats and the number of seats still available. Order the results in ascending order by origin city followed by departure date.
*/
-- solution 
DROP PROCEDURE IF EXISTS get_future_trips_with_available_seats;
DELIMITER //
CREATE PROCEDURE get_future_trips_with_available_seats()
BEGIN 
	SELECT origin_city_name, origin_state, destination_city_name, destination_state, scheduled_date_time, r_instance, type, model, fare_price,
		capacity, SUM(seats_booked) AS sold, (capacity - IFNULL(SUM(seats_booked), 0)) AS available 
	FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
		LEFT JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
		JOIN route AS r ON ri.route_id = r.route_id
	WHERE scheduled_date_time > NOW()
	GROUP BY r_instance
    ORDER BY origin_city_name, scheduled_date_time ASC
	;
END //
DELIMITER ;

-- test
CALL get_future_trips_with_available_seats(); -- r_instance = 19 available seats = 51

INSERT INTO bus_journey_booking(payment_method, seats_booked, route_instance, username)
VALUES('Credit Card', 2, 19, 'kathleen');
CALL get_future_trips_with_available_seats(); -- r_instance = 19 available seats = 49

/*
------------------------------------------------------------ 10. ------------------------------------------------------------
Use the ALTER command to modify the route instance table to contain a field called num_seats_available of type INTEGER. Ensure 
this command is executed only once.
*/
-- solution 
DROP PROCEDURE IF EXISTS add_num_seats_available_column;
DELIMITER //
CREATE PROCEDURE add_num_seats_available_column()
BEGIN
	IF NOT EXISTS (
		SELECT * 
		FROM information_schema.COLUMNS 
		WHERE TABLE_NAME = 'route_instance' 
		AND TABLE_SCHEMA = 'bus_schema_LeeK'  -- Replace with your database name
		AND COLUMN_NAME = 'num_seats_available'
	) THEN 
		ALTER TABLE route_instance
		ADD COLUMN num_seats_available INTEGER;
	END IF;
END //
DELIMITER ;

-- test
SELECT * 
FROM route_instance
;
CALL add_num_seats_available_column();

CALL add_num_seats_available_column();
SELECT * 
FROM route_instance
;

/*
------------------------------------------------------------ 11. ------------------------------------------------------------
Write a procedure called set_num_seats_list(route_instance) that accepts a route instance id and initializes the num_seats_available  
field to the number of seats left to be sold on the bus route instance. You can compute num_seats_available by subtracting the 
number of sold seats for the bus instance from the bus’ seat capacity.
*/
-- solution 
DROP PROCEDURE IF EXISTS set_num_seats_list;
DELIMITER //
CREATE PROCEDURE set_num_seats_list (
	route_instance INT
)
BEGIN 
	DECLARE bus_capacity INT;
    DECLARE seats_sold INT;
    DECLARE seats_available INT;
    
    SELECT capacity INTO bus_capacity
    FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
    WHERE r_instance = route_instance
    ;
    
    SELECT IFNULL(SUM(bjb.seats_booked),0) INTO seats_sold
    FROM bus_journey_booking AS bjb JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    WHERE bjb.route_instance = route_instance
    ;
    
    SET seats_available = bus_capacity - seats_sold;
    
    UPDATE route_instance
    SET num_seats_available = seats_available
    WHERE r_instance = route_instance;
    
END //
DELIMITER ;

-- test
CALL set_num_seats_list(24);

INSERT INTO route_instance(route_id, fare_price, scheduled_date_time, driver_id, bus_id)
VALUES (17, 80,'2025-01-25 08:00:00', 7, 15);
CALL set_num_seats_list(26); -- i did this test after doing the insert in the tests for Q12, that's why it's r_instance=26 here

/*
------------------------------------------------------------ 12. ------------------------------------------------------------
Create a procedure named update_all_available_seats() that assigns the route_instance.num_seats_available to the correct value 
for each of the bus route instances.. The correct value is the difference between the capacity and the number of sold seats. 
Use the procedure from problem 11 to complete this procedure. You will need a cursor and a handler to complete this procedure.
*/
-- solution 
DROP PROCEDURE IF EXISTS update_all_available_seats;
DELIMITER //
CREATE PROCEDURE update_all_available_seats()
BEGIN 
	DECLARE done TINYINT DEFAULT FALSE;
    DECLARE current_thing INT;
    
    DECLARE seats_cursor CURSOR FOR
		SELECT r_instance
        FROM route_instance
        ;
	DECLARE CONTINUE HANDLER FOR NOT FOUND
		SET done = TRUE;
        
	OPEN seats_cursor;
    WHILE done = FALSE DO 
		FETCH seats_cursor INTO current_thing;
        IF NOT done THEN 
			CALL set_num_seats_list(current_thing);
		END IF;
    END WHILE;
    CLOSE seats_cursor;
    
END //
DELIMITER ;

-- test
CALL update_all_available_seats();

INSERT INTO route_instance(route_id, fare_price, scheduled_date_time, driver_id, bus_id)
VALUES (17, 80,'2024-12-30 08:00:00', 7, 9);
CALL update_all_available_seats();

/*
------------------------------------------------------------ 13. ------------------------------------------------------------
Write a trigger that updates the number of available seats for a route_instance record when bus tickets are sold for the bus 
route instance. The trigger will need to assign the correct value of available seats for the route instance associated with 
the sold tickets. Name the trigger r_instance_update_after_insert_journey. 

Insert a record into the bus_journey_booking table to verify your trigger is working; 
The customer name = “kathleen”, route instance = 9, number of tickets = 5 and payment method = “Credit card”.
*/
-- solution 
DROP TRIGGER IF EXISTS r_instance_update_after_insert_journey;
DELIMITER //
CREATE TRIGGER r_instance_update_after_insert_journey AFTER INSERT ON bus_journey_booking FOR EACH ROW
BEGIN  
	DECLARE bus_capacity INT;
    DECLARE seats_sold INT;
    
    SELECT capacity INTO bus_capacity
    FROM bus_type AS bt JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
    WHERE r_instance = NEW.route_instance
    ;
    
    SELECT IFNULL(SUM(bjb.seats_booked),0) INTO seats_sold
    FROM bus_journey_booking AS bjb -- JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    WHERE bjb.route_instance = NEW.route_instance
    ;
    
    UPDATE route_instance
    SET num_seats_available = bus_capacity - seats_sold
    WHERE r_instance = NEW.route_instance
    ;
    
END //
DELIMITER ;

-- test
INSERT INTO bus_journey_booking (payment_method, seats_booked, route_instance, username)
VALUES ('Credit Card', 5, 9, 'kathleen');

INSERT INTO bus_journey_booking (payment_method, seats_booked, route_instance, username)
VALUES ('Debit Card', 1, 17, 'kathleen');

/*
------------------------------------------------------------ 14. ------------------------------------------------------------
Write a trigger that updates the number of available seats for a route_instance record when bus tickets that were sold are 
returned, so the journey record is deleted. The trigger will need to  assign the correct value of available seats for the route 
instance associated with the returned tickets. Name the trigger r_instance_update_after_delete_journey. Delete a record from the 
bus_journey_booking table to verify your trigger is working; The customer name = “kathleen”, route instance = 9, 
number of tickets = 5 and payment method = “Credit card”. 
*/
-- solution 
DROP TRIGGER IF EXISTS r_instance_update_after_delete_journey;
DELIMITER //
CREATE TRIGGER r_instance_update_after_delete_journey AFTER DELETE ON bus_journey_booking
FOR EACH ROW 
BEGIN
	UPDATE route_instance AS ri 
    JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
    SET ri.num_seats_available = ri.num_seats_available + OLD.seats_booked
    WHERE ri.r_instance = OLD.route_instance
    ;
END //
DELIMITER ;

-- test
DELETE FROM bus_journey_booking
WHERE username = 'kathleen' AND route_instance = 9
;

DELETE FROM bus_journey_booking
WHERE username = 'kathleen' AND route_instance = 17 -- this is the tuple I added in Q13, which we are now deleting 
;

/*
------------------------------------------------------------ 15. ------------------------------------------------------------
Create and execute a prepared statement from the SQL workbench that calls the function more_route_instances(email1_p, email2_p). 
Use 2 user session variables to pass the two arguments to the function. Pass the values “lemonade@gmail.com” and “kath@gmail.com” 
as the customer name values. Provide at least 2 other test cases.
*/
-- solution 
SET @email1 = 'lemonade@gmail.com';
SET @email2 = 'kath@gmail.com';
SET @email3 = 'adenp@gmail.com';
SET @email4 = 'jayz@yahoo.com';
PREPARE stmt FROM 'SELECT more_route_instances(?, ?)';
EXECUTE stmt USING @email1, @email2;
 
-- test
EXECUTE stmt USING @email1, @email3;
EXECUTE stmt USING @email1, @email4;

EXECUTE stmt USING @email2, @email1;
EXECUTE stmt USING @email2, @email3;
EXECUTE stmt USING @email2, @email4;

EXECUTE stmt USING @email3, @email1;
EXECUTE stmt USING @email3, @email2;
EXECUTE stmt USING @email3, @email4;

EXECUTE stmt USING @email4, @email1;
EXECUTE stmt USING @email4, @email2;
EXECUTE stmt USING @email4, @email3;

DEALLOCATE PREPARE stmt;

/*
------------------------------------------------------------ 16. ------------------------------------------------------------
Create and execute a prepared statement from the SQL workbench that calls the procedure get_customer_journeys(customername_p). 
Use a user session variable to pass the customer name to the value. Pass the value “Chicago_Admin” as the customer’s user name. 
Provide at least 2 other test cases.
*/
-- solution 
SET @customername_p = 'Chicago_Admin';
SET @customername_p2 = 'lemo';
SET @customername_p3 = 'cust3';
SET @customername_p4 = 'kathleen';
SET @customername_p5 = 'aden@123';
PREPARE stmt2 FROM 'CALL get_customer_journeys(?)';
EXECUTE stmt2 USING @customername_p;

-- test
EXECUTE stmt2 USING @customername_p2;
EXECUTE stmt2 USING @customername_p3;
EXECUTE stmt2 USING @customername_p4;
EXECUTE stmt2 USING @customername_p5;

DEALLOCATE PREPARE stmt2;