Feature: [05-05-01] Suspend Cash Till

@SUC:05-05-01
Scenario Outline: UAT_M7_05-01-UAT_M7_05-02-Verify the Process of Suspend Cash Till
Given User navigates to the login page
When Enters the username "userb" and password "Passw0rd" to login
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
@SUC:05-05-01
Scenario: Cash Till Status Suspended
Given User navigates to the login page
When Enters the username "userb" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When enters approved ref number
Then Suspend CashTill
Then Cash Till is now suspended