
package br.com.codersistemas.codergemapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Aplicacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_aplicacao", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_aplicacao") @Column(name="id_aplicacao") 
	private Long id;

	@Column(name="nome", length=60, nullable=false)
	private String nome;
	
	@OneToMany(cascade={CascadeType.DETACH}, mappedBy="aplicacao")
	private List<Entidade> entidades;

}
//Ajuste os tamanhos dos campos.

