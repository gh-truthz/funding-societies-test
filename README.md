# funding-societies-test
The project for interview testing of Funding Societies Company

## I. Test APIs
### I.I Test Cases
#### I.I.I User APIs
| API        | Description                                   | Notes |
|------------|-----------------------------------------------|-------|
| Get Single | 1. Get Valid User with ID                     | -     |
| Get Single | 2. Get Invalid User with ID                   | -     |
| Get Single | 3. Get Valid User with ID                     | -     |
| Get List   | 1. Get List with valid range page             | -     |
| Get List   | 2. Get List with valid range page but no data | -     |
| Get List   | 3. Get List with invalid range page           |This test is failed because the total_page should not be negatived|
| Get List   | 4. Get List with full data                    | -     |
| Create     | 1. Create new record                          | -     |
| Create     | 2. Create record for used ID                  |This test failed because the API should create data for exists id and inform error|
| Update     | 1. Update First name | -     |
| Update     | 2. Update Last name | -     |
| Update     | 3. Update new field | -     |
| Update     | 4. Update field non-exist record |This test failed because The update function should inform that there is no corresponding data|
| Delete     | 1. Delete new record | -     |
| Delete     | 2. Delete record for used ID | -     |
#### I.I.II Resource APIs
| API        | Description                                   | Notes |
|------------|-----------------------------------------------|-------|
| Get Single | 1. Get Valid User with ID                     | -     |
| Get Single | 2. Get Invalid User with ID                   | -     |
| Get Single | 3. Get Valid User with ID                     | -     |
| Get List   | 1. Get List with valid range page             | -     |
| Get List   | 2. Get List with valid range page but no data | -     |
| Get List   | 3. Get List with invalid range page           |This test is failed because the total_page should not be negatived|
| Get List   | 4. Get List with full data                    | -     |
#### I.I.III Register API
| API        | Description                                   | Notes |
|------------|-----------------------------------------------|-------|
| Register   | 1. Success Register                   | -     |
| Register | 2. Failed Register                   | -     |
| Register | 3. Unaccepted Email Register                    | -     |
#### I.I.III Login API
| API      | Description                                   | Notes |
|----------|-----------------------------------------------|-------|
| Login    | 1. Success Login                   | -     |
| Login | 2. Failed Login                   | -     |
| Login | 3. Non-exists Email Login                    | -     |
#### I.I.IV Web Control
| API   | Description   | Notes                                                                                  |
|-------|---------------|----------------------------------------------------------------------------------------|
| Web   | 1. Basic Test | This test is only use the basic web driver of Selenium to control the web interactions |

**Notes**: 
* The data getting from chart should be needed another approach like getting from chart input data (file or API).
* The current implementation is only configure for the Google Chrome Browser. We can update to support other browser with the corresponding WebDriver
### I.II Test Run Steps
#### I.II.I Pre-steps
* Require to install Java SDK 11
* Require to install Maven 3.8.1
#### I.II.I Run-steps
Run maven command with Defined Test Suite
`mvn test -Dsuite=APITestSuite.xml -f pom.xml`
