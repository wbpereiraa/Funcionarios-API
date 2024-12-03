package com.rhempresa.gerenciador_espelho_ponto_api.domain.model;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Funcionario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer numeroCadastro;
	
	@Column(nullable = false)
	private Date dataInicio;
	
	@Column(nullable = false)
	private Date dataDesligamento;
	
	@Column(nullable = false)
	private Double salarioPorHora;
	
	@Column(nullable = false)
	private LocalTime horarioDeEntrada;
	
	@Column(nullable = false)
	private LocalTime horarioDeSaida;
	
	@Column(nullable = false)
	private String cargo;
	
}
