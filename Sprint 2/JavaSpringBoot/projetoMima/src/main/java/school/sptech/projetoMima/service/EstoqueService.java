package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.Estoque;
import school.sptech.projetoMima.exception.RoupaNaoEncontradaException;
import school.sptech.projetoMima.repository.EstoqueRepository;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Estoque buscarPorId(int id) {
        return estoqueRepository.findById(id).orElseThrow( () -> new RoupaNaoEncontradaException("Roupa com id não encontrada!"));
    }

    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }
}
