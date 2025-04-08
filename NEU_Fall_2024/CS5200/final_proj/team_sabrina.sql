/*
This is the file that holds create table statements for my CS 5200 database application project.
*/

CREATE DATABASE IF NOT EXISTS team_sabrina;
USE team_sabrina;

CREATE TABLE album (
album_id INT AUTO_INCREMENT PRIMARY KEY,
album_name VARCHAR(64) NOT NULL, 
release_date DATE NOT NULL
);

CREATE TABLE tour (
tour_name VARCHAR(64) PRIMARY KEY,
start_date DATE NOT NULL, 
end_date DATE, 
tour_status ENUM('Upcoming','Ongoing', 'Ended') NOT NULL
);

CREATE TABLE song (
song_id INT AUTO_INCREMENT PRIMARY KEY,
song_name VARCHAR(64) NOT NULL,  
album_id INT NOT NULL,
tour_name VARCHAR(64),
order_played INT NOT NULL, 
FOREIGN KEY (album_id) REFERENCES album(album_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE location (
location_id INT AUTO_INCREMENT PRIMARY KEY,
city VARCHAR(64) NOT NULL,
state_region VARCHAR(64),
country VARCHAR(64) NOT NULL,
UNIQUE(city, state_region, country)
);

CREATE TABLE venue (
location_id INT,
venue_name VARCHAR(64) NOT NULL, 
capacity INT NOT NULL, 
PRIMARY KEY(location_id, venue_name),
FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE sabrina_show (
tour_name VARCHAR(64), 
show_id INT,
venue_name VARCHAR(64) NOT NULL,
location_id INT NOT NULL,
scheduled_date DATE NOT NULL, 
show_status ENUM('Upcoming', 'Cancelled', 'Completed') NOT NULL,
PRIMARY KEY (tour_name, show_id), 
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (location_id, venue_name) REFERENCES venue(location_id, venue_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE opening_act (
act_id INT AUTO_INCREMENT PRIMARY KEY,
act_name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE opening_to_show (
tour_name VARCHAR(64),
show_id INT,
act_id INT,
performance_order INT,
PRIMARY KEY (tour_name, show_id, act_id),
FOREIGN KEY (tour_name, show_id) REFERENCES sabrina_show(tour_name, show_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (act_id) REFERENCES opening_act(act_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE customer (
customer_id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(256) NOT NULL, 
last_name VARCHAR(256) NOT NULL, 
phone VARCHAR(15) NOT NULL,
email VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE ticket_sales (
transaction_id INT AUTO_INCREMENT PRIMARY KEY, 
purchase_date DATE NOT NULL, 
total_cost DECIMAL(10,2) NOT NULL, 
ticket_quantity INT NOT NULL,
customer_id INT,
tour_name VARCHAR(64), 
show_id INT,
FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (tour_name, show_id) REFERENCES sabrina_show(tour_name, show_id) ON DELETE CASCADE ON UPDATE CASCADE
);

