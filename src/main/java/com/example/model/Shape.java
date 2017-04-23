package com.example.model;

public class Shape {
    public enum Type {
        circle,
        rectangle
    }

    private Type type;
    private Integer radius;
    private Integer width;
    private Integer height;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isCircle() {
        return type == Type.circle && radius != null;
    }

    public boolean isRectangle() {
        return type == Type.rectangle && width != null && height != null;
    }
}
