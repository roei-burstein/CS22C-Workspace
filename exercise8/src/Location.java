import java.text.DecimalFormat;

public class Location {
	private String name = "";
	private String address = "";
	private double latitude; // default 0
	private double longitude; // default 0

	public Location(String nm, String addr, double lat, double lon) {
		setName(nm);
		setAddress(addr);
		setCoordinates(lat, lon);
	}

	public Location(String nm) {
		setName(nm);
	}

	public void setName(String nm) {
		if (nm != null)
			name = nm;
	}

	public void setAddress(String addr) {
		if (addr != null)
			address = addr;
	}

	public void setCoordinates(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String toString() {
		return "Location: " + name + ", " + address + ", (" + latitude + ", " + longitude + ")";
	}

	@Override
	public boolean equals(Object obj) {
		String compName;
		if (obj instanceof Location)
			compName = ((Location) obj).name;
		else
			compName = "";
		return name.equalsIgnoreCase(compName);
	}

	@Override
	public int hashCode() {
		String latString, lonString, resultStr;
		DecimalFormat decFormat = new DecimalFormat("000.0000");
		latString = decFormat.format(latitude + 90.);
		lonString = decFormat.format(longitude + 180.);
		resultStr = latString.replace(".", "") + lonString.replace(".", "");
		return resultStr.hashCode();

	}
}
