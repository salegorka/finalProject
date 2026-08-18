import java.util.ArrayList;
import java.util.List;

public class AppData {
    private List<Bus> busList;
    //To DO


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
    public boolean compareByFieldIndexAsc(int i, int j, int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return this.busList.get(i).number < this.busList.get(j).number;
        } else if(index == 1) {
            return this.busList.get(i).model < this.busList.get(j).model;
        } else if(index == 2) {
            return this.busList.get(i).mileage < this.busList.get(j).mileage;
        } else {
           throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
        };
    }

    public boolean compareByFieldIndexDesc(int i, int j, int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return this.busList.get(i).number > this.busList.get(j).number;
        } else if(index == 1) {
            return this.busList.get(i).model > this.busList.get(j).model;
        } else if(index == 2) {
            return this.busList.get(i).mileage > this.busList.get(j).mileage;
        } else {
            throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
        };
    }

    //метод пермешивает массив в случайном порядке
    public void random() {

    }

    public List<Bus> getBuslit() {
        return this.busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
}
