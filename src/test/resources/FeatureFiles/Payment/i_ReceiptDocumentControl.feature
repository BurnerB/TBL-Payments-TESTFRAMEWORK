Feature: [SUC:05-09] Receipt Document Control
 
 Background:
Given User navigates to the login page
When Enters the username "tripsuser" and password "Passw0rd" to login
Then User should be logged in

#@SUC:05-09
Scenario Outline: UAT_M7_09-01-UAT_M7_09-02-Verify the Process of Receipt Document Control
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|Balaka Office1	|0					  |2      			  |In Stock|

#@SUC:05-09
Scenario Outline: UAT_M7_09-03-Verify the Process Unusable Receipts
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|BALCO101	|0					  |2      			  |Lost|


#@SUC:05-09
Scenario Outline: UAT_M7_09-04-Verify the Process of Validation Failure
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Distribution Status> leaving other blank
And clicks on Receipt Document Control Save Button
Then error message is displayed "Validation Error: Value is required"


#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|BALCO101		| | |In Stock|

#@SUC:05-09
Scenario Outline: UAT_M7_09-05-Verify the Process of Issue Receipts To Cashier
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
And checks Issued To Cashier
Then The System saves the updated Receipt Document record

#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|Balaka Office1	|0					  |2      			  |Issued|

@SUC:05-09
Scenario Outline: UAT_M7_09-06-Verify the Process of Issue Receipts To Cashier
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
And checks Issued To Cash Officer
Then The System saves the updated Receipt Document record

#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|Balaka Office1	|0					  |2      			  |Issued|


#@SUC:05-09
Scenario Outline: UAT_M7_09-07-Verify the Process of Cash Office Receipt
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash Office> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash Office	|Receipt Number - From|Receipt Number - To|Distribution Status|
|Balaka Office1	|0					  |2      			  |In Stock|


