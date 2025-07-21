  CREATE TABLE subcategories(
    id VARCHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6)  NOT NULL,
    deleted_at DATETIME(6) NULL
  );

    CREATE TABLE subcategories_categories(
      subcategories_id VARCHAR(32) NOT NULL,
      category_id VARCHAR(32) NOT NULL,
      CONSTRAINT idx_genre_category UNIQUE (subcategories_id, category_id),
      CONSTRAINT fk_subcategories_id  FOREIGN KEY (subcategories_id) REFERENCES subcategories(id) ON DELETE CASCADE,
      CONSTRAINT fk_category_id1  FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
    );