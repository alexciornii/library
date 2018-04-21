CREATE TABLE IF NOT EXISTS account (
 id bigserial,
 account_name varchar(255) NOT NULL,
 password varchar(255) NOT NULL,
 enabled bool DEFAULT true
);

-- Table: authors

CREATE TABLE IF NOT EXISTS authors
(
 id bigserial,
 name text NOT NULL,
 date_of_birdth varchar(255),
 first_name varchar(255),
 last_name varchar(255),
 phone varchar(255),
 CONSTRAINT authors_pkey PRIMARY KEY (id)
);

-- Table: books

CREATE TABLE IF NOT EXISTS books
(
 id bigserial,
 name text NOT NULL,
 author_id bigserial NOT NULL,
 CONSTRAINT books_pkey PRIMARY KEY (id),
 CONSTRAINT authors_skey FOREIGN KEY (author_id)
     REFERENCES public.authors (id) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: clients

CREATE TABLE IF NOT EXISTS clients
(
 id bigint,
 email character varying(255),
 first_name character varying(255),
 last_name character varying(255),
 phone character varying(255),
 CONSTRAINT clients_pkey PRIMARY KEY (id)
);

-- Table: publishers

CREATE TABLE IF NOT EXISTS publishers
(
 id bigint,
 name text NOT NULL,
 address character varying(255),
 phone character varying(255),
 CONSTRAINT publishers_pkey PRIMARY KEY (id)
);