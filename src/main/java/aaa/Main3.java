package aaa;

public class Main3 {

    public static void main(String[] args) {
        // Raktár méretei
        int warehouseWidth = 50;
        int warehouseLength = 100;
        int warehouseHeight = 200;

        // Doboz méretei
        int boxWidth = 10;
        int boxLength = 20;
        int boxHeight = 30;

        // Számoljuk ki, hány doboz fér el
        int boxesPerWidth = warehouseWidth / boxWidth;
        int boxesPerLength = warehouseLength / boxLength;
        int boxesPerHeight = warehouseHeight / boxHeight;

        // Összes doboz számítása
        int totalBoxes = boxesPerWidth * boxesPerLength * boxesPerHeight;

        // Kiírás a dobozok végpontjairól
        int boxId = 1;
        for (int z = 0; z < boxesPerHeight; z++) {
            for (int y = 0; y < boxesPerLength; y++) {
                for (int x = 0; x < boxesPerWidth; x++) {
                    if (boxId <= totalBoxes) {
                        int endX = (x + 1) * boxWidth;
                        int endY = (y + 1) * boxLength;
                        int endZ = (z + 1) * boxHeight;

                        System.out.println("Box #" + boxId + " -> Ends at (" + endX + ", " + endY + ", " + endZ + ")");
                        boxId++;
                    }
                }
            }
        }

        // Ha a dobozok száma kevesebb, mint a raktár kapacitása, kiírjuk, hogy mi maradt üresen
        int remainingBoxes = totalBoxes - boxId + 1;
        if (remainingBoxes > 0) {
            System.out.println("Maradt " + remainingBoxes + " üres hely a raktárban.");
        }
    }
}
