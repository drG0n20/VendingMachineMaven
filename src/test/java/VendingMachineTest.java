import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VendingMachineTest {

    @Mock
    Kasa kasa;

    @Mock
    Produkty produkty;

    AutomatZBatonami testObject;

    @Before
    public void init() {
        kasa = Mockito.mock(Kasa.class);
        produkty = Mockito.mock(Produkty.class);
        testObject = new AutomatZBatonami(kasa, produkty);  //tworzymy recznie MOCKi to kasy i produktow i tworzymy do nich konstruktora
    }

    @Test
    public void kiedyWkladamyDolaraKasaGoPrzyjmuje() {
        testObject.execute("D");
        verify(kasa).wlozDolar(); //zeby sprawdzic czy cos sie stalo na zmockowanym obiekcie
        checkIfReturnChangeAndNoMoreInteractions();
    }

    @Test
    public void kiedyWkladamyCwiercDolaraKasaGoPrzyjmuje() {
        testObject.execute("Q");
        verify(kasa).wlozCwiercDolara();
        checkIfReturnChangeAndNoMoreInteractions();
    }

    //N
    //d

    @Test
    public void kiedyWkladamy2RozneMonetyKasaJePrzyjmuje() {
        testObject.execute("DQ");
        verify(kasa).wlozDolar();
        verify(kasa).wlozCwiercDolara();
        checkIfReturnChangeAndNoMoreInteractions();
    }

    @Test
    public void kiedyWkladamy2RozneMonetyKasaJePrzyjmuje2() {
        testObject.execute("Nd");
        verify(kasa).wlozNikiel();
        verify(kasa).wlozCent();
        checkIfReturnChangeAndNoMoreInteractions();
    }

    @Test
    public void kiedyWkladamy2DolaryKasaMajePrzyjac() {
        testObject.execute("DD");
        verify(kasa, Mockito.times(2)).wlozDolar(); //zostanie sprawdzone 2x
        checkIfReturnChangeAndNoMoreInteractions();
    }

    @Test
    public void kiedyWlozeDolaraiWybioreProduktNaKtoryMnieNieStacToDostanaDolaraSpowrotem() {
        when(kasa.zwrocReszte()).thenReturn("D"); // akcja -> reakcja
        String result = testObject.execute("DA");
        verify(kasa).wlozDolar();
        checkIfReturnChangeAndNoMoreInteractions();
        assertEquals("D", result);
    }

    private void checkIfReturnChangeAndNoMoreInteractions() {
        verify(kasa).zwrocReszte();
        verifyNoMoreInteractions(kasa);
    }
    //DDC -> C czy stac mnie na ten produkt
    //DDQA ->A
    //QQQ

}
