DROP TABLE IF EXISTS registration;

CREATE TABLE registration (
first_name VARCHAR(255),
last_name VARCHAR(255),
email VARCHAR(255) NOT NULL,
password VARCHAR(255),
address VARCHAR(255)
);

insert into registration (first_name, last_name, email, password, address) VALUES ('first', 'last', 'test@email.com', 'test', 'address');
commit;