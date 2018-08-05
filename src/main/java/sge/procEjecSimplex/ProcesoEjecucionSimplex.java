package sge.procEjecSimplex;

import static java.util.stream.Collectors.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import com.mchange.v1.util.ArrayUtils;

import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoInteligente;
import sge.simplexSolver.SimplexFacade;
import sge.usuario.*;

public class ProcesoEjecucionSimplex {
	private long rangoEjecucionSegs = 0;
	private Cliente unUsuario;
	private Object listaCoeficientes;
	
	public ProcesoEjecucionSimplex(long unRangoDeEjecucion, Cliente unUsuario) {
		this.rangoEjecucionSegs = unRangoDeEjecucion;
		this.unUsuario = unUsuario;
	}

	public long getRangoEjecucionSegs() {
		return rangoEjecucionSegs;
	}

	public void setRangoEjecucionSegs(long rangoEjecucionSegs) {
		this.rangoEjecucionSegs = rangoEjecucionSegs;
	}
	
	public List<Dispositivo> ejecutarPeticion() throws FileNotFoundException, IOException {
		List<Double> listaCoeficientes = new ArrayList<Double>();
		double[] listaSimplex;
		double z;
		PointValuePair variables;
		int inicio = 0;
		int cantidad = this.unUsuario.getDispositivos().size();
		List<Dispositivo> dispositivosSobrepasados = new ArrayList<Dispositivo>();
		
		SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);
		simplexFacade.crearFuncionEconomica(1,1,1,1,1,1,1,1);
		
		
		try {
			for(int i = 0; i <= cantidad; ++i) {
				Dispositivo d = unUsuario.getDispositivos().get(i);
				listaCoeficientes.add(d.obtenerCoeficiente());
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listaSimplex = listaCoeficientes.stream().mapToDouble(d -> d).toArray(); 
		
		simplexFacade.agregarRestriccion(Relationship.LEQ, 440640, listaSimplex);
		
		PointValuePair solucion = simplexFacade.resolver();
		
		z = solucion.getValue();
		variables = solucion;
		
		for (Dispositivo dispositivo : unUsuario.getDispositivos()){
			if (inicio < cantidad){
				if (variables.getPoint()[inicio] <= dispositivo.getConsumoKwH()){ /* consumo */
					System.out.println(variables.getPoint()[inicio]);
					System.out.println(dispositivo.getConsumoKwH()); /* consumo */
					dispositivosSobrepasados.add(dispositivo);
				}
				inicio=inicio+1;
			}
		}
		return dispositivosSobrepasados;
	}
	
	
	public void ejecutarPorTiempo(){
        
		new Timer().schedule(new TimerTask() {
		    @Override
		    public void run() {
		        try {
					ejecutarPeticion();
					System.out.println("Ejecutado");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}, 5, 5);
		
    }
}

	
