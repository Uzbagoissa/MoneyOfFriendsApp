import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Functions functions = new Functions();
        ArrayList<String> list = new ArrayList<>();
        /*некит;550;кирилл,серега,стас
        кирилл;1780;некит,серега
        серега;320;некит,кирилл
        стас;480;серега
        некит;432;кирилл,серега
        некит;630;кирилл,стас
        кирилл;940;некит,серега,стас
        серега;444;некит,кирилл,стас
         */

        System.out.println("ПОЕХАЛИ!!!111БББЛЛлляяя");

        while (true){
            System.out.println("Пиши, что жрали? Если больше ничего, пиши NO");
            String food = scanner.next();
            if (food.equals("NO")){
                break;
            }

            System.out.println("Кто башлял? Скока он забашлял? Кто это жрал вместе с башлявшим? Пишем по образцу:" +
                    " ваня;456;кирилл,катя");
            String answer = scanner.next();
            System.out.println(functions.getCount(answer));
            list.add(answer);
        }
        functions.getDebtorsList(list);
    }
}
