SELECT first_name, last_name FROM mentors;
SELECT nick_name FROM mentors WHERE city = '''Miskolc''';
SELECT concat(first_name, ' ', last_name) AS full_name, phone_number FROM applicants WHERE first_name = '''Carol''';
SELECT concat(first_name, ' ', last_name) AS full_name, phone_number FROM applicants WHERE email like '''%@adipiscingenimmi.edu''';
INSERT INTO applicants(first_name, last_name, phone_number, email, application_code) VALUES('Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com ', 54823);
SELECT * FROM applicants WHERE application_code = 54823;