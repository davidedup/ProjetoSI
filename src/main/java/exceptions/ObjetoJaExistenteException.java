package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ObjetoJaExistenteException extends Exception {

	public ObjetoJaExistenteException(String erro) {
		super("ExcecaoDados: " + erro);
	}
	
}
