# 🌊 AAWSA Water Billing System

A comprehensive Java Swing application for managing water billing operations with MySQL database integration. This system provides separate interfaces for administrators and customers to manage accounts, calculate bills, and process payments.

## 📋 Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation Guide](#installation-guide)
- [Database Setup](#database-setup)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Architecture](#architecture)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### 👥 User Roles
- **Administrator**: Full system control
- **Domestic Customer**: Residential water billing
- **Non-Domestic Customer**: Commercial/Industrial billing

### 🔐 Authentication & Security
- Role-based login system
- SHA-256 password hashing
- SQL injection prevention using PreparedStatement
- Session management

### 💼 Admin Capabilities
- 📊 View all accounts in tabbed interface (Domestic/Non-Domestic)
- ➕ Create new accounts with auto-generated IDs
- ✏️ Edit existing account details
- 🔄 Reset user passwords
- ❌ Remove user accounts with confirmation
- 🔍 Real-time table updates

### 💧 Billing System
- 📈 Tiered pricing based on consumption
- 🏢 15% tax for non-domestic accounts
- ⚡ Real-time cost calculation
- 💳 Multiple payment methods (CBE, Telebirr, BOA)
- 🧾 Automated receipt generation
- 🖨️ Receipt printing/download functionality

### 📊 Database Features
- MySQL relational database
- Secure data persistence
- ACID-compliant transactions
- Efficient query optimization

## 🛠️ Technologies Used

- **Java 8+** - Core programming language
- **Java Swing** - GUI framework
- **MySQL** - Database management
- **JDBC** - Database connectivity
- **SHA-256** - Password hashing algorithm
- **MVC-like Architecture** - Software design pattern



## 🚀 Installation Guide

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (included in lib folder)
- IDE (Eclipse/IntelliJ/NetBeans) or command line

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/aawsa-water-billing.git
cd aawsa-water-billing
