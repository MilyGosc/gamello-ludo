package gg.gamello.ludo.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("task-executor")
public class TaskExecutorProperties {

	/**
	 * Default executors in pool number
	 */
	private int corePoolSize = 1;

	/**
	 * Max executors in pool number
	 */
	private int maxPoolSize = 2;

	/**
	 * Max tasks in queue number
	 */
	private int queueCapacity = 10000;
}
