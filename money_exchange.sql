CREATE TABLE currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    exchange_rate DECIMAL(38, 2) NOT NULL,
    symbol VARCHAR(3) NOT NULL,
    created_date DATETIME(6),
    mod_date DATETIME(6),
    currency_name VARCHAR(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (currency_name)
);

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_date DATETIME(6),
    mod_date DATETIME(6),
    PRIMARY KEY (id)
);

CREATE TABLE user_currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    amount_after_exchange DECIMAL(38, 2) NOT NULL,
    amount_in_krw DECIMAL(38, 2) NOT NULL,
    exchange_request_status TINYINT NOT NULL CHECK (exchange_request_status BETWEEN 0 AND 1),
    created_date DATETIME(6),
    mod_date DATETIME(6),
    to_currency_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (to_currency_id) REFERENCES currency(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);