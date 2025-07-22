CREATE TABLE transaction_category (
    id   SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name TEXT        NOT NULL
);