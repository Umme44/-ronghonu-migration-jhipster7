CREATE TABLE certificates (
                              id BIGINT PRIMARY KEY,
                              certificate_image VARCHAR(255),
                              description TEXT,
                              materials_learned VARCHAR(255), -- Enum stored as string
                              enrollment_date DATE,
                              completion_date DATE,
                              expiration_date DATE,
                              is_expired BOOLEAN
);

ALTER TABLE certificates ADD CONSTRAINT chk_materials_learned
CHECK (materials_learned IN ('JAVA', 'C_PLUS_PLUS', 'SQL'));
