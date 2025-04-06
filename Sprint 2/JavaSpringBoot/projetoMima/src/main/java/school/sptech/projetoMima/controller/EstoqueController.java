package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.EstoqueMapper;
import school.sptech.projetoMima.dto.EstoqueResponseDto;
import school.sptech.projetoMima.dto.VestuarioVendaDTO;
import school.sptech.projetoMima.entity.Estoque;
import school.sptech.projetoMima.service.EstoqueService;
import school.sptech.projetoMima.versãoAntiga.Fornecedor;
import school.sptech.projetoMima.versãoAntiga.Vestuario;
import school.sptech.projetoMima.repository.FornecedorRepository;
import school.sptech.projetoMima.repository.VestuarioRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/vestuarios")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDto> buscarPorId(@PathVariable Integer id) {
        Estoque estoqueEncontrado = estoqueService.buscarPorId(id);

        EstoqueResponseDto cursoResponse = EstoqueMapper.toResponse(estoqueEncontrado);
        return ResponseEntity.status(200).body(cursoResponse);
    }

    @Operation(summary = "Buscar todos os vestuários em estoque") @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = @Content(schema = @Schema(implementation = Vestuario.class))), @ApiResponse(responseCode = "404", description = "Nenhum vestuário em estoque") })
    @GetMapping("/estoque")
    public ResponseEntity<List<EstoqueResponseDto>> listarEstoque() {
        List<Estoque> estoque = estoqueService.listarEstoque();

        if (estoque.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<EstoqueResponseDto> response = estoque.stream()
                .map(EstoqueMapper::toResponse)
                .toList();

        return ResponseEntity.status(200).body(response);
    }

 /*   @Operation(summary = "Buscar todos os vestuários vendidos") @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de vestuários vendidos retornada com sucesso", content = @Content(schema = @Schema(implementation = VestuarioVendaDTO.class))), @ApiResponse(responseCode = "204", description = "Nenhum vestuário vendido encontrado") })
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

    @Operation(summary = "Buscar vestuário vendido por código de identificação") @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Vestuário vendido encontrado", content = @Content(schema = @Schema(implementation = VestuarioVendaDTO.class))), @ApiResponse(responseCode = "400", description = "Código de identificação inválido"), @ApiResponse(responseCode = "204", description = "Vestuário não encontrado ou não vendido") })
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

    @Operation(summary = "Buscar vestuários vendidos entre duas datas") @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de vestuários no intervalo retornada com sucesso", content = @Content(schema = @Schema(implementation = Vestuario.class))), @ApiResponse(responseCode = "404", description = "Nenhum vestuário vendido no período") })
    @GetMapping("/filtro-data-venda")
    public ResponseEntity<List<Vestuario>> buscarFiltroData(@RequestParam("inicio") LocalDate inicio,@RequestParam("fim") LocalDate fim
    ) {
        List<Vestuario> filtroData = vestuarioRepository.findVestuarioByDataVendaBetween(inicio, fim);
        if (filtroData.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(filtroData);
    }

    @Operation(summary = "Realizar venda de vestuário") @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Venda realizada com sucesso", content = @Content(schema = @Schema(implementation = VestuarioVendaDTO.class))), @ApiResponse(responseCode = "400", description = "Quantidade inválida ou insuficiente em estoque"), @ApiResponse(responseCode = "404", description = "Vestuário não encontrado") })
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

    @Operation(summary = "Cadastrar novo vestuário") @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Vestuário cadastrado com sucesso", content = @Content(schema = @Schema(implementation = Vestuario.class))), @ApiResponse(responseCode = "400", description = "Dados inválidos ou código duplicado"), @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado") })
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

    @Operation(summary = "Deletar vestuário") @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Vestuário deletado com sucesso"), @ApiResponse(responseCode = "404", description = "Vestuário não encontrado") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVestuario(@PathVariable int id) {
        if (vestuarioRepository.existsById(id)) {
            vestuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }*/
}
