package aaa;

import java.util.ArrayList;
import java.util.List;

public class Main9 {

    // Raktár méretei
    static int warehouseWidth = 50;
    static int warehouseLength = 100;
    static int warehouseHeight = 100;

    // Doboz osztály
    static class Box {
        int width;
        int length;
        int height;

        Box(int width, int length, int height) {
            this.width = width;
            this.length = length;
            this.height = height;
        }

        // Doboz kezdő és végpontja
        public String getStartPoint(int startX, int startY, int startZ) {
            return String.format("Starts at (%d, %d, %d)", startX, startY, startZ);
        }

        public String getEndPoint(int startX, int startY, int startZ) {
            int endX = startX + width;
            int endY = startY + length;
            int endZ = startZ + height;
            return String.format("Ends at (%d, %d, %d)", endX, endY, endZ);
        }
    }

    public static void main(String[] args) {
        // Lista a dobozok tárolásához
        List<Box> boxes = new ArrayList<>();

        // Három féle fix méretű doboz
        Box box1 = new Box(10, 10, 30);  // 10x10x30
        Box box2 = new Box(15, 15, 40);  // 15x15x40
        Box box3 = new Box(20, 20, 50);  // 20x20x50

        // Több doboz, mint ami elfér
        for (int i = 0; i < 400; i++) {
            // Véletlenszerűen választunk egy dobozt
            if (i % 3 == 0) {
                boxes.add(box1);
            } else if (i % 3 == 1) {
                boxes.add(box2);
            } else {
                boxes.add(box3);
            }
        }

        // Kezdő pozíció
        int lastX = 0, lastY = 0, lastZ = 0;

        // Lista azoknak a dobozoknak, amik nem fértek el
        List<Box> remainingBoxes = new ArrayList<>();

        // Dobozok elhelyezése
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);

            // Ha a doboz elfér szélességben
            if (lastX + box.width <= warehouseWidth) {
                // Ha a doboz elfér hosszúságban
                if (lastY + box.length <= warehouseLength) {
                    // Ha elfér magasságban is
                    if (lastZ + box.height <= warehouseHeight) {
                        // Elhelyezzük a dobozt
                        System.out.println("Box #" + (i + 1) + " -> " + box.getStartPoint(lastX, lastY, lastZ) + " " + box.getEndPoint(lastX, lastY, lastZ));

                        // Frissítjük az utolsó pozíciót
                        lastX += box.width;  // Szélesség növelése

                    } else {
                        // Ha a magasság nem fér el, akkor marad a maradó dobozok között
                        remainingBoxes.add(box);
                    }
                } else {
                    // Ha a hosszúság nem fér el, új sort kezdünk
                    lastY = 0;
                    lastX += box.width;  // Szélesség növelése
                    if (lastX >= warehouseWidth) {
                        // Ha a szélesség elérte a tároló szélességét, akkor új sort kezdünk
                        lastX = 0;
                        lastZ += box.height;  // Magasság növelése
                    }
                }
            } else {
                // Ha a szélesség nem fér el, akkor új sort kezdünk
                lastX = 0;
                lastY += box.length;  // Hosszúság növelése
                if (lastY >= warehouseLength) {
                    // Ha a hosszúság elérte a tároló hosszúságát, új magasságot kezdünk
                    lastY = 0;
                    lastZ += box.height;  // Magasság növelése
                }
            }
        }

        // Ha nem fértek el dobozok
        if (!remainingBoxes.isEmpty()) {
            System.out.println("\nA következő dobozok nem fértek bele a tárolóba:");
            for (int i = 0; i < remainingBoxes.size(); i++) {
                Box box = remainingBoxes.get(i);
                System.out.println("Box #" + (i + 1) + " -> " + box.getStartPoint(0, 0, 0) + " " + box.getEndPoint(0, 0, 0));
            }
        } else {
            System.out.println("\nMinden doboz sikeresen elfért a tárolóban!");
        }
    }
}
