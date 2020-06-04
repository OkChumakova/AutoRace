import java.util.*;

public class Race{

     static List<Car> intermediateResult = Collections.synchronizedList(new ArrayList<>());

    static List<Car> listOfCompetitors = new ArrayList<>();

    public static void main(String[] args) {

        // cars-threads are created
        for(int i = 0; i <10; i++ ){
            listOfCompetitors.add(new Car ("Car" + i));
        }


            // cars-threads start
            for (int i = 0; i < 10; i++) {
                listOfCompetitors.get(i).start();
            }

    }

}
