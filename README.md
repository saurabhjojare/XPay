# XPay

## Overview

**XPay** is a scalable, microservices-based digital payment platform built with **Java 21**, **Spring Boot 3.5.3**, and **Maven 3.9.11**, simulating features of real-world systems like UPI, Stripe, or Razorpay.

## 🧱 Architecture Overview

- **Auth Service** – JWT-based authentication and role management
- **User Service** – User onboarding and profile management
- **Wallet Service** – Digital wallet for balance, deposit, withdraw
- **Transaction Service** – Peer-to-peer and merchant transactions
- **Notification Service** – Email/SMS alerts on key events
- **Gateway** – API routing, rate limiting, and auth filtering
- **Fraud Detection** – Rule-based risk scoring on transactions
- **Reconciliation** – Verifies wallet vs ledger consistency
- **Settlement** – T+1 merchant fund settlements
- **Audit** – Centralized event/audit logging
- **Admin** – Ops interface for refunds, bans, etc.
  
## 🛠️ Tech Stack

- Java 21, Spring Boot 3.5.3, Maven 3.9.11
- Docker & Docker Compose
- REST APIs, JWT, OAuth 2.0, Kafka
- MongoDB, PostgreSQL, Redis


## License
This project is licensed under the [MIT License](LICENSE).
  
For any issues or feedback, please contact saurabh.jojare@gmail.com.
