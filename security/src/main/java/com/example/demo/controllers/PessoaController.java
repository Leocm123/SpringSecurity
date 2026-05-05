package com.example.demo.controllers;

import com.example.demo.models.PessoaModel;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/pessoas")
@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaModel>> buscarTodosOsPessoas(){
        List<PessoaModel> listaDePessoas = pessoaService.buscarTodosPessoas();
        return ResponseEntity.<List<PessoaModel>>ok(listaDePessoas);
    }

    @PostMapping
    public ResponseEntity<PessoaModel> criarPessoa(@RequestBody PessoaModel pessoaModel){

        PessoaModel pessoaCriada =  pessoaService.criarPessoa(pessoaModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(pessoaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).body(pessoaCriada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Optional<PessoaModel> buscarPessoaPorId(@PathVariable Long id){ return pessoaService.buscarPessoaId(id); }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizar(@PathVariable Long id, @RequestBody PessoaModel pessoaModel){
        PessoaModel pessoaAtualizada = pessoaService.atualizarPessoa(id,pessoaModel);

        return ResponseEntity.ok().body(pessoaAtualizada);
    }

}
