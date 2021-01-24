package TheSwordswoman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlashRock extends AbstractBlazCard {

    public final static String ID = makeID("FlashRock");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 12;

    public FlashRock() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new SelectCardsAction(p.hand.group, 1, "Discard 1 card to draw 1 card.", false, c -> !c.cardID.equals(this.cardID), list -> list.forEach(c ->
                {
                    addToTop(new DrawCardAction(p, 1));
                    addToTop(new DiscardSpecificCardAction(c));
                }
        )));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}