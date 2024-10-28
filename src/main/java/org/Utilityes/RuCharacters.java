    package org.Utilityes;

    import java.util.HashMap;
    import java.util.Map;

    public class RuCharacters {
        private final static Map<Character, Integer> ruCharMap = new HashMap<>();
         public final static char[] letters = {
                 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К',
                'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
                'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'
         };

         static {
             for (int i = 0; i < letters.length; i++){
                 ruCharMap.put(letters[i], i + 1);
             }
         }


         public static boolean isRuChar(char ch) {
             return ruCharMap.containsKey(Character.toUpperCase(ch));
         }

         public static char getRuCharFromString(String s) {
    //         Pattern p = Pattern.compile("[\\u0410-\\u044F\\u0401\\u0451]");
    //         Matcher m = p.matcher(s);
    //         if (m.find()) {
    //             return m.group().charAt(0);
    //         } else {
    //             return '\0';
    //         }
             for (char ch : s.toCharArray()) {
                 if (ruCharMap.containsKey(Character.toUpperCase(ch))) {
                     return ch;
                 }
             }
             return 0;

         }

         public static int getIntFromChar(char ch) {
             if (isRuChar(ch)) {
                 return ruCharMap.get(Character.toUpperCase(ch));
             } else {
                 return 0;
             }
         }


         public static char getCharFromInt(int i) {
             return letters[i - 1];
         }
    }
