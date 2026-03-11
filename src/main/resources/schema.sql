DROP TABLE IF EXISTS points_record;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS member_benefit;
DROP TABLE IF EXISTS member_level;

CREATE TABLE member_level (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    level_name VARCHAR(50) NOT NULL,
    level_code VARCHAR(30) NOT NULL UNIQUE,
    level_sort INT NOT NULL,
    min_points INT NOT NULL DEFAULT 0,
    max_points INT,
    discount DECIMAL(3,2) DEFAULT 1.00,
    point_multiplier DECIMAL(3,1) DEFAULT 1.0,
    icon_url VARCHAR(255),
    description VARCHAR(500),
    status INT DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_benefit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    level_id BIGINT NOT NULL,
    benefit_name VARCHAR(100) NOT NULL,
    benefit_type VARCHAR(30) NOT NULL,
    benefit_value VARCHAR(50),
    description VARCHAR(500),
    status INT DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_benefit_level FOREIGN KEY (level_id) REFERENCES member_level(id)
);

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    nickname VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    total_points INT DEFAULT 0,
    available_points INT DEFAULT 0,
    level_id BIGINT,
    register_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_member_level FOREIGN KEY (level_id) REFERENCES member_level(id)
);

CREATE TABLE points_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    points INT NOT NULL,
    type VARCHAR(30) NOT NULL,
    description VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_record_member FOREIGN KEY (member_id) REFERENCES member(id)
);
