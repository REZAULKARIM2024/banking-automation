# Banking Automation – ParaBank

## 📌 Project Overview

This project focuses on **User Interface (UI) automation testing** for a banking application using **ParaBank**.

🔗 Application URL: [https://parabank.parasoft.com](https://parabank.parasoft.com)

The framework is built using **Java, Selenium WebDriver, Cucumber (BDD), and Maven**. It follows Behavior Driven Development practices with clear and readable feature files mapped to Java step definitions.

---

## 🛠️ Technology Stack

* **Programming Language:** Java
* **Automation Tool:** Selenium WebDriver
* **BDD Framework:** Cucumber
* **Build Tool:** Maven
* **Test Runner:** JUnit / TestNG
* **Design Pattern:** Page Object Model (POM)
* **IDE:** IntelliJ IDEA / Eclipse / VS Code

---

## 📂 Project Structure

```
banking-automation
│── src
│   ├── test
│   │   ├── java
│   │   │   ├── runners
│   │   │   ├── stepdefinitions
│   │   │   ├── pages
│   │   ├── resources
│   │   │   ├── features
│── pom.xml
│── README.md
```

---

## 🧪 Test Scenarios Covered

The following **Cucumber feature files** are included in this project:

### ✅ User Registration (Sign Up)

* Sign up to **[www.parabank.parasoft.com](http://www.parabank.parasoft.com)** with valid user information
* Verify successful account creation

### ✅ User Login

* Log in to **parabank.parasoft.com** with valid credentials

**Test User:**
👤 **Username:** REZAUL KARIM

---

## ▶️ How to Run the Tests

1. Clone the repository
2. Open the project in your preferred IDE
3. Ensure Java and Maven are installed
4. Run the test using:

   ```bash
   mvn test
   ```

---

## 📈 Reporting

* Cucumber HTML Reports
* Console execution logs

---

## 👨‍💻 Author

**Rezaul Karim**
Software QA Engineer | Automation & Manual Testing

---

## 📌 Notes

* Make sure the Chrome browser and a compatible ChromeDriver are installed
* Update test data if needed inside feature files or configuration files

---

✅ This project is designed for learning and demonstrating **Banking UI Automation using BDD framework**.

