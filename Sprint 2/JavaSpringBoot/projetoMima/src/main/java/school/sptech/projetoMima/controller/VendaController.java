package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.vendaDto.VendaMapper;
import school.sptech.projetoMima.dto.vendaDto.VendaRequestDto;
import school.sptech.projetoMima.dto.vendaDto.VendaResponseDto;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Venda;
import school.sptech.projetoMima.service.VendaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Registrar uma nova venda")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Venda registrada com sucesso", content = @Content(schema = @Schema(implementation = VendaResponseDto.class))), @ApiResponse(responseCode = "400", description = "Dados inválidos")})
    @PutMapping("/vender")
    public ResponseEntity<VendaResponseDto> vender(@Valid @RequestBody VendaRequestDto request) {
        Venda vendaConvertida = VendaMapper.toEntity(request);
        vendaService.vender(vendaConvertida);
        VendaResponseDto response = VendaMapper.toResponse(vendaConvertida);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Adicionar item a uma venda existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Item adicionado com sucesso", content = @Content(schema = @Schema(implementation = VendaResponseDto.class))), @ApiResponse(responseCode = "400", description = "Dados inválidos")})
    @PutMapping("/adicionar-item")
    public ResponseEntity<VendaResponseDto> adicionarItens(@Valid VendaRequestDto request, @Valid ItemVenda itemVenda) {
        Venda venda = VendaMapper.toEntity(request);
        vendaService.adicionarItem(itemVenda, venda);
        return ResponseEntity.status(200).body(VendaMapper.toResponse(venda));
    }

    @Operation(summary = "Remover item de uma venda")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Item removido com sucesso"), @ApiResponse(responseCode = "400", description = "Dados inválidos")})
    @DeleteMapping
    public ResponseEntity<Void> removerItemDaVenda(@Valid VendaRequestDto request, @Valid ItemVenda itemParaDeletar) {
        Venda venda = VendaMapper.toEntity(request);
        vendaService.deletarItemDaVenda(venda, itemParaDeletar);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Filtrar vendas por intervalo de datas")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Vendas encontradas", content = @Content(schema = @Schema(implementation = Venda.class))), @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada no intervalo")})
    @GetMapping("/filtrar-por-data")
    public ResponseEntity<List<Venda>> filtrarPorData(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorDatas(inicio, fim);
        if (vendasEncontradas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(vendasEncontradas);
    }

    @Operation(summary = "Filtrar vendas por cliente")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Vendas encontradas", content = @Content(schema = @Schema(implementation = Venda.class))), @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada para o cliente")})
    @GetMapping("/filtrar-por-cliente")
    public ResponseEntity<List<Venda>> filtrarPorCliente(@RequestParam Cliente cliente) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorCliente(cliente);
        if (vendasEncontradas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(vendasEncontradas);
    }

    @Operation(summary = "Filtrar vendas por faixa de valor")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Vendas encontradas", content = @Content(schema = @Schema(implementation = Venda.class))), @ApiResponse(responseCode = "204", description = "Nenhuma venda encontrada na faixa de valor")})
    @GetMapping("/filtrar-por-valor")
    public ResponseEntity<List<Venda>> filtrarPorValor(@RequestParam Double valorMinimo, @RequestParam Double valorMax) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorValor(valorMinimo, valorMax);
        if (vendasEncontradas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(vendasEncontradas);
    }
}
