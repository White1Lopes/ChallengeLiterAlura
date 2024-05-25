package com.whitelopes.liter;

import com.whitelopes.liter.principal.MenuPrincipal;
import com.whitelopes.liter.repository.BooksRepository;
import com.whitelopes.liter.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterApplication implements CommandLineRunner {
	@Autowired
	private BooksService _bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal menuPrincipal = new MenuPrincipal(_bookService);
		menuPrincipal.ShowPrincipalMenu();
	}
}
