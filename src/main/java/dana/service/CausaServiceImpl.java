package dana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dana.model.Causa;
import dana.repository.ICausaRepository;

@Service
public class CausaServiceImpl implements CausaService {
	

	@Autowired
	private ICausaRepository causaRepository;

	@Override
	public Causa save(Causa causa) {
		return causaRepository.save(causa);
	}

	@Override
	public Optional<Causa> get(Integer id) {
		return causaRepository.findById(id);
	}

	@Override
	public void update(Causa causa) {
		causaRepository.save(causa);
		
	}

	@Override
	public void delete(Integer id) {
	    causaRepository.deleteById(id);
		
	}

	@Override
	public List<Causa> findAll() {
		 return causaRepository.findAll();
	}

	@Override
	public Optional<Causa> findById(Integer causaId) {
		
		return Optional.empty();
	}

}

