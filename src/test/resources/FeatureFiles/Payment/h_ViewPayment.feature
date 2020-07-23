Feature: [SUC:05-17] View Payment
 
 Background:
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
Then User should be logged in

@SUC:05-17
Scenario Outline: UAT_M7_17-01-UAT_M7_17-02-Verify the Process of Payment Records Not Found
Given navigate to  Revenue Collection>>View Payment
When From Find Payment window enters <TIN> and <Payment Method>
And clicks search
Then System displays message Records Not Found

Examples:
|TIN	 |Payment Method|
|P0024640|Cash|