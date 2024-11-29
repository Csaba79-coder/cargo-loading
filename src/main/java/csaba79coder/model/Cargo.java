package csaba79coder.model;

public class Cargo {

    private int id;
    private int width, length, height, weight;
    private Integer startX, startY, startZ, endX, endY, endZ;

    public Cargo(int id, int width, int length, int height, int weight) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    public int volume() {
        return width * length * height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getStartX() {
        return startX;
    }

    public void setStartX(Integer startX) {
        this.startX = startX;
    }

    public Integer getStartY() {
        return startY;
    }

    public void setStartY(Integer startY) {
        this.startY = startY;
    }

    public Integer getStartZ() {
        return startZ;
    }

    public void setStartZ(Integer startZ) {
        this.startZ = startZ;
    }

    public Integer getEndX() {
        return endX;
    }

    public void setEndX(Integer endX) {
        this.endX = endX;
    }

    public Integer getEndY() {
        return endY;
    }

    public void setEndY(Integer endY) {
        this.endY = endY;
    }

    public Integer getEndZ() {
        return endZ;
    }

    public void setEndZ(Integer endZ) {
        this.endZ = endZ;
    }
}
