package totchi.apps.a.imall.HomePageModule;

/**
 * Created by 3zoOz on 01/01/2018.
 */

public class Category {
    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private double price;
    private int image;

    public Category(int id, String title, int image) {
        this.id = id;
        this.title = title;

        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}