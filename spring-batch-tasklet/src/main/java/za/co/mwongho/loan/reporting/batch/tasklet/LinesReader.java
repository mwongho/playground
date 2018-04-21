package za.co.mwongho.loan.reporting.batch.tasklet;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

import za.co.mwongho.loan.reporting.buisness.domain.model.Loan;

@Component
public class LinesReader implements Tasklet, StepExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(LinesReader.class);
	@Value("${inputFileURI}")
	private String inputFileURI;
    private List<Loan> loans;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    	loans = new ArrayList<>();
    	LOG.debug("inputFileURI {}",inputFileURI);
        LOG.debug("Lines Reader initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    	
    	try(Reader reader = Files.newBufferedReader(new FileSystemResource(this.inputFileURI).getFile().toPath())) {
    		CsvToBeanBuilder<Loan> beanBuilder =  new CsvToBeanBuilder<>(reader);  	
    		this.loans = beanBuilder.withType(Loan.class).withSeparator(',').withQuoteChar('\'').build().parse();
    	} catch (IOException e) {
			LOG.error("File not found");
		}
    	LOG.debug("Loans :{}", loans);
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
          .getJobExecution()
          .getExecutionContext()
          .put("loans", this.loans);
        LOG.debug("Lines Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
