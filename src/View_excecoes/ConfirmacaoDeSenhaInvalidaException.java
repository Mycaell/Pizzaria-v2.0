package View_excecoes;

public class ConfirmacaoDeSenhaInvalidaException extends Exception{

	public ConfirmacaoDeSenhaInvalidaException() {
		super("A confirma��o da senha � incompat�vel");
	}
	
}
