CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            active BOOLEAN NOT NULL
);
INSERT INTO categories (name, active) VALUES
                                          ('Electronics', true),
                                          ('Books', true),
                                          ('Clothing', true),
                                          ('Home & Kitchen', true),
                                          ('Toys', true),
                                          ('Sports', true),
                                          ('Automotive', true),
                                          ('Health & Beauty', true),
                                          ('Office Supplies', true),
                                          ('Garden & Outdoors', true);
