package gasc.model;

import gasc.model.enumeracao.ImpostoRenda;

public abstract class Empregado implements Imposto {
	private String nome;
	private double salarioBase;
	private short numeroDependentes;

	public Empregado() {
		nome = "";
		salarioBase = 0;
		numeroDependentes = 0;
	}
	
	public Empregado(String nome, double salarioBase, short numeroDependentes) {
		this.nome = nome;
		this.salarioBase = salarioBase;
		this.numeroDependentes = numeroDependentes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public short getNumeroDependentes() {
		return numeroDependentes;
	}

	public void setNumeroDependentes(short numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}
	
	@Override
	public double irpf(double salarioBruto) {
		ImpostoRenda impostoRenda = ImpostoRenda.pesquisarImpostoRenda(salarioBruto);

		if(impostoRenda != null)
				return salarioBruto / 10 * impostoRenda.getAliquota() - impostoRenda.getDeducao();
		return 0;
	}
	
	@Override
	public double pss(double salarioBruto) {
		return salarioBruto / 100 * TAXA_CONTRIBUICAO;
	}
	
	public double salarioLiquido() {
		double valorSalarioBruto = salarioBruto(); 
		double valorIrpf = irpf(salarioBruto());
		double valorPss = pss(salarioBruto());
		double descontoPorDependente = DECONTO_POR_DEPENDETE * numeroDependentes;
		
		if(descontoPorDependente < valorIrpf)
			return valorSalarioBruto - (valorIrpf - descontoPorDependente) - valorPss;
		return valorSalarioBruto - valorPss;
	}
	
	public abstract double salarioBruto();
	
	@Override
	public String toString() {
		return String.format("Nome: %s\nSalário base: R$%,1.2f\nNúmero de dependentes: %d", nome, salarioBase, numeroDependentes);
	}
	
}