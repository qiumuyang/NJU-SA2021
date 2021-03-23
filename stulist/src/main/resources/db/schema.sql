DROP TABLE students IF EXISTS;

CREATE TABLE students (
    id          VARCHAR(80) NOT NULL,
    name        VARCHAR(50) NOT NULL,
    sex         VARCHAR(10),
    birthplace  VARCHAR(20),
    birthdate   VARCHAR(20),
    department  VARCHAR(20)
);
CREATE INDEX student_id ON students (id);