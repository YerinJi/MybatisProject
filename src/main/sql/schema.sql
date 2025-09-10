CREATE TABLE student (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         student_no VARCHAR(20) NOT NULL UNIQUE,
                         name VARCHAR(50) NOT NULL,
                         email VARCHAR(100) UNIQUE,
                         dept VARCHAR(50),
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE course (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        code VARCHAR(20) NOT NULL UNIQUE,
                        title VARCHAR(100) NOT NULL,
                        professor VARCHAR(50),
                        credit TINYINT NOT NULL DEFAULT 3,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE enrollment (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            student_id BIGINT NOT NULL,
                            course_id BIGINT NOT NULL,
                            enrolled_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT uk_enroll UNIQUE (student_id, course_id),
                            CONSTRAINT fk_enroll_student FOREIGN KEY(student_id)
                                REFERENCES student(id) ON DELETE CASCADE,
                            CONSTRAINT fk_enroll_course FOREIGN KEY(course_id)
                                REFERENCES course(id) ON DELETE CASCADE
) ENGINE=InnoDB;

- - 추천 인덱스
CREATE INDEX idx_student_name ON student(name);
CREATE INDEX idx_course_title ON course(title);