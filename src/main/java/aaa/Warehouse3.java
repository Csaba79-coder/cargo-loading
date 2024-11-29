package aaa;

import java.util.ArrayList;
import java.util.List;

public class Warehouse3 {

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

        // Kiszámoljuk a doboz térfogatát a méretek alapján
        int volume() {
            return width * length * height;
        }
    }

    /*
    Sorok: 3 sor van (X=0, 20, 40)
    Oszlopok: 5 oszlop van (Y=0, 20, 40, 55)
    Magasság: 5 szint van (Z=0, 50, 100, 150, 200)
        Z=0, 50, 100, 150, 200 (első szint)
        Z=50, 100, 150, 200 (második szint)
        Z=100, 140, 180 (harmadik szint)
     */
    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>(); // Ide fogjuk tárolni azokat a dobozokat, amik nem fértek el

        // Hozzuk létre a 10-10-10 darabot mindegyik doboztípusból
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(10, 10, 30)); // 10x10x30
            boxes.add(new Box(15, 15, 40)); // 15x15x40
            boxes.add(new Box(20, 20, 50)); // 20x20x50
        }

        // Dobozok rendezése a térfogatuk szerint, a legnagyobból a legkisebb felé
        boxes.sort((b1, b2) -> Integer.compare(b2.volume(), b1.volume()));

        // Raktárba történő bepakolás
        int x = 0, y = 0, z = 0;
        List<String> output = new ArrayList<>();
        boolean canFit = true;

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

                output.add("Box #" + (i + 1) + " -> Starts at (" + box.startX + ", " + box.startY + ", " + box.startZ + ") Ends at (" + box.endX + ", " + box.endY + ", " + box.endZ + ")");

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
                // Ha a doboz nem fér el, hozzáadjuk a nem befért dobozok listájához
                cannotFitBoxes.add("Box #" + (i + 1) + " -> Starts at (0, 0, 0) Ends at (" + box.width + ", " + box.length + ", " + box.height + ")");
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
