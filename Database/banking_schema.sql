CREATE TABLE customers (
    id uuid PRIMARY KEY,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    address varchar(200) NOT NULL,
    username varchar(50) NOT NULL,
    password binary(32) NOT NULL
);

CREATE TABLE accounts (
    id uuid PRIMARY KEY,
    owner uuid NOT NULL REFERENCES customers(id),
    name varcher(100) NOT NULL,
    balance bigint NOT NULL,
    frozen bool
);

CREATE TABLE transactions (
    id uuid PRIMARY KEY,
    message varchar(100) NOT NULL,
    amount bigint NOT NULL,
    sender uuid NOT NULL REFERENCES accounts(id),
    receiver uuid NOT NULL REFERENCES accounts(id),
    timestamp datetime NOT NULL
);

CREATE TABLE withdrawals (
    id uuid PRIMARY KEY,
    account_id uuid NOT NULL REFERENCES accounts(id),
    amount bigint NOT NULL,
    timestamp datetime NOT NULL
);

CREATE TABLE deposits (
    id uuid PRIMARY KEY,
    account_id uuid NOT NULL REFERENCES accounts(id),
    amount bigint NOT NULL,
    timestamp datetime NOT NULL
);