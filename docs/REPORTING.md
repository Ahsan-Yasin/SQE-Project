Allure reporting

To generate an Allure HTML report from results produced by this project:

1) Run tests with Maven

```powershell
mvn test
```

2) If Allure CLI is available locally, run:

```powershell
allure serve target/allure-results
```

This will open a local web server showing an interactive report. The repository already contains a few sample attachments in `allure-results/` created by the `SetupTest` helper so you can inspect the folder structure.

Note: test runs must produce files under `target/allure-results` (or `allure-results`) for the UI to show pass/fail data, attachments and screenshots.
