import java.util.ArrayList;
import java.util.List;
interface Observer {
    void update(String message);
}
class TaxAgency {
    private List<Observer> observers = new ArrayList<>();
    private String taxInformation;
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    public void updateTaxInformation(String newInformation) {
        taxInformation = newInformation;

        notifyObservers(newInformation);
    }
}
class TaxPayer implements Observer {
    private String name;
    public TaxPayer(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + ": Получено уведомление о налоговых изменениях - " + message);
    }
}

class AccountingDepartment implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Бухгалтерия: Получено уведомление о налоговых изменениях - " + message);
    }
}
public class Main {
    public static void main(String[] args) {
        TaxAgency taxAgency = new TaxAgency();

        TaxPayer taxPayer1 = new TaxPayer("Батыр");
        TaxPayer taxPayer2 = new TaxPayer("Дина");
        AccountingDepartment accountingDepartment = new AccountingDepartment();

        taxAgency.addObserver(taxPayer1);
        taxAgency.addObserver(taxPayer2);
        taxAgency.addObserver(accountingDepartment);

        taxAgency.updateTaxInformation("Обновление налоговые правила");

        taxAgency.removeObserver(taxPayer2);

        taxAgency.updateTaxInformation("Новые налоговые правила в 2023 году");
    }
}
