package com.practicalddd.temporalio.loanapplication.api;

public class LoanApplication {

    private String loanNo;
    private double loanAmount;
    private String ssn;

    public double getLoanAmount(){return this.loanAmount;}
    public String getLoanNo(){return this.loanNo;}
    public String getSsn(){return this.ssn;}
}
