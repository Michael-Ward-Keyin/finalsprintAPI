SET FOREIGN_KEY_CHECKS = 0;

-- Clear tables in FK-safe order
DELETE FROM flight;
DELETE FROM gate;
DELETE FROM airport;
DELETE FROM aircraft;
DELETE FROM airline;
DELETE FROM city;
DELETE FROM passenger;

SET FOREIGN_KEY_CHECKS = 1;

-- Cities (for airport.city_id)
INSERT INTO city (id, name) VALUES
  (1, 'New York'),
  (2, 'Los Angeles'),
  (3, 'Atlanta'),
  (4, 'Chicago');

-- Airlines (code is required in your model)
INSERT INTO airline (id, code, name) VALUES
  (1, 'AA', 'American Airlines'),
  (2, 'DL', 'Delta Airlines');

-- Aircraft (model only)
INSERT INTO aircraft (id, model) VALUES
  (1, 'Boeing 737'),
  (2, 'Airbus A320');

-- Airports (uses FK city_id, not a text "city" column)
INSERT INTO airport (id, name, city_id) VALUES
  (1, 'JFK International', 1),
  (2, 'LAX International', 2),
  (3, 'ATL International', 3),
  (4, 'ORD International', 4);

-- Gates (assumes columns: id, name, airport_id)
INSERT INTO gate (id, name, airport_id) VALUES
  (1, 'A1', 1),
  (2, 'B2', 1);

-- Flights
-- Columns: id, flight_number, type, status, airline_id, aircraft_id,
--          board_airport_id, other_airport_id, gate_id,
--          scheduled_time, estimated_time, remarks
-- Uses MySQL date math (DATE_ADD) instead of H2 DATEADD
INSERT INTO flight
  (id, flight_number, type, status, airline_id, aircraft_id,
   board_airport_id, other_airport_id, gate_id,
   scheduled_time, estimated_time, remarks)
VALUES
  (1, 'AA100', 'DEPARTURE', 'BOARDING', 1, 1, 1, 4, 1,
   DATE_ADD(NOW(), INTERVAL 30 MINUTE), NULL, 'On time'),
  (2, 'DL220', 'DEPARTURE', 'DELAYED',  2, 2, 1, 3, 2,
   DATE_ADD(NOW(), INTERVAL 60 MINUTE), DATE_ADD(NOW(), INTERVAL 90 MINUTE), 'Weather');