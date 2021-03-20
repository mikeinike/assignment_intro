package ru.alymov.digitaldesign;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static StringBuffer convert(StringBuffer strBuffer){
        String tempUnconvertedSubstring = null;
        String tempConvertedSubstring = null;
        String tempStringToReplace = null;

        int tempNumber = 0;
        int tempLastIndex = 0;
        int tempFirstIndex = 0;

        while (strBuffer.indexOf("]") != -1 ) {
            tempFirstIndex = strBuffer.lastIndexOf("[");
            tempLastIndex = strBuffer.indexOf("]",tempFirstIndex);
            tempUnconvertedSubstring = strBuffer.substring(tempFirstIndex+1,tempLastIndex);

            tempLastIndex = tempFirstIndex;
            for (int i = tempFirstIndex-1; i!=-1 && Character.isDigit(strBuffer.charAt(i)) ; i--) {
                tempFirstIndex = i;
            }
            tempNumber = Integer.parseInt(strBuffer.substring(tempFirstIndex,tempLastIndex));

            tempStringToReplace = tempNumber + "[" + tempUnconvertedSubstring + "]";

            String finalTempUnconvertedSubstring = tempUnconvertedSubstring;
            tempConvertedSubstring = IntStream
                    .range(0,tempNumber)
                    .mapToObj(i-> finalTempUnconvertedSubstring)
                    .collect(Collectors.joining(""));

            strBuffer.replace(
                    strBuffer.indexOf(tempStringToReplace),
                    strBuffer.indexOf(tempStringToReplace) + (tempStringToReplace.length()),
                    tempConvertedSubstring
            );
        }

        return strBuffer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string like number [string]");
        String phrase = scanner.nextLine();
        StringBuffer strBuffer = new StringBuffer(phrase);

        strBuffer = Validator.validate(strBuffer);

        System.out.println(convert(strBuffer));
    }
}
