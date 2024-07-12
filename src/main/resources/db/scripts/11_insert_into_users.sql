INSERT INTO users (firstname, lastname, username, password, role, email, age)
VALUES
    ('Maciej', 'Łomnicki', 'mdss', '$2a$12$87Cvlg3pcSwI2MIbWVzmru9ps9auaAdrWbDbIHU3rCbrQ4JLoRtgW', 'ADMIN', 'mdss@wp.pl', 40),  --password 12345 encoded MTIzNDU=
    ('Grzegoż', 'Brzęczyszczyk', 'grzybrzy', 'grzesiek1234', 'USER', 'grzbrz@wp.pl', 30),
    ('Jan', 'Kowalski', 'jakex', '$2a$12$M/.9mu.nDtDtm0W0UBMz5ux6qSBC94M6FC8DyBjI91ppqZQC4HbvK', 'USER', 'janko@wp.pl', 23),    --password kowal666
    ('Magda', 'Gesler', 'geslerowa', 'bestfood', 'USER','gessler1@gmail.com', 39),
    ('Maćko', 'Zbogdańca', 'jasko', '$2a$12$87Cvlg3pcSwI2MIbWVzmru9ps9auaAdrWbDbIHU3rCbrQ4JLoRtgW', 'USER', 'jasiekzb@gmail.com',44),
    ('Janek', 'Dzbanek', 'jandzban', 'dzbanjan', 'USER', 'janek@dzbanek.com', 27),
    ('Janusz', 'Szymanek', 'Eventim Sp.Z O.O.', 'eventim1', 'ADMIN', 'januszyman@eventim.com', 50)