import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class AutomatZBatonami {
    private final Kasa kasa;
    private final Produkty produkty;

    public AutomatZBatonami(Kasa kasa, Produkty produkty) {

        this.kasa = kasa;
        this.produkty = produkty;
    }

    public String execute(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'Q') {
                kasa.wlozCwiercDolara();
            }
            if (input.charAt(i) == 'D') {
                kasa.wlozDolar();
            }
            if (input.charAt(i) == 'N') {
                kasa.wlozNikiel();
            }
            if (input.charAt(i) == 'd') {
                kasa.wlozCent();
            }
        }
        return kasa.zwrocReszte();
    }
}

