-- Database: jsp-training

-- DROP DATABASE IF EXISTS "jsp-training";

CREATE DATABASE "jsp-training"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


-- Table: public.users

-- DROP TABLE IF EXISTS public.users;
CREATE SEQUENCE IF NOT EXISTS public.users_id_seq;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'),
    login character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT login_unique UNIQUE (login)
)

TABLESPACE pg_default;
ALTER SEQUENCE public.users_id_seq
OWNED BY public.users.id;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
    
    
    
INSERT INTO public.users(
	login, password)
	VALUES ( 'admin', 'admin');