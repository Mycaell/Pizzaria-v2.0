package View_excecoes;

public class FuncionarioExistenteException extends Exception{

	public FuncionarioExistenteException() {
		super("Já existe um funcionário cadastrado com esse e-mail");
	}
	
}
