# XPay

## Overview

This is a peer-to-peer short-term lending platform where users can request loans for a few days and offer an extra amount as interest. Lenders can view these requests and fund them, earning the agreed extra amount. The app is designed for quick, flexible, and transparent lending between users.

## Architecture Overview

- **Auth Service** – JWT-based authentication, user registration, and role management (borrower/lender)  
- **User Service** – Onboarding, profile management, loan history, and ratings  
- **Wallet Service** – Digital wallet for deposits, withdrawals, and loan balances  
- **Loan Service** – Create, track, and manage short-term loan requests  
- **Transaction Service** – Handles peer-to-peer loan funding and repayments  
- **Notification Service** – Email/SMS alerts for loan requests, approvals, and repayments  
- **Gateway** – API routing, authentication, and rate limiting  
- **Fraud Detection** – Rule-based checks to prevent default or malicious activity  
- **Reconciliation** – Ensures wallet balances match loan/transaction records  
- **Audit** – Centralized logging of all loan requests, funding, and repayments  
- **Admin Panel** – Interface for monitoring, approving, or flagging loans and users
  
## Tech Stack

- Java 21, Spring Boot 3.5.3, Apache Maven 3.9.11
- Docker & Docker Compose
- REST APIs, JWT, OAuth, Kafka
- MongoDB, PostgreSQL, Redis

## License
This project is licensed under the [MIT License](LICENSE).
  
For any issues or feedback, please contact saurabh.jojare@gmail.com.
