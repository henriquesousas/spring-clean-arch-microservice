CREATE TABLE categories (
    id CHAR(36) not null primary key,
    name VARCHAR(255) not null,
    description VARCHAR(255) not null,
    active BOOLEAN not null default true,
    created_at DATETIME(6) not null,
    updated_at DATETIME(6) not null,
    deleted_at DATETIME(6) null
);