CREATE TABLE IF NOT EXISTS events
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    title               VARCHAR(128) NOT NULL,
    description         TEXT NOT NULL,
    country             VARCHAR(128) NOT NULL,
    city                VARCHAR(255) NOT NULL,
    street              VARCHAR(255),
    number              VARCHAR(128),
    date                TIMESTAMP NOT NULL,
    cost                DOUBLE,
    active              BOOL,
    owner_id            BIGINT,
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

