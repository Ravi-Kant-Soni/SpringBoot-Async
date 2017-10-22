package com.sc.async.rest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.async.service.AsyncService;

@RestController
public class AsyncController {
	private final static Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private AsyncService asyncService;

	@GetMapping("/")
	String home() throws InterruptedException, ExecutionException {

		Future<String> result = asyncService.process();
		while (!(result.isDone())) {
			Thread.sleep(3000); // 10-millisecond pause between each check
			LOGGER.info("Waiting for Long Process...");
		}
		LOGGER.info("Done..." + result.get());
		
		return "Hello World!";
	}

}
