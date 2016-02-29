package balanced_expresions;

import java.util.Stack;

public class Balanceo {
	
	public boolean estaBalanceado(String ecuacion){
		Stack<Character> pila = new Stack<Character>();
		
		for (char letra : ecuacion.toCharArray() ){
			switch (letra){
			case '(' : 
			case '[' : 
			case '{':
				pila.push(letra);
				break;
			case ')' :
				if ( pila.isEmpty() ||  pila.pop() != '(' )
					return false;
				break;
			case ']' :
				if ( pila.isEmpty() ||  pila.pop() != '[' )
					return false;
				break;
			case '}' :
				if ( pila.isEmpty() ||  pila.pop() != '{' )
					return false;
				break;
			}
		}
		return true;
	}
	
	
	
	
}
