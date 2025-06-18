package UserDashboard;

import java.time.LocalDate;
import java.util.List;

public class TempIncomingEntry {
    private String phoneName, color, size, authorized, supplier;
    private LocalDate date;
    private List<String> imeis;

    public TempIncomingEntry(String phoneName, String color, String size, String authorized,
                             String supplier, LocalDate date, List<String> imeis) {
        this.phoneName = phoneName;
        this.color = color;
        this.size = size;
        this.authorized = authorized;
        this.supplier = supplier;
        this.date = date;
        this.imeis = imeis;
    }

    public List<String> getImeis() { return imeis; }

    // Add other getters if needed for TableView binding
}
