package Mining;

import java.util.HashMap;

public class Pirate {
	private HashMap<String, Boolean> _attributes = new HashMap<String, Boolean>();

	public boolean hasAttribute(String name)
	{
		return _attributes.containsKey(name) && _attributes.get(name);
	}
	
	public void setAttribute(String name, boolean h)
	{
		_attributes.put(name, h);
	}
}
