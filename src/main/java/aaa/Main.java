package aaa;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Rakodóterem méretei (szélesség, hosszúság, magasság)
        int warehouseWidth = 50;
        int warehouseLength = 100;
        int warehouseHeight = 200;

        // Doboz mérete (szélesség, hosszúság, magasság)
        int boxWidth = 10;
        int boxLength = 10;
        int boxHeight = 50;

        // Ellenőrzés: legalább 4 magas
        if (warehouseHeight / boxHeight < 4) {
            System.out.println("Nem lehet legalább 4 magasra pakolni a dobozokat!");
            return;
        }

        // Koordináták kiszámítása
        int boxesPerRow = warehouseWidth / boxWidth;
        int boxesPerColumn = warehouseLength / boxLength;
        int layers = warehouseHeight / boxHeight;

        // Összes férőhely számítása
        int totalBoxesThatFit = boxesPerRow * boxesPerColumn * layers;

        // Példa dobozszám
        int totalBoxes = 110; // Ezt szabadon állíthatod

        // Doboz elhelyezése
        List<String> placedBoxes = new ArrayList<>();
        List<Integer> remainingBoxes = new ArrayList<>();

        int currentBoxNumber = 1;
        boolean isFull = false;

        for (int z = 0; z < layers; z++) {
            for (int y = 0; y < boxesPerColumn; y++) {
                for (int x = 0; x < boxesPerRow; x++) {
                    if (currentBoxNumber > totalBoxes) {
                        isFull = true;
                        break;
                    }

                    // Koordináták meghatározása
                    int posX = x * boxWidth;
                    int posY = y * boxLength;
                    int posZ = z * boxHeight;

                    // Doboz rögzítése
                    placedBoxes.add("Box #" + currentBoxNumber + " -> (" + posX + ", " + posY + ", " + posZ + ")");
                    currentBoxNumber++;
                }
                if (isFull) break;
            }
            if (isFull) break;
        }

        // Kimaradt dobozok
        for (int i = currentBoxNumber; i <= totalBoxes; i++) {
            remainingBoxes.add(i);
        }

        // Eredmény kiírása
        System.out.println("Elhelyezett dobozok:");
        placedBoxes.forEach(System.out::println);

        System.out.println("\nKimaradt dobozok:");
        if (remainingBoxes.isEmpty()) {
            System.out.println("Nincs kimaradt doboz!");
        } else {
            remainingBoxes.forEach(boxNumber -> System.out.println("Box #" + boxNumber));
        }
    }
}

