CREATE TABLE product_tags (
    product_id CHAR(32) NOT NULL,
    tag_id CHAR(32) NOT NULL,

    PRIMARY KEY (product_id, tag_id),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);