package com.practicalddd.temporalio.loanapplication.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface UnderwritingActivity {
    void underwriteLoan(String loanNumber,double amount);
}
