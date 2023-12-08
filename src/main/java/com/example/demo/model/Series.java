package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity(name = "series")
@Table(name = "series")
@Getter
@Setter
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 50, message = "O título não pode ter mais de 50 caracteres.")
    private String titulo;
    private String genero;
    @Column(name = "ano_lancamento")
    @Min(value = 1900, message = "O ano de lançamento deve ser igual ou maior que 1900.")
    @Max(value = 2030, message = "O ano de lançamento foi digitado errado.")
    private Integer anoLancamento;
    // Construtores
    public Series() {
        // Construtor vazio padrão
    }
    public Series( Long id, String titulo, String genero, Integer anoLancamento ) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }
    public class SeriesValidator {
        private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        private static final Validator validator = factory.getValidator();
        public static void validarCampos(Series series) {
            // Valida os campos da entidade Series
            Set<ConstraintViolation<Series>> violations = validator.validate(series);
            if (!violations.isEmpty()) {
                // Se houver violações de validação, crie uma mensagem de erro
                StringBuilder errorMessage = new StringBuilder("Erros de validação:");
                for (ConstraintViolation<Series> violation : violations) {
                    errorMessage.append(" ").append(violation.getMessage());
                }
                // Lance uma ConstraintViolationException com a mensagem de erro
                throw new ConstraintViolationException(errorMessage.toString(), violations);
            }
        }
    }
}