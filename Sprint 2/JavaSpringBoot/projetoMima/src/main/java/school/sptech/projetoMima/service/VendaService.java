package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.itemDto.ItemVendaRequestDto;
import school.sptech.projetoMima.dto.vendaDto.VendaMapper;
import school.sptech.projetoMima.dto.vendaDto.VendaRequestDto;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.entity.Venda;
import school.sptech.projetoMima.entity.item.Item;
import school.sptech.projetoMima.exception.Usuario.UsuarioNaoEncontradoException;
import school.sptech.projetoMima.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.exception.Venda.QuantidadeIndisponivelException;
import school.sptech.projetoMima.repository.ClienteRepository;
import school.sptech.projetoMima.repository.ItemRepository;
import school.sptech.projetoMima.repository.ItemVendaRepository;
import school.sptech.projetoMima.repository.VendaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Venda vender(VendaRequestDto dto) {
        if (dto.getItensVenda().isEmpty()) {
            throw new CarrinhoVazioException("Não há itens registrados para vender.");
        }

        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));

        List<ItemVenda> itensVenda = dto.getItensVenda().stream()
                .map(id -> itemVendaRepository.findById(id)
                        .orElseThrow(() -> new CarrinhoVazioException(
                                "ItemVenda com id " + id + " não encontrado")))
                .toList();

        for (ItemVenda itemVenda : itensVenda) {
            int novaQtdEmEstoque = itemVenda.getItem().getQtdEstoque() - itemVenda.getQtdParaVender();
            itemVenda.getItem().setQtdEstoque(novaQtdEmEstoque);
        }

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setItensVenda(itensVenda);
        venda.setValorTotal(dto.getValorTotal());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        venda.setUsuario(usuarioLogado);

        return vendaRepository.save(venda);
    }

    public Venda adicionarItem(ItemVendaRequestDto request) {
        Venda venda = vendaRepository.findById(request.getVendaId())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (item.getQtdEstoque() < request.getQtdParaVender()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        item.setQtdEstoque(item.getQtdEstoque() - request.getQtdParaVender());

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setItem(item);
        itemVenda.setVenda(venda);
        itemVenda.setQtdParaVender(request.getQtdParaVender());

        itemVendaRepository.save(itemVenda);

        venda.getItensVenda().add(itemVenda);

        return vendaRepository.save(venda);
    }

    public void deletarItemDaVenda(Integer itemVendaId, Integer vendaId) {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com ID: " + vendaId));

        ItemVenda itemEncontrado = venda.getItensVenda().stream()
                .filter(iv -> iv.getId().equals(itemVendaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ItemVenda com ID " + itemVendaId + " não encontrado na venda."));

        venda.getItensVenda().remove(itemEncontrado);

        double valorItem = itemEncontrado.getItem().getPreco() * itemEncontrado.getQtdParaVender();
        venda.setValorTotal(venda.getValorTotal() - valorItem);

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
