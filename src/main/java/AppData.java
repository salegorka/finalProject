import java.util.ArrayList;
import java.util.List;

public class AppData {
    private List<Bus> busList;

    public AppData() {
        this.busList = List.of();
    }

    public AppData(List<Bus> busList) {
        this.busList = busList;
    }

    // метод возращает размер массива
    public int size() {
        return this.busList.size();
    }

    //метод для сравнения

    public boolean compare(int i, int j, int index, boolean asc) throws IndexOutOfBoundsException {
        if (asc) {
            if (index == 0) {
                return this.busList.get(i).getNumber() < this.busList.get(j).getNumber();
            } else if(index == 1) {
                return this.busList.get(i).getModel().compareTo(this.busList.get(j).getModel()) < 0;
            } else if(index == 2) {
                return this.busList.get(i).getMileage() < this.busList.get(j).getMileage();
            } else {
                throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
            }
        } else {
            if (index == 0) {
                return this.busList.get(i).getNumber() > this.busList.get(j).getNumber();
            } else if(index == 1) {
                return this.busList.get(i).getModel().compareTo(this.busList.get(j).getModel()) > 0;
            } else if(index == 2) {
                return this.busList.get(i).getMileage() > this.busList.get(j).getMileage();
            } else {
                throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
            }
        }
    }

    //метод меняет местами элементы i и j
    public void swap(int i, int j) {
        Bus temp = this.busList.get(i);
        this.busList.set(i, this.busList.get(j));
        this.busList.set(j, temp);
    }

    //метод пермешивает массив в случайном порядке
    public void random() {

    }

    public List<Bus> getBusList() {
        return this.busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
}
