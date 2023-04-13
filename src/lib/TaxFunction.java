package lib;

public class TaxFunction {

	// Bad smell Magic Numbers(1) -----------
	// Angka diubah karena tidak memiliki penjelasan yang jelas
	static int maxMonth = 12; // mengubah angka 12 menggunakan variable
	static int maxChild = 3; // mengubah angka 3 menggunakan variable
	static double taxKoma = 0.05; // mengubah 0.05 menggunakan variable
	static int taxA = 54000000; // mengubah angka 54000000 menggunakan variable
	static int taxB = 4500000; // mengubah angka 4500000 menggunakan variable
	static int taxC = 1500000; // mengubah angka 1500000 menggunakan variable
	// ------------------------------------
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > maxMonth) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > maxChild) {
			numberOfChildren = maxChild;
		}
		
		if (isMarried) {
			tax = (int) Math.round(taxKoma * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (taxA + taxB + (numberOfChildren * taxC))));
		}else {
			tax = (int) Math.round(taxKoma * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - taxA));
		}
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
			 
	}
	
}
