# XPay (Backend)

XPay is a distributed payment system that enables users to securely send, receive, and manage payments across a decentralized network.

The backend is designed as a **distributed microservice-based system**, with separate services responsible for authentication, users, wallets, loans, transactions, notifications, fraud detection, reconciliation, auditing, and administration.

The platform focuses on providing a secure and transparent flow for short-term lending between borrowers and lenders.

## Architecture

XPay follows a microservice architecture where each service owns a specific business responsibility and communicates with other services through REST APIs and event-driven messaging.

```text id="xpay-architecture"
                         ┌──────────────────┐
                         │      Client      │
                         └────────┬─────────┘
                                  │
                                  ▼
                         ┌──────────────────┐
                         │     Gateway      │
                         │ Auth / Routing   │
                         │  Rate Limiting   │
                         └────────┬─────────┘
                                  │
              ┌───────────────────┼───────────────────┐
              │                   │                   │
              ▼                   ▼                   ▼
       ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
       │ Auth Service│     │ User Service│     │ Loan Service│
       └─────────────┘     └─────────────┘     └──────┬──────┘
                                                       │
                                  ┌────────────────────┼────────────────────┐
                                  │                    │                    │
                                  ▼                    ▼                    ▼
                           ┌─────────────┐      ┌─────────────┐      ┌─────────────┐
                           │Wallet       │      │Transaction  │      │Fraud        │
                           │Service      │      │Service      │      │Detection    │
                           └──────┬──────┘      └──────┬──────┘      └─────────────┘
                                  │                    │
                                  └──────────┬─────────┘
                                             ▼
                                      ┌─────────────┐
                                      │Reconciliation│
                                      └─────────────┘

                  ┌──────────────────┐       ┌──────────────────┐
                  │ Notification     │       │      Audit       │
                  │ Service          │       │      Service      │
                  └──────────────────┘       └──────────────────┘

                                      ┌─────────────┐
                                      │ Admin Panel │
                                      └─────────────┘
```

## Services

### Auth Service

Responsible for authentication and authorization.

- User registration and login
- JWT-based authentication
- OAuth integration
- Role management
- Borrower and lender authorization
- Access control for protected resources

### User Service

Manages user profiles and user-related information.

- User onboarding
- Profile management
- User verification information
- Loan history
- User ratings
- Borrower and lender information

### Wallet Service

Manages digital wallet operations and balances.

- Wallet creation and management
- Deposits
- Withdrawals
- Available balance management
- Loan-related balance updates
- Wallet transaction records

### Loan Service

Handles the complete lifecycle of short-term loan requests.

- Create loan requests
- Specify requested amount
- Specify loan duration
- Define the agreed interest/extra repayment amount
- Track loan status
- Manage loan approval and funding state
- Track repayment status
- Maintain loan history

### Transaction Service

Handles financial transactions between borrowers and lenders.

- Loan funding
- Repayment processing
- Transaction tracking
- Transaction status management
- Integration with wallet operations
- Transaction history

### Notification Service

Responsible for communicating important events to users.

- Loan request notifications
- Loan funding notifications
- Approval notifications
- Repayment notifications
- Email/SMS notifications
- Event-driven notification processing

### Fraud Detection Service

Provides rule-based checks to identify potentially fraudulent activity.

- Validate suspicious loan requests
- Detect unusual transaction patterns
- Apply configurable fraud rules
- Flag potentially malicious activity
- Provide risk information to relevant services

### Reconciliation Service

Ensures consistency between wallet, loan, and transaction records.

- Compare wallet balances with transaction records
- Validate loan balances
- Detect inconsistencies
- Identify failed or incomplete transactions
- Support financial reconciliation workflows

### Audit Service

Maintains centralized audit information for important system operations.

- Loan request events
- Loan funding events
- Repayment events
- Wallet operations
- User-related changes
- Administrative actions
- Security-sensitive operations

### Gateway

Acts as the primary entry point for client requests.

Responsibilities include:

- API routing
- Authentication enforcement
- Request authorization
- Rate limiting
- Service discovery/routing
- Centralized request handling

### Admin Panel

Provides administrative capabilities for monitoring and managing the platform.

Administrators can:

- Monitor users
- Monitor loan requests
- Review potentially fraudulent activity
- Approve or flag loans
- Monitor transactions
- Review audit information
- Monitor reconciliation issues

## Loan Flow

A typical lending workflow looks like this:

```text id="loan-flow"
Borrower
   │
   │ Create loan request
   ▼
Loan Service
   │
   ├──► Fraud Detection
   │
   ├──► Notification Service
   │
   ▼
Available Loan Request
   │
   │ Lender funds loan
   ▼
Transaction Service
   │
   ├──► Wallet Service
   │
   ├──► Loan Service
   │
   └──► Audit Service
   │
   ▼
Loan Funded
   │
   │ Borrower repays
   ▼
Transaction Service
   │
   ├──► Wallet Service
   ├──► Loan Service
   ├──► Reconciliation
   ├──► Notification Service
   └──► Audit Service
   │
   ▼
Loan Completed
```

## Technology Stack

### Backend

- **Java**
- **Spring Boot**
- **Maven**
- **REST APIs**

### Security

- **JWT**
- **OAuth**
- Role-based authorization

### Messaging

- **Apache Kafka**
- Event-driven communication between services

### Databases & Storage

- **PostgreSQL** — relational and transactional data
- **MongoDB** — document-oriented data
- **Redis** — caching and fast-access data

### Infrastructure

- **Docker**
- **Docker Compose**

## Key Backend Concepts

### Authentication & Authorization

XPay uses JWT-based authentication to secure APIs and OAuth for supported authentication flows.

Authorization is role-based, allowing the system to distinguish between:

- Borrowers
- Lenders
- Administrators

Protected APIs validate the authenticated user's identity and permissions before processing requests.

### Event-Driven Communication

Kafka is used for asynchronous communication between services where appropriate.

For example:

```text
Loan Created
     │
     ▼
   Kafka
     │
     ├──► Fraud Detection
     ├──► Notification Service
     └──► Audit Service
```

This reduces tight coupling between services and allows independent processing of business events.

### Financial Consistency

Because wallet, loan, and transaction data must remain consistent, XPay includes a dedicated reconciliation process.

The reconciliation service can identify differences between:

```text
Wallet Balance
      ↕
Transaction Records
      ↕
Loan Records
```

This provides an additional layer of protection against inconsistent financial state.

## Project Structure

A typical repository structure can be organized as follows:

```text id="project-structure"
xpay-backend/
│
├── services/
│   ├── auth-service/
│   ├── user-service/
│   ├── wallet-service/
│   ├── loan-service/
│   ├── transaction-service/
│   ├── notification-service/
│   ├── fraud-detection-service/
│   ├── reconciliation-service/
│   ├── audit-service/
│   └── gateway/
│
├── admin-panel/
│
├── docker-compose.yml
├── pom.xml
└── README.md
```

> The exact structure may differ depending on the current implementation.

## Running Locally

### Prerequisites

Install the following before running the project:

- Java
- Maven
- Docker
- Docker Compose

### Clone the Repository

```bash id="clone-xpay"
git clone https://github.com/saurabhjojare/XPay.git
cd XPay
```

### Switch to the Backend Branch

```bash
git checkout backend
```

### Build the Project

```bash id="build-xpay"
mvn clean install
```

### Start Infrastructure

Use Docker Compose to start the required infrastructure services:

```bash id="docker-xpay"
docker compose up -d
```

Depending on the environment, Docker Compose may start services such as:

- PostgreSQL
- MongoDB
- Redis
- Kafka
- XPay backend services

### Stop the Environment

```bash id="stop-xpay"
docker compose down
```

## Frontend

The frontend for this project is maintained separately in the `frontend` branch.

```bash
git checkout frontend
```

See the frontend README for information about the React application and user interface.

## Configuration

Service configuration should be provided through environment variables or application configuration files.

Typical configuration includes:

- Database connection details
- JWT configuration
- OAuth credentials
- Kafka broker configuration
- Redis configuration
- Service URLs
- Notification provider credentials

**Do not commit secrets, passwords, private keys, or production credentials to the repository.**

## API

The backend exposes REST APIs for:

- Authentication
- User management
- Wallet operations
- Loan management
- Loan funding
- Repayments
- Transactions
- Administrative operations

API documentation can be added using **Swagger/OpenAPI** for easier development and testing.

## Development Guidelines

When adding or modifying a service:

1. Keep business logic isolated within the responsible service.
2. Avoid unnecessary coupling between services.
3. Use REST APIs for synchronous communication where appropriate.
4. Use Kafka events for asynchronous workflows.
5. Validate requests at service boundaries.
6. Protect sensitive endpoints with authentication and authorization.
7. Maintain audit records for important financial and administrative operations.
8. Keep financial operations transactional and idempotent where required.
9. Never commit secrets or credentials.
10. Add appropriate tests for business-critical functionality.

## License

This project is licensed under the **MIT License**.

## Contact

For issues, suggestions, or feedback: **Email:** saurabh.jojare@gmail.com
