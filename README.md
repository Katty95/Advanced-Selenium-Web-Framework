# 🚀 Advanced Selenium Hybrid Framework (OrangeHRM Automation)

A professional-grade, scalable, and robust Automation Framework built with **Java** and **Selenium WebDriver**. This project demonstrates an enterprise-level implementation of the **Page Object Model (POM)** with integrated Data-Driven testing.

## 🌟 Framework Highlights
* **Architecture:** Hybrid Framework (Data-Driven + Modular).
* **Design Pattern:** **Page Object Model (POM)** for clean separation of UI Repository and Test Logic.
* **Testing Engine:** **TestNG** with advanced features like `DataProvider`, `Groups`, and `DependsOnMethods`.
* **Reporting:** Rich, interactive HTML reports using **Extent Reports** (AdvanceReport).
* **Stability:** Integrated **Retry Mechanism** and **TestNG Listeners** for automatic failure handling and screenshots.

## 🧩 Technical Implementation Details

### 1. Data-Driven Employee Management
The framework utilizes **TestNG @DataProvider** to execute the PIM module tests with multiple data sets iteratively.
* **Scenario:** `verifyAddEmployee`
* **Data Flow:** Uses `getEmpdata()` to feed `firstName`, `lastName`, and `id` dynamically into the test.

### 2. Smart Dependency & Data Capture
To ensure end-to-end data integrity, the framework implements a dynamic capture mechanism:
* **ID Capture:** A global variable `captureId` stores the system-generated ID using `pm.GeneratedEmployeeId()`.
* **Test Dependency:** The `deleteEmployee` test is linked via `dependsOnMethods = "verifyAddEmployee"`. This ensures we only attempt to delete a record if it was successfully created.
* **Assertions:** Advanced validations using `ExpectedConditions` to verify URL changes (`viewPersonalDetails`) and "No Records Found" messages after deletion.

### 3. CI/CD & Build Management
* **Build Tool:** Maven integration for dependency management and execution.
* **Execution:** Fully compatible with `mvn test` for headless execution in Jenkins/GitHub Actions pipelines.
* **Security:** Sensitive configurations managed via `.gitignore` to protect credentials.

## 📂 Project Structure
* `src/main/java/base`: Contains `BaseTest` for browser setup/teardown.
* `src/main/java/pages`: Object Repository (LoginPage, DashboardPage, PIM).
* `src/main/java/genericUtilities`: Reusable PropertyFile and WebDriver utilities.
* `src/test/java/demo`: Functional Test Scripts (`AddEmployeeTest`).

## ⚙️ How to Run
1. Clone the repository.
2. Update `config.properties` with your OrangeHRM credentials.
3. Run `testng.xml` from Eclipse or use `mvn test` from terminal.

---
**Author:** Katty95 (Ankit Singh)  
**Experience:** 8+ Years in Software Quality Engineering