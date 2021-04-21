package com.practicalddd.temporalio.loanapplication.api;

import com.practicalddd.temporalio.loanapplication.workflow.LoanApplicationWorkflow;
import com.practicalddd.temporalio.loanapplication.workflow.LoanApplicationWorkflowImpl;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoanApi {
    @PostMapping("/loanApplication")
    public ResponseEntity loanApplication(@RequestBody LoanApplication loanApplication) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("LOAN_APPLICATION_TASK_QUEUE")
                .setWorkflowId("loan-application-workflow")
                .build();
        WorkflowClient client = WorkflowClient.newInstance(service);
        LoanApplicationWorkflow workflow = client.newWorkflowStub(LoanApplicationWorkflow.class,options);
        WorkflowExecution we = WorkflowClient.start(workflow::applyForALoan,loanApplication.getLoanNo(),loanApplication.getSsn(),loanApplication.getLoanAmount());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
