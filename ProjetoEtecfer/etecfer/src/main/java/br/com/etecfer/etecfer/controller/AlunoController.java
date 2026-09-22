package br.com.etecfer.etecfer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.etecfer.etecfer.entity.Aluno;
import br.com.etecfer.etecfer.entity.Curso;
import br.com.etecfer.etecfer.service.AlunoService;
import br.com.etecfer.etecfer.service.CursoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    // Salvar um aluno
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Aluno aluno,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {

        try {
            if (foto != null && !foto.isEmpty()) {
                aluno.setFotoAluno(foto.getBytes());
                aluno.setTipoFoto(foto.getContentType());
            } else if (aluno.getIdAluno() != null) {
                Aluno alunoExistente = alunoService.findById(aluno.getIdAluno());

                if (alunoExistente != null) {
                    aluno.setFotoAluno(alunoExistente.getFotoAluno());
                    aluno.setTipoFoto(alunoExistente.getTipoFoto());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar a foto do aluno.", e);
        }

        alunoService.save(aluno);
        return "redirect:/alunos/listar";
    }

    // Listar alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);

        return "aluno/listarAlunos";
    }

    // Abrir formulário para criar um aluno
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());

        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);

        return "aluno/formularioAluno";
    }

    // Excluir um aluno
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id) {
        alunoService.deleteById(id);

        return "redirect:/alunos/listar";
    }

    // Abrir formulário para editar um aluno
    @GetMapping("/editar/{id}")
    public String editarForm(
            @PathVariable("id") Integer id,
            Model model) {

        Aluno aluno = alunoService.findById(id);
        model.addAttribute("aluno", aluno);

        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);

        return "aluno/formularioAluno";
    }
}
