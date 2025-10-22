# 💰 SpringInvoicePro: The Smart & Simple Invoice Builder 🚀

A robust, full-stack application built with Spring Boot designed to streamline the entire invoicing process, from product entry to instant PDF bill generation, complete with automated tax and discount calculations.

---

## 🛠️ What It Does

SpringInvoicePro eliminates the complexity and manual errors associated with traditional invoicing. It provides a simple web-based interface for:
1.  **Product and Customer Management:** Easily add and manage product details and customer information.
2.  **Automated Calculations:** Instantly calculates   and applies discounts on the fly.
3.  **One-Click PDF Generation:** Generates a professional, print-ready invoice bill as a PDF for the customer.

This solution is perfect for small to medium businesses needing a reliable, fast, and compliant billing system.

---

## ✨ Features

-   **Automated   & Tax Calculation** ✓
-   **Dynamic Discount Application** ✓
-   **Print-Ready PDF Invoice Generation** ✓
-   **Customer and Product Detail Management** ✓
-   **Intuitive Web-Based User Interface** ✓
-   **Secure Data Storage (Spring Boot backend)** ✓

---

## 💻 Tech Stack

| Category | Technology | Reason |
| :--- | :--- | :--- |
| **Backend Framework** | Spring Boot | Rapid application development, powerful ecosystem, and robust REST API capabilities. |
| **Language** | Java | Enterprise-level stability and high performance. |
| **Database** | MySQL
| **Frontend** Thymeleaf / Plain HTML/CSS/JS

---

## ⚙️ How to Run Locally

Follow these steps to get a development copy running on your local machine.

### Prerequisites

You will need the following software installed:

-   [Java Development Kit (JDK) 17+](https://www.oracle.com/java/technologies/downloads/)
-   [Apache Maven 3.8+](https://maven.apache.org/download.cgi)
-   A text editor or IDE VS Code

### Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Manikarajhp/JavaSpringBoot.git]
    cd JavaSpringBoot
    ```
2.  **Configure Database:**
    * Open `src/main/resources/application.properties` (or `.yml`).
    * Update the database connection details (URL, username, password) for your environment.
3.  **Build the Project:**
    ```bash
    mvn clean install
    ```
4.  **Run the Application:**
    ```bash
    java -jar target/spring-invoice-pro-0.0.1-SNAPSHOT.jar
    ```
5.  **Access the Application:**
    * Open your web browser and navigate to: `http://localhost:8080` (or your configured port).
