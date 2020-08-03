Feature: [SUC:13-11] Print Refund Documents
 
 Background:
Given User navigates to the login page
When Enters the username "tripscrmuser12" and password "Passw0rd" to login
Then User should be logged in

@SUC:13-11
Scenario Outline: UAT_M7_14-01-Verify the Process of print Taxpayer documents
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And From Payment Details window click on Save Button
And clicks yes on confirmation prompt
Given Payment Summary window click on Save Button
Then Reciept generated successfully

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |20000129	|Geet Hireman		  |100|PAYE	|	1000		|

@SUC:13-11
Scenario Outline: UAT_M7_14-02-Verify the Process of print Taxpayer documents
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And From Payment Details window click on Save Button
And clicks yes on confirmation prompt
Given Payment Summary window click on Save Button
Then Reciept generated successfully

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |20000129	|Geet Hireman		  |100|PAYE	|	1000		|

@SUC:13-11
Scenario Outline: UAT_M7_14-03-Verify the Process of print Taxpayer documents
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And From Payment Details window click on Save Button
And clicks yes on confirmation prompt
Given Payment Summary window click on Save Button
Then Reciept generated successfully

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |20000129	|Geet Hireman		  |100|PAYE	|	1000		|