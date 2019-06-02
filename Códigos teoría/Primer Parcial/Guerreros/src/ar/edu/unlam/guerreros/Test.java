package ar.edu.unlam.guerreros;

public class Test {
	
	class C1 {
		public C1() { System.out.print("1");
		super(); }
			class C2 extends C1 { 
				public C2() { System.out.print("2"); 
				super(); }
			
			class C3 extends C2 {
				public C3() { System.out.print("3");
				super(); }
			class App {
				public static void main(String[] args) {
					new C3(); }
			}


}
