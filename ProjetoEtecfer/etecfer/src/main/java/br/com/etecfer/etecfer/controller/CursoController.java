package br.com.etecfer.etecfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.etecfer.etecfer.entity.Curso;
import br.com.etecfer.etecfer.repository.CursoRepository;
import br.com.etecfer.etecfer.service.CursoService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/Cursos")
public class CursoController {
    
    // Injeção de depenedentes da service para a classe Curso
    @Autowired
    private CursoService cursoService;

    // Metodo para salvar um Curso 
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Curso curso) {
        cursoService.save(curso);
        return "redirect:/Cursos/listar";
    }
     
    @GetMapping("/listar")
    public String listar(Model  model) {
        List<Curso> cursos = cursoService.findAll();;
        model.addAttribute("Curso", curso);
        return "Curso/listarCursos";
    }

    // Metodo para criar um formulario com um novo objeto Curso
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("Curso", new Curso());
        return "Curso/formularioCurso";
    }

    @GetMapping("/excluir/{id}")
    public String excluir (@PathVariable("id") Integer id) {
        cursoService.deleteById(id);
        return "redirect:/Cursos/listar";
    }

    
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Integer id, Model model) {
        Curso curso = (Curso) cursoService.findById(id);
        model.addAttribute("Curso", curso);
        
        return "Curso/formularioCurso";
    }
    
    
    
    
}
