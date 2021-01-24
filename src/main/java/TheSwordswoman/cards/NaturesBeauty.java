package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.NaturesBeautyPower;

public class NaturesBeauty extends AbstractBlazCard {

    public final static String ID = makeID("NaturesBeauty");

    //stupid intellij stuff RARE, SELF, POWER

    public NaturesBeauty() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NaturesBeautyPower(1));
    }

    public void upp() {
        isEthereal = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}