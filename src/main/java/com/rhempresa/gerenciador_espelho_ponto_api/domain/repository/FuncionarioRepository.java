package com.rhempresa.gerenciador_espelho_ponto_api.domain.repository;

import com.rhempresa.gerenciador_espelho_ponto_api.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
