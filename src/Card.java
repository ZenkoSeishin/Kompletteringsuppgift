
// Card class represents a playing card
class Card extends Deck{
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        if (value.equals("Ace")) {
            return 1;
        } else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")) {
            return 10;
        } else {
            return Integer.parseInt(value);
        }
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}