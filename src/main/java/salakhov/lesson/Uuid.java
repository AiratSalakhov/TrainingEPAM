package salakhov.lesson;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Uuid {
    private byte[] uuid = new byte[16];

    public Uuid() {
        for (int i = 0; i < 16; i++) {
            this.uuid[i] = (byte) (Math.random() * 255);
        }
    }

    public byte[] getUuid() {
        return uuid;
    }

    public void setUuid(byte[] uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(HexBin.encode(this.uuid));
        s.insert(20, "-");
        s.insert(16, "-");
        s.insert(12, "-");
        s.insert(8, "-");
        return s.toString();
    }
}
