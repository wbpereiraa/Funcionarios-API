package com.rhempresa.gerenciador_espelho_ponto_api.domain.exception;

public class FuncionarioNaoEncontradoException extends NegocioException{

    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FuncionarioNaoEncontradoException(Long funcionarioId) {
        this(String.format("Não existe um funcionario com código %d", funcionarioId));
    }
}
