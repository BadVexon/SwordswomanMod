package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class StanceChange extends AbstractBlazCard {

    public final static String ID = makeID("StanceChange");

    //stupid intellij stuff SKILL, SELF, COMMON

    public StanceChange() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        swapStance();
        applyToSelf(new StrengthPower(p, 1));
        if (!upgraded)
            applyToSelf(new WeakPower(p, 1, false));
    }

    public void upp() {
        exhaust = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}