package com.sc.async.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ByDeafult Spring provides a SimpleAsyncTaskExecutor executor. Spring also
 * supports an AsyncConfigurerSupport class to customize AsyncExecutor.
 * 
 * @EnableAsync is used to detect asynchronized method with @Async by Spring.
 * 
 * @author ravik_000
 *
 */
@Configuration
@EnableAsync
public class AsyncCustomizeExecutor extends AsyncConfigurerSupport {

	@Value("${pool.size:10}")
	private int poolSize;
	
	@Value("${pool.size:10}")
	private int maxPoolSize;

	@Value("${queue.capacity:20}")
	private int queueCapacity;
	


	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}
}
