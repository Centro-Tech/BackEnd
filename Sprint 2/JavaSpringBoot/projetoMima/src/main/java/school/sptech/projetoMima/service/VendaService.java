package school.sptech.projetoMima.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.itemDto.ItemVendaRequestDto;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioDetalhesDto;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioMapper;
import school.sptech.projetoMima.dto.vendaDto.VendaMapper;
import school.sptech.projetoMima.dto.vendaDto.VendaRequestDto;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.entity.Venda;
import school.sptech.projetoMima.entity.item.Item;
import school.sptech.projetoMima.exception.Usuario.UsuarioNaoEncontradoException;
import school.sptech.projetoMima.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.exception.Venda.EstoqueInsuficienteException;
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
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));

        List<ItemVenda> itensCarrinho = itemVendaRepository.findByClienteIdAndVendaIsNull(cliente.getId());
        if (itensCarrinho.isEmpty()) {
            throw new CarrinhoVazioException("Carrinho está vazio");
        }

        double valorTotal = itensCarrinho.stream()
                .mapToDouble(iv -> iv.getItem().getPreco() * iv.getQtdParaVender())
                .sum();

       /* UsuarioDetalhesDto funcionario = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/

        Venda venda = new Venda();
        venda.setCliente(cliente);
        /*venda.setUsuario(UsuarioMapper.toUsuarioFromUsuarioDetalhes(funcionario));*/
        venda.setValorTotal(valorTotal);
        venda.setItensVenda(itensCarrinho);

        vendaRepository.save(venda);


        for (ItemVenda itemVenda : itensCarrinho) {
            Item item = itemVenda.getItem();

            if (item.getQtdEstoque() < itemVenda.getQtdParaVender()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para: " + item.getNome());
            }

            item.setQtdEstoque(item.getQtdEstoque() - itemVenda.getQtdParaVender());
            itemRepository.save(item);

            itemVenda.setVenda(venda);
            itemVendaRepository.save(itemVenda);
        }

        return venda;
    }

/*    public Venda adicionarItem(ItemVendaRequestDto request) {
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
    }*/

    @Transactional
    public void deletarItemDaVenda(Integer itemVendaId, Integer vendaId) {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com ID: " + vendaId));

        ItemVenda itemEncontrado = venda.getItensVenda().stream()
                .filter(iv -> iv.getId().equals(itemVendaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ItemVenda com ID " + itemVendaId + " não encontrado na venda."));


        Item item = itemEncontrado.getItem();
        item.setQtdEstoque(item.getQtdEstoque() + itemEncontrado.getQtdParaVender());


        double valorItem = item.getPreco() * itemEncontrado.getQtdParaVender();
        venda.setValorTotal(venda.getValorTotal() - valorItem);


        venda.getItensVenda().remove(itemEncontrado);


        itemRepository.save(item);
        vendaRepository.save(venda);
        itemVendaRepository.delete(itemEncontrado);
    }


}
