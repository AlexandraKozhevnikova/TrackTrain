package org.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sequence {


    public static void main(String[] args) {
        // прием данных
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstListInt = new LinkedList<>();
        String line = "5 1 2 3 4 5";//scanner.nextLine();

        Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).forEach(firstListInt::add);
        firstListInt.remove(0);

        List<Integer> secondListInt = new LinkedList<>();
        String line2 = "4 9 10 11 12";//scanner.nextLine();
        Arrays.stream(line2.split(" ")).mapToInt(Integer::parseInt).forEach(secondListInt::add);
        secondListInt.remove(0);

        List<Integer> thirdListInt = new LinkedList<>();
        String line3 = "5 5 6 7 8 9";//scanner.nextLine();
        Arrays.stream(line3.split(" ")).mapToInt(Integer::parseInt).forEach(thirdListInt::add);
        thirdListInt.remove(0);


        // обработка

        StringBuilder sb = new StringBuilder();
        Check check = new Check();

        StringBuilder sb123 = new StringBuilder(sb);
        StringBuilder sb312 = new StringBuilder(sb);
        StringBuilder sb231 = new StringBuilder(sb);


        //123
        firstListInt.stream().map(Character::toChars).map(String::valueOf).forEach(sb123::append);

        sb123 = check.check(sb123, secondListInt);
        sb123 = check.check(sb123, thirdListInt);

        //312
        thirdListInt.stream().map(Character::toChars).map(String::valueOf).forEach(sb312::append);

        sb312 = check.check(sb312, firstListInt);
        sb312 = check.check(sb312, secondListInt);


        // 231
        secondListInt.stream().map(Character::toChars).map(String::valueOf).forEach(sb231::append);

        sb231 = check.check(sb231, thirdListInt);
        sb231 = check.check(sb231, firstListInt);


        //выбор лучшего
        List<StringBuilder> sort = new ArrayList<>(List.of(sb123, sb312, sb231));
        sort.sort(Comparator.comparingInt(o -> o.length()));


        //вывод
        String[] res = sort.get(0).toString().split("(?!^)");
        System.out.println(res.length);


        Arrays.stream(res)
                .map(it -> it.charAt(0))
                .map(it -> (int) it)
                .forEach(it -> System.out.print(it + " "));
    }
}


class Check {

    public StringBuilder check(StringBuilder bulder, List<Integer> listInt) {
        StringBuilder seqString = new StringBuilder();

        listInt.stream().map(Character::toChars).map(String::valueOf).forEach(seqString::append);

        String sub = seqString.toString();
        String subVirgin = sub;

        StringBuilder result = new StringBuilder();

        if (!bulder.toString().contains(sub)) {

            // назад подклеиваем
            int indexSub = 0;
            sub = subVirgin;
            indexSub = subVirgin.length();
            while (sub.length() > 1) {
                indexSub--;
                sub = sub.substring(0, indexSub);
                if (bulder.toString().endsWith(sub)) {
                    bulder.append(subVirgin, indexSub, subVirgin.length());
                    return bulder;
                } else {
                    continue;
                }
            }


            indexSub = 0;
            sub = subVirgin;
            // вперед подклеиваем
            while (sub.length() > 1) {
                indexSub++;
                sub = sub.substring(1);
                if (bulder.toString().startsWith(sub)) {
                    result.append(subVirgin, 0, indexSub);
                    result.append(bulder);
                    bulder = result;
                    return bulder;
                } else {
                    continue;
                }
            }

            bulder.append(subVirgin);
            return bulder;

        }
        return bulder;
    }
}




