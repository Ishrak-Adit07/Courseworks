interface ShakeMaker {
    Shake customize();

    public ShakeMaker setName(String Name);
    public ShakeMaker setBaseIngredient(String BaseIngredient);
    public ShakeMaker setSweetener(String Sweetener);
    public ShakeMaker setAdditionalIngredients(String[] AdditionalIngredients);
    public ShakeMaker setBasePrice(float BasePrice);

    public ShakeMaker makeLactoseFree();
    public ShakeMaker addCandyTopping();
    public ShakeMaker addCookieTopping();

    public ShakeMaker setTotalPrice(float totalPrice);
    public float getTotalPrice();
}
