import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppData {
    private List<Bus> busList;
    private final Random random;
    //To DO


    public AppData() {
        this.busList = List.of();
        this.random = new Random();
    }

    public AppData(List<Bus> busList, Random random) {
        this.busList = busList;
        this.random = random;
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

    public void setDatalist(List<Bus> datalist) {
        this.busList = datalist;
    }


    //метод пермешивает массив в случайном порядке
    public void random() {
        // Fisher-Yates shuffle
        for (int i = busList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap
            Bus temp = busList.get(i);
            busList.set(i, busList.get(j));
            busList.set(j, temp);
        }
    }

    public List<Bus> getBuslit() {
        return this.busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
}
