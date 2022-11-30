INSERT INTO drones (serial_number, model, weight_limit, battery_capacity, state) VALUES
('axDH34', 'Lightweight', 150, 100, 'DELIVERING'),
('LBks34', 'Cruiserweight', 500, 24, 'IDLE');

INSERT INTO medications (id, name, weight, code, image, drone_serial_number) VALUES
(0, 'COVID19 Test kit', 90, 'SNDP_566', '', 'axDH34');
