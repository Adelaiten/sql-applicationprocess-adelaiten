SELECT first_name, last_name FROM mentors;
SELECT nick_name FROM mentors WHERE city = '''Miskolc''';
SELECT concat(first_name, ' ', last_name) AS full_name, phone_number FROM applicants WHERE first_name = '''Carol''';
SELECT concat(first_name, ' ', last_name) AS full_name, phone_number FROM applicants WHERE email like '''%@adipiscingenimmi.edu''';
