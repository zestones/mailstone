package com.client.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.client.client.controller.question.IQuestion;
import com.client.client.utils.Watcher;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		try {
			System.out.println(new File(IQuestion.FOLDER_QUESTION).exists());
			new Watcher(Paths.get(IQuestion.FOLDER_QUESTION)).processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}