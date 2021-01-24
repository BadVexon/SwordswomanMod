package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EchoPower;

public class MirrorImage extends AbstractBlazCard {

    public final static String ID = makeID("MirrorImage");

    //stupid intellij stuff POWER, SELF, RARE

    public MirrorImage() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EchoPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}