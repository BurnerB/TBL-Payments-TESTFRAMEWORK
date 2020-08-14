Feature: [SUC:05-16] Reverse Payment
 
 Background:
Given User navigates to the login page
When Enters the username "tripsuser" and password "Passw0rd" to login
Then User should be logged in

@SUC:05-16-
Scenario Outline: UAT_M7_16-01-UAT_M7_16-02-Verify the Process of Reverse Payment
Given navigate Revenue Collection>>Reverse Payment
When enters <TIN> on Find Payment page
And click search on find payment page
Then Searched results should be displayed in a table
When user clicks Taxpayers Payment to be reversed
And clicks Continue button
Then Payment Summary window displayed <TIN>
When clicks on payment list and view
And enters required comment <comment>
And clicks reverse button
Then Payment Summary window displayed <TIN>
When clicks confirm reversal button
Then message is displayed "Payment has been successfully reversed"


Examples:
|TIN		|comment|
|%	|Genuine miscalculation		  |


@SUC:05-16
Scenario Outline: UAT_M7_16-03-Verify the Process of Payment Records Not Found
Given navigate Revenue Collection>>Reverse Payment
When enters <TIN> on Find Payment page
And click search on find payment page
Then no records found


Examples:
|TIN		|
|B0013357	|

@SUC:05-16
Scenario Outline: UAT_M7_16-04-Verify the Process of Validation Failed
Given navigate Revenue Collection>>Reverse Payment
When enters <TIN> on Find Payment page
And click search on find payment page
Then Searched results should be displayed in a table
When user clicks Taxpayers Payment to be reversed
And clicks Continue button
Then Payment Summary window displayed <TIN>
When clicks on payment list and view
And clicks reverse button
Then message is displayed "Validation Error: Value is required"


Examples:
|TIN		|
|%	|