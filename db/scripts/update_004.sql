CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    FOREIGN KEY (city_id) INTEGER REFERENCES cities (id),
    date DATE
);