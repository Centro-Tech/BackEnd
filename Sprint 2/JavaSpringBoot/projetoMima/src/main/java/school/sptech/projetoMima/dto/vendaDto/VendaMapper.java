    package school.sptech.projetoMima.dto.vendaDto;

    import school.sptech.projetoMima.entity.ItemVenda;
    import school.sptech.projetoMima.entity.Venda;

    import java.util.List;

    public class VendaMapper {
        public static Venda toEntity(VendaRequestDto request, List<ItemVenda> itensVenda) {
            Venda venda = new Venda();
            venda.setValorTotal(request.getValorTotal());
            venda.setItensVenda(itensVenda);
            return venda;
        }

        public static VendaResponseDto toResponse(Venda venda) {
            VendaResponseDto response = new VendaResponseDto();
            response.setValorTotal(venda.getValorTotal());
            response.setItensVenda(venda.getItensVenda());
            return response;
        }
    }
