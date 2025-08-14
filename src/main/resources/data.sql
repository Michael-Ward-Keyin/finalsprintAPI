-- CITIES
INSERT INTO city (id, name) VALUES (1,'New York'),(2,'Toronto'),(3,'London');

-- AIRPORTS
INSERT INTO airport (id, name, city_id) VALUES
 (1,'JFK International Airport',1),
 (2,'LaGuardia Airport',1),
 (3,'Toronto Pearson International Airport',2),
 (4,'Heathrow Airport',3);

-- AIRCRAFT
INSERT INTO aircraft (id, model) VALUES
 (1,'Boeing 737-800'),
 (2,'Airbus A320'),
 (3,'Boeing 777-300ER'),
 (4,'Airbus A350-900');

-- AIRLINES
INSERT INTO airline (id, code, name) VALUES
 (1,'AA','American Airlines'),
 (2,'DL','Delta Air Lines'),
 (3,'BA','British Airways'),
 (4,'AC','Air Canada');

-- GATES
INSERT INTO gate (id, name, airport_id) VALUES
 (1,'A12',1),
 (2,'B7',1),
 (3,'C3',3),
 (4,'T5-18',4);

-- FLIGHTS
-- Departures (board shown at JFK)
INSERT INTO flight (id, flight_number, type, status, airline_id, aircraft_id, board_airport_id, other_airport_id, gate_id, scheduled_time, estimated_time, remarks)
VALUES
 (1,'AA100','DEPARTURE','BOARDING',1,1,1,4,1,  DATEADD('MINUTE', 30, CURRENT_TIMESTAMP), NULL, 'On time'),
 (2,'DL220','DEPARTURE','DELAYED',  2,2,1,3,2,  DATEADD('MINUTE', 60, CURRENT_TIMESTAMP), DATEADD('MINUTE',90,CURRENT_TIMESTAMP), 'Weather');

-- Arrivals (board shown at JFK)
INSERT INTO flight (id, flight_number, type, status, airline_id, aircraft_id, board_airport_id, other_airport_id, gate_id, scheduled_time, estimated_time, remarks)
VALUES
 (3,'BA117','ARRIVAL','EN_ROUTE',  3,3,1,4,1,  DATEADD('MINUTE', 45, CURRENT_TIMESTAMP), NULL, NULL),
 (4,'AC709','ARRIVAL','SCHEDULED', 4,2,1,3,2,  DATEADD('MINUTE',120, CURRENT_TIMESTAMP), NULL, NULL);

-- Heathrow board
INSERT INTO flight (id, flight_number, type, status, airline_id, aircraft_id, board_airport_id, other_airport_id, gate_id, scheduled_time, estimated_time, remarks)
VALUES
 (5,'BA150','DEPARTURE','SCHEDULED',3,4,4,1,4, DATEADD('MINUTE', 75, CURRENT_TIMESTAMP), NULL, NULL),
 (6,'AA101','ARRIVAL','LANDED',    1,1,4,1,4, DATEADD('MINUTE', -10, CURRENT_TIMESTAMP), NULL, 'Arrived');
