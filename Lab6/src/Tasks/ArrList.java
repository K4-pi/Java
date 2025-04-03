package Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyClass {
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class ArrList {
    public void Arr() {
        MyClass[] array = {
                new MyClass("Object1"),
                new MyClass("Object2"),
                new MyClass("Object3"),
                new MyClass("Object4"),
                new MyClass("Object5")
        };

        List<MyClass> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("List: " + list);

        List<MyClass> subList = list.subList(1, 4);
        System.out.println("SubList: " + subList);
        subList.clear();

        System.out.println("Po usunięciu: " + list);
    }

}

