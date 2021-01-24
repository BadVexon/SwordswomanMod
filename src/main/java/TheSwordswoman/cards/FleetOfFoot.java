package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FleetOfFoot extends AbstractBlazCard {

    public final static String ID = makeID("FleetOfFoot");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public FleetOfFoot() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(p, p, 4, false));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}