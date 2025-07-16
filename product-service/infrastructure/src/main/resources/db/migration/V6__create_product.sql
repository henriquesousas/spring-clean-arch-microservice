  CREATE TABLE products (
    id CHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    model VARCHAR(100),
    brand_id VARCHAR(32) NOT NULL,
    category_id VARCHAR(32) NOT NULL,
    subcategory_id VARCHAR(32) NOT NULL,
    color VARCHAR(50),
    site TEXT NULL,
    active  BOOLEAN NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6)  NOT NULL,
    deleted_at DATETIME(6) NULL,

    CONSTRAINT fk_brand_id  FOREIGN KEY (brand_id) REFERENCES brands ON DELETE CASCADE,
    CONSTRAINT fk_category_id  FOREIGN KEY (category_id) REFERENCES categories ON DELETE CASCADE,
    CONSTRAINT fk_subcategory_id  FOREIGN KEY (subcategory_id) REFERENCES subcategories ON DELETE CASCADE
  );