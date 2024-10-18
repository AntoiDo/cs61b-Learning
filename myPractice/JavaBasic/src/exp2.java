import java.util.Scanner;
import java.util.ArrayList;
public class exp2 {
    // 抽象咖啡基类
    public static String myStringJoin(ArrayList<String> Ingredients){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < Ingredients.size(); i++){
            builder.append(Ingredients.get(i));
            if(i < Ingredients.size()-1){
                builder.append("+");
            }
        }
        return builder.toString();
    }

    abstract static class coffee{
        protected ArrayList<String> Ingredients = new ArrayList<>();
        double CostIngredients;

        // 获取花费
        public abstract double cost();
        // 获取制作详情，比如塑料杯白咖啡为:PlasticCup+espresso+milk
        public abstract String getDescription();
        // 获取咖啡种类昵称
        public abstract String getName();

    }
    abstract static class cup{
        public abstract String getDescription();
        public abstract double getPrice();
    }

    /* 这里是关于杯的相关类声明 */
    public static class PlasticCup extends cup {
        @Override
        public double getPrice() {
            return 1.5;
        }
        @Override
        public String getDescription() {
            return "PlasticCup";
        }
    }
    public static class PaperCup extends cup {
        @Override
        public double getPrice() {
            return 2.0;
        }
        @Override
        public String getDescription() {
            return "PaperCup";
        }
    }

    /**
     * 白咖啡：浓缩咖啡 + 牛奶，浓缩咖啡：3元、牛奶：1元
     */
    public static class WhiteCoffee extends coffee{
        WhiteCoffee() {
            Ingredients.add("espresso");
            Ingredients.add("milk");
        }
        @Override
        public double cost() {
            return 3.0 + 1.0;
        }
        @Override
        public String getDescription() {
            return myStringJoin(Ingredients);
            // return String.join("+", Ingredients);
        }
        @Override
        public String getName() {
            return "WhiteCoffee";
        }
    }

    public static class Latte extends coffee {
        Latte(){
            Ingredients.add("espresso");
            Ingredients.add("milk");
            Ingredients.add("milk foam");
        }

        @Override
        public double cost() {
            return 3.0 + 1.0 + 0.5; // espresso + milk + milk foam
        }

        @Override
        public String getDescription() {
            return myStringJoin(Ingredients);
            // return String.join("+", Ingredients);
        }

        @Override
        public String getName() {
            return "Latte";
        }
    }

    public static class CoconutLatte extends coffee {
        public CoconutLatte() {
            Ingredients.add("espresso");
            Ingredients.add("coconut milk");
            Ingredients.add("milk foam");
        }

        @Override
        public double cost() {
            return 3.0 + 1.5 + 0.5; // espresso + coconut milk + milk foam
        }

        @Override
        public String getDescription() {
            return myStringJoin(Ingredients);
            // return String.join("+", Ingredients);
        }

        @Override
        public String getName() {
            return "CoconutLatte";
        }
    }

    public static class CoffeeOrder {
        private coffee Coffee;
        private cup Cup;

        public CoffeeOrder(coffee Coffee, cup Cup) {
            this.Coffee = Coffee;
            this.Cup = Cup;
        }

        public void makeCoffee() {
            if(Coffee == null || Cup == null) {
                System.out.println("This type of drink is currently unavailable.");
                return;
            }
            System.out.println(Cup.getDescription() + " Used");
//            if(Objects.equals(Cup.getDescription(), "PlasticCup")){
//                Cup = new PaperCup();
//            }else{
//                Cup = new PlasticCup();
//            }
            for(int i = 0; i < Coffee.Ingredients.size(); i++){
                System.out.println(Coffee.Ingredients.get(i) + " Added");
            }
            System.out.printf("%s is ready: %s+%s, total price: %.1f CNY%n%n",Coffee.getName(),Cup.getDescription(),Coffee.getDescription(),Cup.getPrice() + Coffee.cost());
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        String buffer = sc.nextLine();
        while(cnt-- > 0){
            cup Cup = null;
            coffee Coffee = null;
            String order = sc.nextLine();
            String[] orderList = order.split(" ");
            String cupType = orderList[0];
            String coffeeType = orderList[1];
            //先配对cupType
            switch (cupType){
                case "PlasticCup":
                    Cup = new PlasticCup();
                    break;
                case "PaperCup":
                    Cup = new PaperCup();
                    break;
                default:
                    System.out.println("This type of drink is currently unavailable.");
                    System.out.println();
                    continue;
            }

            switch (coffeeType) {
                case "WhiteCoffee":
                    Coffee = new WhiteCoffee();
                    break;
                case "Latte":
                    Coffee = new Latte();
                    break;
                case "CoconutLatte":
                    Coffee = new CoconutLatte();
                    break;
                default:
                    System.out.println("This type of drink is currently unavailable.");
                    continue;
            }
            CoffeeOrder guestOrder = new CoffeeOrder(Coffee, Cup);
            guestOrder.makeCoffee();
        }
    }
}