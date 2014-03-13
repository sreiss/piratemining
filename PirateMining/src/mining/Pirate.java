package mining;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pirate {
	private HashMap<String, Boolean> _attributes = new HashMap<String, Boolean>();
	
	private static final String[] attributesName = new String[]{"bandeau", "chapeau", "dent", "barbe", "crochet", "epee"};
	//private static final String[] attributesName = new String[]{"bidule", "truc", "machin", "chose"};

	boolean _isOk = false;
	
	public Pirate()
	{
		for(String s : attributesName)
		{
			_attributes.put(s, false);
		}
	}

	public boolean hasAttribute(String name)
	{
		return _attributes.get(name);
	}
	
	public HashMap<String, Boolean> getTable()
	{
		return _attributes;
	}
	
	public void setAttribute(String name, boolean h)
	{
		_attributes.put(name, h);
	}
	
	public boolean getAttributeValue(String name) {
		return _attributes.get(name);
	}
	
	static public int countAttributes()
	{
		return attributesName.length;
	}
	
	static public String[] getAttributeNames()
	{
		return attributesName;
	}
	
	public void setOk(boolean ok)
	{
		_isOk = ok;
	}
	
	public boolean isOk()
	{
		return _isOk;
	}
	
	public static Pirate randomPirate() {
		Pirate p = new Pirate();
		Random rnd = new Random();
		for (Map.Entry<String, Boolean> entry : p.getTable().entrySet()) {
			p.setAttribute(entry.getKey(), rnd.nextBoolean());
		}
		return p;
	}

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Pirate) {
			Pirate p = (Pirate)obj;
			for (Map.Entry<String, Boolean> entry : p.getTable().entrySet()) {
				if (entry.getValue() != this.getAttributeValue(entry.getKey())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
