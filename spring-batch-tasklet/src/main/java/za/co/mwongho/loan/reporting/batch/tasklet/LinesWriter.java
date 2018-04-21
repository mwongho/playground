package za.co.mwongho.loan.reporting.batch.tasklet;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

import com.opencsv.bean. StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import za.co.mwongho.loan.reporting.buisness.domain.model.LoanReport;

public class LinesWriter implements Tasklet, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LinesWriter.class);
    @Value("${outputFileURI}")
    private String outputFileURI;
    private List<LoanReport> loanReport;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
          .getJobExecution()
          .getExecutionContext();
        this.loanReport = (List<LoanReport>) executionContext.get("loanReports");
        logger.debug("Lines Writer initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    	if(loanReport != null) {
    		try(Writer writer = Files.newBufferedWriter(Paths.get(new FileSystemResource(this.outputFileURI).getURI()))) {
    			StatefulBeanToCsv<LoanReport> statefulBeanToCsv = new StatefulBeanToCsvBuilder<LoanReport>(writer).build();
    			statefulBeanToCsv.write(this.loanReport);
    		}
    		
    	} else {
    		 logger.debug("No lines written");
    	}
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Lines Writer ended.");
        return ExitStatus.COMPLETED;
    }
}