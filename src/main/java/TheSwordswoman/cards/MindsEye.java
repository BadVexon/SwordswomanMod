package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MindsEye extends AbstractBlazCard {

    public final static String ID = makeID("MindsEye");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public MindsEye() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(reverb() + 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}