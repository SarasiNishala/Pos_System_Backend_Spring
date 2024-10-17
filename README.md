<h1 align="center" id="title">POS System NIKE</h1>

# Manage Customers, Items, and Orders System

![Java](https://img.shields.io/badge/Java-11+-blue.svg)
![Spring](https://img.shields.io/badge/Spring-Framework-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-lightgrey.svg)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

A robust backend system for managing customers, items, and orders with **real-time inventory management** built using Java Spring framework. This system exposes REST APIs, handles database interactions using **Hibernate ORM**, and offers smooth exception handling with JSON-based API responses.

---

## 🚀 Features

- **Manage Customers**: Create, update, delete, and retrieve customer details.
- **Manage Items**: Add, update, delete, and retrieve item details.
- **Order Management**: Create new orders.
- **Real-time Inventory Management**: Tracks item inventory.
- **Exception Handling**: Ensures robust error management.
- **RESTful API**: JSON-based API responses for easy integration.

---

## 🛠️ Built With

- **Spring**: Java backend framework for scalable systems.
- **Hibernate**: ORM for simplifying database interactions.
- **Spring Data JPA**: Data repository for easy data access.
- **MySQL**: Relational database to store information.
- **Lombok**: Java library to eliminate boilerplate code.

---

## 📊 API Endpoints

### Customer Endpoints
- **GET** `/customer`: Retrieve all customers.
- **POST** `/customer`: Create a new customer.
- **PUT** `/customer`: Update an existing customer.
- **DELETE** `/customer/{id}`: Delete a customer by ID.

### Item Endpoints
- **GET** `/item`: Retrieve all items.
- **POST** `/item`: Create a new item.
- **PUT** `/item`: Update an existing item.
- **DELETE** `/item/{id}`: Delete an item by ID.

### Order Endpoints
- **POST** `/order`: Create a new order.

---

## 📚 API Documentation

Detailed API documentation with example requests and responses can be viewed [here](https://documenter.getpostman.com/view/36300739/2sA3s3GW2m).

---

## 🛠️ Getting Started

### Prerequisites
- Java 11+
- MySQL Server
- Maven

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/SarasiNishala/Pos_System_Backend_Spring.git
