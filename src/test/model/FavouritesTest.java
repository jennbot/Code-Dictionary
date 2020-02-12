package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FavouritesTest {
    Favourites testfav;

    @BeforeEach
    void runBefore() {
        testfav = new Favourites();
    }

    @Test
    void testAddFavourite() {
        testfav.addFavourites("String");
        assertEquals(testfav.favourites(), "String\n");
    }

    @Test
    void testAddFavouriteNotInRecipe(){
        testfav.addFavourites("weee");
        testfav.addFavourites("String");
        assertEquals(testfav.favourites(), "String\n");
    }

    @Test
    void testDeleteFavourite() {
        testfav.addFavourites("String");
        testfav.addFavourites("ArrayList");
        testfav.deleteFavourites("ArrayList");
        assertEquals(testfav.favourites(), "String\n");
    }

    @Test
    void testDeleteFavouriteEmpty(){
        assertEquals(testfav.favourites(), "");
    }

}
