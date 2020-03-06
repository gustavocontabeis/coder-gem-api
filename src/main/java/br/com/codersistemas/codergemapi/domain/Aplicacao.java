
package br.com.codersistemas.codergemapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

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
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="seq_aplicacao", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name="seq_aplicacao", allocationSize=1) @Column(name="id_aplicacao", nullable=false)
	private Long id;

	@ClassLabelAttribute
	@NotEmpty 
	@Column(name="nome", length=120, nullable=false)
	private String nome;

	@OneToMany(mappedBy="aplicacao", fetch=FetchType.LAZY)
	private List<Entidade> entidades;

}
