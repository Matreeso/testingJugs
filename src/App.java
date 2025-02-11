import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        jugs j = new jugs();
        int count = 0;
        boolean went = false;

        ArrayList<ArrayList<Integer>> dup = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        // adding original state to duplicates
        temp.add(0);
        temp.add(0);
        temp.add(11);
        dup.add(temp);

        int iteration = 0;

        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        System.out.println("capacity: " + "{'Jug A': " + 3 + ", 'Jug B': " + 5 + ", 'Jug C': " + 11 + "},");
        System.out.println("Initial amount of water" + "{'Jug A': " +  0 + ", 'Jug B': " + 0 + ", 'Jug C': " + 11 + "},");
        System.out.println("Step: New Measurements: New states");
        System.out.println("----:-----------------:---------------------------------");
        r = j.jra(3,5,11,0,0,11);
        System.out.print(iteration + ":              ");
        iteration++;
        for (ArrayList<Integer> a : r){
            System.out.print("[");
            for (Integer i : a){
                System.out.print(i + ", ");
            }
            System.out.print("],");
            q.add(a);
            dup.add(a);
        }
        System.out.println();
        while(!q.isEmpty()){
            r = j.jra(3,5,11, q.peek().get(0),q.peek().get(1),q.peek().get(2));
            q.remove();
            for (ArrayList<Integer> a : r){
                //System.out.println(notDup(dup, a));
                if (notDup(dup, a)){
                    if (count == 0){
                        System.out.print(iteration + ":              ");
                        iteration++;
                    }
                    went = true;
                    count++;
                    q.add(a);
                    dup.add(a);
                    System.out.print("[");
                    for (Integer i : a){
                        System.out.print(i + ", ");
                    }
                    System.out.print("],");
                }
            }
            count = 0;
            if (went){
                System.out.println();
            }
            went = false;
        }
    }

    public static boolean notDup(ArrayList<ArrayList<Integer>> scroll, ArrayList<Integer> trainee){
        for (ArrayList<Integer> A : scroll){
            if (A.equals(trainee)){
                return false;
            }
        }
        return true;
    }
}
