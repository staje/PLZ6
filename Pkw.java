import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * @author <Nemanja filipovic>
 * Matrikelnummer:01348905
 */

public class Pkw extends Fahrzeug {
	

	private static final long serialVersionUID = 1L;
	
	private int serviceJahr;
	
	
	public Pkw(String id, String marke, String modell, int baujahr, double grundpreis, int serviceJahr) {
		super(id, marke, modell, baujahr, grundpreis);
		setServiceJahr(serviceJahr);

	}
	
	
	public double getRabatt() {
		double rabatt;
		rabatt = ((getAlter() * 0.05) + (((Calendar.getInstance().get(Calendar.YEAR)) - getServiceJahr()) * 0.02)) * getGrundpreis();
		if ((rabatt/getGrundpreis()) < 0) {
			return 0.0;
		}
		else if (rabatt >= (0.15 * getGrundpreis())) {
			return 0.15 * getGrundpreis();
		}
		else {
			return rabatt;
		}
	}

	public int getServiceJahr() {
		return serviceJahr;
	}

	public void setServiceJahr(int serviceJahr) {
		Calendar heute = Calendar.getInstance();
		try {
			if (serviceJahr > heute.get(Calendar.YEAR)) {
				throw new IllegalArgumentException("Error: Servicejahr ungueltig.");
			} 
			this.serviceJahr = serviceJahr;
		} catch (IllegalArgumentException ex) {
            System.out.println("Error: Servicejahr ungueltig.");
		}
	}
	
	
	public String toString() {
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		return "Typ:         PKW\n" + super.toString() + "\nServicejahr: " + serviceJahr + "\nPreis:       " + df.format(getPreis()) + "\n";
		 
	}
	
	
	
}
