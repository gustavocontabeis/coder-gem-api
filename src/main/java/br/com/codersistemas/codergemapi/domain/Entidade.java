package br.com.codersistemas.codergemapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.codersistemas.libs.annotations.ClassLabelAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_entidade", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_entidade", allocationSize=1) @Column(name="id_entidade") 
	private Long id;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_aplicacao")
	private Aplicacao aplicacao;

	@ClassLabelAttribute
	@Column(name="nome", length=100, nullable=false)
	private String nome;

	@Column(name="rotulo", length=100, nullable=false)
	private String rotulo;

	@Column(name="tabela", length=100, nullable=false)
	private String tabela;
	
	@OneToMany(cascade={CascadeType.DETACH}, mappedBy="entidade")
	private List<Atributo> atributos;

}
//Ajuste os tamanhos dos campos.

