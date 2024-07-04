CREATE TABLE IF NOT EXISTS comments
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    rating              INT NOT NULL DEFAULT 0,
    message             TEXT NOT NULL,
    date                TIMESTAMP NOT NULL,
    author_id           BIGINT,
    event_id            BIGINT,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);