$ErrorActionPreference = 'Stop'

Write-Host "Creating sample Excel test-data and running mvn test..."
# create test-data using a small Java helper via ExcelUtils (tests will create it at runtime as well)

mvn test

if (Get-Command allure -ErrorAction SilentlyContinue) {
    Write-Host "Generating Allure report (will open locally)..."
    allure serve target/allure-results
} else {
    Write-Host "Allure CLI not installed. Install Allure if you want to generate an HTML report from target/allure-results"
}
