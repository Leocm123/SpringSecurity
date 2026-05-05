package com.example.demo.services;

import com.example.demo.models.PessoaModel;
import com.example.demo.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> buscarTodosPessoas() { return pessoaRepository.findAll(); }

    public PessoaModel criarPessoa(PessoaModel pessoaModel) { return pessoaRepository.save(pessoaModel); }

    public Optional<PessoaModel> buscarPessoaId(Long id) { return pessoaRepository.findById(id); }

    public PessoaModel atualizarPessoa(Long id, PessoaModel PessoaModel) {
        PessoaModel model = pessoaRepository.findById(id).get();
        model.setNome(PessoaModel.getNome());
        return pessoaRepository.save(model);
    }

    public void excluir(Long id) { pessoaRepository.deleteById(id);}

}
