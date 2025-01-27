package dana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles")
public class DetalleDonacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String causa;
	private double cantidad;
	private double importe;
	private double total;
	
	@ManyToMany
	private Donacion donacion;
	
	@ManyToMany
	private String causa1;
	
	public void DetalleCausa() {
	
	}
	public DetalleDonacion(Integer id, String nombre, String descripcion, String causa, double cantidad, double importe, double total) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.setDescripcion(descripcion);
		this.causa = causa;
		this.cantidad = cantidad;
		this.importe = importe;
		this.total = total;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	public String getDescripcion() {
	return descripcion;
    }
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
	public String getCausa() {
		return causa;
	}
	public String setCausa(String causa) {
		return this.causa = causa;
	}
	
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public Donacion getDonacion() {
		return donacion;
	}
	public void setDonacion(Donacion donacion) {
		this.donacion = donacion;
	
	}
	@Override
	public String toString() {
		return "DetalleCausa [id=" + id + ", nombre=" + nombre + ", causa=" + causa + ", cantidad=" + cantidad + ", importe=" + importe
				+ ", total=" + total + "]";

	}
}
