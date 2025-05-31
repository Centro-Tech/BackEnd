package school.sptech.projetoMima.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.entity.item.*;
import school.sptech.projetoMima.exception.Item.ItemCampoVazioException;
import school.sptech.projetoMima.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.exception.Item.ItemQuantidadeInvalida;
import school.sptech.projetoMima.repository.ItemRepository;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private ItemService itemService;


    private Item criarItemValido() {
        Item item = new Item();
        Categoria categoria = new Categoria();
        categoria.setNome("CAMISETA");
        item.setCategoria(categoria);
        Tamanho tamanho = new Tamanho();
        tamanho.setNome("M");
        item.setTamanho(tamanho);
        Cor cor = new Cor();
        cor.setNome("AZUL");
        item.setCor(cor);
        Material material = new Material();
        material.setNome("ALGODÃO");
        item.setMaterial(material);
        item.setQtdEstoque(10);
        item.setPreco(79.9);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1);
        fornecedor.setNome("Empresa XYZ LTDA");
        fornecedor.setTelefone("11987654321");
        fornecedor.setEmail("contato@empresa.com");
        item.setFornecedor(fornecedor);
        return item;
    }

    @Test
    @DisplayName("BuscarPorId com ID válido retorna o item esperado e chama findById uma vez")
    void testeX1() {
        Item itemValido = criarItemValido();
        when(itemRepository.findById(1)).thenReturn(Optional.of(itemValido));

        Item resultado = itemService.buscarPorId(1);


        assertEquals(itemValido, resultado);
        verify(itemRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("BuscarPorId com ID inválido lança ItemNaoEncontradoException e chama findById uma vez")
    void testeX2() {

        when(itemRepository.findById(999)).thenReturn(Optional.empty());

        ItemNaoEncontradoException exception = assertThrows(ItemNaoEncontradoException.class, () -> {
            itemService.buscarPorId(999);
        });

        assertTrue(exception.getMessage().contains("não encontrado"));
        verify(itemRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Deletar chama método delete do repositório exatamente uma vez")
    void testeDeletar() {
        Item itemValido = criarItemValido();

        itemService.deletar(itemValido);

        verify(itemRepository, times(1)).delete(itemValido);
    }

    @Test
    @DisplayName("Validação lança exceção quando campos obrigatórios estiverem vazios")
    void testeValidarCampoVazio() {
        Item itemValido = criarItemValido();

        itemValido.getCategoria().setNome("   ");

        ItemCampoVazioException exception = assertThrows(ItemCampoVazioException.class, () -> {
            itemService.validarCampoVazio(itemValido);
        });

        assertEquals("Campos em vazio no cadastro de item", exception.getMessage());
        verifyNoInteractions(itemRepository);
    }

    @Test
    @DisplayName("Lança exceção se preço for inválido (não numérico) ou menor que zero")
    void testeValidarPreco() {
        Item itemValido = criarItemValido();
        itemValido.setPreco(null);

        ItemCampoVazioException exception = assertThrows(ItemCampoVazioException.class, () -> {
            itemService.validarPreco(itemValido);
        });

        assertTrue(exception.getMessage().contains("Preço inválido"));
        verifyNoInteractions(itemRepository);
    }

    @Test
    @DisplayName("Lança exceção se quantidade for menor ou igual a zero")
    void testeValidarQuantidade() {
        Item itemValido = criarItemValido();
        itemValido.setQtdEstoque(0);

        ItemQuantidadeInvalida exception = assertThrows(ItemQuantidadeInvalida.class, () -> {
            itemService.validarQuantidade(itemValido);
        });

        assertEquals("Quantidade deve ser maior que zero", exception.getMessage());
        verifyNoInteractions(itemRepository);
    }

    @Test
    @DisplayName("Retorna true se categoria contém caracteres especiais")
    void testeValidarCaracteres() {

        Item itemValido = criarItemValido();
        itemValido.getCategoria().setNome("CAMISETA!"); // contém !

        boolean resultado = itemService.validarCaracteres(itemValido);

        assertTrue(resultado);
        verifyNoInteractions(itemRepository);
    }

    @Test
    @DisplayName("Cadastrar item válido chama save e retorna item com código gerado")
    void testeCadastrarItem() {
        Item itemValido = criarItemValido();
        Fornecedor fornecedor = itemValido.getFornecedor();

        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArgument(0));

        Item resultado = itemService.cadastrarItem(itemValido, fornecedor);

        assertNotNull(resultado.getCodigo());
        assertTrue(resultado.getCodigo().startsWith("BL"));
        assertEquals(fornecedor, resultado.getFornecedor());
        verify(itemRepository, times(1)).save(any(Item.class));
    }
}
