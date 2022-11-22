package main;
import java.util.ArrayList;

public class Pizza
{
	private String name;
	private ArrayList<String> toppings;
	
	public Pizza(String pName)
	{
		name = pName;
		toppings = new ArrayList<String>();
	}
	
	public Pizza(String pName, ArrayList<String> tops)
	{
		name = pName;
		toppings = tops;
	}
	
	public void setName(String pName)
	{
		name = pName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<String> getToppingsList()
	{
		return toppings;
	}
	
	public String getTopping(int index)
	{
		if(index > toppings.size())
		{
			return "";
		}
		else
		{
			return toppings.get(index);
		}
	}
	
	public boolean addTopping(String tName)
	{
		if(isInArrayList(tName) >= 0)
		{
			return false;
		}
		else
		{
			toppings.add(tName);
			return true;
		}
	}
	
	public boolean hasTopping(String tName)
	{
		if(isInArrayList(tName) >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean removeTopping(String tName)
	{
		int index = isInArrayList(tName);
		if(index >= 0)
		{
			toppings.remove(index);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int numOfToppings()
	{
		return toppings.size();
	}
	
	private int isInArrayList(String topic)
	{
		if(toppings.size() > 0)
		{
			for(int i = 0; i < toppings.size(); i++)
			{
				if(topic.toLowerCase().compareTo(toppings.get(i).toLowerCase()) == 0)
				{
					return i;
				}
			}
		}
		return -1;
	}
}
