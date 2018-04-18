package main.java.sge.models;

import java.util.Calendar;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Clase Cliente Extends Usuario
 * @author Alejandro Mattioli
 *
 */
//Esto hace que ignoren los campos que sean null en una serializacion
@JsonInclude(JsonInclude.Include.NON_NULL)
//Define el orden de las properties que vienen el registro 
@JsonPropertyOrder({ "tipoDoc", "nroDoc", "telefono", "categoria", "dispositivos"})

public class Cliente extends Usuario {

	public enum tipoDoc { DNI, CI, LE, LI }

	@JsonProperty("tipoDoc")
	private String tipoDoc;

	@JsonProperty("nroDoc")
	private Integer nroDoc;

	@JsonProperty("telefono")
	private Integer telefono;

	@JsonProperty("categoria")
	private Categoria categoria;
	//Tengo duda en que si se da de alta empieza en R1 o ya viene con la categoria
	//private Categoria categoria = new Categoria("R1");
	
	@JsonProperty("dispositivos")
	private List<Dispositivo> dispositivos;

	public Cliente() {
		super();
	}

	public Cliente(String valorUsuario, String valorPassword, String valorNombre, String valorApellido, String valorDomicilio,
			Calendar valorFechaAlta, String valorTipoDoc, Integer valorNroDoc, Integer valorTelefono, Categoria valorCategoria,
			List<Dispositivo> valorDispositivos, Float valorConsumoMensual) {

		super(valorUsuario, valorPassword, valorNombre, valorApellido, valorDomicilio, valorFechaAlta);
		this.tipoDoc = valorTipoDoc;
		this.nroDoc = valorNroDoc;
		this.telefono = valorTelefono;
		this.categoria = valorCategoria;
		this.dispositivos = valorDispositivos;

	}

	//Cantidad de Dispositivos Encendidos
	public int cantidadDispositivosEncencidos(List<Dispositivo> dispositivos) {
		int dispositivosEncendidos = 0;
		for (Dispositivo unDispositivo : dispositivos) {
			if (unDispositivo.getEstado() == true) {
				dispositivosEncendidos = dispositivosEncendidos + 1;
			}
		}
		return dispositivosEncendidos;
	}
	
	//Cantidad Total de Dispositivos
	public int cantidadTotalDispositivos(List<Dispositivo> dispositivos) {
		int cantDispositivos = dispositivos.size();
		return cantDispositivos;
	}
	
	//Cantidad Dispositivos Apagados
	public int cantidadDispositivosApagados(List<Dispositivo> dispositivos) {
		int dispositivosApagados = 0;
		dispositivosApagados = cantidadTotalDispositivos(dispositivos) - cantidadDispositivosEncencidos(dispositivos);
		return dispositivosApagados;
	}

	//Existen dispositivos Encendidos
	public boolean hayDispositivosEncendidos(List<Dispositivo> dispositivos) {
		if(dispositivos.size() > 0){
			return true;
		};
		return false;
	}
	
	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * @param tipoDoc the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return the nroDoc
	 */
	public Integer getNroDoc() {
		return nroDoc;
	}

	/**
	 * @param nroDoc the nroDoc to set
	 */
	public void setNroDoc(Integer nroDoc) {
		this.nroDoc = nroDoc;
	}

	/**
	 * @return the telefono
	 */
	public Integer getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the dispositivos
	 */
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	/**
	 * @param dispositivos the dispositivos to set
	 */
	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	
}