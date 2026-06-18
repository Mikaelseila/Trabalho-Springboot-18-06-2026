package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContaBancaria;
import com.example.demo.repository.ContaBancariaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaBancariaRepository repository;

    public ContaController(ContaBancariaRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public List<ContaBancaria> listarTodasContas(){
        return repository.findAll();
    }

    @PostMapping
    public ContaBancaria criarConta(@RequestBody ContaBancaria conta){
        return repository.save(conta);
    }

    @GetMapping("/{id}")
    public ContaBancaria buscarContaId(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/depositar")
    public ContaBancaria depositar(@PathVariable long id, @RequestBody Map<String, Double> payload) {
        ContaBancaria conta = repository.findById(id).orElse(null);
        if (conta != null) {
            Double valor = payload.get("valor");
            if (valor != null && valor > 0) {
                conta.depositar(valor);
                return repository.save(conta);
            }
            else {
                throw new RuntimeException("o valor inserido é inválido, pô .-.");
            }
        }
        return null;
    }

    @PutMapping("/{id}/sacar")
    public ContaBancaria sacar(@PathVariable long id, @RequestBody Map<String, Double> payload) {
        ContaBancaria conta = repository.findById(id).orElse(null);
        if (conta != null) {
            Double valor = payload.get("valor");
            if (valor != null && valor > 0) {
                conta.sacar(valor);
                return repository.save(conta);
            }
            else {
                throw new RuntimeException("você sabe que não dá pra sacar dinheiro negativo ou que tu não tem, né?");
            }
        }
        return null;
    }

    @PutMapping("/{id}/status")
    public ContaBancaria ativarDesativarConta(@PathVariable long id, @RequestParam boolean ativa) {
        ContaBancaria conta = repository.findById(id).orElse(null);
        if (conta != null) {
            conta.setAtiva(ativa);
            return repository.save(conta);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id){
        repository.deleteById(id);
    }


}
