package com.viewnext.BatchStockBien.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

	/**
	 * Probando
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Todo hecho.");
		} else {
			System.out.println("Error, no se ha completado.");
		}
	}

}
