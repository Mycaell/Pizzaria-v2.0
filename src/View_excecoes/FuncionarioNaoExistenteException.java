package View_excecoes;

public class FuncionarioNaoExistenteException extends Exception{
	
	public FuncionarioNaoExistenteException() {
		super("Voc� n�o est� cadastrado");
	}

}
