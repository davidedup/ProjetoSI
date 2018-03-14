package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoInexistenteException extends Exception {

	public ObjetoInexistenteException(String erro) {
		super(erro);
	}
	
}
