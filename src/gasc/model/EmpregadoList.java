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
	
	// Pesquisa pelo nome do empregado na lista.
	public Optional<Empregado> pesquisar(String nome) {
		OptionalInt posicaoEmpregadoOpt = obter(nome);
		
		if(posicaoEmpregadoOpt.isPresent()) {
			int posicaoEmpregado = posicaoEmpregadoOpt.getAsInt();
			Optional<Empregado> empregadoOpt = obter(posicaoEmpregado);
			
			if(empregadoOpt.isPresent())
				return empregadoOpt;
		}
		
		return Optional.empty();
	}
	
	// Obtém um empregado na posição indicada.
	public Optional<Empregado> obter(int posicao) {
		return (posicao >= 0 && posicao < numeroEmpregados()) ? Optional.of(empregadoList.get(posicao)) : Optional.empty();
	}
	
	// Obtém a posição do empregado na lista se o seu nome estiver cadastrado.
	private OptionalInt obter(String nome) {
		for(Empregado empregado : empregadoList)
			if(empregado.getNome().compareToIgnoreCase(nome) == 0)
				return OptionalInt.of(empregadoList.indexOf(empregado));
		return OptionalInt.empty();
	}
	
}
