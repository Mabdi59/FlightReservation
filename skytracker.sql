BEGIN TRANSACTION;

-- Drop the existing tables if they exist
DROP TABLE IF EXISTS flights CASCADE;
DROP TABLE IF EXISTS destinations CASCADE;
DROP TABLE IF EXISTS reservations CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS airports CASCADE;

CREATE TABLE airports (
    airport_code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE flights (
    flight_id SERIAL PRIMARY KEY,
    flight_number VARCHAR(255),
    airline VARCHAR(255),
    departure_airport_code VARCHAR(10) REFERENCES airports(airport_code),
    arrival_airport_code VARCHAR(10) REFERENCES airports(airport_code),
    scheduled_departure_time TIMESTAMP,
    scheduled_arrival_time TIMESTAMP,
    real_time_departure_time TIMESTAMP,
    real_time_arrival_time TIMESTAMP,
    status VARCHAR(50)
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE reservations (
    reservation_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    flight_id INTEGER REFERENCES flights(flight_id),
    reservation_date TIMESTAMP,
    status VARCHAR(50)
);

CREATE TABLE destinations (
    city VARCHAR(255) PRIMARY KEY,
    country VARCHAR(255),
    description TEXT,
    image_url VARCHAR(255),
    virtual_tour_url VARCHAR(255)
);

COMMIT;
