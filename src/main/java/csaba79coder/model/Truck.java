package csaba79coder.model;

public class Truck {

    private int width;
    private int length;
    private int height;
    private int maxWeight;

    public Truck(int width, int length, int height, int maxWeight) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.maxWeight = maxWeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
