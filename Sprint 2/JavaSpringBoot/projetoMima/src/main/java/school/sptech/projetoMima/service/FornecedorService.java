package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.exception.Fornecedor.FornecedorExistenteException;
import school.sptech.projetoMima.exception.Fornecedor.FornecedorNaoEncontradoException;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Optional<Fornecedor> findById(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor cadastrar(Fornecedor fornecedor) {
        if (fornecedorRepository.existsByNome(fornecedor.getNome())) {
            throw new FornecedorExistenteException("Fornecedor com este CNPJ já cadastrado!");
        }
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor deletar(Integer id) {
        Fornecedor fornecedor = findById(id).orElseThrow(() -> new FornecedorNaoEncontradoException("Fornecedor não encontrado!"));
        fornecedorRepository.delete(fornecedor);
        return fornecedor;
    }
}
