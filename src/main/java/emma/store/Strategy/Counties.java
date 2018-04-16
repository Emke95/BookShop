package emma.store.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counties {

	public final static String IE = "IE";

	@SuppressWarnings("serial")
	public final static Map<String, String> mapOfIECounties = new HashMap<String, String>() {

		{
			put("AN", "Antrim");
			put("AM", "Armagh");
			put("CAR", "Carlow");
			put("CAV","Cavan");
			put("CL", "Clare");
			put("CO","Cork");
			put("DE","Derry");
			put("DON","Donegal");
			put("DOW","Down");
			put("DUB", "Dublin");
			put("FE","Fermanagh");
			put("GA","Galway");
			put("KE","Kerry");
			put("KD","Kildare");
			put("KK","Kilkenny");
			put("LA","Laois");
			put("LEI","Leitrim");
			put("LI","Limerick");
			put("LON","Longford");
			put("LOU","Louth");
			put("MAY","Mayo");
			put("ME","Meath");
			put("MON","Monaghan");
			put("OFF","Offaly");
			put("ROS","Roscommon");
			put("SL","Sligo");
			put("TIP","Tipperary");
			put("TY","Tyrone");
			put("WA","Waterford");
			put("WE","Westmeath");
			put("WEX","Wexford");
			put("WI","Wicklow");
		}
	};
	
	
	public final static List<String> listOfIECountCode = new ArrayList<>(mapOfIECounties.keySet());
	public final static List<String> listOfIECountyName = new ArrayList<>(mapOfIECounties.values());

}