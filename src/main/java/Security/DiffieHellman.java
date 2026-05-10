package Security;

import java.util.List;
import java.math.BigInteger;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        BigInteger p = BigInteger.valueOf(q);
        BigInteger g = BigInteger.valueOf(alpha);
        BigInteger xA = BigInteger.valueOf(xa);
        BigInteger xB = BigInteger.valueOf(xb);

        BigInteger ya = g.modPow(xA, p);
        BigInteger yb = g.modPow(xB, p);

        BigInteger ka = yb.modPow(xA, p);
        BigInteger kb = ya.modPow(xB, p);

        return List.of(ka.intValue(), kb.intValue());
    }
}
