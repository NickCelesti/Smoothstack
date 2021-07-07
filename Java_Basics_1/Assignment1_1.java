// Nick Celesti
package Java_Basics_1;

class Assignment1_1 {
    public static void main(String[] args) {
        // -----------------------------------
        System.out.println("1)");
        String stars = "****";
        String spaces = "";
        for (int i = 1; i <= 4; i++) {
            System.out.println(stars.substring(0, i)); // substring does not alter the string itself
        }
        // -----------------------------------
        System.out.println(".........\n2)\n..........");

        stars = "****";
        spaces = "";

        for (int i = 0; i < 4; i++) {
            System.out.println(stars.substring(i));
        }

        // -----------------------------------
        System.out.println("3)");

        spaces = "     ";
        stars = "*";

        for (int i = 0; i < 4; i++) {
            System.out.println(spaces.substring(i) + stars);
            stars += "**"; // concatenate 2 stars
        }
        // -----------------------------------
        System.out.println("...........\n4)\n............");

        stars = "*********";
        spaces = "     ";

        for (int i = 2; i < 6; i++) {
            stars = stars.substring(2); // remove 2 stars
            System.out.println(spaces.substring(0, i) + stars);
        }

    }
}