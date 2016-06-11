CREATE TABLE books
(
  id bigint NOT NULL,
  price double precision,
  title text,
  pages integer,
  isbn character varying(255),
  rating double precision,
  review integer,
  "desc" text,
  issue_date character varying(255),
  category character varying(255),
  subcategory character varying(255),
  author character varying(255),
  CONSTRAINT books_primary_id PRIMARY KEY (id)
);