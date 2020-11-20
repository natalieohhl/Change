/**
 * @author Oh Huan Lin 
 * @date: 2020-06-14
 * @filename: This is my assignment 1 for ICT167. 
 * @purpose: get user input on change and output change based on user menu choice
 * @assumptions: user will input based on required variable type (i.e. input integer for integer type, and not String) 
 * every user should have change of at least 5 cents. 
 */
import java.util.Scanner;

public class Main {
    
    static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        StudentInfo();
        
        //declare and initialize working storage
        String name;
        int change;
        
        //Change[] changeA = {new Change("Jane",65) , new Change("John",175) , new Change("Ryan", 10), new Change("Tom", 15), new Change("Natalie", 95), new Change("Sylvia", 90), new Change("Bob", 55), new Change("Cat", 75)};
        
        Change[] changeA = {new Change(), new Change(),new Change(),new Change(),new Change(),new Change(),new Change(),new Change()};
        
        System.out.println("\nEnter the number of inputs to enter. "); //prompt user on how many objects they want to input.
        int size = keyboard.nextInt();
        if (size <= 0) {
            size = 8;
        }
        
        //start of getting user input for array 
        for (int i =0; i<size;i++) {
            System.out.println("\nEnter user's  name: ");
            name = keyboard.next();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // standardize first letter of the name is capitalized.
        
            //find the position of the name in the array
            int position = nameSearch(changeA, name);

            //if not found in the array, get their change to put in an object
            if (position == -1) {
                    changeA[i] = getInput(name);
            }
            else {
                while (true){
                    System.out.println("Enter "+ name+ "'s coin value: ");
                    change = keyboard.nextInt();
                        //check if the change is acceptable before adding to the existing change 
                    if ((change >= 5) && (change<=95) && ((change%5)==0)){ 
                        int temp = changeA[position].getChange();
                        changeA[position].setChange(temp + change); 
                        break;
                        }
                    else  {
                        System.out.println("Incorrect coin value. Must be in the range 5 to 95, and multiple of 5.");
                    }
                }
            }
            if (moretoEnter() == false) {
            break; }
        }
        //end of getting user input for array 
        while (true) {
     
            switch(Menu()){
                case 1:
                    caseOne(changeA);
                    break;
                case 2: 
                    caseTwo(changeA);
                    break;
                case 3:
                    caseThree(changeA);
                    break;
                case 4: 
                    caseFour(changeA);
                    break;
                case 5: 
                    System.out.println("The session will now end. Good bye!");
                    System.exit(0); 
            }
        }
    }
    
    //get the input of the change of a new user set the name and change into the object.
    private static Change getInput(String name) {
    Change c = new Change();
    int change;
                

        while (true) {
            System.out.println("Enter "+ name+ "'s coin value: ");
            change = keyboard.nextInt();
            if ((change >= 5) && (change<=95) && ((change%5)==0)){ 
                c.setChange(change); 
                c.setName(name.trim());
                break;
                }
            else  {
                System.out.println("Incorrect coin value. Must be in the range 5 to 95, and multiple of 5.");

            }
        }
        return c;
    }
        
    //check whether there are any more users to enter
    public static boolean moretoEnter() {    
        char input; 
        char upper; 
        while (true) {
        System.out.println("Do you have one more person to enter (Y/N)");
            input = keyboard.next().charAt(0);
            upper = Character.toUpperCase(input); //convert everything to uppercase
            if (upper !='N' && upper !='Y') {
                System.out.println("Enter only Y/N");}   
            else break;
        }

            if (upper=='N') 
                return false;
            else 
                return true; 
    }
    
    //print menu and get choice of menu item
    private static int Menu() {
    
        int m;
    
        System.out.print("1. Enter a name and display change to be given for each denomination\n" +
                          "2. Find the name with the smallest amount and display change to be given for each denomination\n" +
                          "3. Find the name with the largest amount and display change to be given for each denomination\n" +
                          "4. Calculate and display the total number of coins for each denomination, and the sum of these totals\n" +
                          "5. Exit\n");  

        while (true) {
           System.out.print("Your input: ");
           m = keyboard.nextInt();

           if ((m <1) || (m > 5)){
                System.out.println("Please enter a valid number from 1 to 5."); } // to get a valid input of menu item
           else 
           return m;
           }
    
    }
    
    //case 1: find the change of the name searched. 
    private static void caseOne(Change arr[]) {
        String search;
        int change=0;
        
        //prompt user to enter a name to find the change to be given and display the change in denominations
        System.out.println("\nPlease enter a name");
        search = keyboard.next();
        search = search.substring(0, 1).toUpperCase() + search.substring(1).toLowerCase();
        boolean notfound =true; //flag to check whether a name is found
        
        //compare name with all items in the array
        for (int j = 0; j < arr.length; j++) {
                if (search.trim().equals(arr[j].getName())) {
                    change= arr[j].getChange();//retrieves the change from the array object
                    notfound=false;
                    }
        }
        //for everything not inside the array
        if (notfound) {
              System.out.println("Not found");  
       }
       else
       System.out.println("The change to be given for " + search + " is: ");
                    divide(change); }         
    
    //case 2: find the person with the smallest amount of change and display the change in denominations
    private static void caseTwo(Change arr[]) {
        int smallest = arr[0].getChange(); //initalize to first change in the array
        int smallc = 0; //this is the position of the smallest value 
        
        
        for (int i = 0; i< arr.length; i++) {
            for (int j = i + 1; j < arr.length ; j++) {
                    if ((arr[i].getChange()!=0) && (arr[i].getChange() < smallest)) { //ensure that the smallest value is not initalized value 0 
                    smallest = arr[i].getChange();
                    smallc = i;
                    }
        
            }
        }
        
        System.out.println("The person with the smallest amount of change: " + arr[smallc].getName());
        divide(arr[smallc].getChange());
    }
        
    //case 3: find the person with the largest amount of change and display the change in denominations
    private static void caseThree(Change arr[]) {
        int largest = arr[0].getChange(); //initalize to first change in the array
        int largec = 0; //this is the position of the largest value 
  
        for (int i = 1; i <8; i++)
        
        if (arr[i].getChange() > largest) {
            largest = arr[i].getChange();
            largec = i;
        }
        System.out.println("The person with the largest amount of change: " + arr[largec].getName());
        divide(arr[largec].getChange());
        
        
    }
    
    //case 4: retrieve the change from each object in the array and add up all the change in the respective denominations
    private static void caseFour(Change arr[]) {
       int[] coins = {50,20,10,5}; //this is the array of coin denomination value 
       int[] densum = new int[4]; //array to hold sum of each denomination of coins
       int qty; //quantity of each coin 
       int sum =0; //sum of all the quantities of coins 
       
       for (int i = 0; i <8; i++) {
        int change = arr[i].getChange();
        
        for (int j =0; j <4; j++) {
                qty = change/coins[j];
                change = (change - qty*(coins[j])); 
                densum[j] = densum[j] + qty;
        }            
        
       }
       //display the quantity of coins for each denomination 
       System.out.println("The number of coins for: ");
       for (int i = 0; i<4; i++) {
           if (densum[i] > 0) {
           System.out.println(coins[i] + "cents is " + densum[i]);}
           
       sum = sum + densum[i]; 
       }
       
       System.out.println("\nThe total number of coins: " + sum + "\n");

    }    
    
    //method to divide a change into the respective denominations 
    public static void divide(int c) {
      int[] coins = {50,20,10,5};
      int qty;
      
      for (int i =0; i <4; i++) { // loop to go through the coins denomination array
                    
        qty = c/coins[i]; 
        c = (c - qty*(coins[i])); 
        if (qty > 0) { 
           System.out.println(coins[i] + " cents: " + qty);
        }
   
      }    
   System.out.println("");
   
   }
    
    //method to find the name in an array and return the position of the name in the array
    public static int nameSearch(Change[] arr, String name) {  
        int position = -1; //default return value if the name is not found in the array
            for (int i =0; i<arr.length;i++) {
                if (arr[i].getName().equals(name)) {
                  position = i; 
                  break;
                }
            }
            return position; 
   }

    public static void StudentInfo() {  
        System.out.println("Name of student: Natalie Oh"); 
  
    }

}
