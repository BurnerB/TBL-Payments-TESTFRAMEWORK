Feature: [SUC:05-19] Bulk Upload - Payments
 
 Background:
Given User navigates to the login page
When Enters the username "tripsuser" and password "Passw0rd" to login
Then User should be logged in

#@SUC:05-19
 @trial2
Scenario Outline: UAT_M7_19-01-UAT_M7_19-02-Verify the Process of Payment Bulk Upload
Given navigate Revenue Collection>>Bulk Payment
When user clicks on browse button
And Selects the file to be uploaded <path>
And Then click Save Button on upload
#Then message is displayed "Bulk payment process successfully commenced"

Examples:
|path|
|C:\Users\v-bakam\Downloads\Bulk Upload Template.csv|

#@SUC:05-19
 @trial2
Scenario Outline: UAT_M7_19-03-Verify the Process of Payment Bulk Upload
Given navigate Revenue Collection>>Bulk Payment
When user clicks on browse button
And Selects the file to be uploaded <path>
And Then click Save Button on upload
#Then message is displayed "Please upload CSV file only"

Examples:
|path|
|C:\Users\v-bakam\Downloads\id_doc.png|