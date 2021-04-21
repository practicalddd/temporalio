package com.practicalddd.temporalio.loanapplication.activities;

public class UnderwritingActivityImpl implements UnderwritingActivity{
    public void underwriteLoan(String loanNumber,double amount){
        System.out.printf(
                "\nLoan Number\n" + loanNumber + "\nunderwritten"
        );
    }
}
