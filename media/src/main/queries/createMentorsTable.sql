CREATE TABLE mentors
(
  id SERIAL NOT NULL PRIMARY KEY,
  first_name text,
  last_name text,
  nick_name text,
  phone_number text,
  email text,
  city text,
  favourite_number integer
);
ALTER TABLE public.mentors
  OWNER TO karol;