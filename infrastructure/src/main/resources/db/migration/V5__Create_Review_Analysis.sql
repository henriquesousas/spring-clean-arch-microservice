  CREATE TABLE reviews_analysis(
    id CHAR(32) NOT NULL PRIMARY KEY,
    moderator_id CHAR(32) ,
    user_id CHAR(32) NOT NULL,
    review_id CHAR(32) NOT NULL,
    type VARCHAR(50) NOT NULL,
    reason VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    started_at DATETIME(6),
    finished_at DATETIME(6)
  );
