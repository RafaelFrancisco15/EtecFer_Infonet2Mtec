package br.com.etecfer.etecfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.etecfer.etecfer.entity.Aluno;
import br.com.etecfer.etecfer.entity.Curso;
import br.com.etecfer.etecfer.service.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "curso/listarCursos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formularioCurso";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id) {
        cursoService.deleteById(id);
        return "redirect:/cursos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Integer id, Model model) {
        Curso curso = cursoService.findById(id);
        model.addAttribute("curso", curso);

        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("aluno", alunos);


 
 
        return "curso/formularioCurso";
    }
}
