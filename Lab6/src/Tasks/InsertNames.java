package Tasks;

import RunInput.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsertNames {
    public void Insert() {
        System.out.println("----- Insert names -----");
        System.out.println("Insert /exit to quit program: ");
        List<String> nameList = new ArrayList<>();

        while (true) {
            System.out.print(">>> ");

            String inputName = new Input().InputString();
            if(Objects.equals(inputName, "/exit")) break;
            else {
                nameList.add(inputName);
            }
        }

        for (String s : nameList) {
            System.out.println(s);
        }
    }

}
