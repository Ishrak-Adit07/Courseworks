import java.util.ArrayList;
import java.util.Scanner;

public class cafe {

    private ArrayList<Shake> shakeOrders;

    cafe(){
        shakeOrders = new ArrayList<Shake>();
    }

    public void orderController(){

        ShakeDirector shakeDirector = new ShakeDirector();
        Shake shake;

        Scanner sc = new Scanner(System.in);
        char c = 'a';

        while(c != '6'){
            System.out.println("Place your order from the following- ");
            System.out.println("1. Chocolate Shake");
            System.out.println("2. Coffee Shake");
            System.out.println("3. Strawberry Shake");
            System.out.println("4. Vanilla Shake");
            System.out.println("5. Zero Shake");
            System.out.println("6. Close order");

            if(sc.hasNext()) c = sc.next().charAt(0);
            switch (c) {
                case '1':
                    ShakeMaker  chocolateShakeMaker = new FlavoredShakeMaker();
                    shakeDirector.setShakeMaker(chocolateShakeMaker);

                    shakeDirector.make("Chocolate");
                    customizeShake(shakeDirector);

                    placeShakeOrder(shakeDirector.customize());
                    break;

                case '2':
                    ShakeMaker coffeeShakeMaker = new FlavoredShakeMaker();
                    shakeDirector.setShakeMaker(coffeeShakeMaker);

                    shakeDirector.make("Coffee");
                    customizeShake(shakeDirector);

                    placeShakeOrder(shakeDirector.customize());
                    break;
                
                case '3':
                    ShakeMaker strawberryShakeMaker = new FlavoredShakeMaker();
                    shakeDirector.setShakeMaker(strawberryShakeMaker);

                    shakeDirector.make("Strawberry");
                    customizeShake(shakeDirector);

                    placeShakeOrder(shakeDirector.customize());
                    break;

                case '4':
                    ShakeMaker vanillaShakeMaker = new FlavoredShakeMaker();
                    shakeDirector.setShakeMaker(vanillaShakeMaker);

                    shakeDirector.make("Vanilla");
                    customizeShake(shakeDirector);

                    placeShakeOrder(shakeDirector.customize());
                    break;
                
                case '5':
                    ShakeMaker zeroShakeMaker = new FlavoredShakeMaker();
                    shakeDirector.setShakeMaker(zeroShakeMaker);

                    shakeDirector.make("Zero");
                    customizeShake(shakeDirector);

                    placeShakeOrder(shakeDirector.customize());
                    break;

                case '6':
                    break;

                default:
                    System.out.println("Please finish order before any other request");
                    break;
            }
        }

        placeOrder();
    }

    public void customizeShake(ShakeDirector shakeDirector){

        Scanner scn = new Scanner(System.in);
        char c = 'a';

        while(c != '4'){
            System.out.println("You may customize your " + shakeDirector.customize().getName() + " with the following- ");
            System.out.println("1. Make the shake Lactose Free(with almond milk instead of regular milk)");
            System.out.println("2. Add candy topping");
            System.out.println("3. Add cookie topping");
            System.out.println("4. Order shake");

            c = scn.next().charAt(0);
            switch (c) {
                case '1':
                    shakeDirector.makeLactoseFree();
                    break;
            
                case '2':
                    shakeDirector.addCandyTopping();
                    break;
            
                case '3':
                    shakeDirector.addCookieTopping();
                    break;

                case '4':
                    break;
        
                default:
                    System.out.println("Invalid Customization");
                    break;
            }
        }

    }

    public void placeShakeOrder(Shake shake){
        shakeOrders.add(shake);
    }

    public void placeOrder(){

        if(shakeOrders.size()==0){
            System.out.println("Order must contain at least 1 shake");
            orderController();
            return;
        }
        else{
            int count = 1;
            float totalOrderPrice = 0;
            for(Shake shake : shakeOrders){
                System.out.println(count + "  " + shake.getName());

                shake.printShakeOrder();
                totalOrderPrice += shake.getTotalPrice();
                count++;
            }
            System.out.println("Total Order Price: " + totalOrderPrice);

            shakeOrders.clear();
        }
    }
    
    public static void main(String[] args) {

        cafe shakeCafe = new cafe();

        Scanner sc = new Scanner(System.in);
        char c = 'a';

        while(c != 'e'){
            System.out.println("Press o to open an order, and e to end.");
            c = sc.next().charAt(0);

            if(c == 'o')
                shakeCafe.orderController();
            
            else if(c == 'e') break;
            else System.out.println("Invalid Request");
            
        }

        System.out.println("Thank you");
        sc.close();
    }
}


//javac cafe.java