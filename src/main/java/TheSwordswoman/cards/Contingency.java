package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.ContingencyPower;

public class Contingency extends AbstractBlazCard {

    public final static String ID = makeID("Contingency");

    //stupid intellij stuff POWER, SELF, 

    public Contingency() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ContingencyPower(1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}