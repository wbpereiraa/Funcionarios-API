package com.rhempresa.gerenciador_espelho_ponto_api.domain.service;

import com.rhempresa.gerenciador_espelho_ponto_api.domain.exception.FuncionarioEmUsoException;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.exception.FuncionarioNaoEncontradoException;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.model.Funcionario;
import com.rhempresa.gerenciador_espelho_ponto_api.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroFuncionarioService {

    private static final String MSG_FUNCIONARIO_EM_USO
            = "Funcionario de código %d não pode ser removido, pois está em uso";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario salvar (Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void excluir(Long funcionarioId) throws FuncionarioEmUsoException {
        try {
            funcionarioRepository.deleteById(funcionarioId);

        }catch (EmptyResultDataAccessException e) {
            throw new FuncionarioNaoEncontradoException(funcionarioId);

        } catch (DataIntegrityViolationException e) {
            throw new FuncionarioEmUsoException(
                    String.format(MSG_FUNCIONARIO_EM_USO, funcionarioId));
        }

    }

    public Funcionario buscarOuFalhar(Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(funcionarioId));
    }
}
