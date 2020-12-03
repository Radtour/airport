package employee.id;

import configuration.Configuration;
import employee.Employee;

import java.util.Calendar;
import java.util.Date;

public class IDCard {
    private final String id;
    private final Date validUntil;
    private final Boolean isLocked;
    private final IDType type;
    private final MagnetStripe magnetStripe;
    private Employee employee;

    public IDCard(String id, IDType type) {
        this.id = id;
        this.type = type;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.YEAR, 1);
        this.validUntil = c.getTime();
        this.isLocked = false;
        this.magnetStripe = new MagnetStripe(employee);
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public IDType getType() {
        return type;
    }

    public MagnetStripe getMagnetStripe() {
        return magnetStripe;
    }

    public String getId() {
        return id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
