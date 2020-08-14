Feature: [SUC:05-13] Revenue Collection Reports
 
 Background:
Given User navigates to the login page
When Enters the username "tripsuser" and password "Passw0rd" to login
Then User should be logged in

@SUC:05-13
Scenario Outline: UAT_M7_13-01-To verify the process of printing Cashiering Report
Given navigate to  Reporting>>Reports
When clicks Cashiering Report
Then Report screen should be displayed <Report Name>
When enters cash till reference as "%"
And clicks run report button
Then Report download should be generate <downloadpath> and <filename>

Examples:
|Report Name|	downloadpath	|filename|
|Cashiering Report|	C:\Users\v-bakam\Downloads	|Cashiering Report.pdf|

@SUC:05-13
Scenario Outline: UAT_M7_13-02-To verify the process of printing Daily Payment Report
Given navigate to  Reporting>>Reports
When clicks Daily Payment Report
Then Report screen should be displayed <Report Name>
When enters Daily Payment report parameters
And clicks run report button
Then Report download should be generate <downloadpath> and <filename>

Examples:
|Report Name|	downloadpath	|filename|
|Daily Payment Report|	C:\Users\v-bakam\Downloads	|Daily Payment Report.pdf|


@SUC:05-13-
Scenario Outline: UAT_M7_13-03-To verify the process of printing Bank Lodgement Slip
Given navigate to Revenue Collection>>Cash Office Daily Control
When selects Cash Office Name <cfN>
And clicks Open Cash Office
And clicks reconcile Cash Office
And clicks Generate Bank Lodgement Report button
Then Report download should be generate <downloadpath> and <filename>


Examples:
|cfN|	downloadpath	|filename|
|Balaka Office3|	C:\Users\v-bakam\Downloads	|BankLodgementSlip.pdf|

@SUC:05-13-
Scenario Outline: UAT_M7_13-04-To verify the process of printing Unreconciled Till Report
Given navigate to Revenue Collection>>Cash Office Daily Control
When selects Cash Office Name <cfN>
And clicks Open Cash Office
And clicks reconcile Cash Office
And clicks Generate Bank Lodgement Report button
Then Report download should be generate <downloadpath> and <filename>


Examples:
|cfN|	downloadpath	|filename|
|Balaka Office3|	C:\Users\v-bakam\Downloads	|BankLodgementSlip.pdf|

@SUC:05-13-
Scenario Outline: UAT_M7_13-05-To verify the process of printing Cash Office Enquiry (Cash Office Summary Report)
Given navigate to Revenue Collection>>Cash Office Daily Control
When selects Cash Office Name <cfN>
 And Opens the Cash Office
And clicks  Cash Office Summary Report button
Then Report download should be generate <downloadpath> and <filename>

Examples:
|cfN|	downloadpath	|filename|
|Balaka Office3|	C:\Users\v-bakam\Downloads	|CashOfficeSummary.pdf|

@SUC:05-13
Scenario Outline: UAT_M7_13-06-To verify the process of printing Receipt Document Summary
Given navigate to  Reporting>>Reports
When clicks Receipt Document Summary
Then Report screen should be displayed <Report Name>
When enters Receipt Document Summary report parameters
And clicks run report button
Then Report download should be generate <downloadpath> and <filename>

Examples:
|Report Name|	downloadpath	|filename|
|Receipt Document Summary|	C:\Users\v-bakam\Downloads	|Receipt Document Summary.pdf|


@SUC:05-13
Scenario Outline: UAT_M7_13-07-Print Report Validation Error
Given navigate to  Reporting>>Reports
When clicks Receipt Document Summary
Then Report screen should be displayed <Report Name>
And clicks run report button
Then message is displayed "Receipt Status: Validation Error: Value is required"
When enters Receipt Document Summary report parameters
And clicks run report button
Then Report download should be generate <downloadpath> and <filename>

Examples:
|Report Name|	downloadpath	|filename|
|Receipt Document Summary|	C:\Users\v-bakam\Downloads	|Receipt Document Summary.pdf|


@SUC:05-13
Scenario Outline: UAT_M7_13-08-Abandon Report
Given navigate to  Reporting>>Reports
When clicks Cashiering Report
Then Report screen should be displayed <Report Name>
When enters cash till reference as "%"
And clicks cancel button
Then navigated back to Reports screen

Examples:
|Report Name|
|Cashiering Report|