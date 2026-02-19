# SauceLabs Demo App - Appium Mobile Automation

Automated mobile testing framework for the **SauceLabs Demo Android App** using Appium, Java, TestNG, Cucumber BDD, and Allure reporting.

---

## Technologies Used

| Technology          | Version | Purpose                       |
|---------------------|---------|-------------------------------|
| IntelliJ IDEA       | 2024.3  | IDE                           |
| Java                | 21.0.5  | Programming language          |
| Maven               | 3.9.12  | Build & dependency management |
| Appium Java Client  | 8.6.0   | Mobile automation library     |
| Appium Server       | 3.2.0   | Automation server             |
| Selenium            | 4.13.0  | WebDriver protocol support    |
| UiAutomator2 Driver | 6.8.1   | Android automation driver     |
| Cucumber            | 7.20.1  | BDD test framework            |
| TestNG              | 7.10.2  | Test runner                   |
| Allure              | 2.29.0  | Test reporting                |
| AssertJ             | 3.26.3  | Fluent assertions             |
| SLF4J               | 2.0.16  | Logging                       |


---

## Prerequisites

Make sure the following are installed and configured before running the project:

### 1. Java JDK 21.0.5
- Download from [Adoptium](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Set `JAVA_HOME` environment variable to the JDK installation path
- Add `%JAVA_HOME%\bin` to your system `PATH`
- Verify installation:
  ```bash
  java -version
  ```

### 2. Apache Maven 3.9.12
- Download from [Apache Maven](https://maven.apache.org/download.cgi)
- Add the `bin` directory to your system `PATH`
- Verify installation:
  ```bash
  mvn -version
  ```

### 3. Android Studio
- Download from [Android Studio](https://developer.android.com/studio)
- During setup, install the **Android SDK** via SDK Manager
- Set `ANDROID_HOME` environment variable to the SDK location (e.g., `C:\Users\<user>\AppData\Local\Android\Sdk`)
- Add the following to your system `PATH`:
  ```
  %ANDROID_HOME%\platform-tools
  %ANDROID_HOME%\emulator
  %ANDROID_HOME%\tools
  %ANDROID_HOME%\tools\bin
  ```
- Create an **Android Virtual Device (AVD)** via Android Studio > Device Manager
- Verify setup:
  ```bash
  adb devices
  ```

### 4. Node.js & npm
- Download from [Node.js](https://nodejs.org/) (LTS version recommended)
- Required for installing Appium
- Verify installation:
  ```bash
  node -v
  npm -v
  ```

### 5. Appium Server 3.2.0
- Install globally via npm:
  ```bash
  npm install -g appium
  ```
- Install the UiAutomator2 driver:
  ```bash
  appium driver install uiautomator2
  ```
- Verify installation:
  ```bash
  appium -v
  appium driver list --installed
  ```

### 6. Allure Commandline (Optional - for viewing reports)
- Install via npm:
  ```bash
  npm install -g allure-commandline
  ```
- Or via Scoop (Windows):
  ```bash
  scoop install allure
  ```
- Verify:
  ```bash
  allure --version
  ```

---

## Architecture

```
┌──────────────────────────────────────────────────────────┐
│                    TestNG + Cucumber Runner               │
├──────────────────────────────────────────────────────────┤
│   Feature Files (.feature)                               │
│   ├── login.feature       (@login @smoke)                │
│   └── purchase.feature    (@purchase @e2e)               │
├──────────────────────────────────────────────────────────┤
│   Step Definitions                                       │
│   ├── LoginSteps.java                                    │
│   └── PurchaseSteps.java                                 │
├──────────────────────────────────────────────────────────┤
│   Page Objects (Page Object Model)                       │
│   ├── BasePage.java           (common actions)           │
│   ├── LoginPage.java                                     │
│   ├── ProductsPage.java                                  │
│   ├── ProductDetailPage.java                             │
│   ├── CartPage.java                                      │
│   ├── CheckoutInfoPage.java                              │
│   ├── CheckoutOverviewPage.java                          │
│   └── CheckoutCompletePage.java                          │
├──────────────────────────────────────────────────────────┤
│   Utilities                                              │
│   ├── DriverManager.java      (ThreadLocal<AndroidDriver>)│
│   ├── WaitUtils.java          (explicit waits)           │
│   ├── ScreenshotUtils.java    (Allure @Attachment)       │
│   └── ConfigReader.java       (properties loader)        │
├──────────────────────────────────────────────────────────┤
│   Hooks.java  (@Before → init driver, @After → quit)    │
├──────────────────────────────────────────────────────────┤
│                 Appium Server (UiAutomator2)              │
├──────────────────────────────────────────────────────────┤
│              Android Emulator / Real Device               │
└──────────────────────────────────────────────────────────┘
```

---

## Setup & Run

### 1. Clone the Repository

```bash
git clone <repository-url>
cd SauceLabs
```

### 2. Install Appium and UiAutomator2 Driver

```bash
npm install -g appium
appium driver install uiautomator2
```

### 3. Place the APK

Copy `sauce-labs.apk` into the `app/` directory:

```
app/sauce-labs.apk
```

### 4. Configure Test Settings

Edit `src/test/resources/config.properties` if needed:

| Property             | Default                    | Description                 |
|----------------------|----------------------------|-----------------------------|
| appium.server.url    | http://127.0.0.1:4723      | Appium server URL           |
| platform.name        | Android                    | Mobile platform             |
| device.name          | emulator-5554              | Device/emulator name        |
| automation.name      | UiAutomator2               | Appium automation engine    |
| app.path             | app/sauce-labs.apk         | Path to APK file            |
| implicit.wait        | 10                         | Implicit wait (seconds)     |
| explicit.wait        | 30                         | Explicit wait (seconds)     |

Update `device.name` to match your emulator or connected device.

### 5. Start the Android Emulator

```bash
emulator -avd <your_avd_name>
```

Or launch it from Android Studio > Device Manager.

### 6. Start Appium Server

```bash
appium
```

The server starts on `http://127.0.0.1:4723` by default.

### 7. Run Tests

Run all tests:

```bash
mvn clean test
```

### 8. Generate & View Allure Report

```bash
mvn allure:serve
```

This generates the report and opens it in your browser automatically.

---

## Running Tests by Tag

```bash
# Login / Smoke tests
mvn clean test -Dcucumber.filter.tags="@login"
mvn clean test -Dcucumber.filter.tags="@smoke"

# Purchase / E2E tests
mvn clean test -Dcucumber.filter.tags="@purchase"
mvn clean test -Dcucumber.filter.tags="@e2e"
```

Override configuration via system properties:

```bash
mvn clean test -Ddevice.name=Pixel_6_API_33
```

---

## Project Structure

```
SauceLabs/
├── pom.xml                                          # Maven config & dependencies
├── testng.xml                                       # TestNG suite configuration
├── app/                                             # Place sauce-labs.apk here
├── src/main/java/com/saucelabs/
│   ├── config/ConfigReader.java                     # Properties loader
│   ├── pages/
│   │   ├── BasePage.java                            # Common page actions
│   │   ├── LoginPage.java                           # Login screen
│   │   ├── ProductsPage.java                        # Product catalog
│   │   ├── ProductDetailPage.java                   # Product detail
│   │   ├── CartPage.java                            # Shopping cart
│   │   ├── CheckoutInfoPage.java                    # Checkout information
│   │   ├── CheckoutOverviewPage.java                # Checkout overview
│   │   └── CheckoutCompletePage.java                # Order confirmation
│   └── utils/
│       ├── DriverManager.java                       # ThreadLocal driver management
│       ├── WaitUtils.java                           # Explicit wait helpers
│       └── ScreenshotUtils.java                     # Allure screenshot capture
├── src/test/java/com/saucelabs/
│   ├── runners/TestRunner.java                      # Cucumber-TestNG runner
│   ├── steps/
│   │   ├── LoginSteps.java                          # Login step definitions
│   │   └── PurchaseSteps.java                       # Purchase step definitions
│   └── hooks/Hooks.java                             # Before/After hooks
└── src/test/resources/
    ├── features/
    │   ├── login.feature                            # Login scenarios
    │   └── purchase.feature                         # Purchase E2E scenario
    ├── config.properties                            # Test configuration
    └── allure.properties                            # Allure configuration
```

---

## Test Scenarios

### Login Feature (`@login @smoke`)
- Successful login with valid credentials

### Purchase Feature (`@purchase @e2e`)
- Full end-to-end purchase flow:
  1. Login with valid credentials
  2. Add "Sauce Labs Backpack" to cart
  3. Add "Sauce Labs Bike Light" to cart
  4. Validate cart contents (2 items)
  5. Complete checkout with shipping info
  6. Validate total = item total + tax
  7. Finish order and verify confirmation message
