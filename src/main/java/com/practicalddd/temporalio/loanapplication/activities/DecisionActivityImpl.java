package com.practicalddd.temporalio.loanapplication.activities;

public class DecisionActivityImpl implements DecisionActivity{
    @Override
    public void decideOnLoan
            (String loanNumber,double amount) {
        System.out.printf(
                "\nLoan Number\n" + loanNumber + "\naccepted"
        );
    }
}
