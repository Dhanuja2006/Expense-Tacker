import java.util.*;
import java.util.Scanner;

abstract class ExpenseAmount
{
    static int gro,maint,ins,trans,health,gym,beauty;
    
    abstract void addAmount(int amount);
    
    int cal_balance(int netAmount)
    {
        int amountSpent = gro + maint + ins + trans + health + gym + beauty; 
        return netAmount - amountSpent;
    }
}

class Groceries extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.gro += amount;
    }
}

class House_maint extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.maint += amount;
    }
}

class Insurance extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.ins += amount;
    }
}

class Transportation extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.trans += amount;
    }
}

class Health extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.health += amount;
    }
}

class Gym extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.gym += amount;
    }
}

class Beauty extends ExpenseAmount
{
    void addAmount(int amount)
    {
        super.beauty += amount;
    }
}


class ExpenseRecord
{
    int amountSpent;
    String categoryName;
    String date;
    ExpenseRecord(int amountSpent,String categoryName,String date)
    {
        this.amountSpent = amountSpent;
        this.categoryName = categoryName;
        this.date = date;
    }
    @Override
    public String toString() {
        return "ExpenseRecord{" +"Amount Spent=" + amountSpent +",Category Name=" +categoryName  +", Date=" + date +'}';
    }
}

class getCategory
{
    public ExpenseAmount getCat(String cat_Type)
    {
        switch(cat_Type)
        {
            case "Groceries":
            {
                return new Groceries ();
            }
            case "Maintanence":
            {
                return new House_maint();
            }
            case "Insurance":
            {
                return new Insurance();
            }
            case "Transportation":
            {
                return new Transportation();
            }
            case "Health":
            {
                return new Health();
            }
            case "Gym":
            {
                return new Gym();
            }
            case "Beauty":
            {
                return new Beauty();
            }
            default:
            {
                return null;
            }
        }
    }
}

public class ExpenseTracker
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter the amount for your wallet:\n");
	    int netAmount = sc.nextInt();
	    
	    int target = 500;
	    int[] catList = new int[7];
	    catList[1] = 1;
	    catList[0] = 1;
	    for(int i = 2 ; i < 7 ; i++)
	    {
	        catList[i] = 0;
	    }
	    System.out.println("Categories present:\nCat1: Groceries\nCat2 :House maintanance");
	    
	    ArrayList<ExpenseRecord> cr = new ArrayList<ExpenseRecord>();
	    
	    char c = 'Y';
	    
	    System.out.print("1)Add Category\n2)Add Expense\n3)Display Expense Record\n4)Amount Spent On Each Category\n5)Exit");
	    
	    while(c == 'Y')
	    {
	        System.out.print("\nEnter your choice:");
	        int ch = sc.nextInt();
	        
	        switch(ch)
	        {
	            case 1:
	            {
	                System.out.println("Categories available:\nCat1: Groceries\nCat2: House maintanance\nCat3: Insurance\nCat4: Transportation\nCat5 :Healthcare\nCat6 :Gym\nCat7: Beauty");
	                int cat_no;
	                System.out.println("Enter the category number to be added:");
	                cat_no = sc.nextInt();
	                if(cat_no > 7 && cat_no <= 0)
	                {
	                    System.out.println("Invalid category number");
	                }
	                else if(catList[cat_no-1] == 1)
	                {
	                    System.out.println("Already added");
	                }
	                else
	                {
	                    catList[cat_no-1] = 1;
	                    System.out.println("Added");
	                }
	                break;
	            }
	            case 2:
	            {
	               System.out.println("Enter Expense Amount:");
	               int amountSpent = sc.nextInt();
	               sc.nextLine();
	               System.out.println("Enter the category:");
	               String className = sc.nextLine();
	               System.out.println("Enter the category number:");
	               int cat_no = sc.nextInt();
	               sc.nextLine();
	               System.out.println("Enter date:");
	               String d = sc.nextLine();
	               getCategory gc = new getCategory();
	               ExpenseAmount category = gc.getCat (className);
	               int f = 0;
	               
	               if(catList[cat_no-1] == 1)
	               {
	                   int balance = category.cal_balance(netAmount);
	                   if(balance - amountSpent > 0)
	                   {
	                       if(balance - amountSpent < target)
	                       {
	                           System.out.println("Your balance is "+balance);
	                           System.out.println("Do still want to spend?(yes:1)");
	                           int a = sc.nextInt();
	                           if(a == 1)
	                           {
	                               f = 1;
	                           }
	                       }
	                       else
	                       {
	                           f=1;
	                       }
	                   }
	                   else
	                   {
	                       System.out.println("Exceeding wallet amount!");
	                   }
	               }
	               else
	               {
	                   System.out.println("You do not have access to this category");
	               }
	               if(f == 1)
	               {
	                   category.addAmount(amountSpent);
	                   ExpenseRecord e = new ExpenseRecord(amountSpent,className,d);
	                   cr.add(e);
	                   System.out.println("Expense Added\nYour balance is "+category.cal_balance(netAmount));
	                   
	               }
	               break;
	            }
	            case 3:
	            {
	                Iterator<ExpenseRecord> itr=cr.iterator();
	                while(itr.hasNext()) 
	                {
                        ExpenseRecord ex = itr.next();
                        System.out.println(ex);
                    }
	                break;
	            }
	            case 4:
	            {
	                Groceries g = new Groceries();
	                System.out.println("Groceries: "+g.gro);
	                System.out.println("House Maintanance: "+g.maint+"\nInsurance: "+g.ins+"\nTransportaion: "+g.trans+"\nHealth care: "+g.health+"\nGym: "+g.gym+"\nBeauty: "+g.beauty);
	                break;
	            }
	            case 5:
	            {
	                c = 'N';
	                break;
	            }
	            default:
	            {
	                System.out.println("Invalid choice!");
	            }
	        }
	    }
	}
}

