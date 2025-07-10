  CREATE TABLE reviews(
    id CHAR(32) NOT NULL PRIMARY KEY,
    user_id CHAR(32) NOT NULL,
    user_name VARCHAR(255) NULL,
    photo_url VARCHAR(255) NULL,
    product_id CHAR(32) NOT NULL,
    title VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    pros VARCHAR(255) NULL,
    cons VARCHAR(255)  NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    ra_overall INT NOT NULL,
    store VARCHAR(255)  NULL,
    recommend  BOOLEAN NULL,
    is_verified BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6)  NOT NULL,
    deleted_at DATETIME(6) NULL
--    CONSTRAINT fk_review_user  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
  );
