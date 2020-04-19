package ru.orlovvv;

public class Card {

    int fill;

    int count;

    int shape;

    int color;



    public Card(final int fill, final int count, final int shape, final int color) {
        this.fill = fill;
        this.count = count;
        this.shape = shape;
        this.color = color;
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

    private static int getThirdValue(final int firstValue, final int secondValue) {
        return firstValue == secondValue ? firstValue : 6 - (firstValue + secondValue);
    }
}
