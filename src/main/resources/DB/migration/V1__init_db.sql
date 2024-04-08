CREATE TABLE worker (
	id serial PRIMARY KEY,
	NAME varchar (1000) NOT NULL CHECK (LENGTH(NAME)>=2),
	BIRTHDAY date NOT NULL CHECK (BIRTHDAY > '1900-01-01'),
	LEVEL varchar (50) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
	salary int CHECK (salary >= 100 AND salary <= 100000)
);


CREATE TABLE CLIENT(
	id serial PRIMARY KEY,
	name varchar (1000) NOT NULL CHECK (LENGTH (NAME) >= 2)
);

CREATE TABLE project(
	id serial PRIMARY KEY,
	client_id int,
	START_DATE date,
	FINISH_DATE date,
	FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)
);

CREATE TABLE project_worker(
	PROJECT_ID int,
	WORKER_ID int,
	PRIMARY KEY (PROJECT_ID, WORKER_ID),
	FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
	FOREIGN KEY (WORKER_ID) REFERENCES worker (ID)
);