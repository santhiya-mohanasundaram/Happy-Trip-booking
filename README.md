# Travel Booking System

## Project Overview

The Travel Booking System is a web-based application designed to make travel planning and booking easier for users. It allows users to explore tourist destinations, view travel packages, register, log in, book trips, apply discount coupons, make payments, and download booking tickets.

The system also provides an Admin Dashboard to manage users, travel packages, and bookings.

## Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript

### Backend
- Java
- Spring Boot
- REST API

### Database
- MySQL

### Development Tools
- Visual Studio Code
- IntelliJ IDEA
- MySQL Workbench
- Git
- GitHub

## Main Features

### 1. Home Page
- Attractive travel homepage.
- Destination browsing.
- Travel package sections.
- About Us section.

### 2. User Authentication
- User Registration.
- User Login.
- Admin Login.
- Password validation.
- Secure password storage.
- Role-based access.

### 3. Destination Details
- Destination overview.
- Tourist attractions.
- Best time to visit.
- Travel information.
- Accommodation details.
- Local food and travel tips.

### 4. Travel Packages
- Holiday Packages.
- Honeymoon Packages.
- Family Holiday Packages.
- Adventure Packages.
- Beach Packages.
- Luxury Packages.

### 5. Package Like System
- Like and Unlike packages.
- Display total likes.
- Store likes in the database.

### 6. Booking System
- Three-step booking process.
- Checkout.
- Booking Confirmation.
- Payment Confirmation.
- Price calculation.
- Coupon code application.

### 7. Coupon System
- Apply discount coupon codes.
- Validate coupon codes.
- Automatically calculate discounted prices.

### 8. Travel Ticket
- Generate booking tickets.
- Download tickets as PDF.
- Display booking ID and passenger details.
- Store ticket information.

### 9. My Trips
- All Bookings.
- Upcoming Tours.
- Completed Trips.
- Cancelled Bookings.
- My Tickets.
- Download previous tickets.

### 10. Admin Dashboard
- View registered users.
- Manage travel packages.
- View all bookings.
- Monitor payment status.
- Manage booking information.

## Project Architecture

The application follows a frontend-backend architecture.

- Frontend: HTML, CSS, JavaScript.
- Backend: Java Spring Boot.
- Database: MySQL.
- Communication: REST APIs.

## Database Configuration

The project uses MySQL to store:

- User Details
- Admin Details
- Destinations
- Travel Packages
- Bookings
- Payments
- Tickets
- Coupons
- Package Likes
- Cancellation Details

## Installation and Setup

### Step 1: Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### Step 2: Open the Project

Open the project folder in Visual Studio Code or IntelliJ IDEA.

### Step 3: Configure MySQL

- Start MySQL Server.
- Create the required database.
- Configure database credentials using local environment variables.
- Verify the database connection.

### Step 4: Configure Environment Variables

Create a local `.env` file using the provided `.env.example` template.

Add the required database and API configuration values.

**Important:** Never upload the actual `.env` file to GitHub.

### Step 5: Run the Backend

Navigate to the backend folder and run:

```bash
mvn spring-boot:run
```

### Step 6: Run the Frontend

Open the frontend folder and run the application using Live Server or your configured development server.

## Security

- Password hashing.
- Role-based authentication.
- Protected admin routes.
- Environment-based configuration.
- Secure database access.
- User-specific booking access.

## Future Enhancements

- Online payment gateway integration.
- Email booking confirmation.
- Real-time travel package availability.
- Advanced destination search.
- User reviews and ratings.
- Improved travel recommendations.

 