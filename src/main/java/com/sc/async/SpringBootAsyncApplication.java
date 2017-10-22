package com.sc.async;

import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sc.async.service.AsyncService;

@SpringBootApplication
public class SpringBootAsyncApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(SpringBootAsyncApplication.class);

	@Resource
	private AsyncService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncApplication.class, args);
	}

	/**
	 * Implement a run function of CommandLineRunner.
	 */
	@Override
	public void run(String... args) throws Exception {
		service.asyncMethod();
		service.asyncMethod();

		Future<String> process1 = service.process();
		Future<String> process2 = service.process();
		Future<String> process3 = service.process();

		while (!(process1.isDone() && process2.isDone() && process3.isDone())) {
			Thread.sleep(2000);
		}

		log.info("All Process is Done");

		log.info("Process 1: " + process1.get());
		log.info("Process 2: " + process2.get());
		log.info("Process 3: " + process3.get());
	}
}
