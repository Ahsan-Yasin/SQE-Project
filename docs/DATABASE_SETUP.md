MySQL example schema (for demo purposes)

1) Create database and products table

```sql
CREATE DATABASE demo;
USE demo;
CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

INSERT INTO products (name) VALUES
('Sauce Labs Backpack'),
('Sauce Labs Bike Light'),
('Sauce Labs Bolt T-Shirt');
```

2) Update tests to point to jdbc:mysql://localhost:3306/demo with correct user/password

This project includes a `DBConnector` that will attempt to read product names from a `products` table.
