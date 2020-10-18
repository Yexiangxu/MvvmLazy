package luyao.wanandroid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Classtest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        Subclass subject = new Subclass();
        Pox jdk = new Pox(subject);
        Subinject proxy = (Subinject) jdk.getInstance();
        proxy.add(2, 3);
    }
}
