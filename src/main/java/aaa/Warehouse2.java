package aaa;

import java.util.ArrayList;
import java.util.List;

public class Warehouse2 {

    // Raktár méretei
    static int warehouseWidth = 50;
    static int warehouseLength = 100;
    static int warehouseHeight = 200;

    // Dobozok
    static class Box {
        int width, length, height;
        int startX, startY, startZ;
        int endX, endY, endZ;

        Box(int width, int length, int height) {
            this.width = width;
            this.length = length;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>(); // Ide fogjuk tárolni azokat a dobozokat, amik nem fértek el

        // Hozzuk létre a 10-10-10 darabot mindegyik doboztípusból
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(10, 10, 30)); // 10x10x30
            boxes.add(new Box(15, 15, 40)); // 15x15x40
            boxes.add(new Box(20, 20, 50)); // 20x20x50
        }

        // Raktárba történő bepakolás
        int x = 0, y = 0, z = 0;
        List<String> output = new ArrayList<>();
        boolean canFit = true;

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

                output.add("Box #" + (i + 1) + " -> Starts at (" + box.startX + ", " + box.startY + ", " + box.startZ + ") Ends at (" + box.endX + ", " + box.endY + ", " + box.endZ + ")");

                // Frissítjük a következő elhelyezés koordinátáit (most a Z-t emeljük, mivel ezt is figyelembe kell venni)
                z = box.endZ;

                // Ha a Z (magasság) meghaladja a raktár magasságát, akkor több doboz nem fér el
                if (z >= warehouseHeight) {
                    canFit = false;
                    break;
                }
            } else {
                // Ha a doboz nem fér el, hozzáadjuk a nem befért dobozok listájához
                cannotFitBoxes.add("Box #" + (i + 1) + " -> Starts at (0, 0, 0) Ends at (" + box.width + ", " + box.length + ", " + box.height + ")");
            }

            // Ellenőrizzük, hogy a dobozok közötti hely kitöltése szükséges-e
            if (z + box.height > warehouseHeight && y + box.length < warehouseLength) {
                z = 0; // Visszaállítjuk Z-t, ha megtelt a magasság
                y += box.length; // Továbbmegyünk a következő sorra
                if (y >= warehouseLength) {
                    y = 0;
                    x += box.width; // Ha már minden helyet kihasználtunk a hosszon is
                }
            }
        }

        // Kimenet
        if (canFit) {
            output.add("Minden doboz sikeresen elfért a tárolóban!");
        } else {
            output.add("Nem minden doboz fért el a tárolóban!");
        }

        // Kiíratás
        for (String line : output) {
            System.out.println(line);
        }

        // Kiírjuk a nem befért dobozokat
        if (!cannotFitBoxes.isEmpty()) {
            System.out.println("A következő dobozok nem fértek bele a tárolóba:");
            for (String box : cannotFitBoxes) {
                System.out.println(box);
            }
        }
    }
}
