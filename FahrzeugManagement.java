import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author <Nemanja Filipovice>
 * Matrikelnummer:01348905
 */

public class FahrzeugManagement {
	
	String file;
	private FahrzeugDAO fahrzeugDAO;
	
	public FahrzeugManagement (String file) {
		this.file = file;
		this.fahrzeugDAO = new SerializedFahrzeugDAO(file);
	}
	
	//• Alle Daten aller Fahrzeuge bereitstellen
	public String alleFahrzeugetoString() {
		String alle = "";
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			System.out.println(object.toString());
		}
		return alle;
	}
	
	
	
	//• Alle Daten eines Fahrzeugs bereitstellen
	public String einFahrzeugtoString(String id) {
		return fahrzeugDAO.getFahrzeugbyId(id).toString();
	}
	
	
	
	//• Neues Fahrzeug hinzufügen
	public void addFahrzeug(Fahrzeug neu) {
		fahrzeugDAO.speichereFahrzeug(neu);
	}
	
	//• Bestehendes Fahrzeug löschen
	public void loescheFahrzeug(String id) {
		fahrzeugDAO.loescheFahrzeug(id);
	}
	
	//• Gesamtzahl aller Fahrzeuge ermitteln
	@SuppressWarnings("unused")
	public int totalFahrzeuge() {
		int total = 0;
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			total++;
		}
		return total;
	}
	
	
	//• Gesamtzahl aller PKWs ermitteln
	
	public int totalPkws() {
		int total = 0;
		
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			if (object instanceof Pkw) {
				total++;
			}
		}
		return total;
	}
	
	//• Gesamtzahl aller LKWs ermitteln
	
	public int totalLkws() {
		int total = 0;
		
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			if (object instanceof Lkw) {
				total++;
			}
		}
		return total;
	}
	
	//• Durchschnittspreis aller Fahrzeuge ermitteln
	
	public String preisFahrzeuge() {
		double preis = 0;
		
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			preis += object.getPreis();
		}
		return numberToString(preis / totalFahrzeuge());
	}
	
	
	//• Id(s) des(r) aeltesten Fahrzeugs(e) ermitteln
	
	public String aeltestestFahrzeug() {
		int alt = 0;
		String aus = "";
		
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			if (object.getAlter() > alt) {
				alt = object.getAlter();	

			}
		}
		

    
		for (Fahrzeug object : fahrzeugDAO.getFahrzeugList()) {
			if (object.getAlter() == alt) {
          aus += "Id: " + object.getId() + "\n";
      }
		}
		return aus;
	}
	
	
	
	
	

	private String numberToString(double number) {
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(number);
	}
	
	
	
}
