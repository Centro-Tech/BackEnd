package school.sptech.projetoMima.core.infrastructure.fornecedor;

import school.sptech.projetoMima.core.adapter.fornecedorDto.FornecedorRequestDto;
import school.sptech.projetoMima.core.adapter.fornecedorDto.FornecedorResponseDto;
import school.sptech.projetoMima.core.domain.Fornecedor;

public class FornecedorMapper {

    public static FornecedorResponseDto toResponse(Fornecedor fornecedor) {
        FornecedorResponseDto response = new FornecedorResponseDto();

        response.setId(fornecedor.getId());
        response.setNome(fornecedor.getNome());
        response.setTelefone(fornecedor.getTelefone());
        response.setEmail(fornecedor.getEmail());

        return response;
    }

    public static Fornecedor toEntity(FornecedorRequestDto fornecedor) {
        Fornecedor response = new Fornecedor();

        response.setNome(fornecedor.getNome());
        response.setEmail(fornecedor.getEmail());
        response.setTelefone(fornecedor.getTelefone());

        return response;
    }





}
