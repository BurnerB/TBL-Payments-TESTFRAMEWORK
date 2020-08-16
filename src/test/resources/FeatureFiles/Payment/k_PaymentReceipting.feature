Feature: [SUC:05-06] Payment Receipting
 
 Background:
Given User navigates to the login page
When Enters the username "cashofficer4" and password "Passw0rd" to login
Then User should be logged in


 #@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-01-UAT_M7_06-02-Verify the Process of Payment Receipting
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
When From Payment Summary window enters <Name of Person Paying>
And enters designation <designation>
When From Payment Summary window enters <Name of Person Paying>
And enters designation <designation>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And clicks Next button
Then Payment Allocation Summary tab should be displayed
When clicks on On Account Button
Then Account Payment Details pop up window should be displayed
When On Account Payment Details enters <TaxType> and <Amount Allocated>
And clicks ok
Then Payment Allocation Summary tab should be displayed
Given From Payment Details window click on Save Button
  And clicks yes on payment confirmation popup
Then Payment Summary window displayed <TIN>
Given Payment Summary window click on Save Button
Then Reciept generated successfully

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|designation|
|Individual					 |P0017167	|DR Barack Obama		  |1000|Capital Gain Tax(CGT)	|	25		|supervisor|

#@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-03-Verify the Process of Taxpayer Not Registered.
Given navigate to  Revenue Collection>>Receive Payment
And check non registered taxpayer
And enters Non Registered Taxpayer Details <First Name> and <Last Name> and <National ID No> and <Address>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
Given From Payment Details window click on Save Button
And clicks proceed on the popup
And Payment list click on Save Button
Then message is displayed "PaymentReceipt.pdf"

Examples:
|First Name	|Last Name|National ID No|Address	     |amount|
|Barack		|Obama  |21465328      |Lilongwe Malawi|	25		|


#@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-04-Verify the Process of Validation Failed
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
 Then Payment Summary window displayed <TIN>
 When From Payment Summary window enters <Name of Person Paying>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And clicks Next button
Then Payment Allocation Summary tab should be displayed
When clicks on On Account Button
Then Account Payment Details pop up window should be displayed
And clicks ok on empty fields
Then message is displayed "Validation Error: Value is required."

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|
|Individual					 |P0017167	|DR Barack Obama	  |1000|


#@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-05-Verify the Process of Document Not Required (On Account)
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
When From Payment Summary window enters <Name of Person Paying>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And clicks Next button
Then Payment Allocation Summary tab should be displayed
When clicks on On Account Button
Then Account Payment Details pop up window should be displayed
When On Account Payment Details enters <TaxType> and <Amount Allocated>
And clicks ok
Then Payment Allocation Summary tab should be displayed
Given From Payment Details window click on Save Button
 And clicks yes on payment confirmation popup
 Then Payment Summary window displayed <TIN>
 Given Payment Summary window click on Save Button
 Then Reciept generated successfully

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |P0017167	|DR Barack Obama		  |1000|Non Resident Tax(NRT)	|	25		|


#@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-06-Verify the Process of Cash Till Limit Breach
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
And clicks search button
Then Payment Summary window displayed <TIN>
When From Payment Summary window enters <Name of Person Paying>
And clicks add button
Then Payment Details should be displayed
When user fills Payment Amount as <amount>
And clicks Next button
Then Payment Allocation Summary tab should be displayed
When clicks on On Account Button
Then Account Payment Details pop up window should be displayed
When On Account Payment Details enters <TaxType> and <Amount Allocated>
And clicks ok
Then message is displayed "Amount Allocated cannot be greater"

Examples:
|Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
|Individual					 |P0017167	|DR Barack Obama		  |1000|Non Resident Tax(NRT)	|	100000		|

 #@SUC:05-06
 @trial2
Scenario Outline: UAT_M7_06-07-Verify the Process of Document Allocation Payment.
Given navigate to  Revenue Collection>>Receive Payment
When click on Find Button
Then Find Entity pop up window should be displayed
When User enters <Taxpayer Classification Type> and <TIN>
  And clicks search button
  Then Payment Summary window displayed <TIN>
 When From Payment Summary window enters <Name of Person Paying>
 And clicks add button
  Then Payment Details should be displayed
  When user fills Payment Amount as <amount>
  And clicks Next button
  Then Payment Allocation Summary tab should be displayed
  When clicks on Document Allocation Button
  Then Find document Details pop up window should be displayed

 Examples:
  |Taxpayer Classification Type|TIN		|Name of Person Paying|amount|TaxType|Amount Allocated|
  |Individual					 |P0017167	|DR Barack Obama		  |1000|Non Resident Tax(NRT)	|	100000		|