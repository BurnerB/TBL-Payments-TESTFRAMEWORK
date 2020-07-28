Feature: [SUC:05-02] Allocate New Cash Till
@SUC:05-02
Scenario: UAT_M7_02-01-UAT_M7_02-02-Verify the Process of Allocate Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

@SUC:05-02
Scenario: Approve Cash Till
Given Open CRM URL Module
 And Close Popup Window
 And Click on Case management dropdown
 And click on Revenue Collection application
Then switch to frame
When enters reference number in search results
Then switch to frame 
When Click selected Reference Number
And clicks Approve from the dropdown
And click save on Payments
Then Application Account Adjustment status should be "Approved"

@SUC:05-02
Scenario Outline: Cash Till Status Open
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And enters float amount <amount>
And clicks Save
Then Cash Till is now open

Examples:
|amount|
|100|

#Cash Till is then suspended to allow for creation of other Cash Tills
#Delete this scenario if no other cash till is to be created further down
@SUC:05-02
Scenario: Cash Till Status Suspended
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
Then Suspend CashTill
Then Cash Till is now suspended

@SUC:05-02
Scenario: UAT_M7_02-03-Verify the Process Supervisor Rejection
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

@SUC:05-02
Scenario Outline: Reject Cash Till
Given Open CRM URL Module
 And Close Popup Window
 And Click on Case management dropdown
 And click on Revenue Collection application
Then switch to frame
When enters reference number in search results
Then switch to frame 
When Click selected Reference Number
And clicks Decline from the dropdown
Then Enter Outcome Notes <Notes>
And Enter Outcome Reason for Taxpayer accounting
And click save on Payments
Then Application Account Adjustment status should be "Rejected"

Examples:
|    Notes            |Reason|
|Invalid Documentation|Duplicate entity found|

@SUC:05-02
Scenario: UAT_M7_02-04-Verify the Process Float Not Added
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

@SUC:05-02
Scenario: Approve Cash Till
Given Open CRM URL Module
 And Close Popup Window
 And Click on Case management dropdown
 And click on Revenue Collection application
Then switch to frame
When enters reference number in search results
Then switch to frame 
When Click selected Reference Number
And clicks Approve from the dropdown
And click save on Payments
Then Application Account Adjustment status should be "Approved"

@SUC:05-02
Scenario: Cash Till Status Open Error
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And clicks Save
Then error message displayed