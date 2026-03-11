DROP TABLE IF EXISTS operation_log;
DROP TABLE IF EXISTS benefit_record;
DROP TABLE IF EXISTS family_member;
DROP TABLE IF EXISTS family_group;
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
    card_image_url VARCHAR(255),
    privilege_overview_url VARCHAR(255),
    benefit_overview_url VARCHAR(255),
    benefit_intro_url_1 VARCHAR(255),
    benefit_intro_url_2 VARCHAR(255),
    locked_image_url VARCHAR(255),
    fallback_image_url VARCHAR(255),
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
    shelf_status VARCHAR(20) DEFAULT 'ON_SHELF',
    goods_id VARCHAR(50),
    school_id VARCHAR(50),
    department VARCHAR(100),
    claim_type VARCHAR(20) DEFAULT 'MANUAL',
    benefit_category VARCHAR(30),
    use_url VARCHAR(500),
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

CREATE TABLE family_group (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    family_name VARCHAR(100) NOT NULL,
    owner_user_id BIGINT NOT NULL,
    owner_name VARCHAR(50),
    owner_phone VARCHAR(20),
    max_members INT DEFAULT 6,
    current_members INT DEFAULT 1,
    status INT DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE family_member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    family_id BIGINT NOT NULL,
    user_id BIGINT,
    student_code VARCHAR(50),
    member_name VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL,
    phone VARCHAR(20),
    school_id VARCHAR(50),
    school_name VARCHAR(100),
    department VARCHAR(100),
    grade VARCHAR(20),
    status INT DEFAULT 1,
    join_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_fm_family FOREIGN KEY (family_id) REFERENCES family_group(id)
);

CREATE TABLE benefit_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    benefit_id BIGINT NOT NULL,
    benefit_name VARCHAR(100),
    member_id BIGINT,
    family_id BIGINT,
    student_code VARCHAR(50),
    benefit_category VARCHAR(30),
    goods_id VARCHAR(50),
    goods_name VARCHAR(200),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    claim_time TIMESTAMP,
    expire_time TIMESTAMP,
    use_time TIMESTAMP,
    remark VARCHAR(500),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE operation_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operator VARCHAR(50),
    operation_type VARCHAR(30) NOT NULL,
    target_type VARCHAR(50),
    target_id BIGINT,
    content VARCHAR(1000),
    ip VARCHAR(50),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
