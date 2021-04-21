package com.practicalddd.temporalio.loanapplication.activities;

public class BureauCheckActivityImpl implements BureauCheckActivity {

    public void bureauCheck(String ssn){
        System.out.println("Bureau check done against the ssn"+ssn);
    }
}
