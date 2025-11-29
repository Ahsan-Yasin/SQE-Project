Project submission guide — Sauce Demo (Swag Labs) UI Automation

Overview
--------
This repo contains a complete Selenium + Java + Cucumber test automation framework scaffold targeting https://www.saucedemo.com. The framework includes:
- Cucumber feature files (Gherkin) covering login/product/checkout/UI/data-driven scenarios
- Page Object Model (pages/)
- Step definitions (steps/)
- Test runners for JUnit/Cucumber
- Data integration helpers (Excel, DB, Redis)
- Allure test attachments & screenshot hook

Is the project "complete"?
-------------------------
Yes — the repository contains a working, runnable test framework and >15 feature scenarios. However: running tests fully requires certain local dependencies and services which may not be available in every environment (example: Chrome browser, MySQL, Redis, Allure CLI). You should run and verify locally (or in CI) and attach evidence for the viva.

Prerequisites (local machine / CI)
---------------------------------
- Java 21 (JDK)
- Maven (mvn)
- Google Chrome installed (binary) — WebDriverManager will download matching chromedriver
- Optional: MySQL server or Docker for database-driven tests
- Optional: Redis server (or Docker)
- Allure CLI (optional) if you want to serve / generate HTML reports from allure-results

How to run tests locally (recommended sequence)
----------------------------------------------
1) Clone the repository and open a terminal in repository root.
2) Run a regular test run (headless Chrome is enabled in framework):

    mvn test

3) If Allure CLI is installed, view the generated results using:

    allure serve target/allure-results

Notes: If tests fail due to missing services (MySQL, Redis) either start those services or skip those feature scenarios. The framework is tolerant of missing DB/Redis in examples but to get full marks you should enable them.

Setting up supporting services
------------------------------
MySQL example using Docker (simple start):

    docker run -d --name test-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=demo -p 3306:3306 mysql:8

Then create the sample schema below (connect with your favourite client or run mysql CLI):

SQL schema — sample tables (copy-paste)

    -- Create database and table 'products'
    CREATE DATABASE IF NOT EXISTS demo;
    USE demo;

    CREATE TABLE IF NOT EXISTS users (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      role VARCHAR(50)
    );

    CREATE TABLE IF NOT EXISTS products (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      sku VARCHAR(100),
      price DECIMAL(10,2) DEFAULT 0.00
    );

    CREATE TABLE IF NOT EXISTS orders (
      id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT,
      total DECIMAL(10,2) DEFAULT 0.00,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id)
    );

    CREATE TABLE IF NOT EXISTS order_items (
      id INT AUTO_INCREMENT PRIMARY KEY,
      order_id INT,
      product_id INT,
      qty INT DEFAULT 1,
      price DECIMAL(10,2),
      FOREIGN KEY (order_id) REFERENCES orders(id),
      FOREIGN KEY (product_id) REFERENCES products(id)
    );

    -- Sample data
    INSERT INTO users (username, password, role) VALUES ('standard_user', 'secret_sauce', 'user');
    INSERT INTO users (username, password, role) VALUES ('locked_out_user', 'secret_sauce', 'user');

    INSERT INTO products (name, sku, price) VALUES
    ('Sauce Labs Backpack', 'SLB-001', 29.99),
    ('Sauce Labs Bike Light', 'SLB-002', 9.99),
    ('Sauce Labs Bolt T-Shirt', 'SLB-003', 15.99);

Redis (optional - using Docker):

    docker run -d --name test-redis -p 6379:6379 redis:7

Testing tips — why tests might not run locally for you
-----------------------------------------------------
- Chrome binary missing — WebDriverManager downloads the chromedriver but needs the Chrome browser binary installed.
- DB/Redis services not available — tests that use these will either fail or skip depending on how you configure them. The sample `DBConnector` / `RedisClient` log errors and avoid failing the whole run, but to demonstrate data-driven tests you should spin up DB/Redis.
- Allure CLI not installed — you can still collect the results (target/allure-results) and generate the report later.

Screenshots & evidence to capture for your submission (VIVA + GitHub)
-------------------------------------------------------------------
Make sure you include the following screenshots / artefacts in your submission:
1) Terminal output of a successful `mvn test` run (showing tests passed) — take a screenshot and include the console output text file.
2) Allure report main page (after `allure serve`) showing pass/fail counts — screenshot.
3) Allure test details showing both passed and failed test steps and attached screenshots — screenshot(s) of a failed test showing the screenshot in Allure.
4) Demo of data-driven tests: show the Excel file (target/test-data/logins.xlsx) and the test verifying rows were read — screenshot.
5) Database evidence: show the `products` table rows (execute SELECT * FROM products) and include a screenshot of the client or terminal query results.
6) Redis evidence: `GET test_key` should show stored value — screenshot of redis-cli or GUI client result.
7) Project structure screenshot in GitHub (showing features/, pages/, steps/, pom.xml) and at least one commit showing the work history.
8) A short recorded video (1–3 minutes) that runs the full test suite locally and narrates what is happening — include this in your submission as a single file.

Packaging & final submission
---------------------------
Follow the course requirement: your submission folder should be named using the required format `22F_XXXX_SE` (replace XXXX with your student id). Include:
- The complete repository (or a link to your GitHub repo + commit history)
- `README.md` (already present)
- `SUBMISSION_GUIDE.md` (this file) and `docs/` content
- `target/allure-results` or exported Allure report (HTML) if available
- The recorded video file showing the run & explanation
- ExtraTask Python files and tests (in `extra_task/`)

Helpful commands (PowerShell) — run locally
------------------------------------------------
Run all tests:
    mvn test

Serve Allure report (if installed):
    allure serve target/allure-results

Run python tests (extra task) in `extra_task/`:
    python -m pip install pytest
    python -m pytest extra_task/tests -q

If you want, I can also:
- Add a GitHub Actions CI workflow to run tests and publish Allure artifacts.
- Prepare a short video script (commands + checklist) you can use to record your demo.
- Add final packaging script to prepare a zip folder named as required.
