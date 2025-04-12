package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.funcionarioDto.FuncionarioCadastroDto;
import school.sptech.projetoMima.dto.funcionarioDto.FuncionarioMapper;
import school.sptech.projetoMima.entity.Funcionario;
import school.sptech.projetoMima.exception.Funcionario.FuncionarioInvalidoException;
import school.sptech.projetoMima.exception.Funcionario.FuncionarioListaVaziaException;
import school.sptech.projetoMima.exception.Funcionario.FuncionarioNaoEncontradoException;
import school.sptech.projetoMima.repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario findFuncionarioById(int id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado!"));
    }

    public Funcionario cadastrarFuncionario(FuncionarioCadastroDto dto) {
        Funcionario funcionarioCad = FuncionarioMapper.toEntity(dto);
        return funcionarioRepository.save(funcionarioCad);
    }

    public Funcionario atualizarFuncionario(FuncionarioCadastroDto dto, Integer id) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado!"));

        funcionarioExistente.setNome(dto.getNome());
        funcionarioExistente.setCpf(dto.getCpf());
        funcionarioExistente.setTelefone(dto.getTelefone());
        funcionarioExistente.setEmail(dto.getEmail());
        funcionarioExistente.setCargo(dto.getCargo());
        funcionarioExistente.setEndereco(dto.getEndereco());

        return funcionarioRepository.save(funcionarioExistente);
    }


    public List<Funcionario> listarFuncionarios() {
        List <Funcionario> funcionarios = new ArrayList<>();
        for(Funcionario funcionario : funcionarioRepository.findAll()) {
            funcionarios.add(funcionario);
        }

        if(funcionarios.isEmpty()){
            throw new FuncionarioListaVaziaException("Lista de funcionário está vazia");
        }
        return funcionarios;

    }


    public void excluir(Integer id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new FuncionarioNaoEncontradoException("Curso não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }



}
