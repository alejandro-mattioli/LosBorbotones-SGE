package main.java.sge.mappers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.sge.modelos.Cliente;

/**
 * 
 * @author Alejandro
 *
 */
public class UsuariosJsonMapper {

	public UsuariosJsonMapper() {

	}

	/**
	 * El metodo se encarga de extraer la lista de clientes desde un archivo json
	 * 
	 */
	public List<Cliente> extraerClientesJson(String nombreJsonArch) throws IOException {
		// Lee archivo json
		byte[] jsonData = Files.readAllBytes(Paths.get(nombreJsonArch));

		ObjectMapper objectMapper = new ObjectMapper();

		// Obtiene Clientes
		List<Cliente> clientes = objectMapper.readValue(jsonData, new TypeReference<List<Cliente>>() {
		});

		// Devuelve la lista de objetos leídos
		return clientes;
	}
}