package com.sc.async.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	Logger log  = LoggerFactory.getLogger(AsyncService.class);

	/**
	 * Methods with  Return Type.
	 * Annotate process method with @Async annotation so that it will execute in
	 * separate thread when it is invoked.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@Async
	public Future<String> process() throws InterruptedException {
		log.info("## process - Start processing with ID: " + Thread.currentThread().getId());
		log.info(Thread.currentThread().getName());
		Thread.sleep(3000);
		String processInfo = String.format("Processing is done with Thread Id = %d", Thread.currentThread().getId());
		return new AsyncResult<String>(processInfo);
	}

	/**
	 * Methods with void Return Type
	 * 
	 * @throws InterruptedException
	 */
	@Async
	public void asyncMethod() throws InterruptedException {
		log.info("## asyncMethod - Start processing with ID: " + Thread.currentThread().getId());
		log.info(Thread.currentThread().getName());
		Thread.sleep(3000);
		log.info("## asyncMethod - end processing with ID: " + Thread.currentThread().getId());
	}

}
