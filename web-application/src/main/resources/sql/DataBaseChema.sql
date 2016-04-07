CREATE KEYSPACE LinkClick WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 2 };

USE LinkClick;

CREATE TABLE links (
  shortUrlId text PRIMARY KEY,
  originalUrl text
);

CREATE TABLE devices (
  date bigint PRIMARY KEY,
  shortUrlId text,
  ip text,
  os_name text,
  os_platform text
);
