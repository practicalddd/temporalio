package com.practicalddd.temporalio.loanapplication.workflow;


import com.practicalddd.temporalio.loanapplication.activities.BureauCheckActivity;
import com.practicalddd.temporalio.loanapplication.activities.DecisionActivity;
import com.practicalddd.temporalio.loanapplication.activities.UnderwritingActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class LoanApplicationWorkflowImpl implements LoanApplicationWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(500)
            .build();
    private final ActivityOptions options = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();


    //Three activities as part of a loan Application - First the credit bureau check to see if the customer is ok to get a loan,
    // underwriting to see if the loan details are ok and finally the decision whether the loan is granted or not.

    private final BureauCheckActivity bureauCheck = Workflow.newActivityStub(BureauCheckActivity.class, options);
    private final UnderwritingActivity underWriting = Workflow.newActivityStub(UnderwritingActivity.class, options);
    private final DecisionActivity decision = Workflow.newActivityStub(DecisionActivity.class,options);

    /**
     * Orchestration of the loan application workflow
     * @param loanNumber
     * @param ssn
     * @param amount
     */
    public void applyForALoan(String loanNumber,String ssn, double amount){
        bureauCheck.bureauCheck(ssn);
        underWriting.underwriteLoan(loanNumber,amount);
        decision.decideOnLoan(loanNumber,amount);
    }
}
