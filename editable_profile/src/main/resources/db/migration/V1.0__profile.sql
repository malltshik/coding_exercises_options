CREATE TABLE profile (
  id BIGINT NOT NULL AUTO_INCREMENT,
  display_name VARCHAR(256) NOT NULL,
  real_name VARCHAR(256) NOT NULL,
  picture BLOB,
  birthday DATE NOT NULL,
  gender VARCHAR(128) NOT NULL,
  ethnicity VARCHAR(128),
  religion VARCHAR(128),
  height INT,
  figure VARCHAR(128),
  marital_status VARCHAR(128) NOT NULL,
  occupation VARCHAR(256),
  about_me VARCHAR(5000),
  location VARCHAR(128) NOT NULL
);