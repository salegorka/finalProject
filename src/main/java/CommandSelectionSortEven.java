public class CommandSelectionSortEven extends AbstractEvenSortCommand {

    public CommandSelectionSortEven() {
        super("selectionSortEven", "Сортировка выбором");
    }

    @Override
    protected void sortEven(AppData data, int fieldIndex, boolean ascending) {
        for (int currentIndex = 0; currentIndex < data.size() - 1; currentIndex++) {
            if (!isEven(data, currentIndex, fieldIndex)) {
                continue;
            }

            int selectedIndex = currentIndex;

            for (int checkedIndex = currentIndex + 1; checkedIndex < data.size(); checkedIndex++) {
                if (isEven(data, checkedIndex, fieldIndex)
                        && data.compare(checkedIndex, selectedIndex, fieldIndex, ascending)) {
                    selectedIndex = checkedIndex;
                }
            }

            if (selectedIndex != currentIndex) {
                data.swap(currentIndex, selectedIndex);
            }
        }
    }
}
