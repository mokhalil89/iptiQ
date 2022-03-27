Feature: reqres API

  @Regression
  Scenario Outline: Valid Case: Get A single user data for specific id
    Given id number "<ValidIdNumber>" in the path parameter
    When calling Get API and the response received
    Then the response code should be 200
    And  validate id "<id>"
    And  validate first name"<first_name>"
    Examples:
      | ValidIdNumber | id | first_name |
      | 2             | 2  | Janet      |

  Scenario Outline: Valid Case: Create A user
    Given set User Name "<EmployeeName>" and Job title "<JobTitle>" in a json body
    When calling POST API and the response received
    Then the response code should be 201
    Then Validate name"<name>" in the response body
    Examples:
      | EmployeeName | JobTitle | name   |
      | khalil       | leader   | khalil |


  Scenario Outline: Valid Case: Update A user
    Given update User Name "<EmployeeName>" and Job title "<JobTitle>" in a json body for specific ID"<id>"
    When calling PUT API and the response received
    Then the response code should be 200
    And the updatedAt is valid
Examples:
  | EmployeeName | JobTitle | id |
  | khalil       | leader   | 2  |

  Scenario Outline: Valid Case: Delete a User with specific id
    Given set id number "<ValidIdNumber>" in the path parameter
    When calling DELETE API and the response received
    Then the response code should be 204
    Examples:
      | ValidIdNumber |
      | 2             |

    @Negative
    Scenario Outline: Invalid Case: Get A single user data for wrong id
      Given invalid id number "<InvalidIdNumber>" in the path parameter
      When calling Get API and the response received
      Then the response code should be 404
      Examples:
        | InvalidIdNumber |
        | 12342           |
