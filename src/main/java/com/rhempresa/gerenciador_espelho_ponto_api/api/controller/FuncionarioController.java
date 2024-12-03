package com.rhempresa.gerenciador_espelho_ponto_api.api.controller;

import com.rhempresa.gerenciador_espelho_ponto_api.domain.exception.FuncionarioEmUsoException;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.exception.FuncionarioNaoEncontradoException;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.exception.NegocioException;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.model.Funcionario;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.repository.FuncionarioRepository;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.service.CadastroFuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CadastroFuncionarioService cadastroFuncionario;

    @GetMapping
    public List<Funcionario> listar(){
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{funcionarioId}")
    public Funcionario buscar(@PathVariable Long funcionarioId){
        return cadastroFuncionario.buscarOuFalhar(funcionarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario adicionar(@RequestBody Funcionario funcionario ) {
        return cadastroFuncionario.salvar(funcionario);
    }
    //teste
    @PutMapping("/{funcionarioId}")
    public Funcionario atualizar(@PathVariable Long funcionarioId,
                            @RequestBody Funcionario funcionario){
        try {
            Funcionario funcionarioAtual = cadastroFuncionario.buscarOuFalhar(funcionarioId);

            BeanUtils.copyProperties(funcionario, funcionarioAtual, "id");

            return cadastroFuncionario.salvar(funcionarioAtual);
        } catch (FuncionarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{funcionarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long funcionarioId) throws FuncionarioEmUsoException {
        cadastroFuncionario.excluir(funcionarioId);
    }
}
