package com.practicalddd.temporalio.loanapplication.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BureauCheckActivity {

    /** Does a credit check aganst the SSN */
    void bureauCheck(String ssn);
}
