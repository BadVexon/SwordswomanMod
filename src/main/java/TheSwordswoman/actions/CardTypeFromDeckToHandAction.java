package TheSwordswoman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardTypeFromDeckToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private CardType t;

    public CardTypeFromDeckToHandAction(int amount, CardType t) {
        this.p = AbstractDungeon.player;// 20
        this.setValues(this.p, AbstractDungeon.player, amount);// 21
        this.actionType = ActionType.CARD_MANIPULATION;// 22
        this.duration = Settings.ACTION_DUR_MED;// 23
        this.t = t;
    }// 24

    public void update() {
        AbstractCard card;
        if (this.duration == Settings.ACTION_DUR_MED) {// 28
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);// 29

            for (AbstractCard c : this.p.drawPile.group) {
                if (c.type == t) {// 31
                    tmp.addToRandomSpot(c);// 32
                }
            }

            if (tmp.size() == 0) {// 36
                this.isDone = true;// 37
            } else if (tmp.size() == 1) {// 39
                card = tmp.getTopCard();// 40
                if (this.p.hand.size() == 10) {// 42
                    this.p.drawPile.moveToDiscardPile(card);// 43
                    this.p.createHandIsFullDialog();// 44
                } else {
                    card.unhover();// 46
                    card.lighten(true);// 47
                    card.setAngle(0.0F);// 48
                    card.drawScale = 0.12F;// 49
                    card.targetDrawScale = 0.75F;// 50
                    card.current_x = CardGroup.DRAW_PILE_X;// 51
                    card.current_y = CardGroup.DRAW_PILE_Y;// 52
                    this.p.drawPile.removeCard(card);// 53
                    AbstractDungeon.player.hand.addToTop(card);// 54
                    AbstractDungeon.player.hand.refreshHandLayout();// 55
                    AbstractDungeon.player.hand.applyPowers();// 56
                }

                this.isDone = true;// 58
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, "Choose a card to put into your hand.", false);// 62
                this.tickDuration();// 63
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {// 69

                for (AbstractCard abstractCard : AbstractDungeon.gridSelectScreen.selectedCards) {
                    card = abstractCard;
                    card.unhover();// 71
                    if (this.p.hand.size() == 10) {// 73
                        this.p.drawPile.moveToDiscardPile(card);// 74
                        this.p.createHandIsFullDialog();// 75
                    } else {
                        this.p.drawPile.removeCard(card);// 77
                        this.p.hand.addToTop(card);// 78
                    }

                    this.p.hand.refreshHandLayout();// 80
                    this.p.hand.applyPowers();// 81
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();// 83
                this.p.hand.refreshHandLayout();// 84
            }

            this.tickDuration();// 87
        }
    }// 38 59 64 88
}
