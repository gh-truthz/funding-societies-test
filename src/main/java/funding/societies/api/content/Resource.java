package funding.societies.api.content;

public class Resource {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

    public Integer getId() {
        return id;
    }

    public Resource setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public Resource setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Resource setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPantone_value() {
        return pantone_value;
    }

    public Resource setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
        return this;
    }
}
