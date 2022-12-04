package year2021.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<List<Integer>> masterNumList = new ArrayList<>();
        List<List<Integer>> masterIndexList = new ArrayList<>();
        List<Integer> myList = new ArrayList<>(List.of(1,2));
        List<Integer> myList2 = new ArrayList<>(List.of(11,12));
        List<Integer> myList3 = new ArrayList<>(List.of(21,22));
        List<Integer> myList4 = new ArrayList<>(List.of(31,32));
        List<Integer> myList5 = new ArrayList<>(List.of(41,42));
        List<Integer> myList6 = new ArrayList<>(List.of(51,52));

        masterNumList.addAll(List.of(myList, myList2, myList3, myList4, myList5, myList6));

        List<Integer> indexList = new ArrayList<>(List.of(1,3,4));


        List<List<Integer>> objectsToBeDeleted = new ArrayList<>();

        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            objectsToBeDeleted.add(masterNumList.get(index));
        }

        // want only 1-10, 30-40, 50
        System.out.println(objectsToBeDeleted);

        System.out.println("=== BEFORE ===");
        System.out.println(masterNumList);

        masterNumList = masterNumList.stream()
                        .filter(list -> !list.contains(objectsToBeDeleted.get(0)))
                                .collect(Collectors.toList());

        System.out.println("=== AFTER ===");
        //masterIndexList.removeAll(objectsToBeDeleted);
        System.out.println(masterNumList);

    }
}
