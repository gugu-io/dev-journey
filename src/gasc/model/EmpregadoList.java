package gasc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class EmpregadoList {
	private List<Empregado> empregadoList;

	public EmpregadoList() {
		empregadoList = new ArrayList<Empregado>();
	}
	
	// Informa o número atual de empregados da lista.
	public int numeroEmpregados() {
		return empregadoList.size();
	}
	
	// Insere um empregado na lista. Retorna true se foi inserido ou false se não.
	public boolean inserir(Empregado empregado) {
		return empregadoList.add(empregado);
	}
	
//	// Pesquisa pelo nome do empregado na lista.
//	public Optional<Empregado> pesquisar(String nome) {
//		
//	}
//	
//	// Obtém um empregado na posição indicada.
//	public Optional<Empregado> obter(int posicao) {
//		
//	}
//	
//	// Obtém a posição do empregado na lista se o seu nome estiver cadastrado.
//	private OptionalInt obter(String nome) {
//		
//	}
	
}
