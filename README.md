# UI Automation Framework — Sauce Demo (Swag Labs)

This repository contains a sample Selenium + Cucumber + Java test automation framework built for the Sauce Demo website (https://www.saucedemo.com).

Features
- Cucumber BDD (Gherkin) feature files
- Page Object Model (pages/)
- Reusable step definitions (steps/)
- Cucumber runner (runners/TestRunner.java)
- Allure reporting hooks (screenshots on failure in hooks)
- Data-driven example using Excel (Apache POI) created at runtime
- Example utilities for JDBC (DBConnector) and Redis (RedisClient)

Run the tests (Windows PowerShell)
```powershell
# run all tests via Maven
mvn test

# generated Cucumber JSON is available at target/cucumber.json
# generate Allure report (Allure CLI must be installed)
allure serve target/allure-results || echo "Install Allure to view report"
```

Project structure
- src/test/java/pages — Page Objects
- src/test/java/steps — Cucumber step definitions
- src/test/java/hooks — Cucumber hooks (setup/teardown + screenshots)
- src/test/resources/features — Gherkin feature files

Notes
- This is a starter project designed to meet course requirements: use of Cucumber features, Allure reporting, and data-driven examples (Excel/DB/Redis).
