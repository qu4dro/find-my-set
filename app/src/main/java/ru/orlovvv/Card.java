package ru.orlovvv;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Card {

    int count, fill, shape, color;
    int x, y, width, height;


    public Card(int fill, int count, int shape, int color) {
        this.fill = fill;
        this.count = count;
        this.shape = shape;
        this.color = color;
    }

    public void setDrawableValues(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public String toString() {
        return "fill: " + fill + " " + "count: " + count + " " + "shape: " + shape + " " + ", color: " + color + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return fill == card.fill &&
                count == card.count &&
                shape == card.shape &&
                color == card.color;
    }


    public static Card getThird(final Card firstCard, final Card secondCard) {
        return new Card(getThirdValue(firstCard.fill, secondCard.fill),
                getThirdValue(firstCard.count, secondCard.count),
                getThirdValue(firstCard.shape, secondCard.shape),
                getThirdValue(firstCard.color, secondCard.color)
        );
    }


    public boolean isSelected(float touchX, float touchY) {
        if (touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height) {
            return true;
        } else return false;
    }

    private static int getThirdValue(final int firstValue, final int secondValue) {
        return firstValue == secondValue ? firstValue : 6 - (firstValue + secondValue);
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
