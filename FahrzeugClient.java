/**
 * @author <Nemanja Filipovic>
 * Matrikelnummer:01348905
 */

public class FahrzeugClient {
	


	public static void main(String[] args) {	

	try {	
		if (args.length < 2 || args.length > 9)
			throw new IllegalArgumentException("1");
		
	FahrzeugManagement fm = new FahrzeugManagement(args[0]);
	if (args[1].equals("show")) {
		if (args.length == 2) {
			System.out.println(fm.alleFahrzeugetoString());
		} else if (args.length == 3) {
			System.out.println(fm.einFahrzeugtoString(args[2]));
		}
	} else if (args[1].equals("add")) {
		if (args[2].equals("lkw")) {
			if (args.length != 8) //exception
								//falls zu wenige Parameter
				throw new IllegalArgumentException("1");
			Lkw f = new Lkw(args[3], args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
			fm.addFahrzeug(f);
			//System.out.println("Pkw ist hinzugefuegt worden!");
		} else if (args[2].equals("pkw")) {
			if (args.length != 9)
				throw new IllegalArgumentException ("1");
			Pkw p = new Pkw (args[3], args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
			fm.addFahrzeug(p);
			//System.out.println("Lkw ist hinzugefuegt  worden!");
		}else {   //falss kein lkw oder pkw eingegeben ist
			throw new IllegalArgumentException("2");
		}
	} else if (args[1].equals("del")) {
		fm.loescheFahrzeug(args[2]);
	}
	
	else if (args[1].equals("count")) {
		if (args.length == 2) {
			System.out.println(fm.totalFahrzeuge());
		} else if (args.length == 3) {
			if (args[2].equals("lkw")) {
				System.out.println(fm.totalLkws());
			} else if (args[2].equals("pkw")) {
				System.out.println(fm.totalPkws());
			}
			} else { 
				throw new IllegalArgumentException("2");
			}
		} else if (args[1].equals("meanprice")) {
				System.out.println(fm.preisFahrzeuge());
		} else if (args[1].equals("oldest")) {
				System.out.println(fm.aeltestestFahrzeug());
		} else {
			throw new IllegalArgumentException("3");
		}
	
	} catch (IllegalArgumentException exc) {
		if (exc.getMessage().equals("1")) {
			System.out.println("Error: Parameter ungueltig.");
		//	System.exit(1);
		} else if (exc.getMessage().equals("2")) {
			System.out.println("Error: Parameter ungueltig.");
		//	System.exit(1);
		} else if (exc.getMessage().equals("3")) {
			System.out.println("Error: Parameter ungueltig.");
		//	System.exit(1);
		} else if (exc.getMessage().equals("4")) {
			System.out.println("Error: Fahrzeug nicht vorhanden.");
		//	System.exit(1);
		}
    
	}
		
			
		
		
		
	}
}
