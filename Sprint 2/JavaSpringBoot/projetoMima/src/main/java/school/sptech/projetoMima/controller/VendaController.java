package school.sptech.projetoMima.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

    @PutMapping ("/vender")
    public ResponseEntity<VendaResponseDto> vender (@Valid VendaRequestDto request) {
        Venda vendaConvertida = VendaMapper.toEntity(request);
        vendaService.vender(vendaConvertida);
        VendaResponseDto response = VendaMapper.toResponse(vendaConvertida);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/adicionar-item")
    public ResponseEntity<VendaResponseDto> adicionarItens (@Valid VendaRequestDto request, @Valid ItemVenda itemVenda) {
        Venda venda = VendaMapper.toEntity(request);
        vendaService.adicionarItem(itemVenda, venda);
        return ResponseEntity.status(200).body(VendaMapper.toResponse(venda));
    }

    @DeleteMapping
    public ResponseEntity<Void> removerItemDaVenda (@Valid VendaRequestDto request, @Valid ItemVenda itemParaDeletar) {
        Venda venda = VendaMapper.toEntity(request);
        vendaService.deletarItemDaVenda(venda, itemParaDeletar);

        return ResponseEntity.status(204).build();
    }

    @GetMapping("/filtrar-por-data")
    public ResponseEntity<List<Venda>> filtrarPorData (@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorDatas(inicio, fim);

        if (vendasEncontradas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(vendasEncontradas);
    }

    @GetMapping("/filtrar-por-cliente")
    public ResponseEntity<List<Venda>> filtrarPorCliente (@RequestParam Cliente cliente) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorCliente(cliente);

        if (vendasEncontradas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(vendasEncontradas);
    }

    @GetMapping("/filtrar-por-valor")
    public ResponseEntity<List<Venda>> filtrarPorValor (@RequestParam Double valorMinimo, @RequestParam Double valorMax) {
        List<Venda> vendasEncontradas = vendaService.filtrarPorValor(valorMinimo, valorMax);

        if (vendasEncontradas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(vendasEncontradas);
    }
}
