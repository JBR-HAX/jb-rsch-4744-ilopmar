CREATE TABLE robot_movement(
    id UUID NOT NULL PRIMARY KEY,
    movements jsonb,
    locations jsonb
);
