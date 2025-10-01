package school.sptech.projetoMima.core.application.usecase.Venda;

import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.domain.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListarVendasUseCase {

    private final VendaGateway vendaGateway;

    public ListarVendasUseCase(VendaGateway vendaGateway) {
        this.vendaGateway = vendaGateway;
    }

    public List<Venda> execute() {
        return vendaGateway.findAll();
    }

    public Page<Venda> execute(Pageable pageable) {
        return vendaGateway.findAll(pageable);
    }
}