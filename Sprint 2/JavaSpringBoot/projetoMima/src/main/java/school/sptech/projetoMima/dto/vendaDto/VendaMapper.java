package school.sptech.projetoMima.dto.vendaDto;

import school.sptech.projetoMima.entity.Venda;

public class VendaMapper {
    public static Venda toEntity (VendaRequestDto request) {
        Venda response = new Venda();

        response.setValorTotal(request.getValorTotal());
        response.setCliente(request.getCliente());
        response.setItensVenda(request.getItensVenda());
        return response;
    }

    public static VendaResponseDto toResponse (Venda request) {
        VendaResponseDto response = new VendaResponseDto();

        response.setValorTotal(response.getValorTotal());
        response.setItensVenda(response.getItensVenda());
        return response;
    }
}
