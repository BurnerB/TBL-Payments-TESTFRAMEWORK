Feature: [SUC:05-18] Taxpayer Payment - Portal


  Background:
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "Barackobama" and password "Codei@maseno2020" to login

  @SUC:05-18 @trial2
  Scenario Outline: UAT_M7_18-01-UAT_M7_18-02-Verify the Process of Pay In Bank
    Given navigate to My Tax>>make payment
    Then Outstanding Payments List Screen should be displayed
    Given Selects the Tax Type liability to be paid
    And click Pay Selected Button
    Then Payment Reference Number screen should be displayed
    Given user submits valid payment amount <amount>
    And Clicks on Pay At The Bank button
    Then confirmation and payment ref number is shown

    Examples:
      | amount |
      | 10     |

  @SUC:05-18 @trial2
  Scenario Outline: UAT_M7_18-03-Verify the Process of Pay In Bank
    Given navigate to My Tax>>make payment
    Then Outstanding Payments List Screen should be displayed
    Given Selects the Tax Type liability to be paid
    And click Pay Selected Button
    Then Payment Reference Number screen should be displayed
    Given user submits valid payment amount <amount>
    And Clicks on Pay At The Bank button
    Then Portal error is displayed "Error:Payment amount cannot be zero or greater than liability amount"

    Examples:
      | amount |
      | 0      |

  @SUC:05-18 @trial2
  Scenario Outline: UAT_M7_18-04-Verify the Process of Amend Payment Due
    Given navigate to My Tax>>make payment
    Then Outstanding Payments List Screen should be displayed
    Given Selects the Tax Type liability to be paid
    And click Pay Selected Button
    Then Payment Reference Number screen should be displayed
    Given user submits valid payment amount <amount>
    And Clicks on Pay At The Bank button
    Then confirmation and payment ref number is shown

    Examples:
      | amount |
      | 10     |