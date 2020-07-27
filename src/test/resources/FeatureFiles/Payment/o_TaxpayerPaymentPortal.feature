Feature: [SUC:05-18] Taxpayer Payment - Portal
 

Background:
Given User navigates to the Portal login page
When User clicks login as Taxpayer
And Enters the Portal username "payereturns" and password "Codei@maseno2020" to login

@SUC:05-18
Scenario Outline: UAT_M7_18-01-UAT_M7_18-0-Verify the Process of Pay In Bank
Given navigate to My Tax>>make payment
Then Outstanding Payments List Screen should be displayed
Given Selects the Tax Type liability to be paid
And click Pay Selected Button
Then Payment Reference Number screen should be displayed
Given user submits valid payment amount <amount>
And Clicks on Pay At The Bank button

Examples:
|amount|
|100|