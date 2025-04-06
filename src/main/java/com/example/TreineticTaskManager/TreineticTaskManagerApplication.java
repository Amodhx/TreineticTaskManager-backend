package com.example.TreineticTaskManager;

import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.TreineticTaskManager", "com.example.TreineticTaskManager.dao"})
public class TreineticTaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreineticTaskManagerApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.addMappings(new PropertyMap<TaskEntity, TaskDTO>() {
			@Override
			protected void configure() {
				map().setUser_id(source.getUser().getId());
			}
		});

		return mapper;
	}
}
