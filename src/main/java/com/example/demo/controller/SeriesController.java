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

    @GetMapping({"/static"})
    public String mostrarPaginaInicial() {
        return "index";
    }

    @GetMapping("/form")
    public String mostrarFormularioAdicionarSeries(Model model) {
        model.addAttribute("series", new Series());
        return "form";
    }

    @PostMapping("/form")
    public String adicionarSerie( @ModelAttribute Series series, Model model ) {
        try {
            // Lógica para validar os campos do formulário
            Series.SeriesValidator.validarCampos(series);

            // Lógica para salvar no banco de dados se a validação passar
            seriesRepository.save(series);

            // Recupera todas as séries do banco de dados
            List<Series> lista = seriesRepository.findAll();
            // Adiciona a lista de séries e uma mensagem ao modelo para serem exibidos na view
            model.addAttribute("series", seriesRepository.findAll());
            model.addAttribute("mensagem", "Série adicionada com sucesso!");
            // Retorna o nome da página a ser exibida após o sucesso
//            return "listarSeries";
            // Redirecionar para uma nova página após o processamento bem-sucedido
            return "listarSeries";
        } catch (ConstraintViolationException e) {
            // Se ocorrer erro de validação, redireciona para a página de erro sem salvar no banco
            // Adiciona o objeto 'series' ao modelo para ser exibido na página de erro
            model.addAttribute("series", listarSeries(model));

            // Retorna o nome da página de erro
            return "errorPage";
        }
    }

    @GetMapping("/sucesso")
    public String exibirSucesso() {
        return "listarSeries";
    }
    // Rota para listar todas as séries

    @GetMapping("/listarSeries")
    public String listarSeries(Model model) {
        List<Series> lista = seriesRepository.findAll();
        model.addAttribute("series", seriesRepository.findAll());
        return "listarSeries";
    }

    /**
     * Este bean CommandLineRunner será executado na inicialização da aplicação.
     * Ele exclui o arquivo do banco de dados H2 para garantir que os dados sejam
     * limpos a cada reinicialização da aplicação.
     *
     * @return Uma instância CommandLineRunner para exclusão do arquivo do banco de dados.
     */

    // Adicione este método para lidar com a exceção específica
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException( ConstraintViolationException e, Model model ) {
        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList());
        model.addAttribute("errorMessages", errorMessages);
        return "errorPage";
    }

    @GetMapping("/errorPage")
    public String errorPage() {
        return "errorPage";
    }

}