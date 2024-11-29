package aaa;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        // Rakodóterem méretei (szélesség, hosszúság, magasság)
        int warehouseWidth = 50;
        int warehouseLength = 100;
        int warehouseHeight = 200;

        // Doboz mérete (szélesség, hosszúság, magasság)
        int boxWidth = 10;
        int boxLength = 10;
        int boxHeight = 50;

        // Összes doboz száma (több, mint ami beférhet)
        int totalBoxes = 300;

        // Számítsuk ki, hány doboz fér el a raktárban
        int boxesPerRow = warehouseWidth / boxWidth;
        int boxesPerColumn = warehouseLength / boxLength;
        int layers = warehouseHeight / boxHeight;
        int maxBoxesInWarehouse = boxesPerRow * boxesPerColumn * layers;

        // Elhelyezett és kimaradt dobozok listája
        List<String> placedBoxes = new ArrayList<>();
        List<Integer> remainingBoxes = new ArrayList<>();

        int currentBoxNumber = 1;

        // Dobozok elhelyezése
        for (int z = 0; z < layers; z++) {
            for (int y = 0; y < boxesPerColumn; y++) {
                for (int x = 0; x < boxesPerRow; x++) {
                    if (currentBoxNumber > totalBoxes) {
                        break; // Nincs több doboz
                    }

                    // Koordináták meghatározása
                    int posX = x * boxWidth;
                    int posY = y * boxLength;
                    int posZ = z * boxHeight;

                    // Doboz elhelyezése
                    placedBoxes.add("Box #" + currentBoxNumber + " -> (" + posX + ", " + posY + ", " + posZ + ")");
                    currentBoxNumber++;

                    // Ellenőrizzük, hogy tele van-e a raktár
                    if (placedBoxes.size() == maxBoxesInWarehouse) {
                        break;
                    }
                }
                if (placedBoxes.size() == maxBoxesInWarehouse) {
                    break;
                }
            }
            if (placedBoxes.size() == maxBoxesInWarehouse) {
                break;
            }
        }

        // Kimaradt dobozok listájának feltöltése
        for (int i = currentBoxNumber; i <= totalBoxes; i++) {
            remainingBoxes.add(i);
        }

        // Eredmény kiírása
        System.out.println("Elhelyezett dobozok (" + placedBoxes.size() + "/" + totalBoxes + "):");
        placedBoxes.forEach(System.out::println);

        System.out.println("\nKimaradt dobozok (" + remainingBoxes.size() + "):");
        remainingBoxes.forEach(boxNumber -> System.out.println("Box #" + boxNumber));
    }
}