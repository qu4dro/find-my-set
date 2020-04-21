package ru.orlovvv;

import java.util.ArrayList;

public class CardField {
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;


    public CardField(ArrayList<Card> cards) {
        this.cards = cards;
        this.selectedCards = new ArrayList<>();
    }

    public boolean cardTouched(float x, float y) {
        for (Card c : cards) {
            if (c.isSelected(x, y)) {
                if (selectedCards.contains(c)) {
                    selectedCards.remove(c);
                } else {
                    selectedCards.add(c);
                }
                return true;
            }

        }
        return false;
    }

    public boolean isSelectedAreSet(ArrayList<Card> selectedCards) {
        if (selectedCards == null || selectedCards.size() != 3) {
            return false;
        }

        Card thirdCard = Card.getThird(selectedCards.get(0), selectedCards.get(1));
        return thirdCard.equals(selectedCards.get(2));
    }

    //TODO FIND SET

    public void setDrawCoordinates(int width, int height) {
        int x = 20;
        int y = 20;

        int card_width = (width - 100) / 3;
        int card_height = card_width;

        for (Card c : cards) {
            c.setDrawableValues(x, y, card_width, card_height);

            x += card_width + 20;

            if ((x + card_width) > width) {
                x = 20;
                y += card_height + 20;
            }
        }

    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public ArrayList<Card> getSelectedCards() {
        return selectedCards;
    }


}
