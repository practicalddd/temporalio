# Loan Application Workflow using Temporal

This repo provides an example for a typical Loan Application Workflow implemented using Temporal. The Loan Application has 3  stages - a Credit Bureau Check which checks for the credit worthiness of the customer / Underwriting which checks all aspects of the Loan Application  and finally Decisioning which makes the final decision to go ahead or not. 

## Tools used

The Workflow uses the Java SDK provided by Temporal. In addition this is run as a Spring Boot App which provides API(s) to initiate the workflow i.e. send across the signal to Temporal server as well as create Workers (though not optimal will be refactored in time). The Temporal server is run as a local Docker process with Cassandra as the store.

## Workflow Implementation

The Workflow Implementation _LoanApplicationWorkflowImpl_ has been implemented using the Stereotypes that Temporal provides for creating Workflow Implementations (@WorkflowInterface/@WorkflowMethod). 

## Activities

There are 3 activities _BureauCheckActivityImpl_ / _DecisionActivityImpl_ and _UnderwritingActivityImpl_ again implemented using Activity Stereotypes that Temporal Provides (@ActivityInterface)

## Workers

There is currently only one Worker _LoanApplicationWorker_ which caters to all 3 Activities and a single Task Queue. This obviously needs to be refactored. 

## Running the App

The whole App is a Spring Boot App using Maven as the build process. So build it and run it using "java -jar loanapplication-0.0.1-SNAPSHOT.jar". 

_To start a Loan Application Workflow Process_

curl to http://localhost:8080/loans/loanApplication as a POST with the following JSON structure -> 

{
	"loanNo": "XXX",
	"ssn": "YYY",
    "loanAmount" : 100
}

_To start the Worker_

curl to http://localhost:8080/loanapplicationworker as a GET

Enjoy the Flow !
