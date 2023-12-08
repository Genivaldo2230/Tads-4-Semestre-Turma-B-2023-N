import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public String adicionarFilmes( ConstraintViolationException e, @ModelAttribute HttpStatus.Series series, Model model ) {
        List<String> errorMessages = e.getConstraintViolations().stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());

        model.addAttribute("errorMessages", errorMessages);
        // Evita salvar no banco se houver erros de validação
//        return "errorPage";
        return "adicionarFilme";
    }
}
