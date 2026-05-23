package gasc.model;

public final class Chefe extends Empregado {
	private double gratificacao;

	public Chefe(String nome, double salarioBase, short numeroDependentes, double gratificacao) {
		super(nome, salarioBase, numeroDependentes);
		this.gratificacao = gratificacao;
	}
	
	public double getGratificacao() {
		return gratificacao;
	}

	public void setGratificacao(double gratificacao) {
		this.gratificacao = gratificacao;
	}

	@Override
	public double salarioBruto() {
		return super.getSalarioBase() + gratificacao;
	}
	
	@Override
	public String toString() {
		return String.format("%s\nGratificação: R$%,1.2f", super.toString(), gratificacao);
	}	
}