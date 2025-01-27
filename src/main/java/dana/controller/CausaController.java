package dana.controller;

import dana.model.Solicitante;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "causas")
public class CausaController {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String nombre;
		private String descripcion;
		private String causa;
		private String imagen;
		private double importe;
		private int cantidad;
		
		@ManyToMany
		private Solicitante solicitante;
		
		public void Causa() {

		}

		
		public void Causa(Integer id, String nombre, String descripcion, String causa, String imagen, double importe, int cantidad,
				Solicitante solicitante) {
			this.id = id;
			this.nombre = nombre;
			this.descripcion= descripcion;
			this.causa = causa;
			this.imagen = imagen;
			this.importe = importe;
			this.cantidad = cantidad;
			this.solicitante = solicitante;
		}

}
