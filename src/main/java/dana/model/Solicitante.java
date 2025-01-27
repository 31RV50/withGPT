package dana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitantes")
public class Solicitante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String causa;
	private String imagen;
	private double importe;
	private int cantidad;
	
	@ManyToOne
	private Solicitante solicitante;
	
	public void Causa() {

	}

	
	public void Causa (Integer id, String nombre, String descripcion, String causa, String imagen, double importe, int cantidad,
	Solicitante solicitante) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.causa = causa;
		this.imagen = imagen;
		this.importe = importe;
		this.cantidad = cantidad;
		this.solicitante = solicitante;
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

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	@Override
	public String toString() {
		return "Causa [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", causa=" + causa + ", imagen=" + imagen
				+ ", importe=" + importe + ", cantidad=" + cantidad + ", solicitante=" + solicitante + "]";
	}


	public Object getUsername() {
	
		return null;
	}


	public String getTipo() {
		
		return null;
	}


	public Object getPassword() {
		
		return null;
	}


	public void setTipo(String string) {
		
		
	}


	public void setPassword(Object encode) {
		
		
	}


	public void setPassword(Object encode) {
	
		
	}


	public String getEmail() {
		
		return null;
	}
	
	
}
