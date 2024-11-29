package aaa;

import java.util.ArrayList;
import java.util.List;

/*
Teherautó pakolási szempontok
Súlyeloszlás:

Az optimális eloszlás érdekében a nehezebb dobozok kerüljenek a teherautó aljára és a tengelyek fölé (középső részek).
Az egyensúly érdekében ügyelj arra, hogy a dobozok súlya egyenletesen oszoljon meg az oldalakon is.
Helykihasználás:

A helyet maximalizálni kell, de figyelni kell arra, hogy minden doboz stabilan helyezkedjen el.
Hasznos lehet egy prioritási sorrend (pl. legfontosabb áruk kerüljenek könnyebben hozzáférhető helyre).
Rögzítés:

A rakomány mozgatása közben elcsúszhat, ezért fontos olyan elrendezést kialakítani, ahol a dobozok jól illeszkednek egymáshoz.

Súlyellenőrzés:

A currentWeight értékének folyamatos figyelése biztosítja, hogy ne lépd túl a teherautó maximális terhelhetőségét.
Pakolási logika:

A sorok feltöltése a y koordináta növelésével történik, majd ha egy sor megtelik, áttérsz az x koordinátára.
Kimenet:

Részletes adatok a sikeresen bepakolt és nem elférő dobozokról.

lineáris, többváltozós probléma megoldásnak heroisztikus módszerrel
 */
public class TruckPacking {

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

    /*
    Koordináták értelmezése (x, y, z):
    A teherautót háromdimenziós térként képzelhetjük el, ahol a koordináták a következőket jelölik:

    x (szélesség):

    A teherautó egyik oldalától a másikig tartó irány (pl. balról jobbra).
    Ez határozza meg, hogy a doboz "melyik sorba" kerüljenek a szélességi dimenzióban.
    y (hosszúság):

    A teherautó elejétől a hátuljáig tartó irány (pl. elölről hátrafelé).
    Ez határozza meg, hogy a doboz "melyik oszlopba" kerüljenek a hosszúsági dimenzióban.
    z (magasság):

    A teherautó aljától a tetejéig tartó irány (pl. alulról felfelé).
    Ez jelenti a megrakott magasságot: az egyes dobozok egymás tetejére pakolását.
     */
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

                output.add("Box #" + box.id + " weight: " + box.weight + " starts at (" + box.startX + ", " + box.startY + ", " + box.startZ
                        + ") and ends at (" + box.endX + ", " + box.endY + ", " + box.endZ + ")");

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
/*
Miért heurisztikus módszer?
Közelítő megoldás:

Az algoritmus nem törekszik az abszolút optimális megoldás megtalálására (pl. maximális térkihasználás vagy minimális üres hely).
Ehelyett egyszerű szabályokat használ (pl. súly szerinti rendezés, rétegek feltöltése) a probléma gyors megoldására.
Gyors és gyakorlati alkalmazás:

A heurisztikus megközelítés célja, hogy időt takarítson meg, miközben a megoldás elég jó legyen a valós életben felmerülő helyzetekre.
Példa: Egy teherautó-rakodás során fontosabb lehet az egyszerűség és a stabilitás, mint a matematikailag tökéletes térkihasználás.
Nem determinisztikus döntések:

Az algoritmus viselkedése az elején meghatározott sorrendtől (például súly szerinti rendezéstől) függ, és nem garantálja, hogy a végeredmény globálisan optimális lesz.
 */
