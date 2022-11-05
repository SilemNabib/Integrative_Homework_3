package model;

public class Podcast extends Audio {
    private String description;
    private Category category;

    public Podcast(String name, String url, double duration, String description, Category category) {
        super(name, url, duration);
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
