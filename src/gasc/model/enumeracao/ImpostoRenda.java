package gasc.model.enumeracao;

public enum ImpostoRenda {
	ALIQUOTA_7_5(2428.81, 7.5, 182.16),
	ALIQUOTA_15(2826.66, 15, 394.16),
	ALIQUOTA_22_5(3751.06, 22.5, 675.49),
	ALIQUOTA_27_5(4664.68, 27.5, 908.73);	
	
	private final double baseCalculo;
	private final double aliquota;
	private final double deducao;
	
	private ImpostoRenda(double baseCalculo, double aliquota, double deducao) {
		this.baseCalculo = baseCalculo;
		this.aliquota = aliquota;
		this.deducao = deducao;
	}

	public double getBaseCalculo() {
		return baseCalculo;
	}

	public double getAliquota() {
		return aliquota;
	}

	public double getDeducao() {
		return deducao;
	}
	
	public static ImpostoRenda pesquisarImpostoRenda(double baseCalculo) {
		for(int indice = 0; indice < values().length - 1; indice ++)
			if(Double.compare(baseCalculo, values()[indice].getBaseCalculo())  >= 0 && Double.compare(baseCalculo, values()[indice+1].getBaseCalculo()) < 0)
				return values()[indice];
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("Calculo Base: R$%,1.2f | Alíquota: %,1.2f%% | Dedução: R$%,1.2f", baseCalculo, aliquota, deducao);
	}
	
}