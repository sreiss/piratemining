package Mining;

import java.util.HashMap;

public class Pirate {
	private HashMap<String, Boolean> _attributes = new HashMap<String, Boolean>();
	
	private static final String[] attributesName = new String[]{"bidule", "truc"};
	
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
	
	static public int countAttributes()
	{
		return attributesName.length;
	}
	
	static public String[] getAttributeNames()
	{
		return attributesName;
	}
}
