package school.sptech.projetoMima.dto.itemDto;

import school.sptech.projetoMima.dto.fornecedorDto.FornecedorMapper;
import school.sptech.projetoMima.entity.item.Categoria;
import school.sptech.projetoMima.entity.item.Item;

public class ItemMapper {

    public static ItemResponseDto toResponse (Item item) {
        ItemResponseDto response = new ItemResponseDto();


        response.setCodigo(item.getCodigo());
        response.setNome(item.getNome());
        response.setQtdEstoque(item.getQtdEstoque());

        return response;
    }

    public static ItemListDto toList (Item item) {
        ItemListDto response = new ItemListDto();

        response.setCodigo(item.getCodigo());
        response.setPreco(item.getPreco());
        response.setNome(item.getNome());
        response.setTamanho(item.getTamanho());
        response.setPreco(item.getPreco());
        response.setFornecedor(FornecedorMapper.toResponse(item.getFornecedor()));

        return response;
    }

    public static Item toEntity (ItemRequestDto request) {
        Item response = new Item();

        response.setNome(request.getNome());
        response.setQtdEstoque(request.getQtdEstoque());
        response.setTamanho(request.getTamanho());
        response.setCor(request.getCor());
        response.setMaterial(request.getMaterial());

        Categoria categoria = new Categoria();
        categoria.setNome(request.getCategoria().getNome());
        response.setCategoria(categoria);

        response.setPreco(request.getPreco());
        response.setFornecedor(request.getFornecedor());

        return response;
    }


    public static Item fromResponseToEntity (ItemResponseDto request) {
        Item response = new Item();

        response.setCodigo(request.getCodigo());
        response.setNome(request.getNome());
        response.setQtdEstoque(request.getQtdEstoque());

        return response;
    }

    public static Item fromListToEntity (ItemListDto request) {
        Item response = new Item();

        response.setCodigo(request.getCodigo());
        response.setNome(request.getNome());
        response.setTamanho(request.getTamanho());
        response.setQtdEstoque(request.getQtdEstoque());
        response.setPreco(request.getPreco());

        return response;
    }
}
