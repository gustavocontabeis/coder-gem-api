package br.com.codersistemas.codergemapi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_pessoa", strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="seq_pessoa", initialValue=1000, allocationSize=1) 
	@Column(name="id_pessoa") 
	private Long id;

	@ClassLabelAttribute
	@Column(name="nome", length=120, nullable=false)
	private String nome;

	@Enumerated(EnumType.STRING) @Column(length=255, nullable=false)
	private Genero genero;

	@Column(name="altura", length=10, precision=10, scale=2, nullable=false)
	private Float altura;

	@Column(name="salario", length=10, precision=10, scale=2, nullable=false)
	private Double salario;

	@Column(name="ativo", length=1, nullable=false)
	private Boolean ativo;

	//@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="data_de_nacimento", length=255, nullable=false)
	private Date dataDeNacimento;
	
	@JoinColumn(name="id_pessoa_mae")
	@ManyToOne(cascade= {CascadeType.DETACH}, fetch=FetchType.EAGER)
	private Pessoa mae;

}
