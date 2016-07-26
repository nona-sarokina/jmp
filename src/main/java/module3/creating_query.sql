drop TABLE PERSONS;
CREATE TABLE PERSONS
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  dateOfBirth DATE NOT NULL,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(20) NOT NULL,
  zipcode INT
);


INSERT INTO Persons (name, dateOfBirth, address, city, zipcode) VALUES
  ('Isaak Cleve', '1984-01-28', '1104 Sun Valley Road', 'Wishram', 35078),
  ('Russel Firuz', '1983-11-19', '2906 Tully Street', 'Detroit', 95350),
  ('August Walherich', '1980-01-11', '3744 Spirit Drive', 'Jacksonville', 55109),
  ('Dan Manfred', '1980-01-07', '3692 Wescam Court', 'Fallon', 26330),
  ('Stjepan Alpertti', '1986-05-03', '3384 Marshall Street', 'Beltsville', 32301);
