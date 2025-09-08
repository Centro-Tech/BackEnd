package school.sptech.projetoMima.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.core.application.dto.vendaDto.VendaRequestDto;
import school.sptech.projetoMima.core.application.dto.vendaDto.VendaResponseDto;
import school.sptech.projetoMima.core.application.dto.vendaDto.VendaMapper;
import school.sptech.projetoMima.core.application.command.Venda.*;
import school.sptech.projetoMima.core.application.usecase.Venda.*;
import school.sptech.projetoMima.core.domain.Venda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    // Injeção dos Use Cases
    private final CriarVendaUseCase criarVendaUseCase;
    private final FiltrarVendasPorDataUseCase filtrarVendasPorDataUseCase;
    private final FiltrarVendasPorClienteUseCase filtrarVendasPorClienteUseCase;
    private final FiltrarVendasPorValorUseCase filtrarVendasPorValorUseCase;
    private final RemoverItemDaVendaUseCase removerItemDaVendaUseCase;
    private final RemoverItemDaVendaComDtoUseCase removerItemDaVendaComDtoUseCase;

    @Autowired
    public VendaController(CriarVendaUseCase criarVendaUseCase,
                          FiltrarVendasPorDataUseCase filtrarVendasPorDataUseCase,
                          FiltrarVendasPorClienteUseCase filtrarVendasPorClienteUseCase,
                          FiltrarVendasPorValorUseCase filtrarVendasPorValorUseCase,
                          RemoverItemDaVendaUseCase removerItemDaVendaUseCase,
                          RemoverItemDaVendaComDtoUseCase removerItemDaVendaComDtoUseCase) {
        this.criarVendaUseCase = criarVendaUseCase;
        this.filtrarVendasPorDataUseCase = filtrarVendasPorDataUseCase;
        this.filtrarVendasPorClienteUseCase = filtrarVendasPorClienteUseCase;
        this.filtrarVendasPorValorUseCase = filtrarVendasPorValorUseCase;
        this.removerItemDaVendaUseCase = removerItemDaVendaUseCase;
        this.removerItemDaVendaComDtoUseCase = removerItemDaVendaComDtoUseCase;
    }

    @Operation(summary = "Registrar uma nova venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Venda registrada com sucesso",
                    content = @Content(schema = @Schema(implementation = VendaResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/vender")
    public ResponseEntity<VendaResponseDto> criarVenda(@Valid @RequestBody VendaRequestDto request) {
        // Converter DTO para Command
        CriarVendaCommand command = new CriarVendaCommand(
            request.getValorTotal(),
            request.getClienteId(),
            request.getItensVenda(),
            request.getFuncionarioId()
        );

        // Executar Use Case
        Venda venda = criarVendaUseCase.execute(command);

        // Converter para DTO de resposta
        VendaResponseDto response = VendaMapper.toResponseDto(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Filtrar vendas por intervalo de datas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vendas encontradas"),
        @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada no intervalo")
    })
    @GetMapping("/filtrar-por-data")
    public ResponseEntity<List<VendaResponseDto>> filtrarPorData(@RequestParam LocalDate inicio,
                                                               @RequestParam LocalDate fim) {
        // Converter parâmetros para Command
        FiltrarVendasPorDataCommand command = new FiltrarVendasPorDataCommand(inicio, fim);

        // Executar Use Case
        List<Venda> vendas = filtrarVendasPorDataUseCase.execute(command);

        if (vendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Converter para DTOs de resposta
        List<VendaResponseDto> response = vendas.stream()
                .map(VendaMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Filtrar vendas por cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vendas encontradas"),
        @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada para o cliente")
    })
    @GetMapping("/filtrar-por-cliente/{clienteId}")
    public ResponseEntity<List<VendaResponseDto>> filtrarPorCliente(@PathVariable Integer clienteId) {
        // Converter parâmetro para Command
        FiltrarVendasPorClienteCommand command = new FiltrarVendasPorClienteCommand(clienteId);

        // Executar Use Case
        List<Venda> vendas = filtrarVendasPorClienteUseCase.execute(command);

        if (vendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Converter para DTOs de resposta
        List<VendaResponseDto> response = vendas.stream()
                .map(VendaMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Filtrar vendas por faixa de valor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vendas encontradas"),
        @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada na faixa de valor")
    })
    @GetMapping("/filtrar-por-valor")
    public ResponseEntity<List<VendaResponseDto>> filtrarPorValor(@RequestParam Double valorMinimo,
                                                                @RequestParam Double valorMax) {
        // Converter parâmetros para Command
        FiltrarVendasPorValorCommand command = new FiltrarVendasPorValorCommand(valorMinimo, valorMax);

        // Executar Use Case
        List<Venda> vendas = filtrarVendasPorValorUseCase.execute(command);

        if (vendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Converter para DTOs de resposta
        List<VendaResponseDto> response = vendas.stream()
                .map(VendaMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remover item específico de uma venda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item removido com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Venda ou item não encontrado")
    })
    @DeleteMapping("/{idVenda}/itens/{idItemVenda}")
    public ResponseEntity<VendaResponseDto> removerItemDaVendaComDto(@PathVariable Integer idVenda,
                                                                   @PathVariable Integer idItemVenda) {
        // Converter parâmetros para Command
        RemoverItemDaVendaComDtoCommand command = new RemoverItemDaVendaComDtoCommand(idVenda, idItemVenda);

        // Executar Use Case
        Venda venda = removerItemDaVendaComDtoUseCase.execute(command);

        // Converter para DTO de resposta
        VendaResponseDto response = VendaMapper.toResponseDto(venda);
        return ResponseEntity.ok(response);
    }
}
