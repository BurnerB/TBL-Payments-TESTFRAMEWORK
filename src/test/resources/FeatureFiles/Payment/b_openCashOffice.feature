Feature: [SUC:05-15] Open a Cash Office
 
 Background:
Given User navigates to the login page
When Enters the username "tripsuser" and password "Passw0rd" to login
Then User should be logged in 

@SUC:05-15
Scenario: UAT_M7_02-01-UAT_M7_02-02-Verify the Process of Allocate Cash Till
Given navigate to Revenue Collection>>Cash Office Daily Control
When the User clicks Cash Office Name
And clicks Open Cash Office
Then System opens the Cash Office
