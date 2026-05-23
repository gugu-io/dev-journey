package gasc.model;

public final class EmpregadoHorista extends Empregado {
	private int horasTrabalhadas;
	private final double VALOR_POR_HORA = 30;

	public EmpregadoHorista(String nome, double salarioBase, short numeroDependentes, int horasTrabalhadas) {
		super(nome, salarioBase, numeroDependentes);
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public int getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(int horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	
	public double getValorPorHora() {
		return VALOR_POR_HORA;
	}

	@Override
	public double salarioBruto() {
		return horasTrabalhadas > 0 ? super.getSalarioBase() + (VALOR_POR_HORA * horasTrabalhadas) : super.getSalarioBase();
	}

}