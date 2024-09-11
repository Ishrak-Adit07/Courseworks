public class FlavoredShakeMaker implements ShakeMaker {
    
    private Shake shake;

    private float almondMilkCost = 60;
    private float candyToppingCost = 50;
    private float cookieToppingCost = 40;

    FlavoredShakeMaker(){
        shake = new Shake();
    }

    @Override
    public Shake customize(){
        return shake;
    }

    @Override
    public ShakeMaker setName(String Name){
        shake.setName(Name);
        return this;
    }

    @Override
    public ShakeMaker setBaseIngredient(String BaseIngredient){
        shake.setBaseIngredient(BaseIngredient);
        return this;
    }

    @Override
    public ShakeMaker setSweetener(String Sweetener){
        shake.setSweetener(Sweetener);
        return this;
    }

    @Override
    public ShakeMaker setAdditionalIngredients(String[] AdditionalIngredients){
        shake.setAdditionalIngredients(AdditionalIngredients);
        return this;
    }

    @Override
    public ShakeMaker setBasePrice(float BasePrice){
        shake.setBasePrice(BasePrice);
        return this;
    }

    @Override
    public ShakeMaker makeLactoseFree(){
        if(!shake.getBaseIngredient().equalsIgnoreCase("Almond Milk")){
            shake.setBaseIngredient("Almond Milk");
            shake.setTotalPrice(shake.getTotalPrice()+almondMilkCost);
        }
        return this;
    }
    @Override
    public ShakeMaker addCandyTopping(){
        shake.setToppings("Candy");
        shake.setTotalPrice(shake.getTotalPrice()+candyToppingCost);
        return this;
    }
    @Override
    public ShakeMaker addCookieTopping(){
        shake.setToppings("Cookie");
        shake.setTotalPrice(shake.getTotalPrice()+cookieToppingCost);
        return this;
    }

    @Override
    public ShakeMaker setTotalPrice(float newPrice){
        shake.setTotalPrice(newPrice);
        return this;
    }
    @Override
    public float getTotalPrice(){
        return shake.getTotalPrice();
    }
}
