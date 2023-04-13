package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	// Bad smell pertama yaitu Primitive Obsession pada variabel grade dan gender
	// Solusi membuat tipe data enum
	// Sumber solusi: LMS Minggu 04
	public enum Grade {
		grade1, grade2, grade3;
	}

	public enum Gender {
		male, female;
	}
	private Grade grade; 
	private Gender gender;
	//-----------------------------
	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	public void setMonthlySalary(Grade grade) {
		// Fungsi ini terindikasi bad smell Duplicated Code
		// Pada kode sebelumnya Duplicated Code terjadi in case masukan user isForeigner
		// Saya mengubah if-else menjadi switch-case untuk tipe data enum
		// Untuk mencegah Duplicated Code saya memisahkan kondisi isForeigner	
		switch (grade) {
			case grade1:
				monthlySalary = 3000000;
				break;
			case grade2:
				monthlySalary = 5000000;
				break;
			case grade3:
				monthlySalary = 7000000;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade");
		}
		monthlySalary = isForeigner ? (int) (monthlySalary * 1.5) : monthlySalary;
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
