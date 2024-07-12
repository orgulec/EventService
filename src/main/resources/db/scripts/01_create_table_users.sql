CREATE TABLE IF NOT EXISTS users(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(128) NOT NULL,
    firstname           VARCHAR(128) NOT NULL,
    lastname            VARCHAR(128) NOT NULL,
    password            VARCHAR(255) NOT NULL,
    role                VARCHAR(128),
    email               VARCHAR(128) NOT NULL UNIQUE,
    age                 INT
);