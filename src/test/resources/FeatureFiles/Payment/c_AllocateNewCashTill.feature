Feature: [SUC:05-02] Allocate New Cash Till

#@SUC:05-02
Scenario: UAT_M7_02-01-UAT_M7_02-02-Verify the Process of Allocate Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

#@SUC:05-02
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

#@SUC:05-02
Scenario Outline: Cash Till Status Open
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the ref number
And enters float amount <amount>
And clicks Save
Then Cash Till is now open

Examples:
|amount|
|5000|

#Cash Till is then suspended to allow for creation of other Cash Tills
#Delete this scenario if no other cash till is to be created further down
#@SUC:05-02
Scenario: Cash Till Status Suspended
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the ref number
Then Suspend CashTill
Then Cash Till is now suspended

#@SUC:05-02
Scenario: UAT_M7_02-03-Verify the Process Supervisor Rejection
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

#@SUC:05-02
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

#@SUC:05-02
Scenario Outline: Cash Till Status Rejected
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the ref number
Then cashTill status should be <Status>

Examples:
|Status|
|Rejected|

#@SUC:05-02
Scenario: UAT_M7_02-04-Verify the Process Float Not Added
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

#@SUC:05-02
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

#@SUC:05-02
Scenario: Cash Till Status Open Error
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the ref number
And clicks Save
Then error message displayed

#@SUC:05-02
Scenario Outline: UAT_M7_03-01-UAT_M7_03-02-Verify the Process of Open Suspended Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
And clicks on Open Cash Till button
Then successfuly awaits approval

Examples:
|ref number|
|CT00000721|

#@SUC:05-02
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

#@SUC:05-02
Scenario Outline: status set to open
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
Then cashTill status should be <Status>

Examples:
|ref number|Status|
|CT00000721|Open|

@SUC:05-02
Scenario Outline: UAT_M7_03-03-Verify the Process of Supervisor rejection
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
And clicks on Open Cash Till button
Then successfuly awaits approval

Examples:
|ref number|
|CT00000721|

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
Scenario Outline: status set to suspended
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
Then cashTill status should be <Status>

Examples:
|ref number|Status|
|CT00000721|Suspended|