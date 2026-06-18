package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Integer i;
		i = Integer.MAX_VALUE;
		System.out.println(i);

		Long l;
		l = Long.MAX_VALUE;
		System.out.println(l);
	}

	@Bean
	CommandLineRunner initData(ContaBancariaRepository repo){
		return args -> {
			ContaBancaria c1 = new ContaBancaria();
			c1.setNomeTitular("Cliente 1");
			c1.setNumeroConta("12345-6");
			c1.setSaldo(2000.00);
			c1.setAtiva(true);

			ContaBancaria c2 = new ContaBancaria();
			c2.setNomeTitular("Cliente 2");
			c2.setNumeroConta("78910-1");
			c2.setSaldo(200.00);
			c2.setAtiva(true);
			repo.save(c1);
			repo.save(c2);
			System.out.println("contas cadastradas com sucesso ^-^");
		};
	}
}
