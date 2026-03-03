SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

CREATE DATABASE IF NOT EXISTS board_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE board_db;

-- users 
CREATE TABLE IF NOT EXISTS users (
  id            BIGINT       AUTO_INCREMENT PRIMARY KEY,
  username      VARCHAR(50)  NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  display_name  VARCHAR(50)  NOT NULL,
  created_at    DATETIME     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- announcements 
CREATE TABLE IF NOT EXISTS announcements (
  id                       BIGINT       AUTO_INCREMENT PRIMARY KEY,
  title                    VARCHAR(200) NOT NULL,
  publisher                VARCHAR(50)  NOT NULL,
  publish_date             DATE         NOT NULL,
  expiry_date              DATE         NOT NULL,
  content                  TEXT         NOT NULL,
  attachment_original_name VARCHAR(255) NULL,
  attachment_stored_name   VARCHAR(255) NULL,
  attachment_path          VARCHAR(500) NULL,
  attachment_size          VARCHAR(255) NULL,
  attachment_mime_type     VARCHAR(100) NULL,
  created_at               DATETIME     NOT NULL,
  updated_at               DATETIME     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 初始管理員帳號（admin / 123456）
INSERT INTO users (username, password_hash, display_name, created_at)
VALUES (
  'admin',
  '$2a$10$viOlnU9WQg0QLfGcL2COY.hUQ8fQr5KU1VtIfNrm.lbOURZ7KkXkS',
  '系統管理員',
  NOW()
);