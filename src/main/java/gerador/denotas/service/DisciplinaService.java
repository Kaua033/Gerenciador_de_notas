package gerador.denotas.service;

import gerador.denotas.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;


    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }



}
