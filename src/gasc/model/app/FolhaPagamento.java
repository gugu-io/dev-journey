package gasc.model.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.swing.JOptionPane;

import gasc.model.Chefe;
import gasc.model.Empregado;
import gasc.model.EmpregadoComissionista;
import gasc.model.EmpregadoHorista;

import static gasc.model.util.Constantes.*;

import static mos.io.InputOutput.*;

public class FolhaPagamento {
	private EmpregadoList empregadoList;
	
	public FolhaPagamento() {
		empregadoList = criarListaDeFuncionarios();
		folhaPagamento();
	}
	
	public EmpregadoList criarListaDeFuncionarios() {
		return new EmpregadoList();
	}
	
	public void folhaPagamento() {
		int opc;
		
		do {
			opc = menu("Funcionario", TITULO_MENU_FOLHA_PAGAMENTO, MENU_FOLHA_PAGAMENTO, null);
			
			switch (opc) {
				case 0 -> cadastrarEmpregado();
				case 1 -> consultarEmpregado();
				case 3 -> relatorio();
				default -> System.exit(0);
			}
		} while(opc != JOptionPane.CLOSED_OPTION);
		
	}

	public boolean cadastrarEmpregado() {
		String nome = readString("Insira o nome", TITULO_CADASTRO_FUNCIONARIO);
		double salarioBase = readDouble("Insira o salário base", TITULO_CADASTRO_FUNCIONARIO);
		short numeroDependentes = readShort("Insira o número de dependêntes", TITULO_CADASTRO_FUNCIONARIO);
		int opcao = menu("Encolha o tipo de empregado", TITULO_CADASTRO_FUNCIONARIO, MENU_TIPO_EMPREGADO, null);
		
		switch (opcao) {
			case 0: return cadastrarEmpregadoChefe(nome, salarioBase, numeroDependentes);
			case 1: return cadastrarEmpregadoComissionista(nome, salarioBase, numeroDependentes);
			case 2: return cadastrarEmpregadoHorista(nome, salarioBase, numeroDependentes);
		}
		
		return false;
	}
	
	public boolean consultarEmpregado() {
		String nome = readString("Insira o nome", TITULO_CONSULTA_EMPREGADO);
		
		if(nome.length() != 0 && nome != null) {
			Optional<Empregado> empregadoOpt = empregadoList.pesquisar(nome);
			
			if(empregadoOpt.isPresent()) {
				Empregado empregado = empregadoOpt.get();
				
				writeTextArea(String.format("\n\tNome: %s\n", empregado.getNome()));
				writeTextArea(String.format("\tSalário base: R$%,1.2f\n", empregado.getSalarioBase()));
				writeTextArea(String.format("\tNúmero de dependêntes: %d\n", empregado.getNumeroDependentes()));
				writeTextArea(String.format("\tSalário líquido: R$%,1.2f\n", empregado.salarioLiquido()));
				writeTextArea(String.format("\tSalário Bruto: R$%,1.2f\n", empregado.salarioBruto()));
				writeTextArea(String.format("\tIRPF: R$%,1.2f\n", empregado.irpf()));
				writeTextArea(String.format("\tPSS: R$%,1.2f\n", empregado.pss()));
				
				showTextArea(getTextArea(), TITULO_CONSULTA_EMPREGADO);
				
				writeTextArea(null);
				
				return true;
			}
			
			showInfo("Empregado não cadastrado", TITULO_CONSULTA_EMPREGADO);
		} else
			showInfo("O nome do empregado não pode estar vazio", TITULO_CONSULTA_EMPREGADO);
		
		return false;
	}
	
	public void alterarEmpregado() {
		
	}
	
	public void relatorio() {
		for(int indice = 0; indice < empregadoList.numeroEmpregados(); indice++) {
			Optional<Empregado> empregadoOpt = empregadoList.obter(indice);
						
			if(empregadoOpt.isPresent()) {
				Empregado empregado = empregadoOpt.get();
				
				writeTextArea(String.format("\n\tNome: %s\n", empregado.getNome()));
				writeTextArea(String.format("\tSalário base: R$%,1.2f\n", empregado.getSalarioBase()));
				writeTextArea(String.format("\tNúmero de dependêntes: %d\n", empregado.getNumeroDependentes()));
				
				if(empregado instanceof Chefe chefe)
					writeTextArea(String.format("\tGratificação: R$%,1.2f\n", chefe.getGratificacao()));
				
				else if(empregado instanceof EmpregadoComissionista empregadoComissionista) {
					writeTextArea(String.format("\tNúmero de ítens vendidos: %d\n", empregadoComissionista.getNumeroItensVendidos()));
					writeTextArea(String.format("\tValor por ítem vendido: R$%,1.2f\n", empregadoComissionista.getValorItemVendido()));
				}
				
				else if(empregado instanceof EmpregadoHorista empregadoHorista) {
					writeTextArea(String.format("\tHoras trabalhadas no mês: %d\n", empregadoHorista.getHorasTrabalhadas()));
					writeTextArea(String.format("\tValor por hora: R$%,1.2f\n", empregadoHorista.getValorPorHora()));
				}
				
				writeTextArea(String.format("\tSalário líquido: R$%,1.2f\n", empregado.salarioLiquido()));
				writeTextArea(String.format("\tSalário Bruto: R$%,1.2f\n", empregado.salarioBruto()));
				writeTextArea(String.format("\tIRPF: R$%,1.2f\n", empregado.irpf()));
				writeTextArea(String.format("\tPSS: R$%,1.2f\n", empregado.pss()));
			}
		}
		
		showTextArea(getTextArea(), TITULO_CONSULTA_EMPREGADO);
		writeTextArea(null);
	}
	
	private boolean cadastrarEmpregadoChefe(String nome, double salarioBase, short numeroDependentes) {
		double gratificacao = readDouble("Insira o valor de gratificação", TITULO_CADASTRO_FUNCIONARIO);
		return empregadoList.inserir(new Chefe(nome, salarioBase, numeroDependentes, gratificacao));
	}
	
	private boolean cadastrarEmpregadoComissionista(String nome, double salarioBase, short numeroDependentes) {
		double precoPorItemVendido = readDouble("Insira a comissão por item vendido", TITULO_CADASTRO_FUNCIONARIO);
		short totalItensVendido = readShort("Insira o total de itens vendidos", TITULO_CADASTRO_FUNCIONARIO);
		
		return empregadoList.inserir(new EmpregadoComissionista(nome, salarioBase, numeroDependentes, precoPorItemVendido, totalItensVendido));
	}
	
	private boolean cadastrarEmpregadoHorista(String nome, double salarioBase, short numeroDependentes) {
		int totalHorasTrabalhadas = readInt("Insira o total de horas trabalhadas", TITULO_CADASTRO_FUNCIONARIO);
		return empregadoList.inserir(new EmpregadoHorista(nome, salarioBase, numeroDependentes, totalHorasTrabalhadas));
	}
	
	public static void main(String[] args) {
		new FolhaPagamento();
	}
	
	private class EmpregadoList {
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

}
