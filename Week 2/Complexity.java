//Prithiv Dev Devendran
//CWID - 10453922
//pdevendr@stevens.edu


public class Complexity {
	 
	//O(n^2).
	public static void method1(int n) {
		int counter =1;
		for (int a=0; a<n; a++) {
			for(int b=0;b<n;b++) {
				System.out.println("Operation : "+counter); counter ++;
			}
		}
	 }
	
	//O(n^3).
	public static void method2(int n) {
		int counter =1;
		for(int a=0; a<n; a++) {
			for(int b=0;b<n;b++) {
				for(int k=0;k<n;k++) {
					System.out.println("Operation : "+counter); counter ++;	
				}
			}
		}
	 }
	
	//O(log n).
	public static void method3(int n) {
		int counter =1;
		for (int a=1; a<n; a=a*2) {
				System.out.println("Operation : "+counter); counter ++;
		}
	 }

	//O(n log n).
	public static void method4(int n) {
		int counter =1;
		for(int a=0; a<n; a++) {
		for (int j=1; j<n; j=j*2) {
				System.out.println("Operation : "+counter); counter ++;
		}
	   }
	 }
	
	//O(log log n)
	public static void method5(int n) {
		int counter =1;
		for (int j=2; j<n; j=j*j) {
				System.out.println("Operation : "+counter); counter ++;
	   }
	 }

	
	public static void method6(int n) {
		int counter =1;
		for (int a = 1; a <= Math.pow(2, n); a++) {
				System.out.println("Operation : "+counter); counter ++;
		}
	 }
	
}
