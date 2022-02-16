CREATE TABLE if NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id INTEGER REFERENCES cities (id),
    date DATE
);