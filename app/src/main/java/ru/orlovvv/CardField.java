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

        int cardWidth = (width - 100) / 3;
        int cardHeight = cardWidth;

        for (Card c : cards) {
            c.setDrawableValues(x, y, cardWidth, cardHeight);

            x += cardWidth + 20;

            if ((x + cardWidth) > width) {
                x = 20;
                y += cardHeight + 20;

            }
        }

    }

    public boolean findSet() {
        selectedCards.clear();

        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                Card card = Card.getThird(cards.get(i), cards.get(j));


                if (cards.contains(card)) {
                    selectedCards.add(cards.get(i));
                    selectedCards.add(card);
                    selectedCards.add(cards.get(j));

                    return true;
                }
            }

        }
        return false;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public ArrayList<Card> getSelectedCards() {
        return selectedCards;
    }


}
