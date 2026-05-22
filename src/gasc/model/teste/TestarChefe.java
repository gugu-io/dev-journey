package gasc.model.teste;

import gasc.model.Chefe;

public class TestarChefe {
	
	public static void main(String[] args) {
		Chefe chefe = new Chefe("Gustavo", 2428.80, (short)2, 120);
		
		System.out.println(chefe.toString());
	}
	
}
