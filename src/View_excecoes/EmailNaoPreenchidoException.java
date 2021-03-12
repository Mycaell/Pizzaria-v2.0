package View_excecoes;

public class EmailNaoPreenchidoException extends Exception{

	public EmailNaoPreenchidoException() {
		super("O campo de e-mail não foi preenchido!");
	}
	
}
