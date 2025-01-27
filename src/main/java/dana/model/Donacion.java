package dana.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "donaciones")
public class Donacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private String causa;
	private String cantidad;
	private Date fechaCreacion;
	private Date fechaRecibida;

	private double total;
	
	@ManyToMany
	private Usuario usuario;
	
	@ManyToMany(mappedBy = "donacion")
	private List<DetalleDonacion> detalle;
	
	public Donacion() {
	
	}

	public Donacion(Integer id, String numero, String causa, String cantidad, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.causa = causa;
		this.cantidad = cantidad;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public List<DetalleDonacion> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleDonacion> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Donacion [id=" + id + ", numero=" + numero + ", causa=" + causa + ", cantidad=" + cantidad + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}
	

}
