package br.com.etecfer.etecfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etecfer.etecfer.entity.Curso;
import br.com.etecfer.etecfer.repository.CursoRepository;

@Service
public class CursoService {
    // Injeção de dependencias do repositorio  para a classe Curso 
    @Autowired
   private CursoRepository CursoRepository;

     //  Metodo para salvar um Curso
   public Curso save(Curso Curso){
      return (Curso) CursoRepository.findAll();
    }
    // Metodo para listar todos os Cursos
     public List<CursoRepository> findAll(){
        return CursoRepository.findAll();
     }

     //metodo para excluir um Curso pelo id
     public void deleteById(Integer id){
      CursoRepository.deleteById(id);
     }
     // metodo para buscar o Curso pelo id
     public CursoRepository findById(Integer id){
     return CursoRepository.findById(id).orElse(null);
     }
}
