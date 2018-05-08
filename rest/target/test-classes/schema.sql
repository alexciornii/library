-- Table: authors

CREATE TABLE IF NOT EXISTS authors
(
  id bigint AUTO_INCREMENT,
  first_name varchar(255),
  last_name varchar(255),
  phone varchar(255),
  CONSTRAINT authors_pkey PRIMARY KEY (id)
);

-- Table: books

CREATE TABLE IF NOT EXISTS books
(
  id bigint AUTO_INCREMENT,
  name text NOT NULL,
  author_id bigint NOT NULL,
  CONSTRAINT books_pkey PRIMARY KEY (id),
  CONSTRAINT authors_skey FOREIGN KEY (author_id)
      REFERENCES public.authors (id)
);

-- Table: clients

CREATE TABLE IF NOT EXISTS clients
(
  id bigint AUTO_INCREMENT,
  email character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  phone character varying(255),
  CONSTRAINT clients_pkey PRIMARY KEY (id)
);

-- Table: publishers

CREATE TABLE IF NOT EXISTS publishers
(
  id bigint AUTO_INCREMENT,
  name text NOT NULL,
  address character varying(255),
  phone character varying(255),
  CONSTRAINT publishers_pkey PRIMARY KEY (id)
);

-- Table: books_borrowed
CREATE TABLE IF NOT EXISTS books_borrowed
(
  id bigint AUTO_INCREMENT,
  client_id bigint,
  book_id bigint,
  date_start timestamp,
  CONSTRAINT books_borrowed_pkey PRIMARY KEY (id),
  CONSTRAINT book_id_fkey FOREIGN KEY (book_id)
  REFERENCES books (id),
  CONSTRAINT client_id_fkey FOREIGN KEY (client_id)
  REFERENCES clients (id)
);