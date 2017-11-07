# /**
 * @author <Nemanja Filipovic>
 * Matrikelnummer:01348905
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;


public abstract class Fahrzeug implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String marke;
	private String modell;
	private int baujahr;
	private double grundpreis;
	
	public Fahrzeug(String id, String marke, String modell, int baujahr, double grundpreis) {
		if(grundpreis < 0) throw new IllegalArgumentException("Error: Grunpreis ungueltig.");
		this.id = id;
		this.marke = marke;
		this.modell = modell;
		setBaujahr(baujahr);
		this.grundpreis = grundpreis;
	}
	
	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(int baujahr) {
		Calendar heute = Calendar.getInstance();
		try {
			if (baujahr > heute.get(Calendar.YEAR)) {
				throw new IllegalArgumentException("Error: Baujahr ungueltig.");
			}
			this.baujahr = baujahr;
		} catch (IllegalArgumentException e) {
			System.out.println("Error: Baujahr ungueltig.");
		}
	}

	public double getGrundpreis() {
		return grundpreis;
	}

	public void setGrundpreis(double grundpreis) {
		this.grundpreis = grundpreis;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getAlter() {
		Calendar heute = Calendar.getInstance();
		return heute.get(Calendar.YEAR) - baujahr;
	}
	
	public abstract double getRabatt();
	
	public double getPreis() {
		return getGrundpreis() - getRabatt();
	}

	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}

	@Override
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		return "Id:          " + id + "\nMarke:       " + marke + "\nModell:      " + modell + "\nBaujahr:     " + baujahr
				+ "\nGrundpreis:  " + df.format(grundpreis);
	}		
}
