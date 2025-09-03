package school.sptech.projetoMima.core.application.dto.vendaDto;

import school.sptech.projetoMima.core.application.dto.itemVendaDto.ItemVendaMapper;
import school.sptech.projetoMima.core.application.dto.itemVendaDto.ItemVendaResponseDto;
import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.Venda;

import java.util.List;
import java.util.stream.Collectors;

public class VendaMapper {

    // Converte VendaRequestDto + lista de ItemVenda em Venda (entidade)
    public static Venda toEntity(VendaRequestDto request, List<ItemVenda> itensVenda) {
        Venda venda = new Venda();
        venda.setValorTotal(request.getValorTotal());
        venda.setItensVenda(itensVenda);
        return venda;
    }

    // Converte Venda (entidade) em VendaResponseDto
    public static VendaResponseDto toResponse(Venda venda) {
        VendaResponseDto response = new VendaResponseDto();
        response.setValorTotal(venda.getValorTotal());

        // Converte cada ItemVenda em ItemVendaResponseDto
        List<ItemVendaResponseDto> itensDto = venda.getItensVenda().stream()
                .map(ItemVendaMapper::toResponse)
                .collect(Collectors.toList());

        response.setItensVenda(itensDto);
        return response;
    }
}
