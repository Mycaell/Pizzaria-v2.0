package View_excecoes;

public class ClienteExistenteException extends Exception{

	public ClienteExistenteException() {
		super("J� existe uma cliente cadastrado com esse CPF!");
	}
	
}
