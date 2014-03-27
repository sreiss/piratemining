package mining;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pirate implements Cloneable {
	private HashMap<String, Boolean> _attributes = new HashMap<String, Boolean>();
	
	private static final String[] attributesName = new String[]{"bandeau", "chapeau", "dent", "barbe", "crochet", "epee"};

	private static Random _rand = new Random();
	
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
		
		for (Map.Entry<String, Boolean> entry : p.getTable().entrySet()) {
			p.setAttribute(entry.getKey(), _rand.nextBoolean());
		}
		return p;
	}
	
	/*public static ArrayList<Pirate> getPirates(int N)
	{
		ArrayList<Pirate> res = new ArrayList<Pirate>();
		
		int attIndex = 0;
		
		Pirate lastPirate = new Pirate();
		
		for(int i=0; i<N; i++)
		{
			Pirate p = (Pirate) lastPirate.clone();
			p._attributes[attributesName[attIndex]] = i;
		}
		
		return res;
	}*/

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
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		Pirate p = new Pirate();
		p._attributes = (HashMap<String, Boolean>) this._attributes.clone();
		return p;
	}

}
