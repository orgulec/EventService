CREATE TABLE IF NOT EXISTS users(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname            VARCHAR(128) NOT NULL,
    firstname           VARCHAR(128) NOT NULL,
    lastname            VARCHAR(128) NOT NULL,
    password            VARCHAR(255) NOT NULL,
    email               VARCHAR(128) NOT NULL UNIQUE,
    age                 INT
);