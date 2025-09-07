package school.sptech.projetoMima.core.application.dto.vendaDto;

import school.sptech.projetoMima.core.domain.Venda;

public class VendaMapper {

    public static Venda toEntity(VendaRequestDto request) {
        Venda venda = new Venda();

        venda.setValorTotal(request.getValorTotal());
        return venda;
    }

    public static VendaResponseDto toResponseDto(Venda venda) {
        VendaResponseDto response = new VendaResponseDto();
        response.setId(venda.getId());
        response.setValorTotal(venda.getValorTotal());
        response.setData(venda.getData());

        if (venda.getCliente() != null && venda.getCliente().getId() != null) {
            response.setClienteId(venda.getCliente().getId());
        }
        if (venda.getItensVenda() != null) {
            response.setItensVenda(venda.getItensVenda());
        }
        return response;
    }
}
