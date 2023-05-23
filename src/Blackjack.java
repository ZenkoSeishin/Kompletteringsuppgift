import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {
    private Deck deck;
    private Dealer dealer;
    private Player player;

    public Blackjack() {
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
    }

    public void playGame(boolean showOpponentCards) {
        deck.shuffle();

        // Deal initial cards
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        // Show initial hands
        player.showHand(false);
        if (showOpponentCards) {
            dealer.showHand(true);
        } else {
            System.out.println("\nDealer's hand:");
            System.out.println("Card 1: [Hidden]");
            System.out.println("Card 2: " + dealer.hand.get(1));
        }

        // Player's turn
        while (player.getHandValue() < 21) {
            System.out.println("\n1. Hit");
            System.out.println("2. Stand");
            System.out.print("Choose an option: ");
            Scanner scanner = new Scanner(System.in);

            try {
                int choice = scanner.nextInt();

                if (choice == 1) {
                    Card card = deck.drawCard();
                    if (card != null) {
                        player.addCard(card);
                        player.showHand(false);
                    } else {
                        System.out.println("Deck is empty!");
                        break;
                    }
                } else if (choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        // Dealer's turn
        dealer.play(deck, showOpponentCards);

        // Determine the winner
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        System.out.println("\nPlayer's hand value: " + playerValue);
        System.out.println("Dealer's hand value: " + dealerValue);

        if (playerValue > 21) {
            System.out.println("Player busts! Dealer wins.");
        } else if (dealerValue > 21) {
            System.out.println("Dealer busts! Player wins.");
        } else if (playerValue > dealerValue) {
            System.out.println("Player wins.");
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie.");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a version:");
        System.out.println("1. Normal");
        System.out.println("2. Show opponent's cards");
        System.out.print("Enter your choice: ");
        scanner = new Scanner(System.in);
        int version;
        boolean showOpponentCards = false;

        while (true) {
            try {
                System.out.println("Please enter the version (1 or 2):");
                version = scanner.nextInt();

                if (version == 2) {
                    showOpponentCards = true;
                } else if (version != 1) {
                    System.out.println("Invalid input. Please enter either 1 or 2.");
                    continue;
                }

                break; // Break out of the loop if a valid input is provided

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        Blackjack blackjack = new Blackjack();
        blackjack.playGame(showOpponentCards);
    }
}