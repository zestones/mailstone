package com.server.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.server.controller.response.IResponse;
import com.server.server.utils.Watcher;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		try {
			System.out.println(new File(IResponse.FOLDER_RESPONSE).exists());
			new Watcher(Paths.get(IResponse.FOLDER_RESPONSE)).processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}