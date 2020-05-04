package beans;
/**
 * 
 * @author Ishanka
 * PaymentScheme class
 * which describe the properties, getters and setters of a payment scheme
 *
 */
public class PaymentSchemeBean {

	private int id;
	private int doc_id;
	private int hospital_id;
	private double doc_charge;
	private double hosp_charge;
	private double tax;
	
	
	/**
	 * 
	 * @return
	 */
	public double getTax() {
		return tax;
	}
	/**
	 * 
	 * @param tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * 
	 * @return doc_id
	 */
	public int getDoc_id() {
		return doc_id;
	}
	/**
	 * 
	 * @param doc_id
	 */
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	
	
	/**
	 * 
	 * @return hospital_id
	 */
	public int getHospital_id() {
		return hospital_id;
	}
	
	/**
	 * 
	 * @param hospital_id
	 */
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	
	
	/**
	 * 
	 * @return doc_charge
	 */
	public double getDoc_charge() {
		return doc_charge;
	}
	/**
	 * 
	 * @param doc_charge
	 */
	public void setDoc_charge(double doc_charge) {
		this.doc_charge = doc_charge;
	}
	
	
	/**
	 * 
	 * @return hosp_charge
	 */
	public double getHosp_charge() {
		return hosp_charge;
	}
	/**
	 * 
	 * @param hosp_charge
	 */
	public void setHosp_charge(double hosp_charge) {
		this.hosp_charge = hosp_charge;
	}
	
	
	
}
