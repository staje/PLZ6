import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * @author <Nemanja filipovic>
 * Matrikelnummer:01348905
 */

public class SerializedFahrzeugDAO implements FahrzeugDAO {

	String file;
	
	
	public SerializedFahrzeugDAO (String file) {
		this.file = file;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Fahrzeug> getFahrzeugList() {
		ArrayList<Fahrzeug> Fahrzeug = new ArrayList<Fahrzeug>();
		
		try {
			ObjectInputStream o = new ObjectInputStream (new FileInputStream(file));
			Fahrzeug = (ArrayList<Fahrzeug>) o.readObject();
			o.close();
			
		} catch (FileNotFoundException ex) {
			System.out.println("Fehler bei Deserialisierung:");
		//	System.exit(1);
		} catch (IOException ex) {
			System.out.println("Fehler bei Deserialisierung:");
		//	System.exit(1);
		} catch (ClassNotFoundException ex) {
			System.out.println("Fehler bei Deserialisierung:");
		//	System.exit(1);
		}
	
		return Fahrzeug;
	}

	
	
	@Override
	public Fahrzeug getFahrzeugbyId(String id) {
		Fahrzeug f;
		ArrayList<Fahrzeug> Fahrzeug = (ArrayList<Fahrzeug>) getFahrzeugList();
		int index = 0, copyIndex = 0;
		boolean existiert = false;
		
		try {
			
			for (Fahrzeug object : Fahrzeug) {
				if (id.equals(object.getId())) {
					copyIndex = index;
					existiert = true;
				}
				index ++;
			}
			if (!existiert) {
				throw new IllegalArgumentException("NG");
			}
		}catch (IllegalArgumentException ex) {
			if (ex.getMessage().equals("NG")) {
				System.out.println("Error: Fahrzeug nicht vorhanden.");
				System.exit(1);
				}
		}
		

		f = Fahrzeug.get(copyIndex);
		return f;
	}

	
	
	
	
	@Override
	public void speichereFahrzeug(Fahrzeug zeug) {
	try {
		
		File fa = new File(file);
		ArrayList<Fahrzeug> Fahrzeug;
		
		if(fa.exists()) {
			Fahrzeug = (ArrayList<Fahrzeug>) getFahrzeugList();
		} else {
			Fahrzeug = new ArrayList<Fahrzeug>();
		}
		
		for (Fahrzeug obejct : Fahrzeug) {
			if (zeug.getId().equals(obejct.getId())) {
				throw new IllegalArgumentException ("schon vorhanden");
			}
		}
		
		Fahrzeug.add(zeug);
		ObjectOutputStream o = new ObjectOutputStream (new FileOutputStream(file));
		
		o.writeObject(Fahrzeug);
		o.close();
		
	   }	 catch (IOException ex) {
			 System.out.println("Fehler bei Serialisierung");
			// System.exit(1);
 	   } catch (IllegalArgumentException ex) {
 		   if (ex.getMessage().equals("schon vorhanden")) {
 			   System.out.println("Error: Fahrzeug bereits vorhanden.");
 		//	   System.exit(1);
 		   }
 	   }
		 
		
	}

	@Override
	public void loescheFahrzeug(String id) {
	
	try {
		ArrayList<Fahrzeug> fahrzeug = getFahrzeugList();
		int index = 0, delIndex = 0;
		boolean existiert = false;
		
		for (Fahrzeug object : fahrzeug) {
			if (id.equals(object.getId())) {
				delIndex = index;
				existiert = true;
			}
			index++;
		}
		if (!existiert) {
			throw new IllegalArgumentException ("Error");
		}
		
		fahrzeug.remove(delIndex);
		ObjectOutputStream o = new ObjectOutputStream (new FileOutputStream(file, false));
		o.writeObject(fahrzeug);
		o.close();
	} catch (IOException ex) {
		System.out.println("Fehler beim Loeschen!");
	//	System.exit(1);
	} catch (IllegalArgumentException ex) {
		if (ex.getMessage().equals("Error")) {
			System.out.println("Error: Fahrzeug nicht vorhanden." + " (id=" + id + ")");
			//System.exit(1);
		}
	}
		
	}
	


	
}
