/**
 * @author <Nemanja Filipovic>
 * Matrikelnummer:01348905
 */

import java.util.List;

public interface FahrzeugDAO {

	public List<Fahrzeug> getFahrzeugList();

	public Fahrzeug getFahrzeugbyId(String id);
	
	public void speichereFahrzeug(Fahrzeug fahrzeug);

	public void loescheFahrzeug(String id);
}
