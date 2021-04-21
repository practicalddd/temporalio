package com.practicalddd.temporalio.loanapplication.api;

import com.practicalddd.temporalio.loanapplication.activities.BureauCheckActivityImpl;
import com.practicalddd.temporalio.loanapplication.activities.DecisionActivityImpl;
import com.practicalddd.temporalio.loanapplication.activities.UnderwritingActivityImpl;
import com.practicalddd.temporalio.loanapplication.workflow.LoanApplicationWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loanapplicationworker")
public class LoanApplicationWorker {

    @GetMapping
    public ResponseEntity loanApplicationWorker(){
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("LOAN_APPLICATION_TASK_QUEUE");
        worker.registerWorkflowImplementationTypes(LoanApplicationWorkflowImpl.class);
        // Activities are stateless and thread safe so a shared instance is used.
        worker.registerActivitiesImplementations(new BureauCheckActivityImpl());
        worker.registerActivitiesImplementations(new UnderwritingActivityImpl());
        worker.registerActivitiesImplementations(new DecisionActivityImpl());
        // Start listening to the Task Queue.
        factory.start();
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
