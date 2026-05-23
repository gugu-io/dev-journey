package gasc.model;

public final class EmpregadoComissionista extends Empregado {
	private double valorItemVendido;
	private short numeroItensVendidos;
	
	public EmpregadoComissionista(String nome, double salarioBase, short numeroDependentes, double valorItemVendido, short numeroItensVendidos) {
		super(nome, salarioBase, numeroDependentes);
		this.valorItemVendido = valorItemVendido;
		this.numeroItensVendidos = numeroItensVendidos;
	}

	public double getValorItemVendido() {
		return valorItemVendido;
	}

	public void setValorItemVendido(double valorItemVendido) {
		this.valorItemVendido = valorItemVendido;
	}

	public short getNumeroItensVendidos() {
		return numeroItensVendidos;
	}

	public void setNumeroItensVendidos(short numeroItensVendidos) {
		this.numeroItensVendidos = numeroItensVendidos;
	}

	@Override
	public double salarioBruto() {
		return numeroItensVendidos > 0 ? getSalarioBase() + numeroItensVendidos * valorItemVendido : getSalarioBase();
	}

	@Override
	public String toString() {
		return String.format("%s\nValor por item vendido: R$%,1.2f\nItens vendidos: %d", valorItemVendido, numeroItensVendidos);
	}
	
}
