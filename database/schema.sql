-- ============================================================
--  AI-Based Pest Detection and Crop Protection System
--  MySQL schema + sample data
--  Run this file once:  mysql -u root -p < schema.sql
-- ============================================================
DROP DATABASE IF EXISTS pestdb;
CREATE DATABASE pestdb CHARACTER SET utf8mb4;
USE pestdb;

-- ---------- Farmers (app users) ----------
CREATE TABLE farmer (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(120) NOT NULL UNIQUE,
    password   VARCHAR(64)  NOT NULL,          -- SHA-256 hash (64 hex chars)
    phone      VARCHAR(15),
    address    VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ---------- Admins ----------
CREATE TABLE admin (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL
);

-- ---------- Pest knowledge base ----------
CREATE TABLE pest (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    pest_name  VARCHAR(100) NOT NULL UNIQUE,   -- MUST match a Teachable Machine class label
    crop_name  VARCHAR(100),
    symptoms   TEXT,
    causes     TEXT,
    treatment  TEXT,
    prevention TEXT
);

-- ---------- Every detection is logged here ----------
CREATE TABLE detection_history (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id   INT NOT NULL,
    pest_id     INT,                            -- NULL if pest not in knowledge base
    pest_name   VARCHAR(100) NOT NULL,
    confidence  DECIMAL(5,2) NOT NULL,          -- e.g. 92.35 (percent)
    image_path  VARCHAR(255),
    detected_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_dh_farmer FOREIGN KEY (farmer_id) REFERENCES farmer(id) ON DELETE CASCADE,
    CONSTRAINT fk_dh_pest   FOREIGN KEY (pest_id)   REFERENCES pest(id)   ON DELETE SET NULL
);

-- ============================================================
--  SAMPLE DATA
-- ============================================================
-- Admin login  =>  username: admin   password: admin123
INSERT INTO admin (username, password) VALUES ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

-- Farmer login =>  email: ravi@farm.com   password: farmer123
INSERT INTO farmer (name, email, password, phone, address)
VALUES ('Ravi Kumar', 'ravi@farm.com', '26c07fc7be1668f8ea7e3801d4ffdbf33de487a593a69028936ec49f2c89f6ab', '9876543210', 'Nuzvid, Andhra Pradesh');

-- Pest knowledge base. The pest_name values below should match the class
-- labels you train in Google Teachable Machine.
INSERT INTO pest (pest_name, crop_name, symptoms, causes, treatment, prevention) VALUES
('Aphids', 'Tomato',
 'Curled, yellowing leaves and sticky honeydew on the underside.',
 'Sap-sucking aphid colonies multiplying in warm, dry weather.',
 'Spray neem oil or insecticidal soap; introduce ladybird beetles.',
 'Inspect weekly, avoid excess nitrogen fertiliser, remove weeds.'),
('Leaf Blight', 'Rice',
 'Brown lens-shaped lesions with grey centres spreading across the leaf.',
 'Fungal infection (Bipolaris) favoured by high humidity.',
 'Apply recommended fungicide; remove and burn infected debris.',
 'Use resistant varieties, ensure field drainage, rotate crops.'),
('Healthy Leaf', 'Any',
 'No visible damage. Leaf colour and shape are normal.',
 'No pest detected.',
 'No treatment needed.',
 'Continue regular monitoring and good field hygiene.');
