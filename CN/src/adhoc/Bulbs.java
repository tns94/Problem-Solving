package adhoc;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bulbs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n =sc.nextInt();
        int x= sc.nextInt();
        int y= sc.nextInt();
        int min=x>y?y:x;
        String s=sc.next();
        Pattern pattern = Pattern.compile("0+");
        Matcher matcher=pattern.matcher(s);
        long count=0;
        while (matcher.find())
            count++;

        count=count==0?0:(count-1)*min+y;
        System.out.println(count);




    }
}
