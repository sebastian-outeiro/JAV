package balanced_expresions;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Balanceo balanceo = new Balanceo();
		
		System.out.println("Ingrese la ecuacion: ");
		String ecuacion = scan.nextLine();
		if ( balanceo.estaBalanceado(ecuacion) )
			System.out.println("La ecuacion esta balanceada y anidada");
		else
			System.out.println("La ecuacion NO esta balanceada y/o anidada");
		
		scan.close();

	}

}
