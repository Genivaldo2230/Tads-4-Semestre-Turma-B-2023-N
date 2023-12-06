package com.example.demo.controller;

import com.example.demo.model.Series;
import com.example.demo.repository.SeriesRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SeriesController {

    @Autowired
    private SeriesRepository seriesRepository;

    // Rota para mostrar a página inicial
    @GetMapping("/")
    public String mostrarPaginaInicial() {
        return "index";
    }

    // Rota alternativa para mostrar a página inicial
    @GetMapping("/index")
    public String mostrarPaginaIndex() {
        return "listarSeries";  // redireciona para a lista de séries
    }

    // Rota alternativa para mostrar a página inicial em formato HTML
    @GetMapping("/index.html")
    public String mostrarPaginaHtmlIndex() {
        return "index";
    }

    // Rota para exibir o formulário de adição de séries
    @GetMapping("/addSeries")
    public String mostrarFormularioAdicionarSeries(Model model) {
        model.addAttribute("series", new Series());  // Adiciona uma nova instância de Series ao modelo
        return "addSeries";
    }

    // Método que trata requisições POST para a rota "/adicionarFilme"
    @PostMapping("/adicionarFilme")
    public String adicionarFilme( @ModelAttribute Series series, Model model ) {
        try {
            // Lógica para validar os campos do formulário
            Series.SeriesValidator.validarCampos(series);

            // Lógica para salvar no banco de dados se a validação passar
        seriesRepository.save(series);

            // Recupera todas as séries do banco de dados
        List<Series> lista = seriesRepository.findAll();

            // Adiciona a lista de séries e uma mensagem ao modelo para serem exibidos na view
            model.addAttribute("series", lista);
        model.addAttribute("mensagem", "Série adicionada com sucesso!");

            // Retorna o nome da página a ser exibida após o sucesso
        return "listarSeries";

        } catch (ConstraintViolationException e) {
            // Se ocorrer erro de validação, redireciona para a página de erro sem salvar no banco
            // Adiciona o objeto 'series' ao modelo para ser exibido na página de erro
            model.addAttribute("series", listarSeries(model));

            // Retorna o nome da página de erro
            return "errorPage";
        }
    }

    @GetMapping("/paginaComFormulario")
    public String mostrarPaginaComFormulario() {
        return "paginaComFormulario";
    }

    @GetMapping("/errorPage")
    public String errorPage() {
        return "errorPage";
    }


    // Rota para listar todas as séries
    @GetMapping("/listarSeries")
    public String listarSeries(Model model) {
        List<Series> lista = seriesRepository.findAll();
        model.addAttribute("series", lista);
        return "listarSeries";
    }

    // Rota para redirecionar para a página inicial
    @GetMapping("/voltarParaIndex")
    public String voltarParaIndex() {
        return "redirect:/index";
    }

    // Rota para exibir uma página com vídeo (redireciona para a lista de séries)
    @GetMapping("/paginaComVideo")
    public String paginaComVideo() {
        return "listarSeries";
    }

    // Adicione este método para lidar com a exceção específica
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException( ConstraintViolationException e, Model model ) {
        // Extrai as mensagens de erro da exceção
        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());

        // Adiciona as mensagens de erro ao modelo
        model.addAttribute("errorMessages", errorMessages);

        // Retorna o nome do template Thymeleaf para a página de erro
        return "errorPage";
    }
}
