Feature: [SUC:05-03] Open Suspended Cash Till

@SUC:05-03
Scenario Outline: UAT_M7_03-01-UAT_M7_03-02-Verify the Process of Suspend Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
And clicks on Open Cash Till button
Then successfuly awaits approval

Examples:
|ref number|
|CT00001143|

@SUC:05-03
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

@SUC:05-03
Scenario Outline: status set to open
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
Then cashTill status should be <Status>

Examples:
|ref number|Status|
|CT00001143|Open|

@SUC:05-03
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
|CT00001141|

@SUC:05-03
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

@SUC:05-03
Scenario Outline: status set to suspended
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
Then cashTill status should be <Status>

Examples:
|ref number|Status|
|CT00001141|Suspended|