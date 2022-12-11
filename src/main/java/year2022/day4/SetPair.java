package year2022.day4;

import java.util.Set;

public class SetPair {
    private Set<Integer> set1;
    private Set<Integer> set2;

    public SetPair() {
    }

    public void setSet1(Set<Integer> set1) {
        this.set1 = set1;
    }

    public void setSet2(Set<Integer> set2) {
        this.set2 = set2;
    }

    public Set<Integer> getSet1() {
        return set1;
    }

    public Set<Integer> getSet2() {
        return set2;
    }

    @Override
    public String toString() {
        return "list1=" + set1 +
                ", list2=" + set2;
    }
}
