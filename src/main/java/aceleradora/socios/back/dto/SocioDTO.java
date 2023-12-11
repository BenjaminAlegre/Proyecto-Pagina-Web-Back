package aceleradora.socios.back.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import aceleradora.socios.back.clases.socio.Categoria;
import aceleradora.socios.back.clases.socio.Etiqueta;
import aceleradora.socios.back.clases.socio.Membresia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocioDTO {

	private String presidente;
	private Long id;
	@JsonProperty("name")
	private String nombre;

	private Long cuit;

	private String mail;
	@JsonProperty("dateUnion")
	private Date fechaUnion;
	@JsonProperty("phone")
	private Long telefono;
	@JsonProperty("category")
	private Categoria categoria;

//	private Membresia membresia;
	private Boolean estado;
	@JsonProperty("image")
	private String imagen;
	private String web;
	private List<Etiqueta> etiquetas;

	
//	public SocioDTO() {}
//	public SocioDTO(Long id, String nombre, String descripcion, Long cuit, String contrasenia, String mail, Date fechaUnion,
//			Long telefono, Categoria categoria, Boolean admin, Boolean estado, String imagen, String web, Date fechaCreacion) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		//this.descripcion = descripcion;
//		this.cuit = cuit;
//		//this.contrasenia = contrasenia;
//		this.mail = mail;
//		this.fechaUnion = fechaUnion;
//		this.telefono = telefono;
//		this.categoria = categoria;
//		//this.admin = admin;
//		//this.estado = estado;
//		this.imagen = imagen;
//		this.web = web;
//		this.fechaCreacion = fechaCreacion;
//	}
	

	
	
	
}
