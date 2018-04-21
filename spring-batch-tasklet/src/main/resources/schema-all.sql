DROP TABLE loan IF EXISTS;

CREATE TABLE loan  (
    msisdn VARCHAR(20),
    network VARCHAR(20),
    created DATE,
    product VARCHAR(20),
    amount DECIMAL(13,4)
);