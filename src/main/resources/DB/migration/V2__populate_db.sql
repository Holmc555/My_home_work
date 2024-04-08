INSERT INTO worker (NAME, BIRTHDAY, LEVEL, salary) VALUES
('Ivan Ivanov', '1990-01-01', 'Trainee', 500),
('Petro Petrov', '1985-02-02', 'Junior', 1500),
('Sidor Sidorov', '1980-03-03', 'Middle', 2500),
('Andriy Andriyenko', '1975-04-04', 'Senior', 3500),
('Oleh Olehov', '1992-05-05', 'Trainee', 700),
('Mykola Mykolayenko', '1987-06-06', 'Junior', 1700),
('Volodymyr Volodymyrov', '1982-07-07', 'Middle', 2700),
('Yuriy Yuriev', '1977-08-08', 'Senior', 3700),
('Vasyl Vasylev', '1993-09-09', 'Trainee', 800),
('Taras Tarasov', '1988-10-10', 'Junior', 1900);


INSERT INTO client (name) VALUES
('Oleksandr Holanov'),
('Artem Horbunov'),
('Viktoriia Hоrnyk'),
('Svitlana Zub'),
('Kateryna Kushnirenko');


INSERT INTO project (client_id, START_DATE, FINISH_DATE) VALUES
(1, '2023-01-01', '2023-02-01'),
(2, '2023-03-01', '2023-06-01'),
(3, '2023-07-01', '2023-12-01'),
(4, '2023-02-01', '2023-08-01'),
(5, '2023-09-01', '2024-06-01'),
(1, '2023-04-01', '2023-07-01'),
(2, '2023-08-01', '2024-01-01'),
(3, '2023-10-01', '2024-07-01'),
(4, '2023-05-01', '2023-12-01'),
(5, '2023-11-01', '2024-09-01');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 6),
(2, 7),
(3, 8),
(4, 9),
(5, 10),
(6, 1),
(7, 2),
(8, 3),
(9, 4),
(10, 5);
