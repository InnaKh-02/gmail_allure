# Automated Test Execution & Reporting Guide

## Test Execution

### To run the automated tests, use the following Maven command:

```bash
mvn clean test -Dbrowser=chrome -Denv=qa
```
- Dbrowser: browser to run tests 
- Denv: name of the .properties config file located in src/test/resources 

ℹ️ Both parameters are optional. If not specified, default values will be used.

## Environment Configuration

### Before running the tests, make sure an environment configuration file exists:

📄 src/test/resources/qa.properties (or other based on -Denv)

```properties
email=
password=
recipient=
subject=
body=
```

## Allure Report Generation

### After test execution, an Allure report should be generated.

### Option 1: Generate and view immediately

```bash
allure serve target/allure-results
```
### Option 2: Generate the report manually (via IntelliJ IDEA)

If you're using **IntelliJ IDEA**, you can generate the Allure report directly via the built-in Allure plugin:

1. **Install the Allure Plugin** (if not yet installed):
   - Go to `Settings` → `Plugins` → search for **Allure** and install it.
   - Restart IntelliJ IDEA if required.

2. **After running tests**, make sure Allure test results are generated in: target/allure-results
   
3. **Right-click** on the `target/allure-results` folder → choose  
**Allure** → **Serve** (available if plugin is installed and configured).

## Screenshots on Test Failure
If a test fails, a screenshot is automatically captured and attached to the Allure report.
These attachments are helpful for debugging and are required for submission.
