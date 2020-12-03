package employee.id;

import configuration.Configuration;
import employee.Employee;

import java.util.Random;

public class MagnetStripe {
    String content;

    public MagnetStripe(Employee employee) {
        encrypt(employee);
    }

    private void encrypt(Employee employee){
        Random random = new Random();
        int pin = random.nextInt(9999 - 1000) + 1000;
        String contentDecrypted = "***" + employee.getProfileType().toString() + "***" + pin + "***";
        content = Configuration.instance.aes.encrypt(contentDecrypted, Configuration.instance.secretKey);
    }
}
