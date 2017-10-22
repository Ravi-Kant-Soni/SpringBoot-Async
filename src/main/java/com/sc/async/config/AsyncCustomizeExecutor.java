package com.sc.async.config;

import java.util.concurrent.Executor;

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

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(20);
		executor.setThreadNamePrefix("AsyncThreadSupportDemo");
		executor.initialize();
		return executor;

	}

}
