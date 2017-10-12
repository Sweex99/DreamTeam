package applicationfolder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AppLogic {
    FileReading fileReading = new FileReading();
    //private String fileName = "/text.txt";
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    private int[] buffs = new int[10];

    private static int random_int(int to) {
        int n = 0 + (int) (Math.random() * to);
        return n;
    }


    /*public void setFileReading(String fileName) {
        this.fileName = fileName;
    }*/
    /*public void tes////ting() throws IOException {
        int num = 0;
        int truenum = 0;
        int result = 0;
        int random = 0;
        BufferedReader touch = new BufferedReader(new InputStreamReader(System.in));
        qna();
        for(int i = 0; i < 10; i++) {
            random = random_int();
            for(int k = 0; k < 10; k++) {
                while(buffs[k] == random) random = random_int();
            }
            buffs[i] = random;
            for(int j = 0; j < 4; j++) {
                if(answers.get(random*4 + j).endsWith("!true!")) truenum = j + 1;
            }
            System.out.println((i + 1) + "." + questions.get(random));
            for(int t = 0; t < 4; t++) {
                String str = answers.get(random*4 + t);
                str = str.replaceAll("!true!", "");
                System.out.println(str);
            }
            num = Integer.parseInt(touch.readLine());
            if(num == truenum) result++;
        }
        //Runtime.getRuntime().exec("cls");
        System.out.println("Ваш результат " + result + " правильних відповідей із 10!");
        if(result == 10) System.out.println("Ви справжній програміст!!!");
        else if(result > 7) System.out.println("Вітаю!");
        else if(result >= 5) System.out.println("Непогано");
        else if(result >= 2) System.out.println("Вам варто ще багато вивчити...");
        else System.out.println("Ви часом не гуманітарій?");
    }*/

    private void qna(String fileName, boolean b) throws FileNotFoundException {
        if(b) {
            text = fileReading.filetext(fileName);
        }
        else text = fileReading.fileouttext(fileName);

        for (int i = 1; i < text.size(); i++) {
            String s = text.get(i);
            if (s.charAt(s.length() - 1) == '?') {
                questions.add(s);
            } else {
                answers.add(s);
            }
        }
    }

    public String[] testing(String fileName, boolean b) throws IOException {
        int num = 0;
        int truenum = 0;
        int result = 0;
        int random = 0;
        String[] test = new String[6];
        qna(fileName, b);
        random = random_int(questions.size());
            /*for(int k = 0; k < 10; k++) {
                while(buffs[k] == random) random = random_int();
            }
            buffs[i] = random;*/
        for (int j = 0; j < 4; j++) {
            if (answers.get(random * 4 + j).endsWith("!true!")) truenum = j + 1;
        }
        test[5] = truenum + "";
        test[4] = questions.get(random);
        for (int t = 0; t < 4; t++) {
            String str = answers.get(random * 4 + t);
            str = str.replaceAll("!true!", "");
            //System.out.println(str);
            test[t] = str;
        }
        // if(num == truenum) result++;
        //Runtime.getRuntime().exec("cls");
        /*System.out.println("Ваш результат " + result + " правильних відповідей із 10!");
        if(result == 10) System.out.println("Ви справжній програміст!!!");
        else if(result > 7) System.out.println("Вітаю!");
        else if(result >= 5) System.out.println("Непогано");
        else if(result >= 2) System.out.println("Вам варто ще багато вивчити...");
        else System.out.println("Ви часом не гуманітарій?");*/
        return test;
    }
}
