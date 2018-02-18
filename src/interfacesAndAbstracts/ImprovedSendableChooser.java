package interfacesAndAbstracts;

import java.util.LinkedHashMap;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import static java.util.Objects.requireNonNull;

public class ImprovedSendableChooser<V> extends SendableBase implements Sendable {
  private static final String DEFAULT = "default";
  private static final String SELECTED = "selected";
  private static final String OPTIONS = "options";
  private final LinkedHashMap<String, V> m_map = new LinkedHashMap<>();
  private String m_defaultChoice = "";
  
  public ImprovedSendableChooser() {
  }
  
  public void addObject(String name, V object) {
    m_map.put(name, object);
  }
  
  public void removeObject(String name, V object) {
	  m_map.remove(name, object);
  }

  public void addDefault(String name, V object) {
    requireNonNull(name, "Provided name was null");
    m_defaultChoice = name;
    addObject(name, object);
  }

  public V getSelected() {
	String selected = m_defaultChoice;

    if (m_tableSelected != null) {
      selected = m_tableSelected.getString(m_defaultChoice);
    }
    
    return m_map.get(selected);
  }
  
  private NetworkTableEntry m_tableSelected;
  
  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("String Chooser");
    builder.addStringProperty(DEFAULT, () -> {
      return m_defaultChoice;
    }, null);

    builder.addStringArrayProperty(OPTIONS, () -> {
      return m_map.keySet().toArray(new String[0]);
    }, null);

    m_tableSelected = builder.getEntry(SELECTED);
  }
}

