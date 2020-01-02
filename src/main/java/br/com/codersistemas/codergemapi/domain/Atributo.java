package br.com.codersistemas.codergemapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Atributo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_atributo", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_atributo") @Column(name="id_atributo") 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_entidade")
	private Entidade entidade;

	@Column(name="nome", length=100, nullable=false)
	private String nome;

	@Column(name="rotulo", length=100, nullable=false)
	private String rotulo;

	@Column(name="coluna", length=100, nullable=false)
	private String coluna;

	@Enumerated(EnumType.STRING) @Column(length=100, nullable=false)
	private Tipo tipo;

	@Column(name="obritatorio", length=1, nullable=false)
	private Boolean obritatorio;

	@Column(name="tamanho_minimo", nullable=true)
	private Integer tamanhoMinimo;

	@Column(name="tamanho_maximo", nullable=true)
	private Integer tamanhoMaximo;

}
//Ajuste os tamanhos dos campos.

