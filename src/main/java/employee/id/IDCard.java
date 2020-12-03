package employee.id;

import employee.Employee;

import java.util.Date;

public class IDCard {
    private String id;
    private Date validUntil;
    private Boolean isLocked;
    private IDType type;
    private MagnetStripe magnetStripe;
    private Employee employee;

    public IDCard(String id, Date validUntil, boolean isLocked, IDType type, Employee employee) {
        this.id = id;
        this.validUntil = validUntil;
        this.isLocked = isLocked;
        this.type = type;

        this.magnetStripe = new MagnetStripe(employee);
    }

}
