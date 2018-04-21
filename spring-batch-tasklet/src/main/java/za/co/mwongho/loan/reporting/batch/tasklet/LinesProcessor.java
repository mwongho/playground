package za.co.mwongho.loan.reporting.batch.tasklet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import za.co.mwongho.loan.reporting.buisness.domain.model.Loan;
import za.co.mwongho.loan.reporting.buisness.domain.model.LoanReport;
import za.co.mwongho.loan.reporting.buisness.domain.model.Tuple;

public class LinesProcessor implements Tasklet, StepExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(LinesProcessor.class);

    private List<Loan> loans;
    private List<LoanReport> loanReports;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        for (Loan loan : loans) {
        	LOG.debug("Processing loan :{}", loan);
        }
        
        Map<Tuple, List<Loan>> loansPerTuple = this.loans.stream().collect(Collectors.groupingBy(loan -> new Tuple(loan.getNetwork(), loan.getProduct(), loan.getDate().getMonth())));
        LOG.debug("loansPerTuple :{}",loansPerTuple);
        
        Set<Tuple> keys = loansPerTuple.keySet();
        for (Tuple tuple : keys) {
        	List<Loan> loansForTuple = loansPerTuple.get(tuple);
        	long count = loansForTuple.size();
        	BigDecimal total = loansForTuple.stream().map(loan -> loan.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        	
        	this.loanReports.add(new LoanReport(tuple, count, total));
		}
        
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    	this.loanReports = new ArrayList<>();
        ExecutionContext executionContext = stepExecution
          .getJobExecution()
          .getExecutionContext();
        this.loans = (List<Loan>) executionContext.get("loans");
        LOG.debug("Lines Processor initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
        .getJobExecution()
        .getExecutionContext()
        .put("loanReports", this.loanReports);
        LOG.debug("Lines Processor ended.");
        return ExitStatus.COMPLETED;
    }
}