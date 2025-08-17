# GreenCart Logistics Simulator using Spring Boot

🚚 **GreenCart Logistics Simulator** is a full-stack web application that helps manage **drivers, routes, and orders**, and run **delivery simulations** to analyze fuel cost, time penalties, and driver fatigue.  
It is built with **Spring Boot (Java, MySQL)** for the backend and **React (Vite)** for the frontend.

---

## 🌟 Features

- **Authentication & Authorization**
  - Secure login using Spring Security (session-based authentication).
  - Role-based access (Admin only).

- **Driver Management**
  - Add, edit, delete, and view drivers.
  - Track shift hours, past week hours, and last day hours.

- **Route Management**
  - Define routes with distance, traffic level, and base time.
  - Manage multiple routes for different delivery scenarios.

- **Order Management**
  - Create and manage delivery orders.
  - Assign routes and track scheduled delivery times.

- **Simulation Engine**
  - Run simulations by selecting drivers, routes, and start times.
  - Calculate:
    - Fuel costs (based on traffic and distance).
    - Delivery penalties for late arrivals.
    - Fatigue effects if drivers exceed max hours.
  - Store results in the database for review.

- **Reports**
  - View simulation results in the UI.
  - Data persisted in MySQL for analysis.

---

## 🛠 Tech Stack

### Backend
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- Maven

### Frontend
- React (Vite)

### Tools
- VS Code (Frontend)
- Eclips (Backend)
- MySQL Workbench
- GitHub for version control

---

## 📂 Project Structure

### Backend (`greencart-backend`)

### Frontend (greencart-frontend)

