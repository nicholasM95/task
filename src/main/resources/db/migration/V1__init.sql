CREATE TABLE user_profile
(
    id        CHAR(36)               NOT NULL,
    user_name CHARACTER VARYING(255) NOT NULL,
    banned    BIT                    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE todo
(
    id      CHAR(36)               NOT NULL,
    user_id CHAR(36)               NOT NULL,
    task    CHARACTER VARYING(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user_profile(id, user_name, banned)
VALUES ('bdbace41-9566-4a69-8d54-d388a191cab9', 'Kris', false);

INSERT INTO user_profile(id, user_name, banned)
VALUES ('032635ad-950a-408e-b7fc-f2f73a46e09d', 'Yves', false);

INSERT INTO todo(id, user_id, task)
VALUES ('fff8e263-d992-4b17-9e6f-f83968cb81e8', 'bdbace41-9566-4a69-8d54-d388a191cab9', 'My first task');

INSERT INTO todo(id, user_id, task)
VALUES ('edf0a283-2317-4039-a173-c76065f55c22', 'bdbace41-9566-4a69-8d54-d388a191cab9', 'My second task');

INSERT INTO todo(id, user_id, task)
VALUES ('dd42ab5d-4b82-4875-ae67-dbe51919abe7', '032635ad-950a-408e-b7fc-f2f73a46e09d', 'My first task');

INSERT INTO todo(id, user_id, task)
VALUES ('91d05c5a-e8b5-47da-9a6c-d8bc0697707e', '032635ad-950a-408e-b7fc-f2f73a46e09d', 'My second task');