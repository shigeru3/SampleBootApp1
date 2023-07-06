package com.example.sample1app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleBootApp1Application {
	public static void main(String[] args) {
		SpringApplication.run(SampleBootApp1Application.class, args);
	}

	@RequestMapping("/")
	public String index(
			HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		String content = """
				<html>
					<head><title>Sample Application</title></head>
					<body>
						<h1>Sample Application</h1>
						<p>This is sample app HTML page</p>
					</body>
				</html>
				""";
		return content;
	}
}
