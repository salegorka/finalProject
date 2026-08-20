import java.util.Objects;

public class Bus {
    private final int number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return String.format("Bus{number=%d, model='%s', mileage=%d}", number, model, mileage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return number == bus.number &&
                mileage == bus.mileage &&
                Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }

    public static class Builder {
        private int number;
        private String model;
        private int mileage;

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            validate();
            return new Bus(this);
        }

        private void validate() {
            if (number <= 0) {
                throw new IllegalArgumentException("Bus number must be positive");
            }
            if (number > 999999) {
                throw new IllegalArgumentException("Bus number cannot exceed 999999");
            }
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Model cannot be empty");
            }
            if (mileage < 0) {
                throw new IllegalArgumentException("Mileage cannot be negative");
            }
            if (mileage > 1000000) {
                throw new IllegalArgumentException("Mileage cannot exceed 1,000,000 km");
            }
        }
    }
}