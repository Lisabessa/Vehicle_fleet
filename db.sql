CREATE DATABASE vehicles;

CREATE TABLE cars (
                      id SERIAL PRIMARY KEY,
                      model VARCHAR(50),
                      manufacture_year SMALLINT CHECK(manufacture_year > 0 AND manufacture_year <= 9999),
                      registration_date DATE,
                      owner VARCHAR(100)
)