package com.sc.async.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceCompletableFuture {

	@Async
	public CompletableFuture<String> formString() throws InterruptedException {
		System.out.println("Looking up - formString");
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(3000);
		String processInfo = String.format("Processing is done with Thread Id = %d", Thread.currentThread().getId());
		return CompletableFuture.completedFuture(processInfo + ": formString - done");
	}
}
