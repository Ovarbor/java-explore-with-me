DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_name VARCHAR (100) NOT NULL UNIQUE,
    email VARCHAR (100) NOT NULL UNIQUE,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
    );

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE IF NOT EXISTS categories (
    category_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    category_name VARCHAR (100) NOT NULL UNIQUE,
    CONSTRAINT pk_category PRIMARY KEY (category_id)
);

DROP TABLE IF EXISTS events CASCADE;
CREATE TABLE IF NOT EXISTS events (
    event_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    event_annotation VARCHAR(2000) NOT NULL,
    category_id BIGINT NOT NULL REFERENCES categories(category_id),
    confirmed_request BIGINT DEFAULT 0,
    created_on TIMESTAMP,
    event_description VARCHAR(7000) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    initiator_id BIGINT NOT NULL REFERENCES users(user_id),
    location_lat FLOAT NOT NULL,
    location_lon FLOAT NOT NULL,
    paid BOOLEAN NOT NULL,
    participant_limit INTEGER NOT NULL,
    published_on TIMESTAMP,
    request_moderation BOOLEAN DEFAULT TRUE,
    event_state VARCHAR(10) NOT NULL,
    event_title VARCHAR(120) NOT NULL UNIQUE,
    event_views BIGINT DEFAULT 0,
    CONSTRAINT pk_event PRIMARY KEY (event_id)
);

DROP TABLE IF EXISTS requests CASCADE;
CREATE TABLE IF NOT EXISTS requests
(
    request_id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
    requester_id        BIGINT NOT NULL REFERENCES users (user_id) ,
    event_id            BIGINT NOT NULL REFERENCES events (event_id),
    created             TIMESTAMP NOT NULL,
    request_status      VARCHAR(10) NOT NULL,
    CONSTRAINT pk_request PRIMARY KEY (request_id)
);

DROP TABLE IF EXISTS compilations CASCADE;
CREATE TABLE IF NOT EXISTS compilations
(
    compilation_id      BIGINT GENERATED BY DEFAULT AS IDENTITY,
    pinned              BOOLEAN NOT NULL,
    compilation_title   VARCHAR(120) NOT NULL UNIQUE,
    CONSTRAINT pk_compilation PRIMARY KEY (compilation_id)
);

DROP TABLE IF EXISTS compilation_event CASCADE;
CREATE TABLE IF NOT EXISTS compilation_event
(
    compilation_id      BIGINT NOT NULL REFERENCES compilations (compilation_id) ON DELETE CASCADE,
    event_id            BIGINT NOT NULL REFERENCES events (event_id) ON DELETE CASCADE
);
<<<<<<< HEAD

DROP TABLE IF EXISTS places CASCADE;
CREATE TABLE IF NOT EXISTS places
(
    place_id BIGINT GENERATED ALWAYS AS IDENTITY,
    place_name VARCHAR (250) NOT NULL,
    lat FLOAT NOT NULL,
    lon FLOAT NOT NULL,
    radius FLOAT NOT NULL,
    CONSTRAINT pk_place PRIMARY KEY (place_id)
);
=======
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
