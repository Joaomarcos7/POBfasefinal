package modelo;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_atendimento")
public class Atendimento {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String data;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
	  @JoinColumn(name ="pacientefk")
	Paciente paciente;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
	  @JoinColumn(name = "planofk")
	Plano plano;
	
	public Atendimento(String data, Paciente paciente, Plano plano) {
		this.data=data;
		this.paciente=paciente;
		paciente.adicionar(this);
		this.plano=plano;
		plano.adicionar(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data =  data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		
			this.paciente = paciente;
			paciente.adicionar(this);
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
		paciente.adicionar(this);
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", data=" + data + ", paciente=" + paciente.ToStringPattern()+ ", plano=" + plano.ToStringPattern() + "]";
	}
	
}