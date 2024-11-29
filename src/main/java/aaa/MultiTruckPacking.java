package aaa;

import java.util.ArrayList;
import java.util.List;

public class MultiTruckPacking {

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

    static class Truck {
        int id;
        int currentWeight = 0;
        int x = 0, y = 0, z = 0;
        List<Box> packedBoxes = new ArrayList<>();

        Truck(int id) {
            this.id = id;
        }

        boolean canFit(Box box) {
            return x + box.width <= truckWidth && y + box.length <= truckLength &&
                    z + box.height <= truckHeight && currentWeight + box.weight <= maxWeight;
        }

        void packBox(Box box) {
            box.startX = x;
            box.startY = y;
            box.startZ = z;
            box.endX = x + box.width;
            box.endY = y + box.length;
            box.endZ = z + box.height;

            packedBoxes.add(box);
            currentWeight += box.weight;

            // Következő doboz pozíciójának számítása
            y += box.length;

            if (y >= truckLength) {
                y = 0;
                x += box.width;

                if (x >= truckWidth) {
                    x = 0;
                    z += box.height;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>();
        List<Truck> trucks = new ArrayList<>();

        // Különböző teherautók létrehozása
        trucks.add(new Truck(1));
        trucks.add(new Truck(2)); // Ha szükséges több teherautó, itt adhatsz hozzá újakat

        // Dobozok létrehozása
        int boxId = 1;
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(boxId++, 10, 10, 30, 30));  // 30 kg
            boxes.add(new Box(boxId++, 15, 15, 40, 50));  // 50 kg
        }

        // Súly szerint csökkenő sorrendbe rendezés
        boxes.sort((b1, b2) -> Integer.compare(b2.weight, b1.weight));

        // Dobozok bepakolása a teherautókba
        for (Box box : boxes) {
            boolean packed = false;
            for (Truck truck : trucks) {
                if (truck.canFit(box)) {
                    truck.packBox(box);
                    packed = true;
                    break; // Kilépünk, ha sikerült egy teherautóra pakolni
                }
            }

            if (!packed) {
                cannotFitBoxes.add("Box #" + box.id);
            }
        }

        // Kimenet
        System.out.println("Successfully packed boxes:");
        for (Truck truck : trucks) {
            System.out.println("Truck #" + truck.id);
            for (Box box : truck.packedBoxes) {
                System.out.println("Box #" + box.id + " weight: " + box.weight + " starts at (" + box.startX + ", " + box.startY + ", " + box.startZ
                        + ") and ends at (" + box.endX + ", " + box.endY + ", " + box.endZ + ")");
            }
        }

        if (cannotFitBoxes.isEmpty()) {
            System.out.println("All boxes fit in the trucks.");
        } else {
            System.out.println("Cannot fit boxes:");
            for (String box : cannotFitBoxes) {
                System.out.println(box);
            }
        }
    }
}
