package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Fornecedor;
import school.sptech.projetoMima.Model.Vestuario;
import school.sptech.projetoMima.Repository.FornecedorRepository;
import school.sptech.projetoMima.Repository.VestuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private VestuarioRepository vestuarioRepository;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscar(@PathVariable int id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            Fornecedor fornecedorNovo = fornecedor.get();
            return ResponseEntity.ok(fornecedorNovo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtro-data-registro")
    public ResponseEntity<List<Fornecedor>> filtrarPorDataRegistro(
            @RequestParam("inicio") LocalDate inicio,
            @RequestParam("fim") LocalDate fim) {

        List<Fornecedor> fornecedores = fornecedorRepository.findFornecedorByDataRegistroBetween(inicio, fim);

        if (fornecedores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(fornecedores);
    }

    @GetMapping("/{fornecedorId}/vestuarios")
    public ResponseEntity<List<Vestuario>> buscarVestuariosPorFornecedor(@PathVariable Integer fornecedorId) {
        List<Vestuario> vestuarios = vestuarioRepository.findByFornecedorId(fornecedorId);

        if (vestuarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(vestuarios);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(@RequestBody Fornecedor fornecedor) {
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            return ResponseEntity.badRequest().body(fornecedor);
        }

        return ResponseEntity.ok(fornecedorRepository.save(fornecedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable int id, @RequestBody Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorAtual = fornecedorRepository.findById(id);
        if (fornecedorAtual.isPresent()) {
            Fornecedor fornecedorNovo = fornecedorAtual.get();
            if (fornecedor.getNome() != null) {
                fornecedorNovo.setNome(fornecedor.getNome());
            }
            if (fornecedor.getCnpj() != null) {
                fornecedorNovo.setCnpj(fornecedor.getCnpj());
            }
            if (fornecedor.getEndereco() != null) {
                fornecedorNovo.setEndereco(fornecedor.getEndereco());
            }
            if (fornecedor.getTelefone() != null) {
                fornecedorNovo.setTelefone(fornecedor.getTelefone());
            }
            if (fornecedor.getEmail() != null) {
                fornecedorNovo.setEmail(fornecedor.getEmail());
            }
            fornecedorRepository.save(fornecedorNovo);
            return ResponseEntity.ok(fornecedorNovo);
        }
        return ResponseEntity.status(409).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Fornecedor> excluir(@PathVariable int id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepository.deleteById(id);
            return ResponseEntity.ok(fornecedor.get());
        }
        return ResponseEntity.notFound().build();
    }
}
