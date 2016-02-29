package neflis;

public class Main {
	
	@SuppressWarnings("unused")
	private static Repositorio repositorio;

	private static void iniciarNeflis(){
		repositorio = new Repositorio();
	}

	public static void main(String[] args) {
		Main.iniciarNeflis();

	}

}
