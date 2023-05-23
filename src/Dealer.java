class Dealer extends Player {
    public void play(Deck deck, boolean showAllCards) {
        while (getHandValue() < 17) {
            Card card = deck.drawCard();
            if (card != null) {
                addCard(card);
            } else {
                System.out.println("Deck is empty!");
                break;
            }
        }
        System.out.println("\nDealer's hand:");
        showHand(showAllCards);
    }
}
