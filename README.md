# back_order_management

This is a back-end test assignment built with Java Spring.

## Database Structure
In Database folder, you can find the database model.
The project uses a PostgreSQL database for data storage and management.

Each Order has a submission date and consists OrderLines and is related to a Customer. 
Each OrderLine consists of Product and quantity.

## API
In this assignment, I am creating the following API services:
- Create Customer
- Create Product
- Create Order
- Search all Orders by date
- Search Orders by Product
- Search Orders by Customer
- Change quantity of Products in an OrderLine