package TestCase;

import PageUI.ProductPageUI;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class test {
    public static void main(String[] args){
    System.out.println("final status = " +returnStatus());

    }
    public static boolean returnStatus(){
        boolean status = false;
        ArrayList<String> originalNameList = new ArrayList<>();
        originalNameList.add("c");
        originalNameList.add("b");
        originalNameList.add("a");
        originalNameList.add("d");

        ArrayList<String> expectValue = new ArrayList<>();
        expectValue.add("a");
        expectValue.add("b");
        expectValue.add("d");
        expectValue.add("c");
//        originalNameList.sort(Comparator.reverseOrder());
        originalNameList.sort(Comparator.naturalOrder());
        System.out.println(originalNameList);
        System.out.println(expectValue);
//
        return expectValue.equals(originalNameList);
    }
    }

