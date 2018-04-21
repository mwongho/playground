package za.co.mwongho.loan.reporting.batch.tasklet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.mwongho.loan.reporting.batch.config.TaskletsConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TaskletsConfig.class)
@TestPropertySource(locations="classpath:test.properties")
public class TaskletsTest {
	private static final Logger LOG = LoggerFactory.getLogger(TaskletsTest.class);

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@Test
	public void testJobOk() throws Exception {
		
		JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

		JobExecution jobExecution = this.jobLauncher.run(this.job, jobParameters);

		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		Assert.assertEquals(0, jobExecution.getAllFailureExceptions().size());
		Assert.assertEquals(3, jobExecution.getStepExecutions().size());
		for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
			LOG.info("StepExecution " + stepExecution.getId() + ": StepName = " + stepExecution.getStepName()
					+ ", CommitCount = " + stepExecution.getCommitCount());

			Assert.assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
		}
	}

}
