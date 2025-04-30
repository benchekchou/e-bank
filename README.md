# Bank Management

## Description
This project is a banking management application developed in Java, using **Spring Data JPA** for data persistence and **Jakarta EE** for the entity layer. It allows managing bank accounts, their transactions, and the associated clients.

Additionally, the front-end of the project is built with **Angular** and can be found at [e-front repository](https://github.com/benchekchou/e-front.git).

## Main Features
- **Bank account management**: creation, consultation, modification, deletion.
- **Transaction tracking**: management of transactions related to each account.
- **Client association**: linking accounts to specific clients.
- **Account status and currency management**: tracking account statuses and their respective currencies.

## Technologies Used
- **Java 23**
- **Spring Data JPA**
- **Spring MVC**
- **Jakarta EE** (imports `jakarta.*`)
- **Lombok** to reduce boilerplate code
- **Maven** or **Gradle** for dependency management (depending on your project)
- **Angular** for the front-end

## Project Structure
- **entities**: Contains JPA entities, with the main entity centralizing the logic of a bank account: `BankAccount`.
- **repositories**: Repository interfaces for managing entities with Spring Data JPA.

## Prerequisites
Before you start, ensure you have the following installed on your machine:
- **JDK 23** or higher
- A compatible relational database (H2, MySQL, PostgreSQL, etc.)
- **Maven** or **Gradle** for dependency management
- **Node.js** and **Angular CLI** for running the front-end

## Installation and Running the Project

### 1. Clone the repositories
Clone both the back-end and front-end repositories:

```bash
git clone https://github.com/benchekchou/e-bank.git
cd gestion-de-bank
