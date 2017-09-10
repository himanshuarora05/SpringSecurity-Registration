INSERT INTO role(id,name) VALUES (1,'ROLE_ADMIN');
INSERT INTO role(id,name) VALUES (3,'ROLE_USER');

INSERT into user(id, username, password) VALUES(1, 'admin', '$2a$10$SjAMdfnJ9ml9tnQ4geckjObe/BLYMB9VxycU8Qmn4Ts5GeTHSfMw2');

INSERT into user_role(user_id, role_id) values (1,1);
