package com.yun.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.yun.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {
}
