CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(500) NOT NULL,
    description MEDIUMTEXT NULL,
    price FLOAT NOT NULL,
    stock INT NOT NULL,
    sku VARCHAR(100) NOT NULL,
    image VARCHAR(1000) NULL,
    category_id INT NOT NULL,
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories (id)
);
INSERT INTO products (name, description, price, stock, sku, image, category_id) VALUES
                                                                                    ('Wireless Headphones', 'Bluetooth over-ear headphones with noise cancellation', 89.99, 50, 'WH-2023', 'https://example.com/images/wh-2023.jpg', 1),
                                                                                    ('Java Programming Book', 'Comprehensive guide to Java programming for beginners', 29.99, 120, 'JPB-101', 'https://example.com/images/java-book.jpg', 2),
                                                                                    ('Men’s Casual Shirt', 'Cotton shirt available in multiple colors and sizes', 19.99, 80, 'CS-045', 'https://example.com/images/mens-shirt.jpg', 3),
                                                                                    ('Stainless Steel Blender', 'Multi-purpose kitchen blender with 6-speed settings', 59.99, 30, 'BLD-500', 'https://example.com/images/blender.jpg', 4),
                                                                                    ('Action Figure Set', 'Set of 5 collectible action figures from popular series', 39.99, 200, 'AF-320', 'https://example.com/images/action-figures.jpg', 5),
                                                                                    ('Mountain Bike Helmet', 'Lightweight helmet with shock absorption for biking', 49.99, 40, 'MBH-350', 'https://example.com/images/bike-helmet.jpg', 6),
                                                                                    ('Car Vacuum Cleaner', 'Portable handheld vacuum cleaner for cars and trucks', 24.99, 60, 'CV-240', 'https://example.com/images/car-vacuum.jpg', 7),
                                                                                    ('Organic Skincare Set', 'Includes cleanser, toner, and moisturizer', 74.99, 25, 'SK-101', 'https://example.com/images/skincare-set.jpg', 8),
                                                                                    ('Desk Organizer', '5-compartment desk organizer for office supplies', 15.99, 150, 'DO-102', 'https://example.com/images/desk-organizer.jpg', 9),
                                                                                    ('Garden Tool Set', 'Includes spade, rake, and trowel for gardening', 34.99, 100, 'GT-203', 'https://example.com/images/garden-tools.jpg', 10);
