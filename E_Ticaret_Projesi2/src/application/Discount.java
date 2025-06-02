package application;

import javafx.beans.property.*;

public class Discount {
    private final IntegerProperty id;
    private final StringProperty code;
    private final DoubleProperty percentage;

    public Discount(int id, String code, double percentage) {
        this.id = new SimpleIntegerProperty(id);
        this.code = new SimpleStringProperty(code);
        this.percentage = new SimpleDoubleProperty(percentage);
    }

    public int getId() { return id.get(); }
    public String getCode() { return code.get(); }
    public double getPercentage() { return percentage.get(); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty codeProperty() { return code; }
    public DoubleProperty percentageProperty() { return percentage; }
}
