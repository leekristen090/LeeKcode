/*
Lee, K 
HW 5 - Bus database
*/
USE `bus_schema_leek`;

/*
------------------------------ 1 ------------------------------
For each driver, return the driver’s first name, last name, 
license number and the routes they have driven.  Each returned 
row contains the driver's first name, last name, license number, 
the route_id  and the route instance.  Order the results in 
ascending order using the route id.
*/
-- solution (24 rows)
SELECT driver.driver_id, first_name, last_name, license_no, route_id, r_instance
FROM driver INNER JOIN route_instance ON driver.driver_id = route_instance.driver_id
ORDER BY route_id
;

-- verification (24 rows)
SELECT *
FROM route_instance
;


/*
------------------------------ 2 ------------------------------
Determine the number of journeys each bus has made. The result 
should contain the license plate, bus type and a count. Rename 
the count num_trips. Sort the results in descending order using 
count num_trips. Make sure all buses are present in the result.
*/
-- solution
SELECT license_plate, type, COUNT(r_instance) AS num_trips
FROM bus_type LEFT OUTER JOIN route_instance ON bus_type.bus_id = route_instance.bus_id
GROUP BY license_plate
ORDER BY num_trips DESC
; 
-- verification (17 rows)
SELECT bus_id, COUNT(r_instance) AS num_trips
FROM route_instance
GROUP BY bus_id
ORDER BY num_trips DESC
;


/*
------------------------------ 3 ------------------------------
Determine the number of seats sold for each journey. Each row 
in the  result should contain the route instance id and a 
count of sold seats. Rename the count sold_seats. Sort the 
results in descending order using count sold_seats. Only 
journeys with sold seats need to appear in the result. 
*/
-- solution (rollup = 1277)
SELECT route_instance, SUM(seats_booked) AS sold_seats
FROM bus_journey_booking
GROUP BY route_instance
WITH ROLLUP
ORDER BY sold_seats DESC
;

-- verification (total sold_seats = 127)
SELECT SUM(seats_booked) AS totalSold
FROM bus_journey_booking
;


/*
------------------------------ 4 ------------------------------
Make a separate table from the bus_type table, where the records 
are for the buses that have not been used on a journey. The 
tuples should contain all the fields from the bus_type table. 
Name the new table bus_not_used. Remember, a table can only be 
created once. If you attempt to create the same table multiple 
times it will generate an error. 
*/
-- solution
DROP TABLE IF EXISTS bus_not_used;
CREATE TABLE bus_not_used AS 
SELECT bus_type.*
FROM bus_type LEFT JOIN route_instance ON bus_type.bus_id = route_instance.bus_id
WHERE route_instance.bus_id IS NULL
; -- 10,11,12,13,14,19,20-48,50
-- 36 rows (36 buses not used)

-- verification 
SELECT COUNT(DISTINCT bus_id) AS use_count
FROM route_instance
; --  total 17 (17 buses used)
SELECT COUNT(bus_id)
FROM bus_type
; -- total 53 buses
-- 17 + 36 = 53 and 53 is the total 


/*
------------------------------ 5 ------------------------------
Return a tuple for each scheduled route instance. Each tuple in 
the result should contain the route_id, originating city, 
originating state, destination city, destination state, the date 
of the departure. Order the results in ascending order using the 
originating city and originating state. 
*/
-- solution 
SELECT route_instance.route_id, origin_city_name, origin_state, destination_city_name, destination_state, scheduled_date_time
FROM route INNER JOIN route_instance ON route.route_id = route_instance.route_id
ORDER BY origin_city_name, origin_state
; -- 24 rows

-- verification 
SELECT *
FROM route_instance
; -- 24 rows


/*
------------------------------ 6 ------------------------------
For users who have taken journeys, create an aggregated field 
that contains a list of the origin cities for the user. The 
result set should contain the user name and the grouped list of 
originating city names. Rename the grouped list field result to 
origin_city. Order the results in ascending order by the user 
name. Make sure you list an originating city only once in the 
field. 
*/
-- solution 
SELECT customer.username, GROUP_CONCAT(DISTINCT origin_city_name ORDER BY origin_city_name ASC) AS origin_city
FROM customer 
	JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
	JOIN route_instance ON bjb.route_instance = route_instance.r_instance 
    JOIN route ON route_instance.route_id = route.route_id
GROUP BY customer.username
ORDER BY customer.username ASC
; -- 4 usernames returned

-- verification 
SELECT username, COUNT(username) AS countEach
FROM bus_journey_booking
GROUP BY username
; -- 4 usernames returned with num of bookings they are associated with

SELECT GROUP_CONCAT(DISTINCT username)AS passengers, origin_city_name
FROM bus_journey_booking AS bjb 
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route AS r ON ri.route_id = r.route_id
GROUP BY origin_city_name
ORDER BY origin_city_name
; -- city and users associated with them


/*
------------------------------ 7 ------------------------------
Which destination city is the most popular (has the most number 
of seats sold)? The result should contain the destination city, 
and destination state and the total number of sold seats, 
renamed to sold_seats. Remember, city names are not guaranteed 
to be unique, they must be paired with the state name to identify 
a destination city.
*/
-- solution 
SELECT destination_city_name AS destination_city, destination_state, SUM(seats_booked) AS seats_sold
FROM route JOIN route_instance AS ri ON route.route_id = ri.route_id
	JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY destination_city_name, destination_state
ORDER BY seats_sold DESC
LIMIT 1
; -- miami, FL is most popular destination 

-- verification 
SELECT SUM(seats_booked) AS seats_sold, bjb.route_instance, ri.route_id, destination_city_name AS destination_city, destination_state
FROM bus_journey_booking AS bjb 
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route AS r ON ri.route_id = r.route_id
GROUP BY route_instance
ORDER BY destination_city_name, destination_state 
; -- shows num seats for each route instance booking
SELECT MAX(seats_sold) AS sold
FROM (SELECT destination_city_name AS destination_city, destination_state, SUM(seats_booked) AS seats_sold
	FROM route 
		JOIN route_instance AS ri ON route.route_id = ri.route_id
		JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
	GROUP BY destination_city_name, destination_state) AS mostPop
; -- the most seats sold is 38
SELECT destination_city_name AS destination_city, destination_state, SUM(seats_booked) AS seats_sold
FROM route JOIN route_instance AS ri ON route.route_id = ri.route_id
	JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY destination_city_name, destination_state
ORDER BY seats_sold DESC
; -- shows all dest cities and their seats_sold; miami, FL has the most


/*
------------------------------ 8 ------------------------------
Determine the number of passengers arriving in each destination 
city. The result should contain the destination city, and 
destination state and the total number of  passengers (sold seats), 
renamed to num_travelers. Remember, city names are not guaranteed 
to be unique, they must be paired with the state name to identify 
a destination city. Sort the results in descending order using 
the num_travelers field. 
*/
-- solution 
SELECT destination_city_name AS destination_city, destination_state, SUM(seats_booked) AS num_travelers
FROM route 
	JOIN route_instance AS ri ON route.route_id = ri.route_id
	right JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY destination_city_name, destination_state
ORDER BY num_travelers DESC
; 

-- verification 
SELECT bjb.route_instance, destination_city_name AS destination_city, destination_state, SUM(seats_booked) AS num_travelers
FROM route 
	JOIN route_instance AS ri ON route.route_id = ri.route_id
	right JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY destination_city_name, destination_state, bjb.route_instance
ORDER BY num_travelers DESC
; -- for each route_instance we see the num of passengers


/*
------------------------------ 9 ------------------------------
For each bus model, determine the number of journeys associated 
with it. Rename the number of journeys  to num_bmodel_trip. Sort 
the results in descending order using the num_bmodel_trip field. 
Make sure all bus models appear in the result. 
*/
-- solution 
SELECT COUNT(DISTINCT bjb.route_instance) AS num_bmodel_trip, model
FROM bus_journey_booking AS bjb 
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
GROUP BY model
ORDER BY num_bmodel_trip DESC
; -- 11 rows with 13 total buses journeys

-- verification
SELECT DISTINCT route_instance
FROM bus_journey_booking
; -- 13 distinct route instance journeys

SELECT COUNT(DISTINCT bjb.route_instance)num_bmodel_trip, ri.bus_id, model, GROUP_CONCAT( DISTINCT route_instance) AS routeInstanceList
FROM bus_journey_booking AS bjb 
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
GROUP BY bus_id
;  -- 11 rows with 13 total buses journeys


/*
------------------------------ 10 ------------------------------
Using the route table determine the journey that takes the 
longest amount of time. The result should contain all of the 
fields in the route_instance table. Make sure your query reports 
on all routes with the maximum duration.  
*/
-- solution 
SELECT ri.*, max_duration
FROM route_instance AS ri JOIN route ON ri.route_id = route.route_id 
	JOIN (SELECT MAX(estimated_time) AS max_duration FROM route) AS findMax ON estimated_time = findMax.max_duration
; -- two journeys with time 77

-- verification
SELECT MAX(estimated_time) AS max_duration
FROM route
; -- max duration is 77


/*
------------------------------ 11 ------------------------------
For each customer journey, report the customer’s first and last 
name, the number of seats purchased, the departure time of the bus, 
the origin city, origin state, destination city, destination state, 
the bus model, the bus type, the length of the journey in miles and 
hours. Append the unit of measure to the expected distance field 
(miles) and the expected distance field (hours) field. Order the 
results in ascending order using the origin city followed by the 
origin state. 
*/      
-- solution 
SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS seatsPurchased, 
	scheduled_date_time AS departure, origin_city_name AS originCity, origin_state AS originState, 
	destination_city_name AS destinationCity, destination_state AS destinationState, model, type,
    CONCAT(estimated_time, ' hrs') AS estTime, CONCAT(estimated_miles, ' miles') AS estMiles
FROM customer 
	JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route ON ri.route_id = route.route_id
    JOIN bus_type ON ri.bus_id = bus_type.bus_id
GROUP BY ri.r_instance, customer.username
ORDER BY originCity,originState
;
-- verification
SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS numSeats
FROM customer JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
GROUP BY customer.username
;
/*
jay z = 20 
lemo nade = 21
aden pitt = 15
kathleen grant = 1
*/

SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS numSeats
FROM customer JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
WHERE first_name LIKE 'jay'
GROUP BY firstName, lastName
 -- jay z = 20 
UNION
SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS numSeats
FROM customer JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
WHERE first_name LIKE 'lemo'
GROUP BY firstName, lastName
 -- lemo nade = 21
UNION
SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS numSeats
FROM customer JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
WHERE first_name LIKE 'aden'
GROUP BY firstName, lastName
 -- aden pitt = 15
UNION
SELECT first_name AS firstName, last_name AS lastName, COUNT(seats_booked) AS numSeats
FROM customer JOIN bus_journey_booking AS bjb ON customer.username = bjb.username
WHERE first_name LIKE 'kathleen'
GROUP BY firstName, lastName
; -- kathleen grant = 1


/*
------------------------------ 12 ------------------------------
Return the username, customer’s first name and last name who has 
traveled on all routes. 
*/    
-- solution
SELECT c.username, first_name, last_name, COUNT(DISTINCT ri.route_id) AS NumUniqueRoutes
FROM customer AS c 
	JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route as r ON ri.route_id = r.route_id
GROUP BY c.username,c.first_name,c.last_name
HAVING NumUniqueRoutes = 20
;

-- verification
SELECT COUNT(*) 
FROM route
; -- 20 routes
SELECT c.username, first_name, last_name, COUNT(DISTINCT ri.route_id) AS NumUniqueRoutes, GROUP_CONCAT(DISTINCT ri.route_id) AS routeIdList
FROM customer AS c 
	JOIN bus_journey_booking AS bjb ON c.username = bjb.username
    JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
    JOIN route as r ON ri.route_id = r.route_id
GROUP BY c.username,c.first_name,c.last_name
; -- none travel on all 20 routes


/*
------------------------------ 13 ------------------------------
Return the customer’s username, first name and last name who has 
traveled on all bus types. Order the result in ascending order 
using the customer’s last name. 
enum
*/
-- solution 
SELECT c.username, c.first_name, c.last_name
FROM customer AS c
	JOIN bus_journey_booking AS bjb ON c.username = bjb.username
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
	JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
GROUP BY c.username,c.first_name,c.last_name
HAVING COUNT(DISTINCT bt.type) = (SELECT COUNT(DISTINCT type) FROM bus_type)            
ORDER BY c.last_name
; -- lemo nade

-- verification
SELECT DISTINCT type
FROM bus_type
; -- 4 types of buses (sleeper, seater, double decker, shuttle)

SELECT c.username, c.first_name, c.last_name, COUNT(DISTINCT bt.type) AS UniqueBus
FROM customer AS c
	JOIN bus_journey_booking AS bjb ON c.username = bjb.username
	JOIN route_instance AS ri ON bjb.route_instance = ri.r_instance
	JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
GROUP BY c.username,c.first_name,c.last_name
; -- only lemo nade rides all 4 bus types

/*
------------------------------ 14 ------------------------------
Return the bus color that has made more than one journey. The 
result consists of two columns, the color and the count of 
journeys made by buses with that color. Rename the count to 
num_journeys. Sort the result in descending order using 
num_journeys.
*/
-- solution 
SELECT COUNT(DISTINCT route_instance) AS numJourneys, color
FROM route_instance AS ri 
	JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
    RIGHT OUTER JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY color
HAVING numJourneys > 1
ORDER BY numJourneys DESC
; --  3 rows with 7 total journeys

-- verification
SELECT COUNT(DISTINCT route_instance) AS numJourneys, color, GROUP_CONCAT(DISTINCT route_instance) AS routeInstList
FROM route_instance AS ri 
	JOIN bus_type AS bt ON ri.bus_id = bt.bus_id
    RIGHT OUTER JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY color
ORDER BY numJourneys DESC
; -- 13 rows
SELECT color, COUNT(DISTINCT route_instance) AS numJourneys
FROM bus_type AS bt 
	JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
	JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY color
HAVING numJourneys <= 1
; -- 6 rows of bus colors with one journey 
-- 6 + 7 = 13
SELECT DISTINCT route_instance
FROM bus_journey_booking
; -- 13 total bus journeys 
SELECT DISTINCT color
FROM bus_type
; -- 29 rows -> 29 colors


/*
------------------------------ 15 ------------------------------
Find pairs of users that traveled on the same route instance. 
Each returned tuple should contain the first name, last name for 
the 2 users as well as the route_instance they were on. Order the 
results in ascending order by the route_instance id. Make sure you 
do not match a user with themselves. Also, only report the same 
user pair once. 
*/
-- solution 
SELECT DISTINCT c1.first_name AS user1FirstName, c1.last_name AS user1LastName, 
	c2.first_name AS user2FirstName, c2.last_name AS user2LastName, bjb1.route_instance
FROM bus_journey_booking AS bjb1
    JOIN customer AS c1 ON bjb1.username = c1.username
    JOIN bus_journey_booking AS bjb2 ON bjb1.route_instance = bjb2.route_instance
    JOIN customer AS c2 ON bjb2.username = c2.username
WHERE bjb1.username < bjb2.username
ORDER BY bjb1.route_instance ASC
;
-- verification
SELECT GROUP_CONCAT(DISTINCT bjb.username) AS riders, route_instance
FROM customer AS c JOIN bus_journey_booking AS bjb ON c.username=bjb.username
GROUP BY route_instance
HAVING (COUNT(DISTINCT bjb.username) * (COUNT(DISTINCT bjb.username) - 1))  > 0
; -- groups username pairs based on route isntance


/*
------------------------------ 16 ------------------------------
For each bus color, determine the count of passengers that have 
ridden in it. There should be a row in the result set for each bus 
color. The result set contains the bus color and the count of the 
passengers. Order the results in ascending order using the bus color. 
*/
-- solution 
-- using sum to account for person buying multiple tixs per route_instance
SELECT bt.color, SUM(bjb.seats_booked) AS numPassengers
FROM bus_type AS bt 
	JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
	JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
GROUP BY bt.color
WITH ROLLUP
ORDER BY numPassengers ASC
; -- 127 total seats booked

-- verification
SELECT SUM(seats_booked)
FROM bus_journey_booking
; -- total 127

/*
------------------------------ 17 ------------------------------
For each journey, determine the number of empty seats and sold 
seats on the bus. The results should include the route instance 
id, the route id, the total number of tickets sold for the trip 
and the total number of empty seats, the destination city and 
destination state. Rename the calculated field to empty_seats and 
the number of sold seats to sold_seats. Use the IFNULL function 
to handle the trips with no sold tickets. Order the results in 
ascending order by the calculated number of empty seats.
*/
-- solution
SELECT bjb.route_instance, ri.route_id, destination_city_name AS destinationCity, destination_state AS destinationState, capacity,
	SUM(IFNULL(seats_booked,0)) AS seatsSold, 
    (capacity-SUM(IFNULL(seats_booked,0))) AS emptySeats
FROM bus_type AS bt 
	JOIN route_instance AS ri ON bt.bus_id = ri.bus_id
	JOIN bus_journey_booking AS bjb ON ri.r_instance = bjb.route_instance
    JOIN route AS r ON ri.route_id = r.route_id
GROUP BY r_instance
ORDER BY emptySeats ASC
;

-- verification 
SELECT bjb.route_instance, SUM(IFNULL(seats_booked,0)) AS totalSeatsSold
FROM bus_journey_booking AS bjb
GROUP BY route_instance
WITH ROLLUP
ORDER BY totalSeatsSold DESC
; -- rollup = 127 total sold

SELECT DISTINCT route_instance
FROM bus_journey_booking
; -- 13 rows -> route_instance associated with a journey


/*
------------------------------ 18 ------------------------------
Display all route_instances for routes whose destination city 
begins with the letter M. Return all fields in the route and 
route_instance tables. Order the results in descending order by 
the destination city, followed by the scheduled departure. 
*/
-- solution 
SELECT ri.*, r.*
FROM route AS r JOIN route_instance AS ri ON r.route_id = ri.route_id
WHERE destination_city_name LIKE "m%"
ORDER BY destination_city_name DESC, scheduled_date_time 
;

-- verification
SELECT DISTINCT destination_city_name
FROM route
WHERE destination_city_name LIKE "m%"
; -- miami


/*
------------------------------ 19 ------------------------------
Find all route instances where the number of hours the trip takes 
is between 25 and 35 hours and the number of miles is between 
1,000 and 3,000 miles inclusively. The fare price  must  be less 
than $100. Return all fields in the route table as well as the 
route_instance_id, the schedule_departure_time and the fare_price. 
Order the results in ascending order using the route_instance field. 
*/
-- solution 
SELECT ri.r_instance, r.*, scheduled_date_time AS departure, fare_price AS fare
FROM route AS r RIGHT JOIN route_instance AS ri ON r.route_id = ri.route_id
WHERE estimated_time BETWEEN 25 AND 35 AND estimated_miles BETWEEN 1000 AND 3000 AND fare_price < 100
ORDER BY r_instance
;

-- verification
SELECT *
FROM route
WHERE estimated_time BETWEEN 25 AND 35
UNION 
SELECT *
FROM route
WHERE estimated_miles BETWEEN 1000 AND 3000
ORDER BY estimated_time ASC
; -- 10 rows

SELECT *
FROM route_instance
where fare_price < 100
; -- 19 rows

