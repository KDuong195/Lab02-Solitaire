```java
/*******************************************************************************
 * Solitaire
 * 
 * Copyright (C) 2025 by Martin P. Robillard
 * 
 * See: https://github.com/prmr/Solitaire
 *******************************************************************************/
package ca.mcgill.solitaire.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of 52 cards in a predetermined order
 * that allows the Solitaire game to be won.
 */
public class Deck {
	private CardStack aCards;

	/**
	 * Creates a new deck with a predetermined winnable order.
	 */
	public Deck() {
		shuffle();
	}

	/**
	 * Reinitializes the deck with a predetermined winnable order.
	 *
	 * The order below is specified in DRAW order: the first card
	 * in the list is the first card that will be drawn.
	 *
	 * CardStack stores cards from bottom to top and draw() removes
	 * the last card, so the list is reversed before constructing
	 * the CardStack.
	 */
	public void shuffle() {
		List<Card> cards = new ArrayList<>();

		/*
		 * Tableau configuration.
		 *
		 * The first 28 cards are dealt into piles of sizes:
		 * 1, 2, 3, 4, 5, 6, 7.
		 *
		 * The configuration is arranged so that the cards can
		 * subsequently be moved to the foundations in order.
		 */

		// Tableau pile 1: 5D
		cards.add(Card.get(Rank.FIVE, Suit.DIAMONDS));

		// Tableau pile 2: 6H, 5H
		cards.add(Card.get(Rank.SIX, Suit.HEARTS));
		cards.add(Card.get(Rank.FIVE, Suit.HEARTS));

		// Tableau pile 3: 7S, 6S, 5S
		cards.add(Card.get(Rank.SEVEN, Suit.SPADES));
		cards.add(Card.get(Rank.SIX, Suit.SPADES));
		cards.add(Card.get(Rank.FIVE, Suit.SPADES));

		// Tableau pile 4: JS, 10S, 9S, 8S
		cards.add(Card.get(Rank.JACK, Suit.SPADES));
		cards.add(Card.get(Rank.TEN, Suit.SPADES));
		cards.add(Card.get(Rank.NINE, Suit.SPADES));
		cards.add(Card.get(Rank.EIGHT, Suit.SPADES));

		// Tableau pile 5: JH, 10H, 9H, 8H, 7H
		cards.add(Card.get(Rank.JACK, Suit.HEARTS));
		cards.add(Card.get(Rank.TEN, Suit.HEARTS));
		cards.add(Card.get(Rank.NINE, Suit.HEARTS));
		cards.add(Card.get(Rank.EIGHT, Suit.HEARTS));
		cards.add(Card.get(Rank.SEVEN, Suit.HEARTS));

		// Tableau pile 6: JD, 10D, 9D, 8D, 7D, 6D
		cards.add(Card.get(Rank.JACK, Suit.DIAMONDS));
		cards.add(Card.get(Rank.TEN, Suit.DIAMONDS));
		cards.add(Card.get(Rank.NINE, Suit.DIAMONDS));
		cards.add(Card.get(Rank.EIGHT, Suit.DIAMONDS));
		cards.add(Card.get(Rank.SEVEN, Suit.DIAMONDS));
		cards.add(Card.get(Rank.SIX, Suit.DIAMONDS));

		// Tableau pile 7: JC, 10C, 9C, 8C, 7C, 6C, 5C
		cards.add(Card.get(Rank.JACK, Suit.CLUBS));
		cards.add(Card.get(Rank.TEN, Suit.CLUBS));
		cards.add(Card.get(Rank.NINE, Suit.CLUBS));
		cards.add(Card.get(Rank.EIGHT, Suit.CLUBS));
		cards.add(Card.get(Rank.SEVEN, Suit.CLUBS));
		cards.add(Card.get(Rank.SIX, Suit.CLUBS));
		cards.add(Card.get(Rank.FIVE, Suit.CLUBS));

		/*
		 * Stock configuration.
		 *
		 * First expose A-2-3-4 for every suit.
		 * Once these cards reach the foundations, the 5-11 cards
		 * in the tableau can be moved to their foundations.
		 *
		 * Finally expose Q and K for every suit.
		 */
		for (Rank rank : new Rank[] {
				Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR }) {
			for (Suit suit : Suit.values()) {
				cards.add(Card.get(rank, suit));
			}
		}

		for (Rank rank : new Rank[] {
				Rank.QUEEN, Rank.KING }) {
			for (Suit suit : Suit.values()) {
				cards.add(Card.get(rank, suit));
			}
		}

		/*
		 * CardStack stores cards bottom -> top, while draw()
		 * removes the top card. Therefore reverse the draw order.
		 */
		Collections.reverse(cards);
		aCards = new CardStack(cards);
	}

	/**
	 * Places pCard on top of the deck.
	 * 
	 * @param pCard The card to place on top of the deck.
	 * @pre pCard != null
	 */
	public void push(Card pCard) {
		assert pCard != null;
		aCards.push(pCard);
	}

	/**
	 * Draws a card from the deck and removes it from the deck.
	 * 
	 * @return The card drawn.
	 * @pre !isEmpty()
	 */
	public Card draw() {
		assert !isEmpty();
		return aCards.pop();
	}

	/**
	 * @return True iff there are no cards in the deck.
	 */
	public boolean isEmpty() {
		return aCards.isEmpty();
	}
}