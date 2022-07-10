Feature: This feature covers all the API related scenarios
  Background:
    Given a JWT is generated
  @api
  Scenario: Adding an employee
    Given a request is prepared to create an employee
    When a POST call is made to  create an employee
    Then the status code for the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls
