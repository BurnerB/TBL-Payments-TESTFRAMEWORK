Feature: [SUC:05-04] Close Cash Till

#@SUC:05-04
# @trial1
Scenario: UAT_M5_04-01-UAT_M5_04-02-Verify the Process of Close Cash Till
 Given User navigates to the login page
 When Enters the username "atynkhoma" and password "Passw0rd" to login
 Then User should be logged in
 Given navigate to Revenue Collection>>Cash Till Maintenance
 When enters approved ref number
 And clicks on Open Cash Till button
 Then successfuly awaits approval

# @SUC:05-04
# @trial1
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

 #@SUC:05-04
# @trial1
 Scenario Outline: set status as closed
Given User navigates to the login page
When Enters the username "atynkhoma" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And clicks on close Cash Till button
And enters payment collection <payment>
And clicks Save on cash till mainenance
Then cashTill status should be <Status>

Examples:
|payment|Status|
|0|Closed|

# @trial1
# @SUC:05-11
Scenario: UAT_M5_04-03-Verify the Process of Cash Till Not Reconciled
Given User navigates to the login page
When Enters the username "atynkhoma" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When clicks on Request Cash Till button
Then success message displayed

# @trial1
#@SUC:05-11
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

# @trial1
 #@SUC:05-11
Scenario Outline: Cash Till Status Open
Given User navigates to the login page
When Enters the username "atynkhoma" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And enters float amount <amount>
 And clicks Save on cash till mainenance
Then Cash Till is now open

Examples:
|amount|
|100|

# @trial1
#@SUC:05-11
Scenario Outline:unreconciled errors
Given User navigates to the login page
When Enters the username "atynkhoma" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And clicks on close Cash Till button
And enters payment collection <payment>
 And clicks Save on cash till mainenance
Then unreconciled error message displayed

Examples:
|payment|
|1000|

# @trial1
 #@SUC:05-11
Scenario Outline: UAT_M5_04-04-Verify the Process of Cash Till Unreconciled
Given User navigates to the login page
When Enters the username "atynkhoma" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And clicks on close Cash Till button
And enters payment collection <payment>
 And clicks Save on cash till mainenance
Then unreconciled error message displayed

And enters payment collection <payment>
And clicks on close Cash Till button
 And clicks Save on cash till mainenance

Then cashTill status should be <Status>


Examples:
|payment|Status|
|1000|UnReconciled|
