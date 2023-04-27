import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String source = "Alibaba or Alibubab? I don't know";
        String pattern = "b*b";

        List<Integer> result = search(source, pattern);
        System.out.println(result);


    }

    static String spacialSymbol = "*";

    public static List<Integer> search(String source, String pattern) {
        if(source.length() < pattern.length()) {
            System.out.println("Такой подстроки точно нет!");

        }
        List<Integer> found = new ArrayList<>();
        int patternHash = sumWithoutSymbol(pattern);
        int asterikPosition = pattern.indexOf(spacialSymbol);
        int windowHash = 0;
        for (int start = 0; start < source.length() - (pattern.length() + 1); start++) {
            if(start == 0) {
                windowHash = sumOfTheCodes(source, pattern);
                windowHash -= source.charAt(asterikPosition);
            } else {
                windowHash -= source.charAt(start - 1);
                windowHash += source.charAt(start + (pattern.length() - 1));
                windowHash += source.charAt(start + asterikPosition - 1);
                windowHash -= source.charAt(start + asterikPosition);
            }
            if(windowHash == patternHash) {
                for (int i = 0; i < pattern.length(); i++) {
                    if(pattern.charAt(i) != spacialSymbol.charAt(0)
                            && source.charAt(start +1) != pattern.charAt(i)) {
                        continue;

                    } else {
                        found.add(start);
                    }
                }
            }
        }
        return found;
    }

    public static int sumWithoutSymbol(String expression) {
        int sumOfChar = 0;
        for (int i = 0; i < expression.length(); i++) {
            if(spacialSymbol.charAt(0) != expression.charAt(i)) {
                sumOfChar += expression.charAt(i);
            }
        }
        return sumOfChar;
    }

    public static int sumOfTheCodes(String source, String pattern) {
        int sum = 0;
        for (int i = 0; i < pattern.length(); i++) {
            sum += source.charAt(i);
        }
        return sum;
    }
}
