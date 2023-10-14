INSERT INTO customer (registration_code, full_name, email, telephone)
VALUES
    (12345, 'John Doe', 'john@example.com', 1234567890),
    (67890, 'Jane Smith', 'jane@example.com', 9876543210);

INSERT INTO "order" (submission_date)
VALUES
    ('2023-10-01'),
    ('2023-10-02');

INSERT INTO product (name, sku_code, unit_price)
VALUES
    ('Product A', 1001, 10.99),
    ('Product B', 1002, 15.99);


INSERT INTO order_line (product_id, quantity)
VALUES
    (1, 5),
    (2, 3);

INSERT INTO customer_order (customer_id, order_id)
VALUES
    (1, 1),
    (2, 2);

INSERT INTO order_order_line (order_id, order_line_id)
VALUES
    (1, 1),
    (2, 2);
