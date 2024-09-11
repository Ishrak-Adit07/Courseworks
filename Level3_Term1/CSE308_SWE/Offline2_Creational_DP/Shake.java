import java.util.ArrayList;

public class Shake {
    private String name;
    private String baseIngredient;
    private String sweetener;
    private ArrayList<String> additionalIngredients;
    private ArrayList<String> toppings;

    private float almondMilkCost = 60;
    private float candyToppingCost = 50;
    private float cookieToppingCost = 40;

    private float basePrice;
    private float totalPrice;

    //Constructors
    public Shake(){
        additionalIngredients = new ArrayList<String>();
        toppings = new ArrayList<String>();
    }

    //Setters Getters
    public void setName(String Name) { name = Name; }
    public String getName() { return name; }

    public void setBaseIngredient(String BaseIngredient) { baseIngredient = BaseIngredient; }
    public String getBaseIngredient() { return baseIngredient; }

    public void setSweetener(String Sweetener) { sweetener = Sweetener; }
    public String getSweetener() { return sweetener; }

    public void setAdditionalIngredients(String[] AdditionalIngredients){
        for(String ingredients : AdditionalIngredients)
            additionalIngredients.add(ingredients);
    }
    public ArrayList<String> getAdditionalIngredients(){ return additionalIngredients; }

    public void setToppings(String Toppings){
        toppings.add(Toppings);
    }
    public ArrayList<String> getToppings(){ return toppings; }

    public void setBasePrice(float BasePrice) { basePrice = BasePrice; }
    public float getBasePrice() { return basePrice; }

    public void setTotalPrice(float newPrice){ totalPrice = newPrice; }
    public float getTotalPrice(){ return totalPrice; }

    public void printShakeOrder(){
        System.out.println(baseIngredient);
        System.out.print("with " + sweetener);
        for(String adding : additionalIngredients){
            System.out.print(", " + adding);
        }
        System.out.println(" " + basePrice);

        if(basePrice != totalPrice){
            System.out.println("Customized with-");
            if(baseIngredient.equalsIgnoreCase("Almond Milk")){
                System.out.println("Almond Milk  " + almondMilkCost);
            }

            if(toppings.contains("Candy")){
                for(String top : toppings){
                    if(top.equalsIgnoreCase("Candy")){
                        System.out.println("Candy Topping  " + candyToppingCost);
                    }
                }
            }

            if(toppings.contains("Cookie")){
                for(String top : toppings){
                    if(top.equalsIgnoreCase("Cookie")){
                        System.out.println("Cookie Topping  " + cookieToppingCost);
                    }
                }
            }
        }

        System.out.println("Shake price  " + totalPrice);
    }

}
