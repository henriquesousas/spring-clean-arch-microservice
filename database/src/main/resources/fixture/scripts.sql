SELECT * FROM products;
SELECT * FROM tags;
SELECT * FROM product_tags;
SELECT * FROM brands;
SELECT * FROM categories;
SELECT * FROM subcategories;
SELECT * FROM subcategories_categories;
SELECT * FROM photos;

INSERT INTO brands (id, name) VALUES ('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6', 'opinai');

INSERT INTO categories
(id, name, active, created_at, updated_at, deleted_at)
VALUES('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p7', 'Eletrônicos', 1, CURRENT_DATE(), CURRENT_DATE(), null);

INSERT INTO subcategories
(id, name, active, created_at, updated_at, deleted_at)
VALUES('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o588', 'Smartphones',  1, CURRENT_DATE(), CURRENT_DATE(), null);

INSERT INTO subcategories
(id, name,  active, created_at, updated_at, deleted_at)
VALUES('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o123', 'Fones', 1, CURRENT_DATE, CURRENT_DATE, null);

INSERT INTO subcategories_categories
(subcategories_id,category_id)
VALUES('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o123', 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p7');

INSERT INTO subcategories_categories
(subcategories_id,category_id)
VALUES('a1b2c3d4e5f6g7h8i9j0k1l2m3n4o588', 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p7');

INSERT INTO tags (id, name) VALUES
('00112233445566778899aabbccddeeff', 'Novo'),
('11223344556677889900aabbccddeeff', 'Promoção'),
('22334455667788990011aabbccddeeff', 'Mais Vendido'),
('33445566778899001122aabbccddeeff', 'Edição Limitada'),
('44556677889900112233aabbccddeeff', 'Frete Grátis'),
('55667788990011223344aabbccddeeff', 'Sustentável'),
('66778899001122334455aabbccddeeff', 'Exclusivo Online'),
('77889900112233445566aabbccddeeff', 'Popular');


INSERT INTO services.products
(id,
name,
description,
model,
brand_id,
category_id,
subcategory_id,
color,
site,
active,
created_at,
updated_at,
deleted_at
)
VALUES(
'b6efbb8d87654b5982269850b1ba06a4',
'Apple iPhone 15 Plus (256 GB) — Azul',
'A DYNAMIC ISLAND CHEGA AO IPHONE 15 — A Dynamic Island mostra alertas ',
'Apple',
'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6',
'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p7',
'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o123',
'Azul',
null,
1,
CURRENT_DATE,
CURRENT_DATE,
null);

INSERT INTO product_tags (product_id, tag_id)
VALUES('b6efbb8d87654b5982269850b1ba06a4', '00112233445566778899aabbccddeeff');

INSERT INTO product_tags (product_id, tag_id)
VALUES('b6efbb8d87654b5982269850b1ba06a4', '11223344556677889900aabbccddeeff');


INSERT INTO photos (id, product_id, url, alt_text, is_main, sort_order, created_at, updated_at)
VALUES(
	'33efbb8d87654b5982269850b1ba06a5',
	'b6efbb8d87654b5982269850b1ba06a4',
	'minha url',
	'imagem do produto',
	1,
	0,
	CURRENT_DATE(),
	CURRENT_DATE()
);
