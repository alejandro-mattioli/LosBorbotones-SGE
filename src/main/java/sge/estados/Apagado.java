package sge.estados;

public class Apagado extends Estado {

	@Override
	public boolean estaEncendido() {
			return false;
	}

	@Override
	public boolean estaApagado() {
		return true;
	}

	@Override
	public boolean estaEnModoAhorro() {
		return false;
	}

}
