package school.sptech.projetoMima.dto.estoqueDto;

import school.sptech.projetoMima.entity.Estoque;

public class EstoqueMapper {

    public static EstoqueResponseDto toResponse (Estoque estoque) {
        EstoqueResponseDto response = new EstoqueResponseDto();

        response.setCodigo(estoque.getCodigo());
        response.setNome(estoque.getNome());
        response.setQtdEstoque(estoque.getQtdEstoque());

        return response;
    }

    public static EstoqueListDto toList (Estoque estoque) {
        EstoqueListDto response = new EstoqueListDto();

        response.setCodigo(estoque.getCodigo());
        response.setPreco(estoque.getPreco());
        response.setNome(estoque.getNome());
        response.setTamanho(estoque.getTamanho());
        response.setPreco(estoque.getPreco());

        return response;
    }

    public static Estoque toEntity (EstoqueRequestDto request) {
        Estoque response = new Estoque();

        response.setNome(request.getNome());
        response.setQtdEstoque(request.getQtdEstoque());
        response.setTamanho(request.getTamanho());
        response.setCor(request.getCor());
        response.setMaterial(request.getMaterial());
        response.setCategoria(request.getCategoria());
        response.setPreco(request.getPreco());
        response.setFornecedor(request.getFornecedor());

        return response;
    }
}
