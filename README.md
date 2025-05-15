# 🚗 RideSharing Backend

This is a backend system for a ride-sharing platform, inspired by apps like Uber. It includes complete backend functionality for both **riders** and **drivers**, providing robust support for ride management, real-time geolocation features, secure authentication, and email notifications.

---

## 📌 Key Features

### 👤 User & Driver Modules
- **Rider and Driver Registration/Login**
- **Role-based access** using Spring Security
- **Separate flows** for riders and drivers
- Session and token-based authentication

### 🚕 Ride Management
- Riders can **book** a ride.
- Drivers can **accept** or **reject** rides.
- Riders can **cancel** requests.
- Ride status updates: `REQUESTED`, `ACCEPTED`, `REJECTED`, `CANCELLED`, `COMPLETED`.

### 🌐 Geospatial Functionality
- Uses **PostgreSQL + PostGIS** for storing and querying latitude/longitude data.
- Finds the **nearest available drivers** to the rider's location.
- Efficient distance-based filtering for real-time matching.

### ✉️ Email Notifications
- Sends emails to both drivers and riders for:
  - Ride **acceptance**
  - Ride **rejection**
  - Ride **completion**
- Configured using **Spring MailSender**

### 🔐 Security & Authentication
- Implements **Spring Security**
- Supports **JWT Authentication**
- Integrated **OAuth2 login**
- **Session management** for protected routes
- Role-based access control for secure endpoints

---

## 🛠️ Tech Stack

- **Java** (JDK 17+)
- **Spring Boot**
- **Spring Security**, **Spring Data JPA**, **Spring Mail**
- **PostgreSQL** with **PostGIS** extension
- **JWT** (JSON Web Tokens)
- **OAuth2** (Google or GitHub)
- **Maven**

---

## 📦 Getting Started

### 🧰 Prerequisites

- Java 17+
- PostgreSQL with PostGIS enabled
- Maven

### 🚀 Installation

```bash
git clone https://github.com/yourusername/RideSharing-Backend.git
cd RideSharing-Backend
