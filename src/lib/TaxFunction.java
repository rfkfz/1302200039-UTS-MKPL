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
			return 0; // menambahkan return untuk jumlah dari tax yg invalid
			// tujuan untuk memperjelas masukan dari user
		}

		if (numberOfChildren > maxChild) {
			numberOfChildren = maxChild; // mengubah angka menjadi variable
		}

		int totalMonthIncome = monthlySalary + otherMonthlyIncome; // bertujuan mempersingkat aritmatika dalam fungsi
		int totalDeductible = deductible; // bertujuan mempersingkat aritmatika dalam fungsi
		int totalTaxAB = taxA + taxB; // bertujuan mempersingkat aritmatika dalam fungsi

		// Fungsi if-else dibawah sudah diOptimalkan
		if (isMarried) {
			totalDeductible += totalTaxAB + (numberOfChildren * taxC);
		} else {
			totalDeductible += totalTaxAB;
		}
		tax = (int) Math.round(taxKoma * ((totalMonthIncome * numberOfMonthWorking) - totalDeductible));
		
		// menghapus fungsi if-else paling akhir karena menurut saya tidak ada error handlingnya
		return Math.max(tax, 0);
		// menambahkan library Math.max bertujuan sebagai error handling supaya nilai tidak pernah negatif
			 
	}
	
}
