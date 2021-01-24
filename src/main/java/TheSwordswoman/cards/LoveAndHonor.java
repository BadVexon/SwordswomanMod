package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.LoveAndHonorPower;

public class LoveAndHonor extends AbstractBlazCard {

    public final static String ID = makeID("LoveAndHonor");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public LoveAndHonor() {
        super(ID, 3, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LoveAndHonorPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}