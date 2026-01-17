# Warehouse Inventory Control System

## Description
This project is a simple Warehouse Inventory Control System developed as Assignment 3.
It uses Java and PostgreSQL to manage products and their quantities in a warehouse.

## Subject Area
Warehouse Inventory Control System

## Technologies
- Java
- PostgreSQL
- JDBC
- IntelliJ IDEA
- Maven

## Database Structure
The project contains two tables:
- Product (product_id, name, price)
- Inventory (inventory_id, product_id, quantity, last_updated)

A one-to-many relationship is implemented using a foreign key.

## Features
- Database connection using JDBC
- Create (INSERT) records
- Read (SELECT) records
- Update (UPDATE) records
- Delete (DELETE) records

## How to Run
1. Create a PostgreSQL database (e.g. `warehouse_db`)
2. Execute SQL scripts to create tables
3. Configure database credentials in `Main.java`
4. Run the project in IntelliJ IDEA

## Assignment Requirements
✔ 1–2 tables created in PostgreSQL  
✔ Entity structure followed  
✔ Simple database connection implemented  
✔ CRUD operations completed  
✔ Project uploaded to GitHub

## Author
Meirambek Samenkhan
