package sge.dispositivo;

import com.fasterxml.jackson.annotation.JsonProperty;

import sge.estados.Estado;

public class DispositivoEstandar implements Dispositivo {

	@JsonProperty("idDispositivo")
	private int idDispositivo;

	@JsonProperty("nombreDispositivo")
	private String nombreDispositivo;

	@JsonProperty("consumoKwH")
	private double consumoKwH;

	@JsonProperty("tipoDispositivo")
	private String tipoDispositivo;

	@JsonProperty("idFabricante")
	private double idFabricante;

	private double idAdaptador;

	@Override
	public void convertirseAInteligente() {
		this.setIdAdaptador(this.idDispositivo);
		EstandarToInteligenteAdapter dispositivoAAdaptar = new EstandarToInteligenteAdapter(this.idDispositivo, this.nombreDispositivo, this.consumoKwH, this.tipoDispositivo, this.idFabricante, this.idAdaptador);
		System.out.println("Se ha Adaptado el siguiente dispositivo" + dispositivoAAdaptar.getNombreDispositivo());
	}

	@Override
	public boolean esInteligente() {
		return false;
	}

	@Override
	public int getIdDispositivo() {
		return this.idDispositivo;
	}

	@Override
	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	@Override
	public String getNombreDispositivo() {
		return "Dispositivo Estandar";
	}

	@Override
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = "Dispositivo Estandar";
	}

	@Override
	public double getConsumoKwH() {
		return this.consumoKwH;
	}

	@Override
	public void setConsumoKwH(double consumoKwH) {
		this.consumoKwH = consumoKwH;
	}

	@Override
	public String getTipoDispositivo() {
		return "E";
	}

	@Override
	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = "E";
	}

	@Override
	public double getIdFabricante() {
		return this.idFabricante;
	}

	@Override
	public void setIdFabricante(double idFabricante) {
		this.idFabricante = idFabricante;
	}

	@Override
	public Estado getEstado() {
		return null;
	}

	@Override
	public void setEstado(Estado estado) {
	}

	@Override
	public double getIdAdaptador() {
		return this.idAdaptador;
	}

	@Override
	public void setIdAdaptador(double idAdaptador) {
		this.idAdaptador = idAdaptador;
	}
	
}