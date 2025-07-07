**REST Assured API Testing – Swagger Petstore**:
This project is an automated REST API testing framework built using REST Assured and Java, targeting the [Swagger Petstore](https://petstore.swagger.io/) public API.
It covers core CRUD operations on '/pet', '/store' and '/user' endpoints with structured test cases, reusable components, and reporting and some validations using status code, body, json schema etc

**Features Covered**:
. POST requests- add pet, create user and place order
. GET requests- fetch pet by id, fetch store inventory and order by id, fetch user by username
. Delete requests- remove pet by id, delete order by id, delete user by username

**Tech stack used**:
. Java 20
. Rest Assured
. TestNG
. GSON
. Maven
. ExtentReports

**Project Structure**:

<img width="390" alt="Screenshot 2025-07-08 at 12 42 57 AM" src="https://github.com/user-attachments/assets/74430c02-d171-491b-828e-50aab1a7f367" />
<img width="385" alt="Screenshot 2025-07-08 at 12 43 10 AM" src="https://github.com/user-attachments/assets/92e7aec6-a493-4cd7-b47b-0cf41ea6d775" />

**Prerequisites**:
. Java 11 or later
. Maven 3.6 or later
. IDE (Eclipse)

**Clone the repository**:
git clone https://github.com/SouravMa/PetStore.git

**Install dependencies**:
mvn clean install

**Reporting**:
Used listeners and extentreports to generate reports
