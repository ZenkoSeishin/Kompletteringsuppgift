import java.util.ArrayList;
import java.util.List;

// Player class represents a player in the game
class Player extends Deck {
    protected List<Card> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            value += card.getValue();
            if (card.getValue() == 1) {
                numAces++;
            }
        }

        while (value <= 11 && numAces > 0) {
            value += 10;
            numAces--;
        }

        return value;
    }

    public void showHand(boolean showAllCards) {
        System.out.println("Player's hand:");
        for (Card card : hand) {
            System.out.println(card);
        }
        if (!showAllCards) {
            System.out.println("Total value: " + getHandValue());
        }
    }
}
