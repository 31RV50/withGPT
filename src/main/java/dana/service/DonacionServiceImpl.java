package dana.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dana.model.Donacion;
import dana.model.Usuario;
import dana.repository.IDonacionRepository;

@Service
public class DonacionServiceImpl implements IDonacionService{
	
	@Autowired
	private IDonacionRepository donacionRepository;

	@Override
	public Donacion save(Donacion donacion) {
		
		return donacionRepository.save(donacion);
	}
	
    @Override
    public Optional<Donacion> findById(Integer id) {
	return donacionRepository.findById(id);
    }

    @Override
    public List<Donacion> findByUsuario(Usuario usuario) {
	
	return donacionRepository.findByUsuario(usuario);
    }

	@Override
	public List<Donacion> findAll() {
	
		return donacionRepository.findAll();
	}
	
	// 0000010
	public String generarNumeroDonacion() {
		int numero = 0;
		String numeroConcatenado = "";
		
		List<Donacion> donaciones = findAll();
		
		List<Integer> numeros = new ArrayList<Integer>();
		
		donaciones.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero() ) ));
		if (donaciones.isEmpty()) {
			numero = 1;
		} else {
			
			numero = numeros.stream().max(Integer:: compare).get();
			numero++;
			
		}
		
		if (numero < 10) {
			numeroConcatenado = "000000000"+String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "00000000"+String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "0000000"+String.valueOf(numero);
		} else if (numero < 10000) {
			numeroConcatenado = "000000"+String.valueOf(numero);
		} else if (numero < 100000) {
			numeroConcatenado = "00000"+String.valueOf(numero);
		} else if (numero < 1000000) {
			numeroConcatenado = "0000"+String.valueOf(numero);
			
		}
		return numeroConcatenado;
	}
}
