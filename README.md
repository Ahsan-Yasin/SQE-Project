# UITestAutomation - Sauce Demo Test Framework

## Overview
This is a comprehensive Web UI Test Automation Framework built with **Selenium 4**, **Java**, **Cucumber (BDD)**, **TestNG**, and **Maven**.

The framework automates **30+ test cases** for the **Sauce Labs demo e-commerce website** (https://www.saucedemo.com/).

## Key Features Implemented

### ✅ 30+ Automated Test Cases
- **Login Tests** (7 scenarios): Valid/invalid credentials, empty fields, different user types
- **Product Tests** (8 scenarios): Adding products to cart, verifying inventory
- **Checkout Tests** (10 scenarios): Complete checkout flows, multiple products
- **UI Validation Tests** (5+ scenarios): Page title verification

### ✅ Page Object Model (POM)
Organized page objects:
- `LoginPage.java` - Login interactions
- `InventoryPage.java` - Products inventory
- `CartPage.java` - Shopping cart
- `CheckoutPage.java` - Checkout forms

### ✅ BDD with Cucumber
- Feature files with Gherkin syntax
- Step definitions in Java
- Data-driven test support
- Tag-based organization

### ✅ Data Integration
- **Apache POI** - Excel data reading
- **H2 Database** - JDBC demo
- **Jedis** - Redis client

### ✅ Reporting & Screenshots
- **Extent Reports** - HTML reports
- **Cucumber Reports** - BDD reports
- **Automatic Screenshots** - On failures to `target/allure-results/`

## Technologies

- Selenium WebDriver 4.27.0
- Cucumber 7.20.1
- TestNG 7.10.2
- WebDriverManager 5.9.2
- Apache POI 5.2.3
- Extent Reports 5.1.2
- Maven 3.6+

## Setup

### Prerequisites
- Java 8+ (tested with JDK 21)
- Maven 3.6+
- Chrome browser
- Internet connection

### Installation

1. Clone repository:
   ```bash
   git clone https://github.com/GodBiz94/UITestAutomation.git