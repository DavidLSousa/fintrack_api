DROP TABLE tickets;

CREATE TABLE tickers (
    id UUID PRIMARY KEY,
    name_ticket VARCHAR(255) NOT NULL,
    ticket VARCHAR(50) UNIQUE NOT NULL,
    number_of_tickets INT NOT NULL CHECK (number_of_tickets > 0),
    total_value_purchased DECIMAL(10,2) NOT NULL CHECK (total_value_purchased > 0),
    highest_price DECIMAL(10,2) NOT NULL CHECK (highest_price > 0),
    lowest_price DECIMAL(10,2) NOT NULL CHECK (lowest_price > 0),
    average_price DECIMAL(10,2) NOT NULL CHECK (average_price > 0)
);
