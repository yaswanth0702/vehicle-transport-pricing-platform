# vehicle-transport-pricing-platform

# Vehicle Transport Cost Estimator (Mini Full Stack Project)

## Overview

This project is a mini full-stack web application developed using Spring Boot to estimate vehicle transportation cost based on distance, vehicle type, transport type, and operability.

The system calculates the cost using predefined pricing rules and displays a detailed cost breakdown. Users can also save quotes and view previously saved quotes.

This project demonstrates backend development, database integration, REST API design, and frontend integration in a single Spring Boot application.

---

## Features

- Calculate vehicle transport cost instantly
- Display itemized cost breakdown
- Save quotes to database
- View all saved quotes
- Minimum charge protection
- Fuel surcharge calculation
- Vehicle type multiplier
- Enclosed transport multiplier
- Inoperable vehicle surcharge

---

## Technologies Used

Backend:

- Java
- Spring Boot
- Spring Data JPA
- REST API

Database:

- H2 Database (File based)

Frontend:

- HTML
- CSS
- JavaScript

Tools:

- Maven
- IntelliJ / Eclipse

---

## How the System Works

Step 1:

User enters:

- Pickup ZIP
- Dropoff ZIP
- Distance in miles
- Vehicle type
- Transport type
- Operability

Step 2:

System calculates cost using:

- Distance based rate
- Vehicle multiplier
- Transport multiplier
- Fuel surcharge
- Minimum charge rule

Step 3:

System displays:

- Total cost
- Cost breakdown

Step 4:

User can save the quote in database

---

## Cost Calculation Logic

Example rules used:

Distance Rate:

0–100 miles → $2.20 per mile  
101–500 miles → $1.80 per mile  
501–1500 miles → $1.50 per mile  
1500+ miles → $1.25 per mile  

Multipliers:

SUV → 1.10  
Truck → 1.20  
Motorcycle → 0.90  

Enclosed transport → 1.35  

Inoperable vehicle → 1.20  

Fuel surcharge → 8%

Minimum charge → $350

---

## Project Structure

```
vehicle-transport-mini/

pom.xml

src/main/java/com/example/vehicletransport/
VehicleTransportApplication.java
QuoteController.java
QuoteService.java
Quote.java
QuoteRepo.java

src/main/resources/
application.properties

src/main/resources/static/
index.html

README.md
```

---

## How to Run the Project

Step 1:

Open project in IntelliJ or Eclipse

Step 2:

Run the main class:

VehicleTransportApplication.java

OR

Use Maven command:

```
mvn spring-boot:run
```

Step 3:

Open browser:

```
http://localhost:8080
```

---

## Database Access

H2 Database Console:

```
http://localhost:8080/h2
```

JDBC URL:

```
jdbc:h2:file:./data/vehicletransportdb
```

Username:

```
sa
```

Password:

(blank)

---

## Sample Output

Example:

Distance: 1435 miles  
Vehicle: Sedan  
Transport: Open  

Total Cost:

$2325.00

With detailed breakdown.

---

## Key Learning Outcomes

This project helped in understanding:

- Spring Boot backend development
- REST API creation
- Database integration using JPA
- Full stack development in single application
- Cost calculation logic implementation

---

## Future Improvements

Possible enhancements:

- Add user login and authentication
- Add admin panel to change pricing rules
- Integrate MySQL database
- Add PDF quote generation
- Deploy on cloud

---

## Author

Yaswanth Podapati

MS Computer Science

Spring Boot Full Stack Project
