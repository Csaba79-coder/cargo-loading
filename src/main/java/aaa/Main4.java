package aaa;

import java.util.ArrayList;
import java.util.List;

public class Main4 {

    // Raktár méretei
    static int warehouseWidth = 50;
    static int warehouseLength = 100;
    static int warehouseHeight = 200;

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
            int endX = startX + width;  // nem vonunk le 1-et
            int endY = startY + length;
            int endZ = startZ + height;
            return String.format("Ends at (%d, %d, %d)", endX, endY, endZ);
        }
    }

    public static void main(String[] args) {
        // Lista a dobozok tárolásához
        List<Box> boxes = new ArrayList<>();

        // Dobozok méretei: (szélesség, hosszúság, magasság)
        boxes.add(new Box(10, 10, 30));  // Doboz 1
        boxes.add(new Box(20, 20, 20));  // Doboz 2
        boxes.add(new Box(15, 15, 40));  // Doboz 3

        // Kezdő pozíció
        int lastX = 0, lastY = 0, lastZ = 0;

        // Dobozok elhelyezése
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);

            // Kiírjuk a kezdő és végpontot egy sorban
            System.out.println("Box #" + (i + 1) + " -> " + box.getStartPoint(lastX, lastY, lastZ) + " " + box.getEndPoint(lastX, lastY, lastZ));

            // Frissítjük az utolsó pozíciót
            // A következő doboz koordinátáit az előző doboz végpontja alapján frissítjük

            lastX += box.width;  // Szélesség növelése
            if (lastX >= warehouseWidth) {  // Ha a szélesség meghaladja a raktár szélességét
                lastX = 0; // Visszaállítjuk a szélességet
                lastY += box.length;  // Hosszúság növelése
                if (lastY >= warehouseLength) {  // Ha a hosszúság meghaladja a raktár hosszúságát
                    lastY = 0; // Visszaállítjuk a hosszúságot
                    lastZ += box.height;  // Magasság növelése
                }
            }
        }
    }
}
