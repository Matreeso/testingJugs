import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Scanner;

public class bajwa_hendley {
    public static ArrayList<Integer> sortList(List<Integer> set){
        ArrayList<Integer> tra = new ArrayList<>(set);
        int n = tra.size();
        for (int i = 0; i < n - 1; i++){
            int minIndex = i;

            for (int j = i + 1; j < n; j++){
                if (tra.get(j) < tra.get(minIndex)){
                    minIndex = j;
                }
            }

            int temp = tra.get(i);
            tra.set(i, tra.get(minIndex));
            tra.set(minIndex, temp);
        }
        return tra;
    }
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        Queue<List<List<Integer>>> q = new LinkedList<>();
        jugs j = new jugs();
        Set<List<Integer>> forDaQ = new HashSet<>();
        ArrayList<List<Integer>> newMez = new ArrayList<>();
        Set<Integer> mezzes = new HashSet<>();

        // Bucket capacity
        int a = 3; int b = 5; int c = 11;
        // Amount of water
        int x = 0; int y = 2; int z = 11;

        // Inputs
        if (args.length != 6){
            String input = scnr.nextLine();
            String[] inputs = input.split(" ");
            a = Integer.parseInt(inputs[0]);
            b = Integer.parseInt(inputs[1]);
            c = Integer.parseInt(inputs[2]);
            x = Integer.parseInt(inputs[3]);
            y = Integer.parseInt(inputs[4]);
            z = Integer.parseInt(inputs[5]);
        } else {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            c = Integer.parseInt(args[2]);
            x = Integer.parseInt(args[3]);
            y = Integer.parseInt(args[4]);
            z = Integer.parseInt(args[5]);
        }


        List<Integer> tempMez = new ArrayList<>();
        tempMez.add(x);
        mezzes.add(x);
        if (!mezzes.contains(y)){
            tempMez.add(y);
        }
        mezzes.add(y);
        if (!mezzes.contains(z)){
            tempMez.add(z);
        }
        mezzes.add(z);
        newMez.add(tempMez);
        HashMap<List<Integer>, Integer> dup = new HashMap<>();
        int iteration = 0;
        ArrayList<ArrayList<Integer>> r = new ArrayList<>();
        System.out.println("capacity: " + "{'Jug A': " + a + ", 'Jug B': " + b + ", 'Jug C': " + c + "},");
        System.out.println("Initial amount of water" + "{'Jug A': " +  x + ", 'Jug B': " + y + ", 'Jug C': " + z + "},");
        System.out.println("Step: New Measurements: New states");
        System.out.println("----:-----------------:---------------------------------");
        String nm = "";
        String space = "";
        newMez.set(iteration, sortList(newMez.get(iteration)));
        for (Integer k : newMez.get(iteration)){
            nm = nm + k + ", ";
        }
        nm = nm.substring(0, nm.length()-2);
        String space2 = "";
        for (int i = 0; i < (17-nm.length())/2; i++){
            space = space + " ";
        }
        if (nm.length() % 2 == 0){
            space2 = space + " ";
        } else {
            space2 = space;
        }
        System.out.print("   " + iteration + ":" + space2 + nm + space + ":");
        iteration++;
        System.out.println("[" + x + ", " + y + ", " + z + "]");
        q.add(Arrays.asList(Arrays.asList(x,y,z)));
        dup.putIfAbsent(Arrays.asList(x,y,z), iteration);
        while(!q.isEmpty()){
            forDaQ = new HashSet<>();
            tempMez = new ArrayList<>();
            String result = "";
            for (int i = 0; i < q.peek().size(); i++){
                r = j.jra(a,b,c, q.peek().get(i).get(0),q.peek().get(i).get(1),q.peek().get(i).get(2));
                for (ArrayList<Integer> arr : r){
                    if (!dup.containsKey(arr)){
                        result = result + "[";
                        for (Integer L : arr){
                            result = result + L + ", ";
                            if (!mezzes.contains(L)){
                                tempMez.add(L);
                            }
                            mezzes.add(L);
                        }
                        result = result.substring(0, result.length()-2) + "], ";
                        if (!dup.containsKey(arr)){
                            forDaQ.add(arr);
                        }
                        dup.put(arr, iteration);
                    } else if (dup.containsKey(arr) && dup.get(arr).equals(iteration)){ // p2 for when two make the same
                        int rocket = result.indexOf(arr.toString()) + arr.toString().length();
                        result = result.substring(0, rocket) + "p2" + result.substring(rocket, result.length());
                    }
                }
            }
            newMez.add(tempMez);
            if (forDaQ.size() > 0){
                q.add(new ArrayList<>(forDaQ));
            }
            if (result.length() > 0){
                nm = "";
                space = "";
                newMez.set(iteration, sortList(newMez.get(iteration)));
                for (Integer k : newMez.get(iteration)){
                    nm = nm + k + ", ";
                }
                if (nm.length() > 0){
                    nm = nm.substring(0, nm.length()-2);
                }
                space2 = "";
                for (int i = 0; i < (17-nm.length())/2; i++){
                    space = space + " ";
                }
                if (nm.length() % 2 == 0){
                    space2 = space + " ";
                } else {
                    space2 = space;
                }
                System.out.print("   " + iteration + ":" + space2 + nm + space + ":");
                iteration++;
                System.out.println(result.substring(0, result.length()-2));
            }
            q.remove();
        }
        System.out.println("----:-----------------:---------------------------------");
    }
}
