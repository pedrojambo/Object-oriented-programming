import java.util.HashMap;

public class CaracterMaisFrequente {

    public static char encontrarCaracterMaisFrequente(String texto) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] string = texto.toCharArray();
        for (char i : string) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            map.put(i, 1);
        }
        char c = 0;
        int quant = 0;
        for (char j : map.keySet()) {
            if (map.get(j) > quant) {
                quant = map.get(j);
                c = j;
            }
        }
        return c;
    }
}