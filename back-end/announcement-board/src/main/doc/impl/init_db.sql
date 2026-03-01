 CREATE DATABASE IF NOT EXISTS board_db
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_unicode_ci;

 USE board_db;
 
 
  CREATE TABLE users (
     id            BIGINT       NOT NULL AUTO_INCREMENT,
     username      VARCHAR(50)  NOT NULL,
     password_hash VARCHAR(255) NOT NULL,
     display_name  VARCHAR(50)  NOT NULL,
     created_at    DATETIME     NOT NULL,

     PRIMARY KEY (id),
     UNIQUE KEY uq_users_username (username)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 
 
  CREATE TABLE announcements (
     id                       BIGINT       NOT NULL AUTO_INCREMENT,
     title                    VARCHAR(200) NOT NULL,
     publisher                VARCHAR(50)  NOT NULL,
     publish_date             DATE         NOT NULL,
     expiry_date              DATE         NOT NULL,
     content                  TEXT         NOT NULL,
     attachment_original_name VARCHAR(255),
     attachment_stored_name   VARCHAR(255),
     attachment_path          VARCHAR(255),
     attachment_size          VARCHAR(255),
     attachment_mime_type     VARCHAR(100),
     created_at               DATETIME     NOT NULL,
     updated_at               DATETIME     NOT NULL,

     PRIMARY KEY (id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;