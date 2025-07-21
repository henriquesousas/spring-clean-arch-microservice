CREATE TABLE photos (
    id CHAR(32) NOT NULL PRIMARY KEY,
    product_id CHAR(32) NOT NULL,
    url TEXT NOT NULL,
    alt_text VARCHAR(255),
    is_main BOOLEAN DEFAULT FALSE,         -- se é a principal
    sort_order INT DEFAULT 0,              -- ordem de exibição
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6)  NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
