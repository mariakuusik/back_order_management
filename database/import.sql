-- Insert data into the 'customer' table
INSERT INTO customer (registration_code, full_name, email, telephone)
VALUES
    (12345, 'John Doe', 'john@example.com', 1234567890),
    (67890, 'Jane Smith', 'jane@example.com', 9876543210);

-- Insert data into the 'order' table
INSERT INTO "order" (submission_date)
VALUES
    ('2023-10-01'),
    ('2023-10-02');

-- Insert data into the 'product' table
INSERT INTO product (name, sku_code, unit_price)
VALUES
    ('Product A', 1001, 10.99),
    ('Product B', 1002, 15.99);

-- Insert data into the 'order_line' table
INSERT INTO order_line (product_id, quantity)
VALUES
    (1, 5),  -- Assuming 'Product A' has an 'id' of 1
    (2, 3);  -- Assuming 'Product B' has an 'id' of 2

-- Insert data into the 'customer_order' table
INSERT INTO customer_order (customer_id, order_id)
VALUES
    (1, 1),  -- Assuming 'John Doe' has a 'customer_id' of 1 and 'Order 1' has an 'id' of 1
    (2, 2);  -- Assuming 'Jane Smith' has a 'customer_id' of 2 and 'Order 2' has an 'id' of 2

-- Insert data into the 'order_order_line' table
INSERT INTO order_order_line (order_id, order_line_id)
VALUES
    (1, 1),  -- Assuming 'Order 1' has an 'id' of 1 and 'Order Line 1' has an 'id' of 1
    (2, 2);  -- Assuming 'Order 2' has an 'id' of 2 and 'Order Line 2' has an 'id' of 2;
