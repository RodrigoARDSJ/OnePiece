package br.com.rodrigo.onepiece.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rodrigo.onepiece.dto.ExceptionResponseDTO;
import br.com.rodrigo.onepiece.exception.NaoEncontradoException;


@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String HEADER_MESSAGE = "mensagem";

	private static final String TITLE_PARAMETROS_INVALIDOS = "Parâmetros inválidos";
	private static final String TITLE_DADOS_JA_CADASTRADOS = "Dados já cadastrados";
	private static final String TITLE_NAO_ENCONTRADO = "Entidade não encontrada";
	private static final String TITLE_ERRO_SERVIDOR = "Erro no servidor";

	private static final String TYPE_VALIDACAO_PARAMETROS = "Validação de Parâmetros";
	private static final String TYPE_VERIFICACAO_DADOS = "Verificação de dados";
	private static final String TYPE_ERRO_INESPERADO = "Erro inesperado";

	private ExceptionResponseDTO criarExceptionResponseDTO(String title, String type, List<String> detail,
			String instance) {
		ExceptionResponseDTO exception = new ExceptionResponseDTO();
		exception.setDetail(detail);
		exception.setInstance(instance);
		exception.setTitle(title);
		exception.setType(type);
		return exception;
	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handleNaoEncontradoException(NaoEncontradoException exception,
			ServletWebRequest request) {
		
		logger.error(exception.getMessage(), exception);

		ExceptionResponseDTO bodyExceptionResponse = criarExceptionResponseDTO(TITLE_NAO_ENCONTRADO,
				TYPE_VERIFICACAO_DADOS, Arrays.asList(exception.getMessage()), request.getRequest().getRequestURI());
		
		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, exception.getMessage());
		
		return handleExceptionInternal(exception, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(e.getMessage(), e);

		ExceptionResponseDTO bodyExceptionResponse = criarExceptionResponseDTO(TITLE_PARAMETROS_INVALIDOS,
				TYPE_VALIDACAO_PARAMETROS, e.getFieldErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
				request.getDescription(false).replace("uri=", ""));

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getObjectName());

		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaughtException(Exception e, ServletWebRequest request) {

		logger.error(e.getMessage(), e);

		ExceptionResponseDTO bodyExceptionResponse = criarExceptionResponseDTO(TITLE_ERRO_SERVIDOR,
				TYPE_ERRO_INESPERADO, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
