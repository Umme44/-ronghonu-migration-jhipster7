CREATE TABLE certificates (
                              id BIGINT PRIMARY KEY,
                              pin VARCHAR(255),
                              imageUrl VARCHAR(255),
                              description TEXT,
                              materialsLearned VARCHAR(255), -- Enum stored as string
                              enrollmentDate DATE,
                              completionDate DATE,
                              expirationDate DATE,
                              isExpired BOOLEAN
);

select * from certificates;

INSERT INTO certificates (
    id,
    pin,
    imageUrl,
    description,
    materialsLearned,
    enrollmentDate,
    completionDate,
    expirationDate,
    isExpired
) VALUES (
             3,  -- id (if using a sequence, this can be omitted)
             '123',  -- pin
             'http://example.com/image.jpg',  -- imageUrl
             'This is a certificate description.',  -- description
             'sql',  -- materialsLearned (replace with your actual enum value)
             '2024-01-01',  -- enrollmentDate (YYYY-MM-DD format)
             '2024-06-01',  -- completionDate (YYYY-MM-DD format)
             '2025-01-01',  -- expirationDate (YYYY-MM-DD format)
             FALSE  -- isExpired (use TRUE or FALSE)
         );

