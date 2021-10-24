import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        String a = "GO-20211005-135-001 (#489)\t";
        String [] alltext = a.split(" ");
//        System.out.println(alltext);

        System.out.println(randomNumber());
    }

    public static int randomNumber(){
        Random random = new Random();
        return random.nextInt(999);
    }

    public static boolean returnStatus() {
        boolean status = false;
        ArrayList<String> originalNameList = new ArrayList<>();
        ArrayList<Date> listItemDate = new ArrayList<Date>();

        originalNameList.add("Oct 6, 2021");
        originalNameList.add("Dec 2, 2021");
        originalNameList.add("Oct 17, 2021");
        originalNameList.add("Jan 6, 2021");
//        ArrayList<String> newList = new ArrayList<>();
        for (String a : originalNameList) {
            listItemDate.add(convertStringToDate(a));
        }
        System.out.println("new list =" + listItemDate);


        ArrayList<String> expectValue = new ArrayList<>();
        expectValue.add("a");
        expectValue.add("b");
        expectValue.add("d");
        expectValue.add("c");
        Collections.sort(listItemDate);

        System.out.println(listItemDate);
        return expectValue.equals(originalNameList);
    }

    public static Date convertStringToDate(String dateInString) {
        dateInString = dateInString.replaceAll(",", "");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String cutText(String text){
        String [] text1 = text.split("of | entries");
        return text1[1];
    }

}

