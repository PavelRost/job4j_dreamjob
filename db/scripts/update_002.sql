CREATE TABLE if NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    name TEXT
);
INSERT INTO cities(name) VALUES ('Санкт-Петербург');
CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id INTEGER REFERENCES cities (id),
    date DATE
);
