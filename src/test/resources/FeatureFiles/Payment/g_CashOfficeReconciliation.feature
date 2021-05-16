Feature: [SUC:05-11] Cash Office Reconciliation

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-01-UAT_M5_11-02-Verify the Process of Cash Office Reconciliation
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks reconcile Cash Office

    Examples:
      | cfN            |
      | Balaka Office3 |

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-03-Verify the Process of Invalid Tills
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks Open Cash Office
    Then System opens the Cash Office
    And leaves cash office blank and click reconcile
    Then message is displayed "Cash Office Name: Validation Error: Value is required."

    Examples:
      | cfN            |
      | Balaka Office3 |

#@SUC:05-11
  @trial1-
  Scenario Outline: UAT_M5_11-05-Verify the Process of Invalid Data
    Given User navigates to the login page
    When Enters the username "cashsupervisor11" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks reconcile Cash Office
    When enters cash till reference
    Then The System displays the Unreconciled Cash Till details
    When clicks on Save Button
    Then message is displayed "Enter Correct Adjustment Amount"

    Examples:
      | cfN                                |
      | Nsonkho Revenue Collection Point 1 |

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-06-Verify the Process of Unreconciled Report
    Given User navigates to the login page
    When Enters the username "cashsupervisor11" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks reconcile Cash Office
#Then message is displayed "CashTills have been detected for this Cash Office"
    When clicks Generate Unreconciled Report button
    Then Report download should be generate <downloadpath> and <filename>


    Examples:
      | cfN                 | downloadpath                     | filename                   |
      | Mulanje Cash office | C:\Users\barnaby.kamau\Downloads | UnreconciledTillReport.pdf |

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-04-Verify the Process of Unreconciled Tills
    Given User navigates to the login page
    When Enters the username "cashofficer29" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks reconcile Cash Office
    When enters cash till reference
    Then The System displays the Unreconciled Cash Till details
    When user enters <Adjustment Reason>
    Then clicks on Save Button
#And clicks reconcile Cash Office

    Examples:
      | cfN                                | Adjustment Reason |
      | Nsonkho Revenue Collection Point 1 | Calculation Error |



#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-07-Verify the Process of Cash Office Summary Report
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks  Cash Office Summary Report button
#Then message is displayed "CashTills have been detected for this Cash Office"
    Then Report download should be generate <downloadpath> and <filename>


    Examples:
      | cfN            | downloadpath                     | filename              |
      | Balaka Office3 | C:\Users\barnaby.kamau\Downloads | CashOfficeSummary.pdf |

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_11-08-Verify the Process of Bank Lodgement Report
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks reconcile Cash Office
    And clicks Generate Bank Lodgement Report button
#Then message is displayed "CashTills have been detected for this Cash Office"
    Then Report download should be generate <downloadpath> and <filename>


    Examples:
      | cfN            | downloadpath                     | filename              |
      | Balaka Office3 | C:\Users\barnaby.kamau\Downloads | BankLodgementSlip.pdf |

#@SUC:05-11
  @trial1
  Scenario Outline: UAT_M5_15-01-UAT_M5_15-02-Verify the Process of Open a Cash Office
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd" to login
    Then User should be logged in
    Given navigate to Revenue Collection>>Cash Office Daily Control
    When selects Cash Office Name <cfN>
    And clicks Open Cash Office
    Then System opens the Cash Office

    Examples:
      | cfN            |
      | Balaka Office3 |