public class ShakeDirector {
    private ShakeMaker shakeMaker;

    ShakeDirector(){
    }

    public void setShakeMaker(ShakeMaker maker){
        shakeMaker = maker;
    }

    public Shake make(String shakeType){
        if(shakeType.equalsIgnoreCase("Chocolate")){
            return this.makeChocolateShake();
        }
        else if(shakeType.equalsIgnoreCase("Coffee")){
            return this.makeCoffeeShake();
        }
        else if(shakeType.equalsIgnoreCase("Strawberry")){
            return this.makeStrawberryShake();
        }
        else if(shakeType.equalsIgnoreCase("Vanilla")){
            return this.makeVanillaShake();
        }
        else if(shakeType.equalsIgnoreCase("Zero")){
            return this.makeZeroShake();
        }
        return null;
    }

    public Shake makeChocolateShake(){
        String shakeName = "Chocolate Shake";
        String baseIngredient = "Regular Milk";
        String sweetener = "Sugar";
        String[] add = {"Chocolate Syrap", "Chocolate Ice Cream"};
        float basePrice = 230;

        shakeMaker.setName(shakeName);
        shakeMaker.setBaseIngredient(baseIngredient);
        shakeMaker.setSweetener(sweetener);
        shakeMaker.setAdditionalIngredients(add);
        shakeMaker.setBasePrice(basePrice);
        shakeMaker.setTotalPrice(basePrice);

        return shakeMaker.customize();
    }

    public Shake makeCoffeeShake(){
        String shakeName = "Coffee Shake";
        String baseIngredient = "Regular Milk";
        String sweetener = "Sugar";
        String[] add = {"Coffee", "Jello"};
        float basePrice = 250;

        shakeMaker.setName(shakeName);
        shakeMaker.setBaseIngredient(baseIngredient);
        shakeMaker.setSweetener(sweetener);
        shakeMaker.setAdditionalIngredients(add);
        shakeMaker.setBasePrice(basePrice);
        shakeMaker.setTotalPrice(basePrice);

        return shakeMaker.customize();
    }

    public Shake makeStrawberryShake(){
        String shakeName = "Strawberry Shake";
        String baseIngredient = "Regular Milk";
        String sweetener = "Sugar";
        String[] add = {"Strawberry Syrap", "Strawberry Ice Cream"};
        float basePrice = 200;

        shakeMaker.setName(shakeName);
        shakeMaker.setBaseIngredient(baseIngredient);
        shakeMaker.setSweetener(sweetener);
        shakeMaker.setAdditionalIngredients(add);
        shakeMaker.setBasePrice(basePrice);
        shakeMaker.setTotalPrice(basePrice);

        return shakeMaker.customize();
    }

        public Shake makeVanillaShake(){
        String shakeName = "Vanilla Shake";
        String baseIngredient = "Regular Milk";
        String sweetener = "Sugar";
        String[] add = {"Vanilla flavouring", "Jello"};
        float basePrice = 190;

        shakeMaker.setName(shakeName);
        shakeMaker.setBaseIngredient(baseIngredient);
        shakeMaker.setSweetener(sweetener);
        shakeMaker.setAdditionalIngredients(add);
        shakeMaker.setBasePrice(basePrice);
        shakeMaker.setTotalPrice(basePrice);

        return shakeMaker.customize();
    }

        public Shake makeZeroShake(){
        String shakeName = "Zero Shake";
        String baseIngredient = "Regular Milk";
        String sweetener = "Sweetener";
        String[] add = {"Vanilla flavouring", "Sugar-free Jello"};
        float basePrice = 240;

        shakeMaker.setName(shakeName);
        shakeMaker.setBaseIngredient(baseIngredient);
        shakeMaker.setSweetener(sweetener);
        shakeMaker.setAdditionalIngredients(add);
        shakeMaker.setBasePrice(basePrice);
        shakeMaker.setTotalPrice(basePrice);

        return shakeMaker.customize();
    }

    public Shake customize(){
        return shakeMaker.customize();
    }

    public Shake makeLactoseFree(){
        return shakeMaker.makeLactoseFree().customize();
    }

    public Shake addCandyTopping(){
        return shakeMaker.addCandyTopping().customize();
    }

    public Shake addCookieTopping(){
        return shakeMaker.addCookieTopping().customize();
    }
}
