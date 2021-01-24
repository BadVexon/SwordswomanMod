package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlyFlyAgain extends AbstractBlazCard {

    public final static String ID = makeID("FlyFlyAgain");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public FlyFlyAgain() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}