CREATE TABLE IF NOT EXISTS subscriptions
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    subscriber_id           BIGINT,
    event_id                BIGINT,
    FOREIGN KEY (subscriber_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);