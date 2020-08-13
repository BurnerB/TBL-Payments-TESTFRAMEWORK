Feature: [SUC:05-09] Receipt Document Control
 
 Background:
Given User navigates to the login page
When Enters the username "usera" and password "Passw0rd" to login
Then User should be logged in

@SUC:05-09
Scenario Outline: UAT_M7_09-01-UAT_M7_09-02-Verify the Process of Receipt Document Control
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Receipt Number From> and <Receipt Number To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Receipt Number From|Receipt Number To|Distribution Status|
|1|0					  |1     			  |In Stock|

@SUC:05-09
Scenario Outline: UAT_M7_09-03-Verify the Process Unusable Receipts
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Receipt Number - From|Receipt Number - To|Distribution Status|
|1|0					  |1      			  |Lost|


@SUC:05-09
Scenario Outline: UAT_M7_09-04-Verify the Process of Validation Failure
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Distribution Status> leaving other blank
And clicks on Receipt Document Control Save Button
Then message is displayed "Validation Error: Value is required"


#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Distribution Status|
|1		|In Stock|

@SUC:05-09
Scenario Outline: UAT_M7_09-05-Verify the Process of Issue Receipts To Cashier
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
 And checks Issued To Cashier
 And enters cashOffice <Cash Office2>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record

#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Receipt Number - From|Receipt Number - To|Distribution Status|Cash Office2|
|1	|2					  |3      			  |Issued|c|

 @SUC:05-09
Scenario Outline: UAT_M7_09-06-Verify the Process of Issue Receipts To Cash officer
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
 And checks Issued To Cash Officer
 And enters cashOfficer <Cash Officer>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record

#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Receipt Number - From|Receipt Number - To|Distribution Status|Cash Officer|
|1	|2					  |3      			  |Issued|userb|


@SUC:05-09
Scenario Outline: UAT_M7_09-07-Verify the Process of Cash Office Receipt
Given navigate to  Revenue Collection>>Receipt Document Control
When enters Receipt Document Control <Cash OfficeNo> and <Receipt Number - From> and <Receipt Number - To> and <Distribution Status>
And clicks on Receipt Document Control Save Button
Then The System saves the updated Receipt Document record


#increment the reciept Number -To after every successful run
Examples:
|Cash OfficeNo	|Receipt Number - From|Receipt Number - To|Distribution Status|
|1	|3					  |4     			  |In Stock|


