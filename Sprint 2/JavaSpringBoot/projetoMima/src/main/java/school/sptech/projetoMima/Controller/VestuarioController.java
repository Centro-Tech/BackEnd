package school.sptech.projetoMima.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.DTO.VestuarioVendaDTO;
import school.sptech.projetoMima.Model.Fornecedor;
import school.sptech.projetoMima.Model.Vestuario;
import school.sptech.projetoMima.Repository.FornecedorRepository;
import school.sptech.projetoMima.Repository.VestuarioRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/vestuarios")
public class VestuarioController {

    @Autowired
    private VestuarioRepository vestuarioRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Vestuario> buscarPorId(@PathVariable Integer id) {
        Optional<Vestuario> vestuarioExistente = vestuarioRepository.findById(id);

        if (vestuarioExistente.isPresent()) {
            Vestuario vestuarioNovo = vestuarioExistente.get();
            return ResponseEntity.ok(vestuarioNovo);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estoque")
    public ResponseEntity<List<Vestuario>> buscarEstoque() {
        List<Vestuario> estoque = new ArrayList<>();

        for (Vestuario v : vestuarioRepository.findAll()) {
            if (v != null && v.getQuantidade() > 0 && !v.getVendido()) {
                estoque.add(v);
            }
        }

        if (!estoque.isEmpty()) {
            return ResponseEntity.ok(estoque);
        }

        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/vendidos")
    public ResponseEntity<List<VestuarioVendaDTO>> buscarVendidos() {
        List<Vestuario> vestuariosVendidos = vestuarioRepository.findVestuarioByQuantidadeVendidaGreaterThan(0);

        if (vestuariosVendidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<VestuarioVendaDTO> reposta = new ArrayList<>();
        for (Vestuario vestuario : vestuariosVendidos) {
            reposta.add(new VestuarioVendaDTO(vestuario.getCodigoIdentificacao(), vestuario.getNome(), vestuario.getTamanho(), vestuario.getQuantidadeVendida(), vestuario.getDatasVendas()));
        }

        return ResponseEntity.ok(reposta);
    }
    @GetMapping("/vendido")
    public ResponseEntity<VestuarioVendaDTO> buscarVendidoPorCodigo(@RequestParam String codigoIdentificacao) {
        if (codigoIdentificacao == null || codigoIdentificacao.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Vestuario> vestuarioVendido = vestuarioRepository
                .findByCodigoIdentificacaoAndQuantidadeVendidaGreaterThan(codigoIdentificacao, 0);

        if (vestuarioVendido.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Vestuario vestuario = vestuarioVendido.get();
        VestuarioVendaDTO reposta = new VestuarioVendaDTO(vestuario.getCodigoIdentificacao(), vestuario.getNome(), vestuario.getTamanho(), vestuario.getQuantidadeVendida(), vestuario.getDatasVendas()
        );

        return ResponseEntity.ok(reposta);
    }



    @GetMapping("/filtro-data-venda")
    public ResponseEntity<List<Vestuario>> buscarFiltroData(@RequestParam("inicio") LocalDate inicio,@RequestParam("fim") LocalDate fim
    ) {
        List<Vestuario> filtroData = vestuarioRepository.findVestuarioByDataVendaBetween(inicio, fim);
        if (filtroData.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(filtroData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VestuarioVendaDTO> venderVestuario(@PathVariable int id, @RequestBody Vestuario vestuario) {
        Vestuario vestuarioExistente = vestuarioRepository.findById(id).orElse(null);

        if (vestuarioExistente == null) {
            return ResponseEntity.status(404).body(null);
        }
        if (vestuario.getQuantidade() != null && vestuario.getQuantidade() > 0 && vestuarioExistente.getQuantidade() >= vestuario.getQuantidade()) {
            vestuarioExistente.setQuantidade(vestuarioExistente.getQuantidade() - vestuario.getQuantidade());

            if (vestuarioExistente.getQuantidade() == 0) {
                vestuarioExistente.setVendido(true);
            }

            List<LocalDate> datasDeVenda = vestuarioExistente.getDatasVendas();
            for (int i = 0; i < vestuario.getQuantidade(); i++) {
                datasDeVenda.add(LocalDate.now());
            }

            vestuarioExistente.setDatasVendas(datasDeVenda);

            vestuarioExistente.setQuantidadeVendida(vestuarioExistente.getQuantidadeVendida() + vestuario.getQuantidade());

            vestuarioRepository.save(vestuarioExistente);
            VestuarioVendaDTO vestuarioVendaDTO = new VestuarioVendaDTO(vestuarioExistente.getCodigoIdentificacao(), vestuarioExistente.getNome(), vestuarioExistente.getTamanho(), vestuario.getQuantidade(), datasDeVenda
            );

            return ResponseEntity.ok(vestuarioVendaDTO);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Vestuario> cadastrarVestuario(@RequestBody Vestuario vestuario) {
        if (vestuarioRepository.existsByCodigoIdentificacao(vestuario.getCodigoIdentificacao())) {
            return ResponseEntity.status(400).body(null);
        }

        String nomeVestuario = vestuario.getNome().toUpperCase();
        String tamanhoVestuario = vestuario.getTamanho().toUpperCase();

        String codigoIdentificacao = null;

        if (nomeVestuario.contains("BERMUDA")) {
            codigoIdentificacao = "BZ";
        } else if (nomeVestuario.contains("BLAZER")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("BLUSA")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("BRACELETE")) {
            codigoIdentificacao = "BR";
        } else if (nomeVestuario.contains("BRINCO")) {
            codigoIdentificacao = "BC";
        } else if (nomeVestuario.contains("CALÇA")) {
            codigoIdentificacao = "CL";
        } else if (nomeVestuario.contains("CAMISA")) {
            codigoIdentificacao = "CA";
        } else if (nomeVestuario.contains("CAMISETA")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("CARDIGAN")) {
            codigoIdentificacao = "TR";
        } else if (nomeVestuario.contains("CHEMISE")) {
            codigoIdentificacao = "CH";
        } else if (nomeVestuario.contains("COLAR")) {
            codigoIdentificacao = "CR";
        } else if (nomeVestuario.contains("CONJUNTO")) {
            codigoIdentificacao = "CO";
        } else if (nomeVestuario.contains("CROPPED")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("ELASTICO")) {
            codigoIdentificacao = "EL";
        } else if (nomeVestuario.contains("JAQUETA")) {
            codigoIdentificacao = "JA";
        } else if (nomeVestuario.contains("LENÇO")) {
            codigoIdentificacao = "LE";
        } else if (nomeVestuario.contains("MACACÃO")) {
            codigoIdentificacao = "MA";
        } else if (nomeVestuario.contains("MACAQUINHO")) {
            codigoIdentificacao = "MA";
        } else if (nomeVestuario.contains("PARKA")) {
            codigoIdentificacao = "PK";
        } else if (nomeVestuario.contains("PONCHO")) {
            codigoIdentificacao = "TR";
        } else if (nomeVestuario.contains("PULSEIRA")) {
            codigoIdentificacao = "PU";
        } else if (nomeVestuario.contains("REGATA")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("SAIA")) {
            codigoIdentificacao = "SA";
        } else if (nomeVestuario.contains("SHORT")) {
            codigoIdentificacao = "SH";
        } else if (nomeVestuario.contains("TOMARA QUE CAIA")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("TRICOT")) {
            codigoIdentificacao = "TR";
        } else if (nomeVestuario.contains("T-SHIRT")) {
            codigoIdentificacao = "BL";
        } else if (nomeVestuario.contains("VESTIDO")) {
            codigoIdentificacao = "VE";
        } else {
            return ResponseEntity.status(400).body(null);
        }

        Random random = new Random();
        int numeroAleatorio = 1000000 + random.nextInt(9000000);

        String codigoFinal = codigoIdentificacao + numeroAleatorio + tamanhoVestuario;

        vestuario.setCodigoIdentificacao(codigoFinal);
        vestuario.setDataRegistro(LocalDate.now());

        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(vestuario.getFornecedorId());
        if (!fornecedor.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }

        Fornecedor fornecedorExistente = fornecedor.get();
        vestuario.setFornecedorId(fornecedorExistente.getId());
        vestuario.setVendido(false);

        Vestuario novoVestuario = vestuarioRepository.save(vestuario);

        return ResponseEntity.status(201).body(novoVestuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVestuario(@PathVariable int id) {
        if (vestuarioRepository.existsById(id)) {
            vestuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
