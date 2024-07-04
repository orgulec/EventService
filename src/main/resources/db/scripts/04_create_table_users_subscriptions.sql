CREATE TABLE IF NOT EXISTS users_subscriptions
(
    subscribers_id              BIGINT,
    subscriptions_id            BIGINT,
    FOREIGN KEY (subscribers_id) REFERENCES users (id),
    FOREIGN KEY (subscriptions_id) REFERENCES events (id)
);