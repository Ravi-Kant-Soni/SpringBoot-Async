package com.sc.async.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.async.service.AsyncService;
import com.sc.async.service.AsyncServiceCompletableFuture;

@RestController
public class AsyncController {
	private final static Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private AsyncServiceCompletableFuture asyncServiceCompletableFuture;

	@GetMapping("/")
	String home() throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		Future<String> result1 = asyncService.process();
		Future<String> result2 = asyncService.process();
		Future<String> result3 = asyncService.process();
		while (!(result1.isDone() && result2.isDone() && result3.isDone())) {
			Thread.sleep(3000); // 10-millisecond pause between each check
			LOGGER.info("Waiting for Long Process...");
		}

		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " ms");
		System.out.println(result1.get());
		System.out.println(result2.get());
		System.out.println(result3.get());

		return "Hello World!";
	}

	@GetMapping("/hallo")
	String hello() throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		CompletableFuture<String> completableFuture1 = asyncServiceCompletableFuture.formString();
		CompletableFuture<String> completableFuture2 = asyncServiceCompletableFuture.formString();
		CompletableFuture<String> completableFuture3 = asyncServiceCompletableFuture.formString();

		// Wait until they are all done
		while (!(completableFuture1.isDone() && completableFuture2.isDone() && completableFuture3.isDone())) {
			Thread.sleep(3000); // 10-millisecond pause between each check
			LOGGER.info("Waiting for Long Process...");
		}

		// wait until all they are completed.
		CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3).join();

		// Print results, including elapsed time
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " ms");
		System.out.println(completableFuture1.get());
		System.out.println(completableFuture2.get());
		System.out.println(completableFuture3.get());

		return "Hello World!";
	}

}
