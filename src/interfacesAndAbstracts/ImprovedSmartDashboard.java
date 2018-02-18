package interfacesAndAbstracts;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.Sendable;

public class ImprovedSmartDashboard {
	/*private static final NetworkTable table = NetworkTableInstance.getDefault().getTable("SmartDashboard");

	private static class Data {
		Data(Sendable sendable) {
			m_sendable = sendable;
		}

		final Sendable m_sendable;
		final SendableBuilderImpl m_builder = new SendableBuilderImpl();
	}

	private static final Map<String, Data> tablesToData = new HashMap<>();

	static {
		HLUsageReporting.reportSmartDashboard();
	}

	public static synchronized void putData(String key, Sendable data) {
		Data sddata = tablesToData.get(key);
		if (sddata == null || sddata.m_sendable != data) {
			if (sddata != null) {
				sddata.m_builder.stopListeners();
			}
			sddata = new Data(data);
			tablesToData.put(key, sddata);
			NetworkTable dataTable = table.getSubTable(key);
			sddata.m_builder.setTable(dataTable);
			data.initSendable(sddata.m_builder);
			sddata.m_builder.updateTable();
			sddata.m_builder.startListeners();
			dataTable.getEntry(".name").setString(key);
		}
	}

	public static void putData(Sendable value) {
		putData(value.getName(), value);
	}
	
	public static synchronized void removeData(String key, Sendable data) {
		Data sddata = tablesToData.get(key);
		if (sddata == null || sddata.m_sendable != data) {
			if (sddata != null) {
				sddata.m_builder.stopListeners();
			}
			sddata = new Data(data);
			tablesToData.remove(key, sddata);
			NetworkTable dataTable = table.getSubTable(key);
			sddata.m_builder.setTable(dataTable);
			data.initSendable(sddata.m_builder);
			sddata.m_builder.updateTable();
			sddata.m_builder.startListeners();
			dataTable.getEntry(".name").setString(key);
		}
	}

	public static void removeData(Sendable value) {
		removeData(value.getName(), value);
	}

	public static synchronized Sendable getData(String key) {
		Data data = tablesToData.get(key);
		if (data == null) {
			throw new IllegalArgumentException("SmartDashboard data does not exist: " + key);
		} else {
			return data.m_sendable;
		}
	}

	public static NetworkTableEntry getEntry(String key) {
		return table.getEntry(key);
	}

	public static boolean containsKey(String key) {
		return table.containsKey(key);
	}

	public static Set<String> getKeys(int types) {
		return table.getKeys(types);
	}

	public static Set<String> getKeys() {
		return table.getKeys();
	}

	public static void setPersistent(String key) {
		getEntry(key).setPersistent();
	}

	public static void clearPersistent(String key) {
		getEntry(key).clearPersistent();
	}

	public static boolean isPersistent(String key) {
		return getEntry(key).isPersistent();
	}

	public static void setFlags(String key, int flags) {
		getEntry(key).setFlags(flags);
	}

	public static void clearFlags(String key, int flags) {
		getEntry(key).clearFlags(flags);
	}

	public static int getFlags(String key) {
		return getEntry(key).getFlags();
	}

	public static void delete(String key) {
		table.delete(key);
	}

	public static boolean putBoolean(String key, boolean value) {
		return getEntry(key).setBoolean(value);
	}

	public static boolean setDefaultBoolean(String key, boolean defaultValue) {
		return getEntry(key).setDefaultBoolean(defaultValue);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return getEntry(key).getBoolean(defaultValue);
	}

	public static boolean putNumber(String key, double value) {
		return getEntry(key).setDouble(value);
	}

	public static boolean setDefaultNumber(String key, double defaultValue) {
		return getEntry(key).setDefaultDouble(defaultValue);
	}

	public static double getNumber(String key, double defaultValue) {
		return getEntry(key).getDouble(defaultValue);
	}

	public static boolean putString(String key, String value) {
		return getEntry(key).setString(value);
	}

	public static boolean setDefaultString(String key, String defaultValue) {
		return getEntry(key).setDefaultString(defaultValue);
	}

	public static String getString(String key, String defaultValue) {
		return getEntry(key).getString(defaultValue);
	}

	public static boolean putBooleanArray(String key, boolean[] value) {
		return getEntry(key).setBooleanArray(value);
	}

	public static boolean putBooleanArray(String key, Boolean[] value) {
		return getEntry(key).setBooleanArray(value);
	}

	public static boolean setDefaultBooleanArray(String key, boolean[] defaultValue) {
		return getEntry(key).setDefaultBooleanArray(defaultValue);
	}

	public static boolean setDefaultBooleanArray(String key, Boolean[] defaultValue) {
		return getEntry(key).setDefaultBooleanArray(defaultValue);
	}

	public static boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		return getEntry(key).getBooleanArray(defaultValue);
	}

	public static Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		return getEntry(key).getBooleanArray(defaultValue);
	}

	public static boolean putNumberArray(String key, double[] value) {
		return getEntry(key).setDoubleArray(value);
	}

	public static boolean putNumberArray(String key, Double[] value) {
		return getEntry(key).setNumberArray(value);
	}

	public static boolean setDefaultNumberArray(String key, double[] defaultValue) {
		return getEntry(key).setDefaultDoubleArray(defaultValue);
	}

	public static boolean setDefaultNumberArray(String key, Double[] defaultValue) {
		return getEntry(key).setDefaultNumberArray(defaultValue);
	}

	public static double[] getNumberArray(String key, double[] defaultValue) {
		return getEntry(key).getDoubleArray(defaultValue);
	}

	public static Double[] getNumberArray(String key, Double[] defaultValue) {
		return getEntry(key).getDoubleArray(defaultValue);
	}

	public static boolean putStringArray(String key, String[] value) {
		return getEntry(key).setStringArray(value);
	}

	public static boolean setDefaultStringArray(String key, String[] defaultValue) {
		return getEntry(key).setDefaultStringArray(defaultValue);
	}

	public static String[] getStringArray(String key, String[] defaultValue) {
		return getEntry(key).getStringArray(defaultValue);
	}

	public static boolean putRaw(String key, byte[] value) {
		return getEntry(key).setRaw(value);
	}

	public static boolean putRaw(String key, ByteBuffer value, int len) {
		return getEntry(key).setRaw(value, len);
	}

	public static boolean setDefaultRaw(String key, byte[] defaultValue) {
		return getEntry(key).setDefaultRaw(defaultValue);
	}

	public static byte[] getRaw(String key, byte[] defaultValue) {
		return getEntry(key).getRaw(defaultValue);
	}

	public static synchronized void updateValues() {
		for (Data data : tablesToData.values()) {
			data.m_builder.updateTable();
		}

	}*/
}