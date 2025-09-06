# My Rent-a-Car

This is a **My Rent-a-Car** management system, a project developed as part of the **Sistemas de Bases de Dados** (Database Systems) course at the **Instituto Superior de Engenharia de Lisboa (ISEL)**.

---

## Project Description

The project is divided into two parts:

* **Part 1**: The objective was to consolidate knowledge on creating conceptual, logical, and physical models for relational databases and using Structured Query Language (**SQL**) to create the database.
* **Part 2**: The goal was to implement the requirements of a web application using **JavaServer Pages (JSP)** and **Java Database Connectivity (JDBC)** to interface with the database.

The system manages a car rental business with multiple parking locations near airports and urban areas.

---

## Key Features

The web application supports multiple user profiles, each with specific functionalities:

* **Administrator**: Manages client, driver, and vehicle data, and can export/import vehicle data in XML/JSON format.
* **Client**: Can make reservations for vehicles by selecting the type, pickup location, and rental period.
* **Employee**: Can identify the driver of a car on a specific date and evaluate a driver.
* **Driver**: Can manage their profile and evaluate the rental service.
* **Manager**: Can view vehicle history, including maintenance and evaluations, and generate reports, such as the profit ranking by vehicle brand.

---

## Technologies Used

* **Backend**: Java
* **Database**: MySQL
* **Frontend**: JavaServer Pages (JSP)
* **Database Connectivity**: JDBC

---

## Project Structure

The repository contains the following core files and their functionalities:

* `Admin.java`: Servlet for handling administrator actions.
* `AdminManager.java`: Manages administrator business logic.
* `Cliente.java`: Servlet for handling client actions.
* `ClienteManager.java`: Manages client business logic.
* `Condutor.java`: Servlet for handling driver actions.
* `CondutorManager.java`: Manages driver business logic.
* `Funcionario.java`: Servlet for handling employee actions.
* `FuncionarioManager.java`: Manages employee business logic.
* `Gerente.java`: Servlet for handling manager actions.
* `GerenteManager.java`: Manages manager business logic.
* `Connection.java`: Handles the database connection.
* `Configura.java`: Configuration for database connection.
* `Manipula.java`: Handles database interactions and metadata queries.
* `Parque.java`: Defines the `Parque` (Parking Lot) object.
* `tipoVeiculo.java`: Defines the `tipoVeiculo` (Vehicle Type) object.

---

## Authors

* Hugo Sousa
* Guilherme Vicente
* João Fonseca
