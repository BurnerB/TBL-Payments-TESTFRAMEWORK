Feature: [SUC:13-11] Print Refund Documents
 
 Background:
Given User navigates to the login page
When Enters the username "tripscrmuser3" and password "Passw0rd" to login
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
#When From Payment Summary window enters <Name of Person Paying>


#And clicks Next button
#Then Payment Allocation Summary tab should be displayed
#When clicks on On Account Button
#Then Account Payment Details pop up window should be displayed
#When On Account Payment Details enters <TaxType> and <Amount Allocated>
#And clicks ok
#Then Payment Allocation Summary tab should be displayed

#Then Payment Summary window displayed <TIN>



Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |P0024640	|Geet Hireman		  |100|PAYE	|	1000		|