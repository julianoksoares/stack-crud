package io.jks.stackcrud.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class ExceptionsHandlers {

    private static ErrorInfo apply(Exception e) {
        return ErrorInfo.builder()
                .codigo("100")
                .descricao(e.getMessage()).build();
    }


    private static String extractParameterName(ServerWebInputException e){
        String codings = "";
        if (e!= null){
            MethodParameter mp = e.getMethodParameter();
            if (mp != null){
                String param = mp.getParameterName();
                if (StringUtils.isNotBlank(param)){
                    return param;
                }
            }
        }
        return codings;
    }

    private static ErrorInfo apply(ServerWebInputException e) {
        return ErrorInfo.builder()
                .codigo(extractParameterName(e))
                .descricao("Valor não informado para " + extractParameterName(e))
                .build();
    }

    private static ErrorInfo apply(NotFoundException e) {
        return ErrorInfo.builder()
                .codigo("1")
                .descricao(e.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorInfo> notFound(NotFoundException ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(ExceptionsHandlers::apply)
                ;
    }

    @ExceptionHandler(ValorAlteraNaoConfereException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorInfo> valorAlteraNaoConfere(ValorAlteraNaoConfereException ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(e -> ErrorInfo.builder()
                        .codigo("0")
                        .descricao(ex.getMessage()).build())
                ;
    }

    @ExceptionHandler(DocumentoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorInfo> codigoDuplicado(DocumentoDuplicadoException ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(e -> ErrorInfo.builder()
                        .codigo("0")
                        .descricao(ex.getMessage()).build())
                ;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorInfo> notValidField(WebExchangeBindException ex) {
        return Flux.fromIterable(ex.getFieldErrors())
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .concatMap(item -> Mono.just(DetailError.builder()
                        .campo(item.getObjectName() + "." + item.getField())
                        .descricao(item.getDefaultMessage())
                        .valor(item.getRejectedValue())
                        .build()))
                .collectList()
                .map(details -> ExceptionsHandlers.this.getErrorInfo(ex, details));
    }

    private ErrorInfo getErrorInfo(WebExchangeBindException ex, List<DetailError> details) {
        return ErrorInfo.builder()
                .codigo(ex.getObjectName())
                .descricao("Erro na validação dos campos informados")
                .detalhes(details)
                .build();
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorInfo> notValidInput(ServerWebInputException ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(ExceptionsHandlers::apply);
    }

    @ExceptionHandler(CEPInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorInfo> cepInvalido(CEPInvalidoException ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(e -> ErrorInfo.builder()
                        .codigo("0")
                        .descricao(ex.getMessage()).build())
                ;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ErrorInfo> server(Exception ex) {
        return Mono.just(ex)
                .doOnRequest(e -> log.error(ExceptionUtils.getStackTrace(ex)))
                .map(ExceptionsHandlers::apply)
                ;
    }
}
