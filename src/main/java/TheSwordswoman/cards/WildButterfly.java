package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.DoublePlayPower;

public class WildButterfly extends AbstractBlazCard {

    public final static String ID = makeID("WildButterfly");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public WildButterfly() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DoublePlayPower(1));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}