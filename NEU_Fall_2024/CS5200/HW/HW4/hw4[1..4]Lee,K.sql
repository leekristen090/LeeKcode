/*
cs 5200 homework 4 
schema 1-4
Kristen Lee
*/

/*
-------------------------------- Schema 1 --------------------------------
*/
DROP DATABASE IF EXISTS mtba;
CREATE DATABASE mtba;
USE mtba;

CREATE TABLE Staff (
	staff_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50)
);

CREATE TABLE Station (
    station_name VARCHAR(100) PRIMARY KEY,
    longitude DECIMAL(9,6) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    zone VARCHAR(10),
    manager_id INT UNIQUE,
    FOREIGN KEY (manager_id) REFERENCES Staff(staff_id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE Route (
    route_name VARCHAR(100) PRIMARY KEY,
    fare DECIMAL(5,2) NOT NULL NOT NULL,
    days_of_week VARCHAR(100),
    route_mode ENUM('Commuter Rail', 'Subway Line', 'Ferry Routes', 'Bus Routes') NOT NULL,
    number_of_stops INT NOT NULL
);

CREATE TABLE Route_Station (
    route_name VARCHAR(100),
    station_name VARCHAR(100),
    sequence_number INT,
    PRIMARY KEY (route_name, station_name),
    FOREIGN KEY (route_name) REFERENCES Route(route_name) ON UPDATE RESTRICT ON DELETE RESTRICT,
    FOREIGN KEY (station_name) REFERENCES Station(station_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Vehicle (
    vehicle_number INT PRIMARY KEY,
    passenger_capacity INT NOT NULL,
    current_longitude DECIMAL(9,6) NOT NULL,
    current_latitude DECIMAL(9,6) NOT NULL,
    last_maintenance_date DATE NOT NULL
);

CREATE TABLE Trip (
    trip_id INT PRIMARY KEY AUTO_INCREMENT,
    route_name VARCHAR(100),
    vehicle_number INT,
    scheduled_departure_datetime DATETIME,
    scheduled_arrival_datetime DATETIME,
    actual_departure_datetime DATETIME,
    actual_arrival_datetime DATETIME,
    status ENUM('Delayed', 'On Time', 'Canceled'),
    FOREIGN KEY (route_name) REFERENCES Route(route_name) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (vehicle_number) REFERENCES Vehicle(vehicle_number) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE CharlieCard (
    card_number VARCHAR(16) PRIMARY KEY,
    current_balance DECIMAL(5,2) NOT NULL,
    email_id VARCHAR(100) NOT NULL
);

CREATE TABLE Trip_CharlieCard (
    trip_id INT,
    card_number VARCHAR(16),
    usage_time DATETIME NOT NULL,
    PRIMARY KEY (trip_id, card_number),
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (card_number) REFERENCES CharlieCard(card_number) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Operator (
    staff_id INT,
    trip_id INT,
    PRIMARY KEY (staff_id, trip_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);


/*
-------------------------------- Schema 2 --------------------------------
*/
DROP DATABASE IF EXISTS regionalSchool;
CREATE DATABASE regionalSchool;
USE regionalSchool;
CREATE TABLE School (
    school_name VARCHAR(100) PRIMARY KEY,
    level ENUM('primary', 'middle', 'high') NOT NULL,
    street_number INT NOT NULL,
    street_name VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    telephone_number VARCHAR(15) NOT NULL,
    UNIQUE(street_number, street_name, town, zip_code)
);

CREATE TABLE Pupil (
    pupil_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    sex ENUM('Male', 'Female', 'Other') NOT NULL,
    date_of_birth DATE NOT NULL
);

CREATE TABLE Employee (
    nin VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    department VARCHAR(50) NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    preferred_pronoun VARCHAR(20) NOT NULL
);

CREATE TABLE TeachingStaff (
    nin VARCHAR(20) PRIMARY KEY,
    degree_name VARCHAR(100) NOT NULL,
    degree_level ENUM('Bachelors', 'Masters', 'Doctorate') NOT NULL,
    university_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (nin) REFERENCES Employee(nin) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE SchoolYear (
    school_year VARCHAR(9) PRIMARY KEY
);

CREATE TABLE Pupil_SchoolYear (
    pupil_id INT,
    school_name VARCHAR(100),
    school_year VARCHAR(9),
    PRIMARY KEY (pupil_id, school_name, school_year),
    FOREIGN KEY (pupil_id) REFERENCES Pupil(pupil_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (school_name) REFERENCES School(school_name) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (school_year) REFERENCES SchoolYear(school_year) ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE Employee_SchoolYear (
    nin VARCHAR(20),
    school_name VARCHAR(100),
    school_year VARCHAR(9),
    position VARCHAR(50) NOT NULL,
    salary DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (nin, school_name, school_year),
    FOREIGN KEY (nin) REFERENCES Employee(nin) ON UPDATE RESTRICT ON DELETE RESTRICT,
    FOREIGN KEY (school_name) REFERENCES School(school_name) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (school_year) REFERENCES SchoolYear(school_year) ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE Subject (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(100) NOT NULL,
    subject_level INT CHECK(subject_level BETWEEN 0 AND 12) NOT NULL,
    abbreviation VARCHAR(10) NOT NULL,
    UNIQUE (subject_name, subject_level)
);

CREATE TABLE Class (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_id INT NOT NULL,
    school_year VARCHAR(9),
    school_name VARCHAR(100),
    class_hours_per_year INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    FOREIGN KEY (school_year) REFERENCES SchoolYear(school_year) ON UPDATE RESTRICT ON DELETE RESTRICT,
    FOREIGN KEY (school_name) REFERENCES School(school_name) ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE Pupil_Class (
    pupil_id INT,
    class_id INT,
    PRIMARY KEY (pupil_id, class_id),
    FOREIGN KEY (pupil_id) REFERENCES Pupil(pupil_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Class(class_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Teacher_Class (
    nin VARCHAR(20),
    class_id INT,
    teaching_hours_per_year INT,
    PRIMARY KEY (nin, class_id),
    FOREIGN KEY (nin) REFERENCES Employee(nin) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Class(class_id) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

/*
-------------------------------- Schema 3 --------------------------------
*/
DROP DATABASE IF EXISTS bloodBank;
CREATE DATABASE bloodBank;
USE bloodBank;
CREATE TABLE Clinic (
    clinic_no INT PRIMARY KEY,
    clinic_name VARCHAR(100) NOT NULL,
    street_number INT NOT NULL,
    street_name VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    telephone_number VARCHAR(15) NOT NULL,
    UNIQUE(street_number, street_name, town, zip_code)
);

CREATE TABLE Staff (
    emp_no INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    start_date DATE,
    clinic_no INT,
    FOREIGN KEY (clinic_no) REFERENCES Clinic(clinic_no) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BloodClassification (
    classification_id INT PRIMARY KEY AUTO_INCREMENT,
    blood_type ENUM('A', 'B', 'AB', 'O') NOT NULL,
    rh_factor ENUM('positive', 'negative') NOT NULL,
    current_bag_count INT DEFAULT 0
);

CREATE TABLE Donor (
    patient_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    biological_gender ENUM('Male', 'Female') NOT NULL,
    contestant_username VARCHAR(50) UNIQUE NULL,
    classification_id INT,
    FOREIGN KEY (classification_id) REFERENCES BloodClassification(classification_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE BloodDonation (
    donation_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    clinic_no INT,
    donation_date DATE,
    blood_bag_serial VARCHAR(20) UNIQUE,
    FOREIGN KEY (patient_id) REFERENCES Donor(patient_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (clinic_no) REFERENCES Clinic(clinic_no) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Contest (
    contest_name VARCHAR(100) PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    prize VARCHAR(100)
);

CREATE TABLE ContestParticipation (
    contest_name VARCHAR(100),
    patient_id INT,
    donations_count INT DEFAULT 0,
    PRIMARY KEY (contest_name, patient_id),
    FOREIGN KEY (contest_name) REFERENCES Contest(contest_name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES Donor(patient_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Leaderboard (
    contest_name VARCHAR(100),
    patient_id INT,
    ranking INT,
    PRIMARY KEY (contest_name, patient_id),
    FOREIGN KEY (contest_name) REFERENCES Contest(contest_name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES Donor(patient_id) ON DELETE CASCADE ON UPDATE CASCADE
);


/*
-------------------------------- Schema 4 --------------------------------
*/
DROP DATABASE IF EXISTS streaming;
CREATE DATABASE streaming;
USE streaming;

CREATE TABLE Person (
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender ENUM('female', 'male', 'lesbian', 'gay', 'transgender', 'non-binary') NOT NULL,
    preferred_pronoun ENUM('she', 'he', 'they') NOT NULL,
    UNIQUE (first_name, last_name, date_of_birth)
);

CREATE TABLE Actor (
    actor_id INT PRIMARY KEY,
    FOREIGN KEY (actor_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Director (
    director_id INT PRIMARY KEY,
    FOREIGN KEY (director_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Producer (
    producer_id INT PRIMARY KEY,
    FOREIGN KEY (producer_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE TelevisionSeries (
    series_id INT PRIMARY KEY AUTO_INCREMENT,
    series_name VARCHAR(100) NOT NULL,
    number_of_episodes INT NOT NULL,
    release_date DATE NOT NULL,
    calculated_rating DECIMAL(3,2),
    UNIQUE (series_name, release_date)
);

CREATE TABLE character_in_series (
    character_id INT PRIMARY KEY AUTO_INCREMENT,
    character_name VARCHAR(50) NOT NULL,
    actor_id INT,
    series_id INT,
    FOREIGN KEY (actor_id) REFERENCES Actor(actor_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (series_id) REFERENCES TelevisionSeries(series_id) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

CREATE TABLE Season (
    season_id INT PRIMARY KEY AUTO_INCREMENT,
    series_number INT NOT NULL,
    season_name VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    season_status ENUM('ongoing', 'completed') NOT NULL,
    series_id INT,
    FOREIGN KEY (series_id) REFERENCES TelevisionSeries(series_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Episode (
    episode_id INT PRIMARY KEY AUTO_INCREMENT,
    episode_name VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    episode_number INT NOT NULL,
    episode_rating DECIMAL(3,2),
    season_id INT,
    FOREIGN KEY (season_id) REFERENCES Season(season_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Genre (
    genre_name ENUM('animated', 'adventure', 'horror', 'supernatural', 'action', 'romance', 'comedy', 'drama', 'documentary') PRIMARY KEY
);

CREATE TABLE TelevisionSeries_Genre (
    series_id INT,
    genre_name ENUM('animated', 'adventure', 'horror', 'supernatural', 'action', 'romance', 'comedy', 'drama', 'documentary'),
    PRIMARY KEY (series_id, genre_name),
    FOREIGN KEY (series_id) REFERENCES TelevisionSeries(series_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (genre_name) REFERENCES Genre(genre_name) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Visitor (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    visitor_type ENUM('anonymous', 'registered') NOT NULL
);

CREATE TABLE RegisteredUser (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE RegisteredVisitor (
    session_id INT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (session_id) REFERENCES Visitor(session_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Comment (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(2056) NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5) NOT NULL,
    episode_id INT,
    session_id INT,
    user_id INT NULL,
    FOREIGN KEY (episode_id) REFERENCES Episode(episode_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (session_id) REFERENCES Visitor(session_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE BookFranchise (
    franchise_id INT PRIMARY KEY AUTO_INCREMENT,
    franchise_name VARCHAR(100) UNIQUE NOT NULL,
    start_date DATE NOT NULL,
    room_number INT,
    street_number INT,
    street_name VARCHAR(100),
    town VARCHAR(50),
    country VARCHAR(50),
    office_telephone_number VARCHAR(15),
    UNIQUE(street_number, street_name, town, country)
);

CREATE TABLE Creator (
    creator_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    office_address VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE Publisher (
    publisher_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    office_address VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE Volume (
    volume_id INT PRIMARY KEY AUTO_INCREMENT,
    volume_number INT NOT NULL,
    volume_name VARCHAR(100) NOT NULL,
    number_of_pages INT NOT NULL,
    cost DECIMAL(10,2) NOT NULL,
    number_of_chapters INT NOT NULL,
    franchise_id INT,
    creator_id INT,
    publisher_id INT,
    FOREIGN KEY (franchise_id) REFERENCES BookFranchise(franchise_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (creator_id) REFERENCES Creator(creator_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (publisher_id) REFERENCES Publisher(publisher_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

