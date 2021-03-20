package ru.alymov.digitaldesign;

public class Validator {

    public static StringBuffer validate(StringBuffer stringBuffer) throws IllegalArgumentException {
        String temp = stringBuffer.toString().replace(" ","");

        if (!temp.matches("[\\[\\]a-zA-Z0-9]+")){
            throw new IllegalArgumentException("wrong input, allowed input characters are only Latin letters, numbers and brackets []");
        }
        stringBuffer = new StringBuffer(temp);

        int bracketBalancer = 0;

        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.indexOf(String.valueOf(stringBuffer.charAt(i)),i) != stringBuffer.length() - 1 ) {

                if (Character.isLetter(stringBuffer.charAt(i))) {
                    if (!Character.isLetterOrDigit(stringBuffer.charAt(i + 1)) && stringBuffer.charAt(i+1)!=']'  ){
                        throw new IllegalArgumentException("Wrong input after Latin letters ");}
                }
                else
                if (Character.isDigit(stringBuffer.charAt(i))){
                    if(!Character.isDigit(stringBuffer.charAt(i+1)) && stringBuffer.charAt(i+1)!='['){
                        throw new IllegalArgumentException("Wrong input, you should put bracket ( [ ) after a number");
                    }
                }
                else
                if(stringBuffer.charAt(i)=='['){
                    if (stringBuffer.charAt(i+1)=='['|| stringBuffer.charAt(i+1)==']' ){
                        throw new IllegalArgumentException("Wrong input, check the input in brackets");
                    }
                }
                else
                if (stringBuffer.charAt(i) == ']') {
                    if (stringBuffer.charAt(i+1)=='['){
                        throw new IllegalArgumentException("Wrong input, check numbers before brackets");
                    }
                }
                else
                if (!Character.isLetterOrDigit(stringBuffer.charAt(i))) {
                    throw new IllegalArgumentException("Some bad symbols found, allowed input characters are only Latin letters, numbers and brackets []");
                }
            }
            else{
                if (Character.isDigit(stringBuffer.charAt(i))){
                    throw new IllegalArgumentException("Wrong input, some expression expected after number");
                }
            }
            if (stringBuffer.charAt(i)=='['){
                bracketBalancer++;
            }
            if (stringBuffer.charAt(i)==']'){
                bracketBalancer--;
            }
        }
        if (bracketBalancer!=0) throw new IllegalArgumentException("Wrong input, amount of '[' and ']' should match ");

        return stringBuffer;
    }
}