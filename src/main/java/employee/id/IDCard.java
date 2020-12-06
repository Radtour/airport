package employee.id;

import employee.Employee;

import java.util.Calendar;
import java.util.Date;

public class IDCard {
    private final String id;
    private final Date validUntil;
    private final IDType type;
    private Boolean isLocked;
    private MagnetStripe magnetStripe;
    private Employee employee;
    private int failedAttempts;

    public IDCard(String id, IDType type) {
        this.id = id;
        this.type = type;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.YEAR, 1);
        this.validUntil = c.getTime();
        this.isLocked = false;
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

    public void writeMagnetStripe(Employee employee){
        this.magnetStripe = new MagnetStripe(employee);
    }

    public void addFailedAttempts() {
        this.failedAttempts++;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }
}
