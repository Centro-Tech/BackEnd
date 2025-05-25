package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.entity.Venda;
import school.sptech.projetoMima.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.exception.Venda.QuantidadeIndisponivelException;
import school.sptech.projetoMima.repository.VendaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;


    public Venda vender (Venda venda) {
        List<ItemVenda> itensDaVenda = venda.getItensVenda();

        if (itensDaVenda.isEmpty()) {
            throw new CarrinhoVazioException("Não há itens registrados para vender.");
        }

        for (ItemVenda itemVenda : itensDaVenda) {
            int novaQtdEmEstoque = itemVenda.getItem().getQtdEstoque() - itemVenda.getQtdParaVender();
            itemVenda.getItem().setQtdEstoque(novaQtdEmEstoque);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        venda.setUsuario(usuarioLogado);

        vendaRepository.save(venda);
        venda.setValorTotal(0.0);
        return venda;
    }

    public ItemVenda adicionarItem (ItemVenda itemParaAdicionar, Venda venda) {
        if (itemParaAdicionar.getQtdParaVender() > itemParaAdicionar.getItem().getQtdEstoque()) {
            throw new QuantidadeIndisponivelException("Não há quantidade suficiente em estoque.");
        }

        venda.getItensVenda().add(itemParaAdicionar);
        venda.setValorTotal(venda.getValorTotal() + itemParaAdicionar.getItem().getPreco());

        return itemParaAdicionar;
    }

    public void deletarItemDaVenda(Venda venda, ItemVenda itemParaDeletar) {
        venda.getItensVenda().remove(itemParaDeletar);
        vendaRepository.save(venda);
    }

    public List<Venda> filtrarPorDatas (LocalDate inicio, LocalDate fim) {
        return vendaRepository.findByDataBetween(inicio, fim);
    }

    public List<Venda> filtrarPorCliente (Cliente cliente) {
        return vendaRepository.findByCliente(cliente);
    }

    public List<Venda> filtrarPorValor (Double valorMinimo, Double valorMax) {
        return vendaRepository.findByValorTotalBetween(valorMinimo, valorMax);
    }


}
