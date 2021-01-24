package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.ButterflyFormPower;

public class ButterflyForm extends AbstractBlazCard {

    public final static String ID = makeID("ButterflyForm");

    //stupid intellij stuff POWER, SELF, RARE

    public ButterflyForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ButterflyFormPower());
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}