package gasc.model;

public interface Imposto {
	final double TAXA_CONTRIBUICAO = 11;
	final double DECONTO_POR_DEPENDETE = 189.59;
	
	double irpf(double salarioBruto);
	
	double pss(double salarioBruto);
}