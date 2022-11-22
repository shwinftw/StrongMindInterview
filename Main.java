package main;
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{

	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Scanner userInput = new Scanner(System.in);
		menu(userInput);
	}

	public static void menu(Scanner menuInput)
	{	
		// Dynamic arrays that hold the list of toppings and the various pizza combinations
		ArrayList<String> toppingsList = new ArrayList<String>();
		ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		boolean finished = false;
		
		while(!finished)
		{
			System.out.println("Welcome to Pizza!\nPlease input a number to select a role:\n1. Pizza Store Owner\n2. Pizza Chef\n3. Exit");
			String userInput = menuInput.nextLine();
			//System.out.println(userInput);
			if(userInput.compareTo("1") == 0)
			{
				// Option 1: Manage toppings as pizza store owner
				System.out.println("1 selected");
				toppingsList = toppingsMenu(menuInput, toppingsList);
			}
			else if(userInput.compareTo("2") == 0)
			{
				// Option 2: Manage pizza combinations as a pizza chef
				System.out.println("2 selected");
				pizzaList = pizzaMenu(menuInput, pizzaList, toppingsList);
			}
			else if(userInput.compareTo("3") == 0)
			{
				// Option 3: Exit
				System.out.println("Thank you for visiting Pizza! Please come again!");
				finished = true;
			}
			else
			{
				System.out.println("Invalid input, try again");
			}
		}
	}
	
	public static ArrayList<String> toppingsMenu(Scanner toppingsInput, ArrayList<String> toppings)
	{
		
		boolean done = false;
		while(!done)
		{
			System.out.println("Welcome, pizza store owner!\nInput a number to perform the associated task\n1. View available toppings\n2. Add toppings\n3. Delete toppings\n4. Update existing toppings\n5. Return to main menu");
			String input = toppingsInput.nextLine();
			if(input.compareTo("1") == 0)
			{
				// Option 1: view(print) available toppings
				if(toppings.size() > 0)
				{
					System.out.println("Available toppings:");
					for(int i = 0; i < toppings.size(); i++)
					{
						System.out.println("\t" + toppings.get(i));
					}
					
				}
				else
				{
					System.out.println("No topping available");
				}
				
			}
			else if(input.compareTo("2") == 0)
			{
				// Option 2: Add a topping, avoiding duplicates
				System.out.println("What topping would you like to add?");
				String newTopping = toppingsInput.nextLine();
				if(isInArrayList(toppings, newTopping) >= 0)
				{
					System.out.println(newTopping + " is already in the list");
				}
				else
				{
					toppings.add(newTopping);
					System.out.println(newTopping + " added!");
				}
			}
			else if(input.compareTo("3") == 0)
			{
				// Option 3: Delete an existing topping
				if(toppings.size() > 0)
				{
					System.out.println("Please enter which topping you would like to remove");
					String toDelete = toppingsInput.nextLine();
					int index = isInArrayList(toppings, toDelete);
					if(index >= 0)
					{
						String removed = toppings.get(index);
						toppings.remove(index);
						System.out.println(removed + " successfully removed!");
					}
					else
					{
						System.out.println( toDelete + " is not in the list.");
					}
				}
				else
				{
					System.out.println("There are no toppings to remove.");
				}
			}
			else if(input.compareTo("4") == 0)
			{
				// Option 4: Update an existing topping.
				if(toppings.size() > 0)
				{
					System.out.println("Please enter the name of the topping to be updated (case matters)");
					String oldName = toppingsInput.nextLine();
					int oldIndex = isInArrayListCaseMatters(toppings, oldName);
					if(oldIndex >= 0)
					{
						System.out.println("Please enter the new name for this topping");
						String newName = toppingsInput.nextLine();
						if(isInArrayListCaseMatters(toppings, newName) >= 0)
						{
							System.out.println("Sorry, that topping is already in the list.");
						}
						else
						{
							toppings.set(oldIndex, newName);
							System.out.println(oldName + " successfully updated to " + newName + "!");
						}
					}
					else
					{
						System.out.println(oldName + " is not in the list.");
					}
				}
				else
				{
					System.out.println("There are no toppings to update.");
				}
			}
			else if(input.compareTo("5") == 0)
			{
				done = true;
				System.out.println("Returning to main menu");
			}
			else
			{
				System.out.println("Invalid input, try again");
			}
		}
		return toppings;
	}
	
	public static ArrayList<Pizza> pizzaMenu(Scanner pizzaInput, ArrayList<Pizza> pizzas, ArrayList<String> toppings)
	{
		boolean done = false;
		while(!done)
		{
			System.out.println("Welcome, chef!\nInput a number to perform the associated task\n1.View existing pizzas\n2. Create a new pizza and add toppings\n3. Delete a pizza\n4. Change pizza name\n5. Change toppings on a pizza\n6. Return to main menu");
			String input = pizzaInput.nextLine();
			if(input.compareTo("1") == 0)
			{
				// Option 1: Print list of pizzas
				if(pizzas.size() == 0)
				{
					System.out.println("There are no pizzas");
				}
				else
				{
					for(int i = 0; i < pizzas.size(); i++)
					{
						Pizza temp = pizzas.get(i);
						System.out.println(temp.getName());
						if(temp.numOfToppings() == 0)
						{
							System.out.println("\tNo toppings");
						}
						else
						{
							for(int index = 0; index < temp.numOfToppings(); index++)
							{
								System.out.println("\t" + temp.getTopping(index));
							}
						}
					}
				}
			}
			else if(input.compareTo("2") == 0)
			{
				// Option 2: Add a new pizza
				if(toppings.size() > 0) // Pizzas must have at least 1 topping
				{
					Pizza newPie = new Pizza("Temp");
					
					// Ask user to choose toppings from the list provided by the store owner
					System.out.println("Add toppings by entering the number associated with it. One topping at a time.");
					for(int num = 0; num < toppings.size(); num++)
					{
						System.out.println((num + 1) + ". " + toppings.get(num));
					}
					System.out.println((toppings.size() + 1) + ". Finish adding toppings");
					
					boolean pizzaConstructed = false;
					while(!pizzaConstructed)
					{
						String toppingInput = pizzaInput.nextLine();
						int topNum;
						try
						{
							topNum = Integer.parseInt(toppingInput);
							if(topNum == (toppings.size() + 1))
							{
								pizzaConstructed = true;
							}
							else if((topNum <= 0) || (topNum > (toppings.size() + 1)))
							{
								System.out.println("No topping is associated with that number");
							}
							else
							{
								String top = toppings.get((topNum - 1));
								boolean success = newPie.addTopping(top);
								if(!success)
								{
									System.out.println("That topping is already on the pizza.");
								}
								else
								{
									System.out.println(top + " added!");
								}
							}
						}
						catch(NumberFormatException e)
						{
							System.out.println("Not a valid input");
						}
					}
					
					// Now check to see if any existing pizzas have the same topping combinations
					int count = newPie.numOfToppings();
					boolean matchFound = false;
					for(int i = 0; i < pizzas.size(); i++)
					{
						Pizza temp = pizzas.get(i);
						int numOfMatches = 0;
						if(temp.numOfToppings() == count)
						{
							for(int newPieIndex = 0; newPieIndex < count; newPieIndex++)
							{
								String currentTopping = newPie.getTopping(newPieIndex);
								if(temp.hasTopping(currentTopping))
								{
									numOfMatches++;
								}
							}
							if(numOfMatches == count)
							{
								matchFound = true;
							}
						}
					}
					if(matchFound)
					{
						System.out.println("A pizza with those toppings already exists. This pizza cannot be made.");
					}
					else
					{
						// Get name from user and check that the name is not in use
						System.out.println("What would you like to name your creation?");
						boolean nameAccepted = false;
						while(!nameAccepted)
						{
							String pieName = pizzaInput.nextLine();
							boolean inUse = false;
							for(int index = 0; index < pizzas.size(); index++)
							{
								if(pizzas.get(index).getName().toLowerCase().compareTo(pieName.toLowerCase()) == 0)
								{
									inUse = true;
								}
							}
							if(!inUse)
							{
								nameAccepted = true;
								newPie.setName(pieName);
								pizzas.add(newPie);
								System.out.println(pieName + " has successfully been created!");
							}
							else
							{
								System.out.println("That name is already taken. Try again");
							}
						}
					}
					
				}
				else
				{
					System.out.println("No toppings to add. A pizza cannot be made");
				}
			}
			else if(input.compareTo("3") == 0)
			{
				// Option 3: Delete a pizza
				if(pizzas.size() > 0)
				{
					System.out.println("Enter the name of the pizza for deletion");
					String name = pizzaInput.nextLine();
					int index = -1;
					for(int i = 0; i < pizzas.size(); i++)
					{
						if(pizzas.get(i).getName().toLowerCase().compareTo(name.toLowerCase()) == 0)
						{
							index = i;
						}
					}
					if(index >= 0)
					{
						pizzas.remove(index);
						System.out.println(name + " successfully removed!");
					}
					else
					{
						System.out.println("A pizza with that name does not exist.");
					}
				}
				else
				{
					System.out.println("There are no pizzas for deletion");
				}
			}
			else if(input.compareTo("4") == 0)
			{
				// Option 4: Change the pizza's name
				if(pizzas.size() > 0)
				{
					// Search for the pizza to change
					System.out.println("Which pizza do you want to change?");
					String oldName = pizzaInput.nextLine();
					int index = -1;
					for(int i = 0; i < pizzas.size(); i++)
					{
						if(pizzas.get(i).getName().toLowerCase().compareTo(oldName.toLowerCase()) == 0)
						{
							index = i;
						}
					}
					if(index >= 0)
					{
						System.out.println("What should the new name of this pizza be?");
						// Check to see if the new name is already taken
						boolean valid = false;
						while(!valid)
						{
							String newName = pizzaInput.nextLine();
							int search = -1;
							for(int i = 0; i < pizzas.size(); i++)
							{
								if(pizzas.get(i).getName().toLowerCase().compareTo(newName.toLowerCase()) == 0)
								{
									search = i;
								}
							}
							if(search >= 0)
							{
								System.out.println("That name is already taken, try again");
							}
							else
							{
								Pizza temp = pizzas.get(index);
								temp.setName(newName);
								pizzas.set(index, temp);
								System.out.println(oldName + " successfully renamed to " + newName);
								valid = true;
							}
						}
						
					}
					else
					{
						System.out.println("A pizza with that name does not exist.");
					}
				}
				else
				{
					System.out.println("There are no pizzas to change");
				}
			}
			else if(input.compareTo("5") == 0)
			{
				// Option 5: Change the pizza's toppings
				if(pizzas.size() > 0)
				{
					// Search for the pizza to change
					System.out.println("What is the name of the pizza you want to change?");
					String oldName = pizzaInput.nextLine();
					int index = -1;
					for(int i = 0; i < pizzas.size(); i++)
					{
						if(pizzas.get(i).getName().toLowerCase().compareTo(oldName.toLowerCase()) == 0)
						{
							index = i;
						}
					}
					if(index >= 0)
					{
						String tempName = pizzas.get(index).getName();
						ArrayList<String> tempList = new ArrayList<String>();
						for(int tempIndex = 0; tempIndex < pizzas.get(index).numOfToppings(); tempIndex++)
						{
							tempList.add(pizzas.get(index).getTopping(tempIndex));
						}
						Pizza temp = new Pizza(tempName, tempList);
						boolean finished = false;
						while(!finished)
						{
							System.out.println("Input the number to perform the associated task\n1. Add toppings\n2. Remove toppings\n3. Finish");
							String next = pizzaInput.nextLine();
							// Add toppings
							if(next.compareTo("1") == 0)
							{
								System.out.println("Add toppings by entering the number associated with it. One topping at a time.");
								for(int num = 0; num < toppings.size(); num++)
								{
									System.out.println((num + 1) + ". " + toppings.get(num));
								}
								System.out.println((toppings.size() + 1) + ". Finish adding toppings");
								
								boolean pizzaConstructed = false;
								while(!pizzaConstructed)
								{
									String toppingInput = pizzaInput.nextLine();
									int topNum;
									try
									{
										topNum = Integer.parseInt(toppingInput);
										if(topNum == (toppings.size() + 1))
										{
											pizzaConstructed = true;
										}
										else if((topNum <= 0) || (topNum > (toppings.size() + 1)))
										{
											System.out.println("No topping is associated with that number");
										}
										else
										{
											String top = toppings.get((topNum - 1));
											boolean success = temp.addTopping(top);
											if(!success)
											{
												System.out.println("That topping is already on the pizza.");
											}
											else
											{
												System.out.println(top + " added!");
											}
										}
									}
									catch(NumberFormatException e)
									{
										System.out.println("Not a valid input");
									}
								}
							}
							// Remove toppings
							else if(next.compareTo("2") == 0)
							{
								boolean pizzaDeconstructed = false;
								while(!pizzaDeconstructed)
								{
									System.out.println("Remove toppings by entering the number associated with it. One topping at a time.");
									int topMax = temp.numOfToppings();
									for(int num = 0; num < topMax; num++)
									{
										System.out.println((num + 1) + ". " + temp.getTopping(num));
									}
									System.out.println((topMax + 1) + ". Finish removing toppings");
									String toppingInput = pizzaInput.nextLine();
									int topNum;
									try
									{
										topNum = Integer.parseInt(toppingInput);
										if(topNum == (topMax + 1))
										{
											pizzaDeconstructed = true;
										}
										else if((topNum <= 0) || (topNum > (topMax + 1)))
										{
											System.out.println("No topping is associated with that number");
										}
										else
										{
											String top = temp.getTopping((topNum - 1));
											boolean success = temp.removeTopping(top);
											if(!success)
											{
												System.out.println("That topping is not on the pizza.");
											}
											else
											{
												System.out.println(top + " removed!");
											}
										}
									}
									catch(NumberFormatException e)
									{
										System.out.println("Not a valid input");
									}
								}
							}
							// Finish
							else if(next.compareTo("3") == 0)
							{
								finished = true;
								
								int count = temp.numOfToppings();
								boolean matchFound = false;
								for(int i = 0; i < pizzas.size(); i++)
								{
									Pizza p = pizzas.get(i);
									int numOfMatches = 0;
									if(p.numOfToppings() == count)
									{
										for(int newPieIndex = 0; newPieIndex < count; newPieIndex++)
										{
											String currentTopping = temp.getTopping(newPieIndex);
											if(p.hasTopping(currentTopping))
											{
												numOfMatches++;
											}
										}
										if(numOfMatches == count)
										{
											matchFound = true;
										}
									}
								}
								if(matchFound)
								{
									System.out.println("A pizza with those toppings already exists. This pizza cannot be updated.");
								}
								else
								{
									pizzas.set(index, temp);
									System.out.println(temp.getName() + " successfully updated!");
								}
							}
							else
							{
								System.out.println("Invalid input. Try again");
							}
						}
					}
					else
					{
						System.out.println("A pizza with that name does not exist.");
					}
				}
				else
				{
					System.out.println("There are no pizzas to change");
				}
			}
			else if(input.compareTo("6") == 0)
			{
				done = true;
				System.out.println("Returning to main menu");
			}
			else
			{
				System.out.println("Invalid input, try again");
			}
		}
		return pizzas;
	}
	
	
	// Method used to search the ArrayList for a certain entry. Returns the index if found, or -1 if not found
	public static int isInArrayList(ArrayList<String> list, String topic)
	{
		if(list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(topic.toLowerCase().compareTo(list.get(i).toLowerCase()) == 0)
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	public static int isInArrayListCaseMatters(ArrayList<String> list, String topic)
	{
		if(list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(topic.compareTo(list.get(i)) == 0)
				{
					return i;
				}
			}
		}
		return -1;
	}


}
