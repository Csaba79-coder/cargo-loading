package aaa;

import java.util.ArrayList;
import java.util.List;

public class Warehouse6 {

    // Raktár méretei
    static int warehouseWidth = 50;
    static int warehouseLength = 100;
    static int warehouseHeight = 200;

    // Dobozok
    static class Box {
        int id;
        int width, length, height;
        int weight;  // A doboz súlya
        Integer startX, startY, startZ;
        Integer endX, endY, endZ;

        Box(int id, int width, int length, int height, int weight) {
            this.id = id;
            this.width = width;
            this.length = length;
            this.height = height;
            this.weight = weight;  // A súly beállítása
        }

        // Kiszámoljuk a doboz térfogatát a méretek alapján
        int volume() {
            return width * length * height;
        }

        public int getId() {
            return id;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>(); // Nem elférő dobozok

        // Dobozok létrehozása egyedi ID-val és súlyokkal
        int boxId = 1;
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(boxId++, 10, 10, 30, 30));  // 10x10x30, súly: 30
            boxes.add(new Box(boxId++, 15, 15, 40, 50));  // 15x15x40, súly: 50
        }
        for (int i = 0; i < 5; i++) {
            boxes.add(new Box(boxId++, 20, 20, 50, 70));  // 20x20x50, súly: 70
            boxes.add(new Box(boxId++, 20, 20, 50, 40));  // 20x20x50, súly: 40
        }

        // Hozzáadunk egy 5x5x5-ös dobozt a lista végére
        boxes.add(new Box(boxId++, 5, 5, 5, 10));  // 5x5x5, súly: 10

        // Dobozok rendezése súly szerint, a legnehezebb dobozok kerüljenek előre
        boxes.sort((b1, b2) -> Integer.compare(b2.weight, b1.weight));

        // Raktár bepakolás ciklus
        int x = 0, y = 0, z = 0;
        List<String> output = new ArrayList<>();

        // Raktár bepakolás ciklus
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);

            // Ellenőrizzük, hogy elfér-e a raktárban
            if (x + box.width <= warehouseWidth && y + box.length <= warehouseLength && z + box.height <= warehouseHeight) {
                // Doboz elhelyezése
                box.startX = x;
                box.startY = y;
                box.startZ = z;
                box.endX = x + box.width;
                box.endY = y + box.length;
                box.endZ = z + box.height;

                output.add("Box #" + box.getId() + "weight: " + box.getWeight() + " -> Starts at (" + box.startX + ", " + box.startY + ", " + box.startZ + ") Ends at (" + box.endX + ", " + box.endY + ", " + box.endZ + ")");

                // A következő doboz koordinátáit frissítjük
                z = box.endZ;

                // Ha elérjük a raktár magassági határát, akkor megnézzük, hogy van-e hely a következő szintre
                if (z >= warehouseHeight) {
                    z = 0;  // Ha a Z tengely megtelt, akkor új szintre megyünk
                    y += box.length;  // Továbbmegyünk a következő sorra
                    if (y >= warehouseLength) {
                        y = 0;  // Ha a hosszúság is megtelt, akkor visszatérünk az elejére
                        x += box.width;  // Továbbmegyünk a következő oszlopra
                    }
                }
            } else {
                // Ha nem fér be, akkor nem adunk koordinátát és hozzáadjuk a nem elférő dobozokat
                cannotFitBoxes.add("Box #" + box.getId());
            }
        }

        // Kiíratás a befért dobozok koordinátáiról
        for (String line : output) {
            System.out.println(line);
        }

        // Kiírjuk a nem elférő dobozokat
        if (!cannotFitBoxes.isEmpty()) {
            System.out.println("A következő dobozok nem fértek bele a tárolóba:");
            for (String box : cannotFitBoxes) {
                System.out.println(box);
            }
        }
    }
}
