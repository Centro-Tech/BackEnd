package school.sptech.projetoMima.infrastructure.web.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.projetoMima.infrastructure.persistance.ClienteRepository;
import school.sptech.projetoMima.infrastructure.persistance.VendaPersistance.VendaJpaRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vendas")
public class DashboardVendaController {

    private final VendaJpaRepository vendaRepository;

    public DashboardVendaController(VendaJpaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @GetMapping("/total-hoje")
    public Map<String, Object> getTotalVendidoHoje() {
        Double total = vendaRepository.findTotalVendidoHoje();


        if (total == null) {
            total = 0.0;
        }

        // Retorna num formato fácil de consumir no front-end
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("totalVendidoHoje", total);

        return resposta;
    }

    @GetMapping("/quantidade-hoje")
    public Map<String, Object> getQuantidadeVendasHoje() {
        Long quantidade = vendaRepository.findQuantidadeVendasHoje();

        if (quantidade == null) {
            quantidade = 0L;
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("quantidadeVendasHoje", quantidade);

        return resposta;
    }

    @GetMapping("/clientes-ultimo-mes")
    public Map<String, Object> getClientesUltimoMes() {
        Long quantidadeClientes = vendaRepository.findClientesUltimoMes();

        if (quantidadeClientes == null) {
            quantidadeClientes = 0L;
        }

        // Retorna num formato fácil de consumir no front-end
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("clientesUltimoMes", quantidadeClientes);

        return resposta;
    }


}
