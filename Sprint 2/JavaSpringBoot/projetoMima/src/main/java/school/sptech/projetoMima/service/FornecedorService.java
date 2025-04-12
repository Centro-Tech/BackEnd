package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.exception.FornecedorNaoEncontradoException;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Optional<Fornecedor> findById(Integer id) {
        return Optional.empty();
    }

    public List<Fornecedor> listar () {
        return fornecedorRepository.findAll();
    }

    public Fornecedor cadastrar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Optional<Fornecedor> deletar (Integer id) {
        Optional<Fornecedor> fornecedor = findById(id);

        if (fornecedor.isPresent()) {
            fornecedorRepository.delete(fornecedor);
            return fornecedor;
        }
        throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!");
    }
}
