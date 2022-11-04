package model;

public class Podcast extends Audio{

    private String description;
    private Category category;

    public Podcast(String url, String name, int duration, int categoryOption, String description) {
        
        super(url, name, duration);
        this.description = description;

        setCategory(categoryOption);
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

    public void setCategory(int categoryOption) {
        switch (categoryOption) {
            
            case 1:
                category = Category.POLITICS;
                break;

            case 2:
                category = Category.ENTERTAINMENT;
                break;

            case 3:
                category = Category.VIDEOGAMES;
                break;
            
            case 4:
                category = Category.FAHSION;
                break;
        }
    }

    public String toString() {
        String message = "";

        message += super.toString() + "Categoria: " + category;

        return message;
    }

}
