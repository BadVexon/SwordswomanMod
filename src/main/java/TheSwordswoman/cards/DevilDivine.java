package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DevilDivine extends AbstractBlazCard {

    public final static String ID = makeID("DevilDivine");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public DevilDivine() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 3;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < reverb() + 1; i++) {
            atb(new DrawCardAction(p, 1));
            atb(new DiscardAction(p, p, 1, false));
            atb(new GainEnergyAction(1));
            blck();
        }
    }

    public void upp() {
        upgradeBlock(2);
    }
}