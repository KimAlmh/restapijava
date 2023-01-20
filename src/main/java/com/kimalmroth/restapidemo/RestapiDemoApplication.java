package com.kimalmroth.restapidemo;

import com.kimalmroth.restapidemo.passwordManager.PasswordManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootApplication
@RestController
public class RestapiDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestapiDemoApplication.class, args);

	}

	@GetMapping("/")
	public String index(){
		return "Home Page";
	}
}
