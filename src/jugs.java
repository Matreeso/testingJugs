import java.util.ArrayList;

public class jugs {
    public ArrayList<ArrayList<Integer>> jra(int a, int b, int c, int x, int y, int z){
        //System.out.println("x: " + x  + ", y: " + y + ", z: " + z);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(x);
        temp.add(y);
        temp.add(z);

        ArrayList<Integer> container = new ArrayList<>();
        container.add(a);
        container.add(b);
        container.add(c);
        
        ArrayList<Integer> water = new ArrayList<>();
        water.add(x);
        water.add(y);
        water.add(z);
        //System.out.println(water.get(0) + " " + water.get(1) + " " + water.get(2));

        // Return arraylist of arraylist

        // jug a; jug b; jug c
        // x y z
        // a into b (ab), ac, ba, bc, ca, cc
        // double for loop
        // check if can pour, check if empty, change amounts;

        // Container we are pouring from
        for (int i = 0; i < 3; i++){
            // check if container is empty
            if (!water.get(i).equals(0)){
                // Container we are pouring into
                for (int j = 0; j < 3; j++){
                    // Making sure not pouring into same container or that container is full
                    if (j != i && !(water.get(j) == container.get(j))){
                        int w = water.get(j) + water.get(i);
                        // Positive s means that container being pourded into is not full or exactly filled
                        // negative s means that container being poured into is overfilled
                        int s = container.get(j) - w;
                        if (s >= 0){
                            temp.set(j, w);
                            temp.set(i, 0);
                        } else {
                            temp.set(j, container.get(j));
                            temp.set(i, -s);
                        }
                        //if (temp.get(i) + temp.get(j) != 11){
                        //    System.out.println("-------------------");
                        //    for (Integer o: temp){
                        //        System.out.print(o + ", ");
                        //    }
                        //    System.out.println();
                        //    System.out.println("s: " + s);
                        //    System.out.println("w: " + w);
                        //    System.out.println("J: " + j + ", i: " + i);
                        //    System.out.println("x: " + x  + ", y: " + y + ", z: " + z);
                        //    System.out.println("++++++++++++++++++");
                        //}
                        result.add(new ArrayList<>(temp));
                        temp.set(0,x);
                        temp.set(1,y);
                        temp.set(2,z);
                    }
                }
            }
        }


        return result;
    }
}
