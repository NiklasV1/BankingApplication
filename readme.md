Example of a banking webapp using a Vue.js frontend and a Quarkus backend.
This app was mainly tested on Firefox with a 1920x1080 Monitor.



Features:
- Login
- Accounts management
- Transactions
- Deposits/Withdrawals

Technologies used:
- Docker
- Java + Quarkus Framework
- Java Hibernate + Panache
- Java Jackson REST
- Vue.js
- Tailwind css
- nginx
- PostgreSQL



Installation:

1. clone repository

2. cd BankingApplication/Backend/banking-server

3. mvn package

4. docker compose build

5. docker compose up -d



Images:

Database Schema:
![database schema](./schema.png)

Accounts Page:
![accounts page](./accounts.png)

Withdrawals Page:
![withdrawals page](./withdrawals.png)