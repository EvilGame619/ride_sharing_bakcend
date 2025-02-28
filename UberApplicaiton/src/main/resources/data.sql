INSERT INTO app_user (email, name, password)
VALUES
('ashish1@gmail.com', 'Ashish Singh', 'password1'),
('ravi.sharma@gmail.com', 'Ravi Sharma', 'password2'),
('priya.verma@gmail.com', 'Priya Verma', 'password3'),
('amit.yadav@gmail.com', 'Amit Yadav', 'password4'),
('neha.mehta@gmail.com', 'Neha Mehta', 'password5'),
('saurabh.gupta@gmail.com', 'Saurabh Gupta', 'password6'),
('simran.kaur@gmail.com', 'Simran Kaur', 'password7'),
('ankur.pandey@gmail.com', 'Ankur Pandey', 'password8'),
('pooja.gupta@gmail.com', 'Pooja Gupta', 'password9'),
('rohit.reddy@gmail.com', 'Rohit Reddy', 'password10'),
('anjali.sharma@gmail.com', 'Anjali Sharma', 'password11'),
('vishal.kapoor@gmail.com', 'Vishal Kapoor', 'password12'),
('manish.kumar@gmail.com', 'Manish Kumar', 'password13'),
('divya.singh@gmail.com', 'Divya Singh', 'password14'),
('kiran.patil@gmail.com', 'Kiran Patil', 'password15'),
('sunil.jain@gmail.com', 'Sunil Jain', 'password16'),
('geeta.rawat@gmail.com', 'Geeta Rawat', 'password17'),
('mohit.bhatia@gmail.com', 'Mohit Bhatia', 'password18'),
('meena.joshi@gmail.com', 'Meena Joshi', 'password19'),
('tarun.soni@gmail.com', 'Tarun Soni', 'password20');



INSERT INTO user_roles (user_userid, roles) VALUES
(1, 'RIDER'),
(2, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(4, 'RIDER'),
(4, 'DRIVER'),
(5, 'RIDER'),
(5, 'DRIVER'),
(6, 'RIDER'),
(7, 'RIDER'),
(7, 'DRIVER'),
(8, 'RIDER'),
(9, 'RIDER'),
(9, 'DRIVER'),
(10, 'RIDER'),
(11, 'RIDER'),
(11, 'DRIVER'),
(12, 'RIDER'),
(13, 'RIDER'),
(13, 'DRIVER'),
(14, 'RIDER'),
(15, 'RIDER'),
(15, 'DRIVER'),
(16, 'RIDER'),
(17, 'RIDER'),
(17, 'DRIVER'),
(18, 'RIDER'),
(19, 'RIDER'),
(19, 'DRIVER'),
(20, 'RIDER');


INSERT INTO rider ( user_id, rating) VALUES
(1,4.8);


INSERT INTO wallet ( user_userid, balance) VALUES
(1,1500),
(2,2000);


INSERT INTO driver ( user_id, rating, available, current_location) VALUES

( 2, 4.5, true, ST_GeomFromText('POINT(77.2205 28.7048)', 4326)),
( 4, 4.2, false, ST_GeomFromText('POINT(77.3221 28.7043)', 4326)),
( 6, 3.8, true, ST_GeomFromText('POINT(77.0369 28.7041)', 4326)),

( 7, 4.3, false, ST_GeomFromText('POINT(77.6366 28.6460)', 4326)),

( 9, 4.1, true, ST_GeomFromText('POINT(77.3774 28.6637)', 4326)),

( 11, 3.9, false, ST_GeomFromText('POINT(77.1695 28.7044)', 4326)),

( 13, 4.8, true, ST_GeomFromText('POINT(77.2141 28.6023)', 4326)),

( 15, 4.5, false, ST_GeomFromText('POINT(77.1292 28.6139)', 4326)),

( 17, 3.6, true, ST_GeomFromText('POINT(77.2560 28.7109)', 4326)),

( 19, 4.0, true, ST_GeomFromText('POINT(77.2434 28.7007)', 4326));


