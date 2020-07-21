Feature: [05-05-01] Suspend Cash Till

@SUC:05-05-01
Scenario Outline: UAT_M7_05-01-UAT_M7_05-02-Verify the Process of Suspend Cash Till
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in 
Given navigate to Revenue Collection>>Cash Till Maintenance
When selects the <ref number>
Then Suspend CashTill
Then cashTill status should be <Status>

Examples:
|ref number|Status|
|CT00001143|Suspended|