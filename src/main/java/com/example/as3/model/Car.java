package com.example.as3.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {
    private static int nextId = 1;

    private int id;
    @NotBlank(message = "mark cannot be empty")
    @Size(min = 1, max = 30)
    private String mark;
    @NotBlank(message = "model cannot be empty")
    @Size(min = 1, max = 30)
    private String model;

    private Color color;

    public Car() {
    }


    public Car(@NotBlank(message = "mark cannot be empty") @Size(min = 1, max = 30) final String mark, @NotBlank(message = "model cannot be empty") @Size(min = 1, max = 30) final String model, @NotNull(message = "color cannot be null") final Color color) {
        this.id = nextId ++;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(final String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                '}';
    }
}
