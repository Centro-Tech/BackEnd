package school.sptech.projetoMima.dto.funcionarioDto;

import school.sptech.projetoMima.entity.Funcionario;

public class FuncionarioMapper {


    public static Funcionario toEntity(FuncionarioCadastroDto funcionario) {
        Funcionario funcionarioCadastro = new Funcionario();
        funcionarioCadastro.setNome(funcionario.getNome());
        funcionarioCadastro.setEmail(funcionario.getEmail());
        funcionarioCadastro.setCpf(funcionario.getCpf());
        funcionarioCadastro.setTelefone(funcionario.getTelefone());
        funcionarioCadastro.setEndereco(funcionario.getEndereco());

        return funcionarioCadastro;
    }

    public static FuncionarioResumidoDto toResumidoDto(Funcionario funcionario) {
        FuncionarioResumidoDto funcionarioResumidoDto = new FuncionarioResumidoDto();
        funcionarioResumidoDto.setCargo(funcionario.getCargo());
        funcionarioResumidoDto.setNome(funcionario.getNome());

        return funcionarioResumidoDto;
    }

    public static FuncionarioListagemDto toListagemDto(Funcionario funcionario) {
        FuncionarioListagemDto listagemDto = new FuncionarioListagemDto();
        listagemDto.setNome(funcionario.getNome());
        listagemDto.setEmail(funcionario.getEmail());
        return listagemDto;
    }

}
