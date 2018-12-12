CREATE TABLE applicants
(
  id SERIAL NOT NULL PRIMARY KEY,
  first_name text,
  last_name text,
  phone_number text,
  email text,
  application_code integer
);
ALTER TABLE public.applicants
  OWNER TO karol;