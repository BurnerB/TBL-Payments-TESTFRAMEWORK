Feature: [SUC:05-04] Close Cash Till

@SUC:05-04
Scenario Outline: UAT_M5_04-01-UAT_M5_04-02-Verify the Process of Close Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And enters float amount <amount>
And clicks Save
Then Cash Till is now open
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
And clicks on close Cash Till button
And enters payment collection <payment>
And clicks on close Cash Till button
Then prompt to click save appears
And clicks save
Then cashTill status should be <Status>

Examples:
|payment|Status|amount|
|0|closed|100|

Scenario Outline: UAT_M5_04-03-Verify the Process of Cash Till Not Reconciled
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
And clicks on close Cash Till button
And enters payment collection <payment>
And clicks on close Cash Till button
Then prompt to click save appears
And clicks save
Then unreconciled error message displayed

Examples:
|ref number|payment|
|CT00001174|1000|

Scenario Outline: UAT_M5_04-04-Verify the Process of Cash Till Unreconciled
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
And clicks on close Cash Till button
And enters payment collection <payment>
And clicks on close Cash Till button
Then prompt to click save appears
And clicks save
Then unreconciled error message displayed

And enters payment collection <payment>
And clicks on close Cash Till button
Then prompt to click save appears
And clicks save

And enters payment collection <payment>
And clicks on close Cash Till button
Then prompt to click save appears
And clicks save

Then cashTill status should be <Status>


Examples:
|ref number|payment|Status|
|CT00000721|1000|UnReconciled|
