package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {

    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new HashMap<>();
            for (int i = 0; i < players; i++) {
                dealtCards.put("Player " + (i + 1), new ArrayList<>());
            }
            dealtCards.put("Community", new ArrayList<>());
            dealtCards.put("Remaining", new ArrayList<>());

            // Раздаем по 2 карты каждому игроку
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < players; j++) {
                    dealtCards.get("Player " + (j + 1)).add(deck.dealCard());
                }
            }

            // Раздаем 5 карт в "Community"
            for (int i = 0; i < 5; i++) {
                dealtCards.get("Community").add(deck.dealCard());
            }

            // Оставшиеся карты в "Remaining"
            dealtCards.get("Remaining").addAll(deck.restCards());

            return dealtCards;
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new HashMap<>();
            for (int i = 0; i < players; i++) {
                dealtCards.put("Player " + (i + 1), new ArrayList<>());
            }
            dealtCards.put("Remaining", new ArrayList<>());

            // Раздаем по 5 карт каждому игроку
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < players; j++) {
                    dealtCards.get("Player " + (j + 1)).add(deck.dealCard());
                }
            }

            // Оставшиеся карты в "Remaining"
            dealtCards.get("Remaining").addAll(deck.restCards());

            return dealtCards;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new HashMap<>();
            for (int i = 0; i < players; i++) {
                dealtCards.put("Player " + (i + 1), new ArrayList<>());
            }

            // Раздаем по 13 карт каждому игроку
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < players; j++) {
                    dealtCards.get("Player " + (j + 1)).add(deck.dealCard());
                }
            }

            return dealtCards;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new HashMap<>();
            for (int i = 0; i < players; i++) {
                dealtCards.put("Player " + (i + 1), new ArrayList<>());
            }
            dealtCards.put("Trump card", new ArrayList<>());
            dealtCards.put("Remaining", new ArrayList<>());

            // Раздаем по 6 карт каждому игроку
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < players; j++) {
                    dealtCards.get("Player " + (j + 1)).add(deck.dealCard());
                }
            }

            // Одна карта в стэк "Trump card"
            dealtCards.get("Trump card").add(deck.dealCard());

            // Оставшиеся карты в "Remaining"
            dealtCards.get("Remaining").addAll(deck.restCards());

            return dealtCards;
        };
    }
}
