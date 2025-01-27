package dana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles")
public class DetalleCausa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcioncausa;
	private double cantidad;
	private double importe;
	private double total;
	
	@ManyToMany
	private Donacion donacion;
	
	@ManyToMany
	private Causa causa;
	
	public DetalleCausa() {
	
	}
	public DetalleCausa(Integer id, String nombre, String descripcioncausa, double cantidad, double importe, double total) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcioncausa = descripcioncausa;
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
	
	public String getDecripcionCausa() {
			return descripcioncausa;
	}
	public void setDescripcionCausa(String descripcioncausa) {
			this.descripcioncausa = descripcioncausa;
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
	public Causa getCausa() {
		return causa;
	}
	public void setCausa(Causa causa) {
		this.causa = causa;
	}
	@Override
	public String toString() {
		return "DetalleCausa [id=" + id + ", nombre=" + nombre + ", descripcioncausa=" + descripcioncausa + ", cantidad=" + cantidad + ", importe=" + importe
				+ ", total=" + total + "]";
	}

}
