package salakhov.lesson.factory;

public class AdapterJeep extends Jeep {
    private Lada lada;

    public AdapterJeep(Lada lada) {
        this.lada = lada;
    }

    public int getLadaPrice() {
        return lada.getLadaPrice();
    }
}
