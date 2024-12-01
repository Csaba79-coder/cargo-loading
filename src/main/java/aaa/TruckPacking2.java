package aaa;

import java.util.ArrayList;
import java.util.List;

public class TruckPacking2 {

    // Teherautó méretei és teherbírása
    static int truckWidth = 50;
    static int truckLength = 100;
    static int truckHeight = 200;
    static int maxWeight = 1000; // Maximális teherbírás

    static class Box {
        int id;
        int width, length, height;
        int weight;
        Integer startX, startY, startZ;
        Integer endX, endY, endZ;

        Box(int id, int width, int length, int height, int weight) {
            this.id = id;
            this.width = width;
            this.length = length;
            this.height = height;
            this.weight = weight;
        }

        int volume() {
            return width * length * height;
        }
    }

    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>();
        int currentWeight = 0; // Az aktuális teherbírás

        // Dobozok létrehozása
        int boxId = 1;
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(boxId++, 10, 10, 30, 30));  // 30 kg
            boxes.add(new Box(boxId++, 15, 15, 40, 50));  // 50 kg
        }

        // Súly szerint csökkenő sorrendbe rendezés
        boxes.sort((b1, b2) -> Integer.compare(b2.weight, b1.weight));

        int x = 0, y = 0, z = 0;
        int layer = 1; // Réteg számláló
        List<String> output = new ArrayList<>();

        for (Box box : boxes) {
            // Ellenőrizzük, hogy elfér-e és a súlykorlát nem léphető túl
            if (x + box.width <= truckWidth && y + box.length <= truckLength && z + box.height <= truckHeight
                    && currentWeight + box.weight <= maxWeight) {

                // Doboz elhelyezése
                box.startX = x;
                box.startY = y;
                box.startZ = z;
                box.endX = x + box.width;
                box.endY = y + box.length;
                box.endZ = z + box.height;

                // Kimenet kiegészítése a réteginformációval
                output.add("Box #" + box.id + " weight: " + box.weight
                        + " starts at (" + box.startX + ", " + box.startY + ", " + box.startZ
                        + ") and ends at (" + box.endX + ", " + box.endY + ", " + box.endZ
                        + ") in Truck layer: " + layer);

                currentWeight += box.weight;

                // Következő doboz pozíciójának számítása
                y += box.length;

                if (y >= truckLength) {
                    y = 0;
                    x += box.width;

                    if (x >= truckWidth) {
                        x = 0;
                        z += box.height;
                        layer++; // Rétegszám növelése, ha egy új réteg indul
                    }
                }
            } else {
                cannotFitBoxes.add("Box #" + box.id);
            }
        }

        // Kimenet
        System.out.println("Successfully packed boxes:");
        for (String line : output) {
            System.out.println(line);
        }

        System.out.println("Cannot fit boxes:");
        for (String box : cannotFitBoxes) {
            System.out.println(box);
        }

        System.out.println("Total weight of packed boxes: " + currentWeight + " kg");
    }
}
