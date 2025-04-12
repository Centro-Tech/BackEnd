package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.VestuarioVendaDTO;
import school.sptech.projetoMima.dto.itemDto.ItemListDto;
import school.sptech.projetoMima.dto.itemDto.ItemMapper;
import school.sptech.projetoMima.dto.itemDto.ItemRequestDto;
import school.sptech.projetoMima.dto.itemDto.ItemResponseDto;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.entity.Item;
import school.sptech.projetoMima.service.FornecedorService;
import school.sptech.projetoMima.service.ItemService;

import java.util.*;

@RestController
@RequestMapping("/vestuarios")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> buscarPorId(@PathVariable Integer id) {
        Item itemEncontrado = itemService.buscarPorId(id);

        ItemResponseDto cursoResponse = ItemMapper.toResponse(itemEncontrado);
        return ResponseEntity.status(200).body(cursoResponse);
    }

    @Operation(summary = "Buscar todos os vestuários em estoque")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = @Content(schema = @Schema(implementation = Item.class))), @ApiResponse(responseCode = "404", description = "Nenhum vestuário em estoque") })
    @GetMapping("/estoque")
    public ResponseEntity<List<ItemListDto>> listarEstoque() {
        List<Item> item = itemService.listarEstoque();

        if (item.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ItemListDto> response = item.stream()
                .map(ItemMapper::toList)
                .toList();

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{qtd}")
    public ResponseEntity<ItemResponseDto> realizarVenda (@RequestBody ItemResponseDto item, Integer qtdParaVender) {

        if (item.getQtdEstoque() > 0 && item.getQtdEstoque() >= qtdParaVender) {
            itemService.vender(item, qtdParaVender);
            return ResponseEntity.status(200).body(item);
        }

        return ResponseEntity.status(400).build();
    }

    @Operation(summary = "Cadastrar novo vestuário") @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Vestuário cadastrado com sucesso", content = @Content(schema = @Schema(implementation = Item.class))), @ApiResponse(responseCode = "400", description = "Dados inválidos ou código duplicado"), @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado") })
    @PostMapping
    public ResponseEntity<ItemResponseDto> cadastrarItem(@RequestBody ItemRequestDto request) {
        Item item = ItemMapper.toEntity(request);

        if (itemService.existsByCodigo(item.getCodigo())) {
            return ResponseEntity.status(400).body(null);
        }

        if (item.getFornecedor() == null || item.getFornecedor().getId() == null) {
            return ResponseEntity.status(400).body(null);
        }

        Optional<Fornecedor> fornecedorOpt = fornecedorService.findById(item.getFornecedor().getId());
        if (fornecedorOpt.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        String nome = item.getNome().toUpperCase();
        if (!itemService.isCategoriaValida(nome)) {
            return ResponseEntity.status(400).body(null);
        }

        Item novoItem = itemService.cadastrarItem(item, fornecedorOpt.get());
        return ResponseEntity.status(201).body(ItemMapper.toResponse(novoItem));
    }


    @Operation(summary = "Deletar vestuário") @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Vestuário deletado com sucesso"), @ApiResponse(responseCode = "404", description = "Vestuário não encontrado") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVestuario(@RequestBody ItemListDto item) {
        Item itemParaDeletar = ItemMapper.fromListToEntity(item);
        itemService.deletar(itemParaDeletar);

        return ResponseEntity.status(205).build();
    }
}
