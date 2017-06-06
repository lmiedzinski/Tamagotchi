package pl.dbjllmjk;

import org.junit.Assert;
import org.junit.Test;
import pl.dbjllmjk.Model.PetEntry;

public class PetEntryTest {

    @Test
    public void petEntryTest1() {
        PetEntry p = new PetEntry("title", "path");
        Assert.assertEquals("title", p.getTitle());
    }

    @Test
    public void petEntryTest2() {
        PetEntry p = new PetEntry("title", "path");
        Assert.assertNotNull(p.getImage());
    }

    @Test
    public void petEntryTest3() {
        PetEntry p = new PetEntry("title", "path");
        p.getImage();
        Assert.assertNotNull(p.getImage());
    }

    @Test
    public void petEntryTest4() {
        PetEntry p = new PetEntry("title", "path");
        Assert.assertEquals("title", p.toString());
    }
}
