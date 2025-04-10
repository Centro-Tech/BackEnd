package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Optional<Fornecedor> findById(Integer id) {
        return Optional.empty();
    }
}
