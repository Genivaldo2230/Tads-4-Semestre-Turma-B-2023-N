package com.example.demo.controller;

import com.example.demo.model.Series;
import com.example.demo.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeriesController {

    @Autowired
    private SeriesRepository seriesRepository;

    @GetMapping("/")
    public String mostrarPaginaInicial() {
        return "index";
    }

    @GetMapping("/index")
    public String mostrarPaginaIndex() {
        return "listarSeries";
    }

    @GetMapping("/index.html")
    public String mostrarPaginaHtmlIndex() {
        return "index";
    }

    @GetMapping("/addSeries")
    public String mostrarFormularioAdicionarSeries(Model model) {
        model.addAttribute("series", new Series());
        return "addSeries";
    }

    @PostMapping("/adicionarFilme")
    public String adicionarFilme(@ModelAttribute Series series, Model model) {
        seriesRepository.save(series);

        // Retrieve the updated list of series from the database
        List<Series> lista = seriesRepository.findAll();

        // Add the list of series and a success message to the model
        model.addAttribute("series", lista);
        model.addAttribute("mensagem", "Série adicionada com sucesso!");

        // Return the name of the template Thymeleaf to be rendered for the success page
        return "listarSeries";
    }

    @GetMapping("/listarSeries")
    public String listarSeries(Model model) {
        List<Series> lista = seriesRepository.findAll();
        model.addAttribute("series", lista);
        return "listarSeries";
    }

    @GetMapping("/voltarParaIndexs")
    public String voltarParaIndexs() {
        return "redirect:/index";
    }

    @GetMapping("/paginaComVideo")
    public String paginaComVideo() {
        return "listarSeries";
    }




}
