package org.contest;

import java.util.Arrays;

public class SecondExercise {

    public static void main(String[] args) {

        String firstLine = "5 1";
        String secondLine = "0 2 4 3 -1";

        String[] nk = firstLine.split(" ");
        int k = Integer.parseInt(nk[1]);

        int[] a = Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).toArray();


        int[] binary = Arrays.stream(a)
                .map(it -> {

                            if (it % 2 == 0) {
                                it = 0;
                            } else {
                                it = 1;
                            }
                            return it;
                        }
                )
                .toArray();

        while (k > 0) {
            k--;

            int indexOdd = -1; // нечетное
            int indexEven = -1; //четное

            for (int i = 0; i < binary.length; i++) {
                if (binary[i] == 1) {
                    indexOdd = i;
                    break;
                }
            }

            for (int i = binary.length - 1; i > 0; i--) {
                if (binary[i] == 0) {
                    indexEven = i;
                    break;
                }
            }

            if (indexOdd >= 0 && indexEven >= 0) {
                binary[indexOdd] = 0;
                binary[indexEven] = 1;
            } else {
                break;
            }
        }

        int answer = binary.length;

        for (int i = 0; i < binary.length; i++) {
            if (binary[i] == 1) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
