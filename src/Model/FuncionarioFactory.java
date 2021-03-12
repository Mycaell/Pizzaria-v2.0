package Model;

public class FuncionarioFactory {
//ESSA CLASSE NAO ESTÁ SENDO UTILIZADA NO SISTEMA
	
	public Funcionario criarFuncionario(String cargo) {
		
		Funcionario funcionario = null;
		
		switch(cargo) {
			case "Gerente":
				funcionario = new Gerente();
				break;
			case "Atendente":
				funcionario = new Atendente();
				break;
			case "Pizzaiolo":
				funcionario = new Pizzaiolo();
				break;
			case "Motoboy":
				funcionario = new Motoboy();
				break;
		}
		
		return funcionario;
	}
	
	
	
}
