package com.analyzer.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.analyzer.analyzer.email.ReceiveEmail;

@SpringBootApplication
public class AnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args);

		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3s";
		final String userName = "mailstone2022@gmail.com";
		final String password = "dtdxbgmdobizcyqf";
		ReceiveEmail.receiveEmail(pop3Host, mailStoreType, userName, password);
	}

}
