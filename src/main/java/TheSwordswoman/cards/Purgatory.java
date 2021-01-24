package TheSwordswoman.cards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class Purgatory extends AbstractBlazCard {

    public final static String ID = makeID("Purgatory");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Purgatory() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 1;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsAction(p.hand.group, "Choose any number of cards to discard and gain Energy and Block.", true, c -> !c.cardID.equals(this.cardID), list -> list.forEach(c ->
                {
                    addToTop(new GainEnergyAction(1));
                    addToTop(new GainBlockAction(p, block));
                    addToTop(new DiscardSpecificCardAction(c));
                }
        )));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}