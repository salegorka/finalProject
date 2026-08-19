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

    public List<Bus> getBusList() {
        return this.busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
}
